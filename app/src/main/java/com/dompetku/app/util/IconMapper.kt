package com.dompetku.app.util

import com.dompetku.app.R

/**
 * Mapping nama icon (string) ke resource drawable.
 * Digunakan karena Room tidak bisa simpan resource ID langsung.
 */
object IconMapper {

    // ══════════════════════════════════════
    // CATEGORY ICONS
    // ══════════════════════════════════════
    private val categoryIcons = mapOf(
        // Expense
        "ic_cat_food" to R.drawable.ic_cat_food,
        "ic_cat_transport" to R.drawable.ic_cat_transport,
        "ic_cat_shopping" to R.drawable.ic_cat_shopping,
        "ic_cat_bill" to R.drawable.ic_cat_bill,
        "ic_cat_entertainment" to R.drawable.ic_cat_entertainment,
        "ic_cat_health" to R.drawable.ic_cat_health,
        "ic_cat_education" to R.drawable.ic_cat_education,
        "ic_cat_other" to R.drawable.ic_cat_other,

        // Income
        "ic_cat_salary" to R.drawable.ic_cat_salary,
        "ic_cat_freelance" to R.drawable.ic_cat_freelance,
        "ic_cat_investment" to R.drawable.ic_cat_investment,
        "ic_cat_gift" to R.drawable.ic_cat_gift
    )

    // ══════════════════════════════════════
    // ACCOUNT ICONS
    // ══════════════════════════════════════
    private val accountIcons = mapOf(
        "ic_acc_cash" to R.drawable.ic_acc_cash,
        "ic_acc_bank" to R.drawable.ic_acc_bank,
        "ic_acc_ewallet" to R.drawable.ic_acc_ewallet
    )

    fun getCategoryIcon(iconName: String?): Int {
        return categoryIcons[iconName] ?: R.drawable.ic_cat_other
    }

    fun getAccountIcon(iconName: String?): Int {
        return accountIcons[iconName] ?: R.drawable.ic_acc_cash
    }

    fun getAllCategoryIconNames(): List<String> = categoryIcons.keys.toList()

    fun getAllAccountIconNames(): List<String> = accountIcons.keys.toList()
}