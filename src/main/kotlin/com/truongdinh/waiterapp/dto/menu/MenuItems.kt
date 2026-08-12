package com.truongdinh.waiterapp.dto.menu

data class MenuItems(
    val id: Int,
    val name: String,
    val price: Long,
    val image: String,
    val categoryId: Int,
    val isAvailable: Boolean
)
