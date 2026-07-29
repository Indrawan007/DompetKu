package com.dompetku.app.domain.repository

import com.dompetku.app.domain.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAll(): Flow<List<Account>>

    fun getTotalBalance(): Flow<Double>

    suspend fun getById(id: Long): Account?

    suspend fun add(account: Account): Long

    suspend fun update(account: Account)

    suspend fun delete(account: Account)

    suspend fun updateBalance(accountId: Long, amount: Double)
}
