package com.dompetku.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dompetku.app.data.local.converter.DateConverter
import com.dompetku.app.data.local.dao.AccountDao
import com.dompetku.app.data.local.dao.BudgetDao
import com.dompetku.app.data.local.dao.CategoryDao
import com.dompetku.app.data.local.dao.TransactionDao
import com.dompetku.app.data.local.entity.AccountEntity
import com.dompetku.app.data.local.entity.BudgetEntity
import com.dompetku.app.data.local.entity.CategoryEntity
import com.dompetku.app.data.local.entity.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class,
        CategoryEntity::class,
        AccountEntity::class,
        BudgetEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun accountDao(): AccountDao
    abstract fun budgetDao(): BudgetDao

    companion object {
        const val DATABASE_NAME = "dompetku_database"
    }
}
