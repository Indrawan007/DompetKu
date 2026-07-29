package com.dompetku.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Budget(
    val id: Long = 0,
    val categoryId: Long,
    val categoryName: String = "",
    val categoryColor: String = "",
    val categoryIcon: String = "",
    val amountLimit: Double,
    val spentAmount: Double = 0.0,
    val month: Int,
    val year: Int
) : Parcelable {

    val remainingAmount: Double
        get() = amountLimit - spentAmount

    val percentage: Float
        get() = if (amountLimit > 0) {
            ((spentAmount / amountLimit) * 100).toFloat()
        } else 0f

    val isOverBudget: Boolean
        get() = spentAmount > amountLimit

    val isNearLimit: Boolean
        get() = percentage >= 80f && !isOverBudget

    val statusColor: String
        get() = when {
            isOverBudget -> "#F44336"  // Red
            isNearLimit -> "#FF9800"    // Orange
            else -> "#4CAF50"           // Green
        }
}