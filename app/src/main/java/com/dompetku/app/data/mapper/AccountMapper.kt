package com.dompetku.app.data.mapper

import com.dompetku.app.data.local.entity.AccountEntity
import com.dompetku.app.domain.model.Account
import com.dompetku.app.domain.model.AccountType

// Entity → Domain
fun AccountEntity.toDomain(): Account {
    return Account(
        id = id,
        name = name,
        type = AccountType.fromString(type),
        balance = balance,
        icon = icon,
        color = color,
        isDefault = isDefault
    )
}

// Domain → Entity
fun Account.toEntity(): AccountEntity {
    return AccountEntity(
        id = id,
        name = name,
        type = type.name,
        balance = balance,
        icon = icon,
        color = color,
        isDefault = isDefault
    )
}
