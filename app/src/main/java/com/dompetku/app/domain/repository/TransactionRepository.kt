package com.dompetku.app.domain.repository

import com.dompetku.app.data.local.entity.CategorySummary
import com.dompetku.app.data.local.entity.MonthlyTrend
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun getAll(): Flow<List<Transaction>>

    fun getByDateRange(
        startDate: Long,
        endDate: Long
    ): Flow<List<Transaction>>

    fun getByTypeAndDateRange(
        type: TransactionType,
        startDate: Long,
        endDate: Long
    ): Flow<List<Transaction>>

    fun getRecent(limit: Int = 5): Flow<List<Transaction>>

    fun search(query: String): Flow<List<Transaction>>

    fun getTotalIncome(startDate: Long, endDate: Long): Flow<Double>

    fun getTotalExpense(startDate: Long, endDate: Long): Flow<Double>

    fun getCategorySummary(
        type: TransactionType,
        startDate: Long,
        endDate: Long
    ): Flow<List<CategorySummary>>

    fun getMonthlyTrend(
        startDate: Long,
        endDate: Long
    ): Flow<List<MonthlyTrend>>

    suspend fun getById(id: Long): Transaction?

    suspend fun add(transaction: Transaction): Long

    suspend fun update(transaction: Transaction)

    suspend fun delete(transaction: Transaction)

    suspend fun deleteById(id: Long)
}
