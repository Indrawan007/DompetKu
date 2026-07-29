package com.dompetku.app.di

import com.dompetku.app.data.repository.AccountRepositoryImpl
import com.dompetku.app.data.repository.BudgetRepositoryImpl
import com.dompetku.app.data.repository.CategoryRepositoryImpl
import com.dompetku.app.data.repository.TransactionRepositoryImpl
import com.dompetku.app.domain.repository.AccountRepository
import com.dompetku.app.domain.repository.BudgetRepository
import com.dompetku.app.domain.repository.CategoryRepository
import com.dompetku.app.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(
        impl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindAccountRepository(
        impl: AccountRepositoryImpl
    ): AccountRepository

    @Binds
    @Singleton
    abstract fun bindBudgetRepository(
        impl: BudgetRepositoryImpl
    ): BudgetRepository
}
