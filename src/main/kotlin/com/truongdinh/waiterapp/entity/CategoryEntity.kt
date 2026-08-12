package com.truongdinh.waiterapp.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "categories")
data class CategoryEntity(
    @Id
    val id: Int = 0,
    val name: String = ""
)
