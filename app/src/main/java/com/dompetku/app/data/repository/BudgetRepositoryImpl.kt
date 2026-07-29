package com.dompetku.app.data.repository

import com.dompetku.app.data.local.dao.BudgetDao
import com.dompetku.app.data.mapper.toDomain
import com.dompetku.app.data.mapper.toEntity
import com.dompetku.app.domain.model.Budget
import com.dompetku.app.domain.repository.BudgetRepository
import com.dompetku.app.util.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BudgetRepositoryImpl @Inject constructor(
    private val budgetDao: BudgetDao
) : BudgetRepository {

    override fun getByMonth(month: Int, year: Int): Flow<List<Budget>> {
        return budgetDao.getByMonth(month, year).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getWithSpent(
        month: Int,
        year: Int,
        startDate: Long,
        endDate: Long
    ): Flow<List<Budget>> {
        return budgetDao.getWithSpent(month, year, startDate, endDate).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getByCategoryAndMonth(
        categoryId: Long,
        month: Int,
        year: Int
    ): Budget? {
        return budgetDao.getByCategoryAndMonth(categoryId, month, year)?.toDomain()
    }

    override suspend fun set(budget: Budget): Long {
        // Cek apakah budget sudah ada untuk kategori & bulan ini
        val existing = budgetDao.getByCategoryAndMonth(
            budget.categoryId, budget.month, budget.year
        )
        return if (existing != null) {
            // Update yang sudah ada
            val updatedEntity = budget.toEntity().copy(id = existing.id)
            budgetDao.update(updatedEntity)
            existing.id
        } else {
            // Insert baru
            budgetDao.insert(budget.toEntity())
        }
    }

    override suspend fun update(budget: Budget) {
        budgetDao.update(budget.toEntity())
    }

    override suspend fun delete(budget: Budget) {
        budgetDao.delete(budget.toEntity())
    }
}
