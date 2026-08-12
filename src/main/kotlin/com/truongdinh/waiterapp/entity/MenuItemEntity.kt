package com.truongdinh.waiterapp.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "menu_items")
data class MenuItemEntity(
    @Id
    val id: Int = 0,
    val name: String = "",
    val price: Long = 0L,
    val image: String = "",
    val categoryId: Int = 0,
    val isAvailable: Boolean = true
)
