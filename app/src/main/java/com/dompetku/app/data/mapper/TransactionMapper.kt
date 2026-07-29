package com.dompetku.app.data.mapper

import com.dompetku.app.data.local.entity.TransactionEntity
import com.dompetku.app.data.local.entity.TransactionWithDetails
import com.dompetku.app.domain.model.Transaction
import com.dompetku.app.domain.model.TransactionType
import java.util.Date

// Entity → Domain
fun TransactionEntity.toDomain(): Transaction {
    return Transaction(
        id = id,
        amount = amount,
        type = TransactionType.fromString(type),
        categoryId = categoryId,
        accountId = accountId,
        note = note,
        date = Date(date),
        createdAt = Date(createdAt)
    )
}

// TransactionWithDetails → Domain
fun TransactionWithDetails.toDomain(): Transaction {
    return Transaction(
        id = id,
        amount = amount,
        type = TransactionType.fromString(type),
        categoryId = categoryId,
        categoryName = categoryName,
        categoryColor = categoryColor,
        categoryIcon = categoryIcon,
        accountId = accountId,
        accountName = accountName,
        note = note,
        date = Date(date),
        createdAt = Date(createdAt)
    )
}

// Domain → Entity
fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        amount = amount,
        type = type.name,
        categoryId = categoryId,
        accountId = accountId,
        note = note,
        date = date.time,
        createdAt = createdAt.time,
        updatedAt = System.currentTimeMillis()
    )
}
