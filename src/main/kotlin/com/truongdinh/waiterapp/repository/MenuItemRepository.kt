package com.truongdinh.waiterapp.repository

import com.truongdinh.waiterapp.entity.MenuItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MenuItemRepository : JpaRepository<MenuItemEntity, Int> {
    fun findByCategoryId(categoryId: Int): List<MenuItemEntity>
    
    @Query(
        value = "SELECT * FROM menu_items WHERE LOWER(name) = LOWER(:name) OR LOWER(name) LIKE LOWER(CONCAT(:name, '%'))",
        nativeQuery = true
    )
    fun searchByName(name: String): List<MenuItemEntity>
}