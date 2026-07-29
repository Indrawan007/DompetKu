package com.dompetku.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dompetku.app.data.local.entity.CategorySummary
import com.dompetku.app.data.local.entity.DailyTotal
import com.dompetku.app.data.local.entity.MonthlyTrend
import com.dompetku.app.data.local.entity.TransactionEntity
import com.dompetku.app.data.local.entity.TransactionWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    // ══════════════════════════════════════
    // INSERT
    // ══════════════════════════════════════
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: TransactionEntity): Long

    // ══════════════════════════════════════
    // UPDATE
    // ══════════════════════════════════════
    @Update
    suspend fun update(transaction: TransactionEntity)

    // ══════════════════════════════════════
    // DELETE
    // ══════════════════════════════════════
    @Delete
    suspend fun delete(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM transactions")
    suspend fun deleteAll()

    // ══════════════════════════════════════
    // BASIC QUERIES
    // ══════════════════════════════════════
    @Query("""
        SELECT t.id, t.amount, t.type, 
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        ORDER BY t.date DESC, t.created_at DESC
    """)
    fun getAllWithDetails(): Flow<List<TransactionWithDetails>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getById(id: Long): TransactionEntity?

    @Query("""
        SELECT t.id, t.amount, t.type,
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        WHERE t.id = :id
    """)
    suspend fun getByIdWithDetails(id: Long): TransactionWithDetails?

    // ══════════════════════════════════════
    // FILTER QUERIES
    // ══════════════════════════════════════
    @Query("""
        SELECT t.id, t.amount, t.type,
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        WHERE t.date BETWEEN :startDate AND :endDate
        ORDER BY t.date DESC, t.created_at DESC
    """)
    fun getByDateRange(
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionWithDetails>>

    @Query("""
        SELECT t.id, t.amount, t.type,
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        WHERE t.type = :type
        AND t.date BETWEEN :startDate AND :endDate
        ORDER BY t.date DESC
    """)
    fun getByTypeAndDateRange(
        type: String,
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionWithDetails>>

    @Query("""
        SELECT t.id, t.amount, t.type,
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        WHERE t.category_id = :categoryId
        AND t.date BETWEEN :startDate AND :endDate
        ORDER BY t.date DESC
    """)
    fun getByCategoryAndDateRange(
        categoryId: Long,
        startDate: Long,
        endDate: Long
    ): Flow<List<TransactionWithDetails>>

    @Query("""
        SELECT t.id, t.amount, t.type,
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        WHERE t.account_id = :accountId
        ORDER BY t.date DESC
    """)
    fun getByAccount(accountId: Long): Flow<List<TransactionWithDetails>>

    // ══════════════════════════════════════
    // SEARCH
    // ══════════════════════════════════════
    @Query("""
        SELECT t.id, t.amount, t.type,
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        WHERE t.note LIKE '%' || :query || '%'
           OR c.name LIKE '%' || :query || '%'
           OR a.name LIKE '%' || :query || '%'
        ORDER BY t.date DESC
    """)
    fun search(query: String): Flow<List<TransactionWithDetails>>

    // ══════════════════════════════════════
    // AGGREGATION QUERIES
    // ══════════════════════════════════════
    @Query("""
        SELECT COALESCE(SUM(amount), 0.0)
        FROM transactions
        WHERE type = 'INCOME'
        AND date BETWEEN :startDate AND :endDate
    """)
    fun getTotalIncome(startDate: Long, endDate: Long): Flow<Double>

    @Query("""
        SELECT COALESCE(SUM(amount), 0.0)
        FROM transactions
        WHERE type = 'EXPENSE'
        AND date BETWEEN :startDate AND :endDate
    """)
    fun getTotalExpense(startDate: Long, endDate: Long): Flow<Double>

    @Query("""
        SELECT COALESCE(SUM(amount), 0.0)
        FROM transactions
        WHERE type = 'EXPENSE'
        AND category_id = :categoryId
        AND date BETWEEN :startDate AND :endDate
    """)
    fun getTotalExpenseByCategory(
        categoryId: Long,
        startDate: Long,
        endDate: Long
    ): Flow<Double>

    @Query("SELECT COUNT(*) FROM transactions")
    fun getTransactionCount(): Flow<Int>

    // ══════════════════════════════════════
    // REPORT QUERIES
    // ══════════════════════════════════════
    @Query("""
        SELECT c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               COALESCE(SUM(t.amount), 0.0) AS totalAmount,
               COUNT(t.id) AS transactionCount
        FROM transactions t
        INNER JOIN categories c ON t.category_id = c.id
        WHERE t.type = :type
        AND t.date BETWEEN :startDate AND :endDate
        GROUP BY t.category_id
        ORDER BY totalAmount DESC
    """)
    fun getCategorySummary(
        type: String,
        startDate: Long,
        endDate: Long
    ): Flow<List<CategorySummary>>

    @Query("""
        SELECT strftime('%Y-%m', date / 1000, 'unixepoch', 'localtime') AS monthYear,
               COALESCE(SUM(CASE WHEN type = 'INCOME' THEN amount ELSE 0 END), 0.0) AS totalIncome,
               COALESCE(SUM(CASE WHEN type = 'EXPENSE' THEN amount ELSE 0 END), 0.0) AS totalExpense
        FROM transactions
        WHERE date BETWEEN :startDate AND :endDate
        GROUP BY monthYear
        ORDER BY monthYear ASC
    """)
    fun getMonthlyTrend(
        startDate: Long,
        endDate: Long
    ): Flow<List<MonthlyTrend>>

    @Query("""
        SELECT strftime('%Y-%m-%d', date / 1000, 'unixepoch', 'localtime') AS date,
               COALESCE(SUM(amount), 0.0) AS totalAmount,
               type
        FROM transactions
        WHERE date BETWEEN :startDate AND :endDate
        GROUP BY date, type
        ORDER BY date ASC
    """)
    fun getDailyTotal(
        startDate: Long,
        endDate: Long
    ): Flow<List<DailyTotal>>

    // ══════════════════════════════════════
    // RECENT (untuk dashboard)
    // ══════════════════════════════════════
    @Query("""
        SELECT t.id, t.amount, t.type,
               t.category_id AS categoryId,
               c.name AS categoryName,
               c.color AS categoryColor,
               c.icon AS categoryIcon,
               t.account_id AS accountId,
               a.name AS accountName,
               t.note, t.date, t.created_at AS createdAt
        FROM transactions t
        LEFT JOIN categories c ON t.category_id = c.id
        LEFT JOIN accounts a ON t.account_id = a.id
        ORDER BY t.date DESC, t.created_at DESC
        LIMIT :limit
    """)
    fun getRecent(limit: Int = 5): Flow<List<TransactionWithDetails>>
}
// Sudah ada deleteAll() di atas
