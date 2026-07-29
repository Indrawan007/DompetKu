package com.dompetku.app.presentation.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dompetku.app.data.local.entity.CategorySummary
import com.dompetku.app.data.local.entity.MonthlyTrend
import com.dompetku.app.domain.usecase.report.GenerateReportUseCase
import com.dompetku.app.util.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

data class ReportUiState(
    val isLoading: Boolean = true,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val netBalance: Double = 0.0,
    val expenseSummary: List<CategorySummary> = emptyList(),
    val monthlyTrend: List<MonthlyTrend> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class ReportViewModel @Inject constructor(
    private val generateReportUseCase: GenerateReportUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportUiState())
    val uiState: StateFlow<ReportUiState> = _uiState.asStateFlow()

    private val _currentCalendar = MutableStateFlow(Calendar.getInstance())
    val currentCalendar: StateFlow<Calendar> = _currentCalendar.asStateFlow()

    init { loadReport() }

    fun loadReport() {
        val cal   = _currentCalendar.value
        val month = cal.get(Calendar.MONTH) + 1
        val year  = cal.get(Calendar.YEAR)
        val (startDate, endDate) = DateUtils.getMonthRange(month, year)

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            generateReportUseCase(startDate, endDate)
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { report ->
                    _uiState.update {
                        it.copy(
                            isLoading      = false,
                            totalIncome    = report.totalIncome,
                            totalExpense   = report.totalExpense,
                            netBalance     = report.netBalance,
                            expenseSummary = report.expenseCategorySummary,
                            monthlyTrend   = report.monthlyTrend
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
        loadReport()
    }
}
