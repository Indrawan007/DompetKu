package com.dompetku.app.domain.model

data class DashboardSummary(
    val totalBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val recentTransactions: List<Transaction> = emptyList(),
    val budgetAlerts: List<Budget> = emptyList()
) {
    val netIncome: Double
        get() = totalIncome - totalExpense

    val expensePercentage: Float
        get() = if (totalIncome > 0) {
            ((totalExpense / totalIncome) * 100).toFloat()
        } else 0f
}