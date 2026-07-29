package com.dompetku.app.domain.usecase.transaction

import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.TransactionRepository
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {
    sealed class Result {
        object Success : Result()
        data class Error(val message: String) : Result()
    }

    suspend operator fun invoke(
        oldTransaction: Transaction,
        newTransaction: Transaction
    ): Result {
        if (newTransaction.amount <= 0) {
            return Result.Error("Jumlah harus lebih dari 0")
        }

        return try {
            // ── Revert saldo lama ─────────────
            val oldBalanceChange = when (oldTransaction.type) {
                TransactionType.INCOME  -> -oldTransaction.amount
                TransactionType.EXPENSE ->  oldTransaction.amount
            }
            accountRepository.updateBalance(
                oldTransaction.accountId,
                oldBalanceChange
            )

            // ── Jika akun berbeda, revert & apply ─
            // ── Apply saldo baru ──────────────
            val newBalanceChange = when (newTransaction.type) {
                TransactionType.INCOME  ->  newTransaction.amount
                TransactionType.EXPENSE -> -newTransaction.amount
            }
            accountRepository.updateBalance(
                newTransaction.accountId,
                newBalanceChange
            )

            // ── Update transaksi ──────────────
            transactionRepository.update(newTransaction)

            Result.Success
        } catch (e: Exception) {
            Result.Error(e.message ?: "Terjadi kesalahan")
        }
    }
}
