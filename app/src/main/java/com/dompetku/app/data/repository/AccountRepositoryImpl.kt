package com.dompetku.app.data.repository

import com.dompetku.app.data.local.dao.AccountDao
import com.dompetku.app.data.mapper.toDomain
import com.dompetku.app.data.mapper.toEntity
import com.dompetku.app.domain.model.Account
import com.dompetku.app.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
) : AccountRepository {

    override fun getAll(): Flow<List<Account>> {
        return accountDao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getTotalBalance(): Flow<Double> {
        return accountDao.getTotalBalance()
    }

    override suspend fun getById(id: Long): Account? {
        return accountDao.getById(id)?.toDomain()
    }

    override suspend fun add(account: Account): Long {
        return accountDao.insert(account.toEntity())
    }

    override suspend fun update(account: Account) {
        accountDao.update(account.toEntity())
    }

    override suspend fun delete(account: Account) {
        accountDao.delete(account.toEntity())
    }

    override suspend fun updateBalance(accountId: Long, amount: Double) {
        accountDao.updateBalance(accountId, amount)
    }
}
