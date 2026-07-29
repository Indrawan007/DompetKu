package com.dompetku.app.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatter {

    private val localeID = Locale("id", "ID")

    /**
     * Format angka ke format Rupiah
     * Contoh: 50000.0 → "Rp50.000"
     */
    fun format(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(localeID)
        formatter.maximumFractionDigits = 0
        return formatter.format(amount)
    }

    /**
     * Format tanpa simbol mata uang
     * Contoh: 50000.0 → "50.000"
     */
    fun formatWithoutSymbol(amount: Double): String {
        val formatter = DecimalFormat("#,###")
        return formatter.format(amount)
    }

    /**
     * Format dengan tanda + atau -
     * Contoh: 50000.0, INCOME → "+Rp50.000"
     * Contoh: 50000.0, EXPENSE → "-Rp50.000"
     */
    fun formatSigned(amount: Double, isIncome: Boolean): String {
        val prefix = if (isIncome) "+" else "-"
        return "$prefix${format(amount)}"
    }

    /**
     * Parse string ke double
     * Contoh: "50.000" → 50000.0
     */
    fun parse(text: String): Double {
        val cleaned = text.replace("[^\\d]".toRegex(), "")
        return cleaned.toDoubleOrNull() ?: 0.0
    }
}