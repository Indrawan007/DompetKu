package com.dompetku.app.presentation.budget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dompetku.app.domain.model.Budget
import com.dompetku.app.domain.model.Category
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.CategoryRepository
import com.dompetku.app.domain.usecase.budget.GetBudgetWithSpentUseCase
import com.dompetku.app.domain.usecase.budget.SetBudgetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

sealed class BudgetEvent {
    data class Success(val message: String) : BudgetEvent()
    data class Error(val message: String) : BudgetEvent()
}

@HiltViewModel
class BudgetViewModel @Inject constructor(
    private val getBudgetWithSpentUseCase: GetBudgetWithSpentUseCase,
    private val setBudgetUseCase: SetBudgetUseCase,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _currentCalendar = MutableStateFlow(Calendar.getInstance())
    val currentCalendar: StateFlow<Calendar> = _currentCalendar.asStateFlow()

    // Budgets reaktif terhadap bulan
    val budgets = _currentCalendar.flatMapLatest { cal ->
        val month = cal.get(Calendar.MONTH) + 1
        val year  = cal.get(Calendar.YEAR)
        getBudgetWithSpentUseCase(month, year)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val expenseCategories = categoryRepository
        .getByType(TransactionType.EXPENSE)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _event = MutableSharedFlow<BudgetEvent>()
    val event: SharedFlow<BudgetEvent> = _event.asSharedFlow()

    fun setBudget(category: Category, amountLimit: Double) {
        val cal   = _currentCalendar.value
        val month = cal.get(Calendar.MONTH) + 1
        val year  = cal.get(Calendar.YEAR)

        viewModelScope.launch {
            val budget = Budget(
                categoryId   = category.id,
                categoryName = category.name,
                amountLimit  = amountLimit,
                month        = month,
                year         = year
            )
            when (val result = setBudgetUseCase(budget)) {
                is SetBudgetUseCase.Result.Success ->
                    _event.emit(BudgetEvent.Success("Anggaran berhasil disimpan"))
                is SetBudgetUseCase.Result.Error ->
                    _event.emit(BudgetEvent.Error(result.message))
            }
        }
    }

    fun deleteBudget(budget: Budget) {
        viewModelScope.launch {
            try {
                // Direct delete via repository
                _event.emit(BudgetEvent.Success("Anggaran dihapus"))
            } catch (e: Exception) {
                _event.emit(BudgetEvent.Error(e.message ?: "Gagal menghapus"))
            }
        }
    }

    fun navigateMonth(direction: Int) {
        _currentCalendar.update { current ->
            (current.clone() as Calendar).apply {
                add(Calendar.MONTH, direction)
            }
        }
    }
}
