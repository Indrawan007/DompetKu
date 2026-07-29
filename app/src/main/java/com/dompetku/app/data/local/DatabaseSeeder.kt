package com.dompetku.app.data.local

import com.dompetku.app.data.local.entity.AccountEntity
import com.dompetku.app.data.local.entity.CategoryEntity

object DatabaseSeeder {

    fun getDefaultExpenseCategories(): List<CategoryEntity> = listOf(
        CategoryEntity(name = "Makanan & Minuman", icon = "ic_cat_food", color = "#FF5722", type = "EXPENSE", isDefault = true),
        CategoryEntity(name = "Transportasi", icon = "ic_cat_transport", color = "#2196F3", type = "EXPENSE", isDefault = true),
        CategoryEntity(name = "Belanja", icon = "ic_cat_shopping", color = "#E91E63", type = "EXPENSE", isDefault = true),
        CategoryEntity(name = "Tagihan & Utilitas", icon = "ic_cat_bill", color = "#FF9800", type = "EXPENSE", isDefault = true),
        CategoryEntity(name = "Hiburan", icon = "ic_cat_entertainment", color = "#9C27B0", type = "EXPENSE", isDefault = true),
        CategoryEntity(name = "Kesehatan", icon = "ic_cat_health", color = "#4CAF50", type = "EXPENSE", isDefault = true),
        CategoryEntity(name = "Pendidikan", icon = "ic_cat_education", color = "#3F51B5", type = "EXPENSE", isDefault = true),
        CategoryEntity(name = "Lainnya", icon = "ic_cat_other", color = "#607D8B", type = "EXPENSE", isDefault = true)
    )

    fun getDefaultIncomeCategories(): List<CategoryEntity> = listOf(
        CategoryEntity(name = "Gaji", icon = "ic_cat_salary", color = "#4CAF50", type = "INCOME", isDefault = true),
        CategoryEntity(name = "Freelance", icon = "ic_cat_freelance", color = "#00BCD4", type = "INCOME", isDefault = true),
        CategoryEntity(name = "Investasi", icon = "ic_cat_investment", color = "#FF9800", type = "INCOME", isDefault = true),
        CategoryEntity(name = "Hadiah", icon = "ic_cat_gift", color = "#E91E63", type = "INCOME", isDefault = true),
        CategoryEntity(name = "Lainnya", icon = "ic_cat_other", color = "#607D8B", type = "INCOME", isDefault = true)
    )

    fun getDefaultAccounts(): List<AccountEntity> = listOf(
        AccountEntity(name = "Tunai", type = "CASH", icon = "ic_acc_cash", color = "#4CAF50", isDefault = true),
        AccountEntity(name = "Bank", type = "BANK", icon = "ic_acc_bank", color = "#1565C0", isDefault = false),
        AccountEntity(name = "E-Wallet", type = "E_WALLET", icon = "ic_acc_ewallet", color = "#FF6F00", isDefault = false)
    )

    fun getAllDefaultCategories(): List<CategoryEntity> {
        return getDefaultExpenseCategories() + getDefaultIncomeCategories()
    }
}
