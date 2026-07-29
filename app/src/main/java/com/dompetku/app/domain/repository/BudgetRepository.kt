package com.dompetku.app.domain.repository

import com.dompetku.app.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {

    fun getByMonth(month: Int, year: Int): Flow<List<Budget>>

    fun getWithSpent(
        month: Int,
        year: Int,
        startDate: Long,
        endDate: Long
    ): Flow<List<Budget>>

    suspend fun getByCategoryAndMonth(
        categoryId: Long,
        month: Int,
        year: Int
    ): Budget?

    suspend fun set(budget: Budget): Long

    suspend fun update(budget: Budget)

    suspend fun delete(budget: Budget)
}
