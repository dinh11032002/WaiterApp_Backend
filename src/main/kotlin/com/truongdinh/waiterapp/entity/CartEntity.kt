package com.truongdinh.waiterapp.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table

@Entity
@Table(name = "carts")
@IdClass(CartId::class)
data class CartEntity(
    @Id
    val tableId: Int = 0,
    @Id
    val menuItemId: Int = 0,
    val quantity: Int = 0
)
