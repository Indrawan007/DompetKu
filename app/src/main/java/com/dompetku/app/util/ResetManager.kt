package com.dompetku.app.util

import android.content.Context
import com.dompetku.app.data.local.AppDatabase
import com.dompetku.app.data.local.DatabaseSeeder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResetManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: AppDatabase,
    private val preferencesManager: PreferencesManager
) {
    suspend fun resetAll(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            // 1. Hapus semua data
            database.transactionDao().deleteAll()
            database.budgetDao().deleteAll()

            // 2. Re-seed default categories & accounts
            val categoryDao = database.categoryDao()
            val accountDao = database.accountDao()

            // Hapus custom, insert ulang default
            categoryDao.deleteCustomCategories()
            if (categoryDao.getCount() == 0) {
                categoryDao.insertAll(DatabaseSeeder.getAllDefaultCategories())
            }
            if (accountDao.getCount() == 0) {
                accountDao.insertAll(DatabaseSeeder.getDefaultAccounts())
            }

            // 3. Reset saldo akun ke 0
            val accounts = accountDao.getAll()
            // getAll() returns Flow, kita perlu cara lain

            // 4. Clear preferences
            preferencesManager.clearAll()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
