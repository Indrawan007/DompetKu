package com.dompetku.app.domain.usecase.transaction

import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.TransactionRepository
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {
    sealed class Result {
        data class Success(val id: Long) : Result()
        data class Error(val message: String) : Result()
    }

    suspend operator fun invoke(transaction: Transaction): Result {
        // ── Validasi ──────────────────────────
        if (transaction.amount <= 0) {
            return Result.Error("Jumlah harus lebih dari 0")
        }
        if (transaction.accountId == 0L) {
            return Result.Error("Pilih akun terlebih dahulu")
        }

        return try {
            // ── Simpan transaksi ──────────────
            val id = transactionRepository.add(transaction)

            // ── Update saldo akun ─────────────
            val balanceChange = when (transaction.type) {
                TransactionType.INCOME  ->  transaction.amount
                TransactionType.EXPENSE -> -transaction.amount
            }
            accountRepository.updateBalance(transaction.accountId, balanceChange)

            Result.Success(id)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Terjadi kesalahan")
        }
    }
}
