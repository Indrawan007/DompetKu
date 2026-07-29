package com.dompetku.app.data.mapper

import com.dompetku.app.data.local.entity.BudgetEntity
import com.dompetku.app.data.local.entity.BudgetWithSpent
import com.dompetku.app.domain.model.Budget

// Entity → Domain
fun BudgetEntity.toDomain(): Budget {
    return Budget(
        id = id,
        categoryId = categoryId,
        amountLimit = amountLimit,
        month = month,
        year = year
    )
}

// BudgetWithSpent → Domain
fun BudgetWithSpent.toDomain(): Budget {
    return Budget(
        id = id,
        categoryId = categoryId,
        categoryName = categoryName,
        categoryColor = categoryColor,
        categoryIcon = categoryIcon,
        amountLimit = amountLimit,
        spentAmount = spentAmount,
        month = month,
        year = year
    )
}

// Domain → Entity
fun Budget.toEntity(): BudgetEntity {
    return BudgetEntity(
        id = id,
        categoryId = categoryId,
        amountLimit = amountLimit,
        month = month,
        year = year
    )
}
