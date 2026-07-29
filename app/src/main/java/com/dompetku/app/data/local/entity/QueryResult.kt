package com.dompetku.app.data.local.entity

data class CategorySummary(
    val categoryName: String,
    val categoryColor: String,
    val categoryIcon: String,
    val totalAmount: Double,
    val transactionCount: Int
)

data class MonthlyTrend(
    val monthYear: String,
    val totalIncome: Double,
    val totalExpense: Double
)

data class DailyTotal(
    val date: String,
    val totalAmount: Double,
    val type: String
)

data class BudgetWithSpent(
    val id: Long,
    val categoryId: Long,
    val categoryName: String,
    val categoryColor: String,
    val categoryIcon: String,
    val amountLimit: Double,
    val spentAmount: Double,
    val month: Int,
    val year: Int
)

data class TransactionWithDetails(
    val id: Long,
    val amount: Double,
    val type: String,
    val categoryId: Long?,
    val categoryName: String?,
    val categoryColor: String?,
    val categoryIcon: String?,
    val accountId: Long,
    val accountName: String,
    val note: String,
    val date: Long,
    val createdAt: Long
)
