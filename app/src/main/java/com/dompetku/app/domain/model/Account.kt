package com.dompetku.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
    val id: Long = 0,
    val name: String,
    val type: AccountType,
    val balance: Double = 0.0,
    val icon: String,
    val color: String = "#1565C0",
    val isDefault: Boolean = false
) : Parcelable