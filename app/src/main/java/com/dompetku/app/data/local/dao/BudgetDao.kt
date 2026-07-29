package com.dompetku.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dompetku.app.data.local.entity.BudgetEntity
import com.dompetku.app.data.local.entity.BudgetWithSpent
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budget: BudgetEntity): Long

    @Update
    suspend fun update(budget: BudgetEntity)

    @Delete
    suspend fun delete(budget: BudgetEntity)

    @Query("SELECT * FROM budgets WHERE month = :month AND year = :year")
    fun getByMonth(month: Int, year: Int): Flow<List<BudgetEntity>>

    @Query("""
        SELECT * FROM budgets 
        WHERE category_id = :categoryId 
        AND month = :month 
        AND year = :year
        LIMIT 1
    """)
    suspend fun getByCategoryAndMonth(
        categoryId: Long,
        month: Int,
        year: Int
    ): BudgetEntity?

    @Query("""
        SELECT b.id,
               b.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               b.amount_limit AS amountLimit,
               COALESCE(
                   (SELECT SUM(t.amount) 
                    FROM transactions t
                    WHERE t.category_id = b.category_id
                    AND t.type = 'EXPENSE'
                    AND t.date BETWEEN :startDate AND :endDate
                   ), 0.0
               ) AS spentAmount,
               b.month,
               b.year
        FROM budgets b
        INNER JOIN categories c ON b.category_id = c.id
        WHERE b.month = :month AND b.year = :year
        ORDER BY c.name ASC
    """)
    fun getWithSpent(
        month: Int,
        year: Int,
        startDate: Long,
        endDate: Long
    ): Flow<List<BudgetWithSpent>>

    @Query("DELETE FROM budgets WHERE month = :month AND year = :year")
    suspend fun deleteByMonth(month: Int, year: Int)

    @Query("DELETE FROM budgets")
    suspend fun deleteAll()
}