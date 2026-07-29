package com.dompetku.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Transaction(
    val id: Long = 0,
    val amount: Double,
    val type: TransactionType,
    val categoryId: Long? = null,
    val categoryName: String? = null,
    val categoryColor: String? = null,
    val categoryIcon: String? = null,
    val accountId: Long,
    val accountName: String? = null,
    val note: String = "",
    val date: Date,
    val createdAt: Date = Date()
) : Parcelable