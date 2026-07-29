package com.dompetku.app.domain.usecase

import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.TransactionRepository
import com.dompetku.app.domain.usecase.transaction.AddTransactionUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.util.Date

class AddTransactionUseCaseTest {

    private lateinit var useCase: AddTransactionUseCase
    private lateinit var transactionRepo: TransactionRepository
    private lateinit var accountRepo: AccountRepository

    @Before
    fun setup() {
        transactionRepo = mock()
        accountRepo = mock()
        useCase = AddTransactionUseCase(transactionRepo, accountRepo)
    }

    private fun createTransaction(
        amount: Double = 50000.0,
        type: TransactionType = TransactionType.EXPENSE,
        accountId: Long = 1L
    ): Transaction {
        return Transaction(
            amount = amount,
            type = type,
            categoryId = 1L,
            accountId = accountId,
            date = Date()
        )
    }

    // ── Test: Amount harus > 0 ────────────────────
    @Test
    fun `should return error when amount is zero`() = runTest {
        val transaction = createTransaction(amount = 0.0)
        val result = useCase(transaction)

        assertTrue(result is AddTransactionUseCase.Result.Error)
        assertEquals(
            "Jumlah harus lebih dari 0",
            (result as AddTransactionUseCase.Result.Error).message
        )
        verifyNoInteractions(transactionRepo)
    }

    @Test
    fun `should return error when amount is negative`() = runTest {
        val transaction = createTransaction(amount = -100.0)
        val result = useCase(transaction)

        assertTrue(result is AddTransactionUseCase.Result.Error)
    }

    // ── Test: Account harus dipilih ───────────────
    @Test
    fun `should return error when accountId is zero`() = runTest {
        val transaction = createTransaction(accountId = 0L)
        val result = useCase(transaction)

        assertTrue(result is AddTransactionUseCase.Result.Error)
        assertEquals(
            "Pilih akun terlebih dahulu",
            (result as AddTransactionUseCase.Result.Error).message
        )
    }

    // ── Test: Success EXPENSE ─────────────────────
    @Test
    fun `should save expense and subtract balance`() = runTest {
        val transaction = createTransaction(
            amount = 50000.0,
            type = TransactionType.EXPENSE,
            accountId = 1L
        )
        whenever(transactionRepo.add(any())).thenReturn(1L)

        val result = useCase(transaction)

        assertTrue(result is AddTransactionUseCase.Result.Success)
        assertEquals(1L, (result as AddTransactionUseCase.Result.Success).id)
        verify(transactionRepo).add(any())
        verify(accountRepo).updateBalance(1L, -50000.0)
    }

    // ── Test: Success INCOME ──────────────────────
    @Test
    fun `should save income and add balance`() = runTest {
        val transaction = createTransaction(
            amount = 100000.0,
            type = TransactionType.INCOME,
            accountId = 1L
        )
        whenever(transactionRepo.add(any())).thenReturn(2L)

        val result = useCase(transaction)

        assertTrue(result is AddTransactionUseCase.Result.Success)
        verify(accountRepo).updateBalance(1L, 100000.0)
    }

    // ── Test: Exception handling ──────────────────
    @Test
    fun `should return error when repository throws`() = runTest {
        val transaction = createTransaction()
        whenever(transactionRepo.add(any())).thenThrow(RuntimeException("DB error"))

        val result = useCase(transaction)

        assertTrue(result is AddTransactionUseCase.Result.Error)
        assertEquals("DB error", (result as AddTransactionUseCase.Result.Error).message)
    }
}
