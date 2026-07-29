package com.dompetku.app.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import com.dompetku.app.data.local.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackupManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: AppDatabase
) {
    suspend fun exportBackup(): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Tutup WAL checkpoint
            database.openHelper.writableDatabase.execSQL("PRAGMA wal_checkpoint(FULL)")

            val dbFile = context.getDatabasePath(AppDatabase.DATABASE_NAME)
            if (!dbFile.exists()) {
                return@withContext Result.failure(Exception("Database tidak ditemukan"))
            }

            val backupDir = File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "DompetKu"
            )
            backupDir.mkdirs()

            val timestamp = SimpleDateFormat(
                "yyyyMMdd_HHmmss", Locale.getDefault()
            ).format(Date())

            val backupFile = File(backupDir, "dompetku_backup_$timestamp.db")

            FileInputStream(dbFile).use { input ->
                FileOutputStream(backupFile).use { output ->
                    input.copyTo(output)
                }
            }

            Result.success(backupFile.absolutePath)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun importBackup(uri: Uri): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            // Tutup database dulu
            database.close()

            val dbFile = context.getDatabasePath(AppDatabase.DATABASE_NAME)

            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(dbFile).use { output ->
                    input.copyTo(output)
                }
            } ?: return@withContext Result.failure(Exception("Tidak bisa membaca file"))

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
