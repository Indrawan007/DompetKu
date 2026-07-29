package com.dompetku.app.domain.usecase.transaction

import com.dompetku.app.domain.model.DashboardSummary
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.BudgetRepository
import com.dompetku.app.domain.repository.TransactionRepository
import com.dompetku.app.util.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetDashboardSummaryUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
    private val budgetRepository: BudgetRepository
) {
    operator fun invoke(month: Int, year: Int): Flow<DashboardSummary> {
        val (startDate, endDate) = DateUtils.getMonthRange(month, year)

        return combine(
            accountRepository.getTotalBalance(),
            transactionRepository.getTotalIncome(startDate, endDate),
            transactionRepository.getTotalExpense(startDate, endDate),
            transactionRepository.getRecent(5),
            budgetRepository.getWithSpent(month, year, startDate, endDate)
        ) { totalBalance, income, expense, recent, budgets ->

            val budgetAlerts = budgets.filter {
                it.percentage >= 80f
            }

            DashboardSummary(
                totalBalance = totalBalance,
                totalIncome = income,
                totalExpense = expense,
                recentTransactions = recent,
                budgetAlerts = budgetAlerts
            )
        }
    }
}
