package com.dompetku.app.domain.repository

import com.dompetku.app.domain.model.Category
import com.dompetku.app.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAll(): Flow<List<Category>>

    fun getByType(type: TransactionType): Flow<List<Category>>

    suspend fun getById(id: Long): Category?

    suspend fun add(category: Category): Long

    suspend fun update(category: Category)

    suspend fun delete(category: Category)
}
