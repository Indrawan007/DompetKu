package com.dompetku.app.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateUtils {

    private val localeID = Locale("id", "ID")

    /**
     * Mendapatkan range waktu (start, end) untuk bulan tertentu
     * @return Pair(startOfMonth, endOfMonth) dalam milliseconds
     */
    fun getMonthRange(month: Int, year: Int): Pair<Long, Long> {
        val calendar = Calendar.getInstance()

        // Start of month: tanggal 1, jam 00:00:00.000
        calendar.set(year, month - 1, 1, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startDate = calendar.timeInMillis

        // End of month: tanggal terakhir, jam 23:59:59.999
        calendar.set(
            Calendar.DAY_OF_MONTH,
            calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        )
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        val endDate = calendar.timeInMillis

        return Pair(startDate, endDate)
    }

    /**
     * Mendapatkan range waktu untuk tahun tertentu
     */
    fun getYearRange(year: Int): Pair<Long, Long> {
        val calendar = Calendar.getInstance()

        calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startDate = calendar.timeInMillis

        calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        val endDate = calendar.timeInMillis

        return Pair(startDate, endDate)
    }

    /**
     * Format tanggal relatif
     * Contoh: "Hari ini", "Kemarin", "3 hari lalu", "15 Jan 2025"
     */
    fun formatRelative(date: Date): String {
        val now = Date()
        val diff = now.time - date.time
        val days = TimeUnit.MILLISECONDS.toDays(diff)

        return when {
            days == 0L -> "Hari ini"
            days == 1L -> "Kemarin"
            days < 7L -> "$days hari lalu"
            else -> formatDate(date)
        }
    }

    /**
     * Format: "15 Januari 2025"
     */
    fun formatDateFull(date: Date): String {
        val formatter = SimpleDateFormat("dd MMMM yyyy", localeID)
        return formatter.format(date)
    }

    /**
     * Format: "15 Jan 2025"
     */
    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd MMM yyyy", localeID)
        return formatter.format(date)
    }

    /**
     * Format: "Januari 2025"
     */
    fun formatMonthYear(month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month - 1)
        calendar.set(Calendar.YEAR, year)
        val formatter = SimpleDateFormat("MMMM yyyy", localeID)
        return formatter.format(calendar.time)
    }

    /**
     * Format: "Januari 2025" dari Calendar
     */
    fun formatMonthYear(calendar: Calendar): String {
        val formatter = SimpleDateFormat("MMMM yyyy", localeID)
        return formatter.format(calendar.time)
    }

    /**
     * Format: "15 Jan"
     */
    fun formatDayMonth(date: Date): String {
        val formatter = SimpleDateFormat("dd MMM", localeID)
        return formatter.format(date)
    }

    /**
     * Format: "15:30"
     */
    fun formatTime(date: Date): String {
        val formatter = SimpleDateFormat("HH:mm", localeID)
        return formatter.format(date)
    }

    /**
     * Mendapatkan bulan & tahun saat ini
     */
    fun getCurrentMonthYear(): Pair<Int, Int> {
        val calendar = Calendar.getInstance()
        return Pair(
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.YEAR)
        )
    }
}