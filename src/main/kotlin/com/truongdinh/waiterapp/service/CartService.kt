package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.dto.cart.CartDto
import com.truongdinh.waiterapp.dto.cart.CartReplaceRequest
import com.truongdinh.waiterapp.entity.CartEntity
import com.truongdinh.waiterapp.repository.CartRepository
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val messagingTemplate: SimpMessagingTemplate
) {
    fun getCarts(tableId: Int): List<CartDto> {
        return cartRepository.findByTableId(tableId).map { it.toDto() }
    }
    
    fun addItem(tableId: Int, menuItemId: Int): List<CartDto> {
        val existing = cartRepository.findByTableIdAndMenuItemId(tableId, menuItemId)
        if (existing != null) {
            cartRepository.save(existing.copy(quantity = existing.quantity + 1))
        } else {
            cartRepository.save(CartEntity(tableId = tableId, menuItemId = menuItemId, quantity = 1))
        }
        return broadcastAndReturn(tableId) 
    }
    
    fun decreaseItem(tableId: Int, menuItemId: Int): List<CartDto> {
        val existing = cartRepository.findByTableIdAndMenuItemId(tableId, menuItemId)
            ?: return getCarts(tableId)
        if (existing.quantity <= 1) {
            cartRepository.delete(existing)
        } else {
            cartRepository.save(existing.copy(quantity = existing.quantity - 1))
        }
        return broadcastAndReturn(tableId) 
    }
    
    fun deleteItem(tableId: Int, menuItemId: Int): List<CartDto> {
        cartRepository.deleteByTableIdAndMenuItemId(tableId, menuItemId)
        return broadcastAndReturn(tableId)
    }
    
    fun replaceItem(request: CartReplaceRequest): List<CartDto> {
        val oldItem = cartRepository.findByTableIdAndMenuItemId(request.tableId, request.oldMenuItemId)
        if (oldItem != null) {
            if (oldItem.quantity <= 1) {
                cartRepository.deleteByTableIdAndMenuItemId(request.tableId, request.oldMenuItemId)
            } else {
                cartRepository.save(oldItem.copy(quantity = oldItem.quantity - 1))
            }
        }
        
        val existingNew = cartRepository.findByTableIdAndMenuItemId(request.tableId, request.newMenuItemId)
        if (existingNew != null) {
            cartRepository.save(existingNew.copy(quantity = existingNew.quantity + 1))
        } else {
            cartRepository.save(
                CartEntity(
                    tableId = request.tableId, 
                    menuItemId = request.newMenuItemId, 
                    quantity = 1
                    )
            )
        }
        
        return broadcastAndReturn(request.tableId)
    }
    
    fun clearCart(tableId: Int): List<CartDto> {
        cartRepository.deleteByTableId(tableId)
        return broadcastAndReturn(tableId)
    }
    
    private fun broadcastAndReturn(tableId: Int): List<CartDto> {
        val result = getCarts(tableId)
        messagingTemplate.convertAndSend("/topic/carts/$tableId", result)
        return result
    }
    
    private fun CartEntity.toDto() = CartDto(
        tableId = tableId,
        menuItemId = menuItemId,
        quantity = quantity
    )
}