package com.dompetku.app.domain.usecase.budget

import com.dompetku.app.domain.model.Budget
import com.dompetku.app.domain.repository.BudgetRepository
import com.dompetku.app.util.DateUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBudgetWithSpentUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke(month: Int, year: Int): Flow<List<Budget>> {
        val (startDate, endDate) = DateUtils.getMonthRange(month, year)
        return budgetRepository.getWithSpent(month, year, startDate, endDate)
    }
}
