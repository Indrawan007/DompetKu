package com.dompetku.app.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dompetku.app.domain.model.Budget
import com.dompetku.app.domain.model.DashboardSummary
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.usecase.transaction.GetDashboardSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

data class DashboardUiState(
    val isLoading: Boolean = true,
    val totalBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val recentTransactions: List<Transaction> = emptyList(),
    val budgetAlerts: List<Budget> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardSummaryUseCase: GetDashboardSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private val _currentCalendar = MutableStateFlow(Calendar.getInstance())
    val currentCalendar: StateFlow<Calendar> = _currentCalendar.asStateFlow()

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        val cal = _currentCalendar.value
        val month = cal.get(Calendar.MONTH) + 1
        val year = cal.get(Calendar.YEAR)

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            getDashboardSummaryUseCase(month, year)
                .catch { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message)
                    }
                }
                .collect { summary ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            totalBalance = summary.totalBalance,
                            totalIncome = summary.totalIncome,
                            totalExpense = summary.totalExpense,
                            recentTransactions = summary.recentTransactions,
                            budgetAlerts = summary.budgetAlerts
                        )
                    }
                }
        }
    }

    fun navigateMonth(direction: Int) {
        _currentCalendar.update { current ->
            (current.clone() as Calendar).apply {
                add(Calendar.MONTH, direction)
            }
        }
        loadDashboard()
    }
}
