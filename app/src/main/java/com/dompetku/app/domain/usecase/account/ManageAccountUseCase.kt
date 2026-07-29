package com.dompetku.app.domain.usecase.account

import com.dompetku.app.domain.model.Account
import com.dompetku.app.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    sealed class Result {
        data class Success(val id: Long = 0) : Result()
        data class Error(val message: String) : Result()
    }

    fun getAll(): Flow<List<Account>> = accountRepository.getAll()

    fun getTotalBalance(): Flow<Double> = accountRepository.getTotalBalance()

    suspend fun add(account: Account): Result {
        if (account.name.isBlank()) {
            return Result.Error("Nama akun tidak boleh kosong")
        }
        return try {
            val id = accountRepository.add(account)
            Result.Success(id)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Terjadi kesalahan")
        }
    }

    suspend fun update(account: Account): Result {
        if (account.name.isBlank()) {
            return Result.Error("Nama akun tidak boleh kosong")
        }
        return try {
            accountRepository.update(account)
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e.message ?: "Terjadi kesalahan")
        }
    }

    suspend fun delete(account: Account): Result {
        return try {
            accountRepository.delete(account)
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e.message ?: "Terjadi kesalahan")
        }
    }
}
