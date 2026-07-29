package com.dompetku.app.domain.usecase.budget

import com.dompetku.app.domain.model.Budget
import com.dompetku.app.domain.repository.BudgetRepository
import javax.inject.Inject

class SetBudgetUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) {
    sealed class Result {
        data class Success(val id: Long) : Result()
        data class Error(val message: String) : Result()
    }

    suspend operator fun invoke(budget: Budget): Result {
        if (budget.amountLimit <= 0) {
            return Result.Error("Batas anggaran harus lebih dari 0")
        }
        if (budget.categoryId == 0L) {
            return Result.Error("Pilih kategori terlebih dahulu")
        }

        return try {
            val id = budgetRepository.set(budget)
            Result.Success(id)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Terjadi kesalahan")
        }
    }
}
