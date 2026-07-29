package com.dompetku.app.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar
import java.util.Date

class DateUtilsTest {

    @Test
    fun `getMonthRange should return correct start and end`() {
        val (start, end) = DateUtils.getMonthRange(1, 2025) // Januari 2025

        val startCal = Calendar.getInstance().apply { timeInMillis = start }
        val endCal = Calendar.getInstance().apply { timeInMillis = end }

        assertEquals(0, startCal.get(Calendar.MONTH))     // Januari = 0
        assertEquals(2025, startCal.get(Calendar.YEAR))
        assertEquals(1, startCal.get(Calendar.DAY_OF_MONTH))

        assertEquals(0, endCal.get(Calendar.MONTH))
        assertEquals(31, endCal.get(Calendar.DAY_OF_MONTH))

        assertTrue(end > start)
    }

    @Test
    fun `getMonthRange February should have 28 or 29 days`() {
        val (_, end) = DateUtils.getMonthRange(2, 2025)
        val endCal = Calendar.getInstance().apply { timeInMillis = end }
        assertEquals(28, endCal.get(Calendar.DAY_OF_MONTH)) // 2025 bukan leap year
    }

    @Test
    fun `getYearRange should cover full year`() {
        val (start, end) = DateUtils.getYearRange(2025)

        val startCal = Calendar.getInstance().apply { timeInMillis = start }
        val endCal = Calendar.getInstance().apply { timeInMillis = end }

        assertEquals(0, startCal.get(Calendar.MONTH))    // Januari
        assertEquals(11, endCal.get(Calendar.MONTH))     // Desember
        assertEquals(31, endCal.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun `getCurrentMonthYear should return valid values`() {
        val (month, year) = DateUtils.getCurrentMonthYear()
        assertTrue(month in 1..12)
        assertTrue(year >= 2024)
    }

    @Test
    fun `formatRelative today should return Hari ini`() {
        val result = DateUtils.formatRelative(Date())
        assertEquals("Hari ini", result)
    }

    @Test
    fun `formatRelative yesterday should return Kemarin`() {
        val yesterday = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }.time
        val result = DateUtils.formatRelative(yesterday)
        assertEquals("Kemarin", result)
    }
}
