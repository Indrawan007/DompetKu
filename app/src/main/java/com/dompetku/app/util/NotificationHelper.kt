package com.dompetku.app.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dompetku.app.R

object NotificationHelper {

    const val CHANNEL_REMINDER = "daily_reminder"
    const val CHANNEL_BUDGET = "budget_alert"
    const val NOTIFICATION_REMINDER_ID = 1001

    fun createChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val reminderChannel = NotificationChannel(
                CHANNEL_REMINDER,
                "Pengingat Harian",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Pengingat untuk mencatat pengeluaran"
            }

            val budgetChannel = NotificationChannel(
                CHANNEL_BUDGET,
                "Peringatan Anggaran",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifikasi saat anggaran hampir habis"
            }

            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannels(listOf(reminderChannel, budgetChannel))
        }
    }

    fun showDailyReminder(context: Context) {
        val notification = NotificationCompat.Builder(context, CHANNEL_REMINDER)
            .setSmallIcon(R.drawable.ic_add)
            .setContentTitle("DompetKu")
            .setContentText("Jangan lupa catat pengeluaran hari ini!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        try {
            NotificationManagerCompat.from(context)
                .notify(NOTIFICATION_REMINDER_ID, notification)
        } catch (e: SecurityException) {
            // Permission not granted
        }
    }
}
