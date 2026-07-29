package com.dompetku.app.domain.model

enum class AccountType {
    CASH,
    BANK,
    E_WALLET;

    companion object {
        fun fromString(value: String): AccountType {
            return when (value.uppercase()) {
                "CASH" -> CASH
                "BANK" -> BANK
                "E_WALLET" -> E_WALLET
                else -> CASH
            }
        }
    }

    fun toDisplayName(): String {
        return when (this) {
            CASH -> "Tunai"
            BANK -> "Bank"
            E_WALLET -> "E-Wallet"
        }
    }
}