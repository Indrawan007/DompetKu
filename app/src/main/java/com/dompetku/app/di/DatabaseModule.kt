package com.dompetku.app.di

import android.content.Context
import androidx.room.Room
import com.dompetku.app.data.local.AppDatabase
import com.dompetku.app.data.local.DatabaseCallback
import com.dompetku.app.data.local.dao.AccountDao
import com.dompetku.app.data.local.dao.BudgetDao
import com.dompetku.app.data.local.dao.CategoryDao
import com.dompetku.app.data.local.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        categoryDaoProvider: Provider<CategoryDao>,
        accountDaoProvider: Provider<AccountDao>
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .addCallback(DatabaseCallback(categoryDaoProvider, accountDaoProvider))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: AppDatabase): TransactionDao =
        database.transactionDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao =
        database.categoryDao()

    @Provides
    @Singleton
    fun provideAccountDao(database: AppDatabase): AccountDao =
        database.accountDao()

    @Provides
    @Singleton
    fun provideBudgetDao(database: AppDatabase): BudgetDao =
        database.budgetDao()
}
