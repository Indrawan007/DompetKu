package com.dompetku.app.data.repository

import com.dompetku.app.data.local.dao.CategoryDao
import com.dompetku.app.data.mapper.toDomain
import com.dompetku.app.data.mapper.toEntity
import com.dompetku.app.domain.model.Category
import com.dompetku.app.domain.model.TransactionType
import com.dompetku.app.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override fun getAll(): Flow<List<Category>> {
        return categoryDao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getByType(type: TransactionType): Flow<List<Category>> {
        return categoryDao.getByType(type.name).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getById(id: Long): Category? {
        return categoryDao.getById(id)?.toDomain()
    }

    override suspend fun add(category: Category): Long {
        return categoryDao.insert(category.toEntity())
    }

    override suspend fun update(category: Category) {
        categoryDao.update(category.toEntity())
    }

    override suspend fun delete(category: Category) {
        categoryDao.delete(category.toEntity())
    }
}
