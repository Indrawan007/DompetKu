package com.dompetku.app.data.repository

import com.dompetku.app.data.local.dao.TransactionDao
import com.dompetku.app.data.local.entity.CategorySummary
import com.dompetku.app.data.local.entity.MonthlyTrend
import com.dompetku.app.data.mapper.toDomain
import com.dompetku.app.data.mapper.toEntity
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override fun getAll(): Flow<List<Transaction>> {
        return transactionDao.getAllWithDetails().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getByDateRange(
        startDate: Long,
        endDate: Long
    ): Flow<List<Transaction>> {
        return transactionDao.getByDateRange(startDate, endDate).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getByTypeAndDateRange(
        type: TransactionType,
        startDate: Long,
        endDate: Long
    ): Flow<List<Transaction>> {
        return transactionDao.getByTypeAndDateRange(
            type.name, startDate, endDate
        ).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getRecent(limit: Int): Flow<List<Transaction>> {
        return transactionDao.getRecent(limit).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun search(query: String): Flow<List<Transaction>> {
        return transactionDao.search(query).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getTotalIncome(startDate: Long, endDate: Long): Flow<Double> {
        return transactionDao.getTotalIncome(startDate, endDate)
    }

    override fun getTotalExpense(startDate: Long, endDate: Long): Flow<Double> {
        return transactionDao.getTotalExpense(startDate, endDate)
    }

    override fun getCategorySummary(
        type: TransactionType,
        startDate: Long,
        endDate: Long
    ): Flow<List<CategorySummary>> {
        return transactionDao.getCategorySummary(type.name, startDate, endDate)
    }

    override fun getMonthlyTrend(
        startDate: Long,
        endDate: Long
    ): Flow<List<MonthlyTrend>> {
        return transactionDao.getMonthlyTrend(startDate, endDate)
    }

    override suspend fun getById(id: Long): Transaction? {
        return transactionDao.getByIdWithDetails(id)?.toDomain()
    }

    override suspend fun add(transaction: Transaction): Long {
        return transactionDao.insert(transaction.toEntity())
    }

    override suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction.toEntity())
    }

    override suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction.toEntity())
    }

    override suspend fun deleteById(id: Long) {
        transactionDao.deleteById(id)
    }
}
