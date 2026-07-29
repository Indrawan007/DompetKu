package com.dompetku.app.domain.usecase.report

import com.dompetku.app.data.local.entity.CategorySummary
import com.dompetku.app.data.local.entity.MonthlyTrend
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GenerateReportUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    data class Report(
        val totalIncome: Double,
        val totalExpense: Double,
        val netBalance: Double,
        val expenseCategorySummary: List<CategorySummary>,
        val incomeCategorySummary: List<CategorySummary>,
        val monthlyTrend: List<MonthlyTrend>,
        val transactions: List<Transaction>
    )

    operator fun invoke(startDate: Long, endDate: Long): Flow<Report> {
        return combine(
            transactionRepository.getTotalIncome(startDate, endDate),
            transactionRepository.getTotalExpense(startDate, endDate),
            transactionRepository.getCategorySummary(
                TransactionType.EXPENSE, startDate, endDate
            ),
            transactionRepository.getCategorySummary(
                TransactionType.INCOME, startDate, endDate
            ),
            transactionRepository.getMonthlyTrend(startDate, endDate),
            transactionRepository.getByDateRange(startDate, endDate)
        ) { income, expense, expenseCats, incomeCats, trends, transactions ->
            Report(
                totalIncome = income,
                totalExpense = expense,
                netBalance = income - expense,
                expenseCategorySummary = expenseCats,
                incomeCategorySummary = incomeCats,
                monthlyTrend = trends,
                transactions = transactions
            )
        }
    }
}

// combine() hanya support max 5 params, kita buat extension
private fun <T1, T2, T3, T4, T5, T6, R> combine(
    flow1: Flow<T1>,
    flow2: Flow<T2>,
    flow3: Flow<T3>,
    flow4: Flow<T4>,
    flow5: Flow<T5>,
    flow6: Flow<T6>,
    transform: suspend (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = combine(
    combine(flow1, flow2, flow3, ::Triple),
    combine(flow4, flow5, flow6, ::Triple)
) { t123, t456 ->
    transform(
        t123.first,
        t123.second,
        t123.third,
        t456.first,
        t456.second,
        t456.third
    )
}
