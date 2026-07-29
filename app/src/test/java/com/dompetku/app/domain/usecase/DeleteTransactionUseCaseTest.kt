package com.dompetku.app.domain.usecase

import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.TransactionRepository
import com.dompetku.app.domain.usecase.transaction.DeleteTransactionUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import java.util.Date

class DeleteTransactionUseCaseTest {

    private lateinit var useCase: DeleteTransactionUseCase
    private lateinit var transactionRepo: TransactionRepository
    private lateinit var accountRepo: AccountRepository

    @Before
    fun setup() {
        transactionRepo = mock()
        accountRepo = mock()
        useCase = DeleteTransactionUseCase(transactionRepo, accountRepo)
    }

    @Test
    fun `should delete expense and revert balance positive`() = runTest {
        val transaction = Transaction(
            id = 1,
            amount = 50000.0,
            type = TransactionType.EXPENSE,
            accountId = 1L,
            date = Date()
        )

        val result = useCase(transaction)

        assertTrue(result is DeleteTransactionUseCase.Result.Success)
        verify(accountRepo).updateBalance(1L, 50000.0)  // revert: +
        verify(transactionRepo).delete(any())
    }

    @Test
    fun `should delete income and revert balance negative`() = runTest {
        val transaction = Transaction(
            id = 2,
            amount = 100000.0,
            type = TransactionType.INCOME,
            accountId = 1L,
            date = Date()
        )

        val result = useCase(transaction)

        assertTrue(result is DeleteTransactionUseCase.Result.Success)
        verify(accountRepo).updateBalance(1L, -100000.0) // revert: -
    }
}
