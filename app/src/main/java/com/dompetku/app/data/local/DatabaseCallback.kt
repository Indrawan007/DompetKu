package com.dompetku.app.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dompetku.app.data.local.dao.AccountDao
import com.dompetku.app.data.local.dao.CategoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class DatabaseCallback(
    private val categoryDaoProvider: Provider<CategoryDao>,
    private val accountDaoProvider: Provider<AccountDao>
) : RoomDatabase.Callback() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        scope.launch {
            val categoryDao = categoryDaoProvider.get()
            if (categoryDao.getCount() == 0) {
                categoryDao.insertAll(DatabaseSeeder.getAllDefaultCategories())
            }

            val accountDao = accountDaoProvider.get()
            if (accountDao.getCount() == 0) {
                accountDao.insertAll(DatabaseSeeder.getDefaultAccounts())
            }
        }
    }
}
