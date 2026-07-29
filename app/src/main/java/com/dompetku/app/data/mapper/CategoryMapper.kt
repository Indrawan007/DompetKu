package com.dompetku.app.data.mapper

import com.dompetku.app.data.local.entity.CategoryEntity
import com.dompetku.app.domain.model.Category
import com.dompetku.app.domain.model.TransactionType

// Entity → Domain
fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = icon,
        color = color,
        type = TransactionType.fromString(type),
        isDefault = isDefault
    )
}

// Domain → Entity
fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        icon = icon,
        color = color,
        type = type.name,
        isDefault = isDefault
    )
}
