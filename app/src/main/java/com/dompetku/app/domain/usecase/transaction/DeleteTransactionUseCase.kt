package com.dompetku.app.domain.usecase.transaction

import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.TransactionRepository
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {
    sealed class Result {
        object Success : Result()
        data class Error(val message: String) : Result()
    }

    suspend operator fun invoke(transaction: Transaction): Result {
        return try {
            // ── Revert saldo akun ─────────────
            val balanceRevert = when (transaction.type) {
                TransactionType.INCOME  -> -transaction.amount
                TransactionType.EXPENSE ->  transaction.amount
            }
            accountRepository.updateBalance(transaction.accountId, balanceRevert)

            // ── Hapus transaksi ───────────────
            transactionRepository.delete(transaction)

            Result.Success
        } catch (e: Exception) {
            Result.Error(e.message ?: "Terjadi kesalahan")
        }
    }
}
