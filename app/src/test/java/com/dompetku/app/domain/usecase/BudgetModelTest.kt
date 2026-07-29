package com.dompetku.app.domain.usecase

import com.dompetku.app.domain.model.Budget
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BudgetModelTest {

    @Test
    fun `percentage should be correct`() {
        val budget = Budget(
            categoryId = 1,
            amountLimit = 500000.0,
            spentAmount = 250000.0,
            month = 1,
            year = 2025
        )
        assertEquals(50f, budget.percentage, 0.1f)
    }

    @Test
    fun `isOverBudget should be true when spent exceeds limit`() {
        val budget = Budget(
            categoryId = 1,
            amountLimit = 500000.0,
            spentAmount = 600000.0,
            month = 1,
            year = 2025
        )
        assertTrue(budget.isOverBudget)
        assertTrue(budget.percentage > 100f)
    }

    @Test
    fun `isNearLimit should be true at 80 percent`() {
        val budget = Budget(
            categoryId = 1,
            amountLimit = 500000.0,
            spentAmount = 400000.0,
            month = 1,
            year = 2025
        )
        assertTrue(budget.isNearLimit)
        assertFalse(budget.isOverBudget)
    }

    @Test
    fun `remainingAmount should be correct`() {
        val budget = Budget(
            categoryId = 1,
            amountLimit = 500000.0,
            spentAmount = 300000.0,
            month = 1,
            year = 2025
        )
        assertEquals(200000.0, budget.remainingAmount, 0.01)
    }

    @Test
    fun `remainingAmount can be negative when over budget`() {
        val budget = Budget(
            categoryId = 1,
            amountLimit = 500000.0,
            spentAmount = 700000.0,
            month = 1,
            year = 2025
        )
        assertEquals(-200000.0, budget.remainingAmount, 0.01)
    }

    @Test
    fun `percentage should be zero when limit is zero`() {
        val budget = Budget(
            categoryId = 1,
            amountLimit = 0.0,
            spentAmount = 100.0,
            month = 1,
            year = 2025
        )
        assertEquals(0f, budget.percentage, 0.01f)
    }
}
