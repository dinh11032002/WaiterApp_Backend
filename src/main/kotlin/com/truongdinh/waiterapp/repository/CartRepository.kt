package com.truongdinh.waiterapp.repository

import com.truongdinh.waiterapp.entity.CartEntity
import com.truongdinh.waiterapp.entity.CartId
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying

interface CartRepository : JpaRepository<CartEntity, CartId> {
    fun findByTableId(tableId: Int): List<CartEntity>
    fun findByTableIdAndMenuItemId(tableId: Int, menuItemId: Int): CartEntity?
    @Modifying
    @Transactional
    fun deleteByTableIdAndMenuItemId(tableId: Int, menuItemId: Int)
    @Modifying
    @Transactional
    fun deleteByTableId(tableId: Int)
}