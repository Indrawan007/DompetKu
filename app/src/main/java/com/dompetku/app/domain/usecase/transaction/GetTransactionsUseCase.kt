package com.dompetku.app.domain.usecase.transaction

import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.repository.TransactionRepository
import com.dompetku.app.util.DateUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository
) {
    // Semua transaksi
    fun getAll(): Flow<List<Transaction>> {
        return transactionRepository.getAll()
    }

    // Transaksi per bulan
    fun getByMonth(month: Int, year: Int): Flow<List<Transaction>> {
        val (start, end) = DateUtils.getMonthRange(month, year)
        return transactionRepository.getByDateRange(start, end)
    }

    // Transaksi terbaru untuk dashboard
    fun getRecent(limit: Int = 5): Flow<List<Transaction>> {
        return transactionRepository.getRecent(limit)
    }

    // Cari transaksi
    fun search(query: String): Flow<List<Transaction>> {
        return transactionRepository.search(query)
    }

    // Transaksi by range custom
    fun getByDateRange(startDate: Long, endDate: Long): Flow<List<Transaction>> {
        return transactionRepository.getByDateRange(startDate, endDate)
    }
}
