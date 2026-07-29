package com.dompetku.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Long = 0,
    val name: String,
    val icon: String,
    val color: String,
    val type: TransactionType,
    val isDefault: Boolean = false
) : Parcelable