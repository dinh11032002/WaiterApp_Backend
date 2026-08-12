package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.dto.cart.CartReplaceRequest
import com.truongdinh.waiterapp.entity.CartEntity
import com.truongdinh.waiterapp.repository.CartRepository
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.messaging.simp.SimpMessagingTemplate
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class CartServiceTest {

    @Mock
    lateinit var cartRepository: CartRepository

    @Mock
    lateinit var messagingTemplate: SimpMessagingTemplate

    @Test
    fun `addItem tao moi khi chua ton tai`() {
        val cartService = CartService(cartRepository, messagingTemplate)

        `when`(cartRepository.findByTableIdAndMenuItemId(1, 2))
            .thenReturn(null)

        `when`(cartRepository.findByTableId(1))
            .thenReturn(listOf(CartEntity(tableId = 1, menuItemId = 2, quantity = 1)))

        val result = cartService.addItem(1, 2)

        assertEquals(1, result.size)
        assertEquals(1, result[0].quantity)
    }

    @Test
    fun `addItem tang so luong khi da ton tai`() {
        val cartService = CartService(cartRepository, messagingTemplate)

        val existing = CartEntity(tableId = 1, menuItemId = 2, quantity = 1)
        `when`(cartRepository.findByTableIdAndMenuItemId(1, 2))
            .thenReturn(existing)

        `when`(cartRepository.findByTableId(1))
            .thenReturn(listOf(existing.copy(quantity = 2)))

        val result = cartService.addItem(1, 2)

        assertEquals(2, result[0].quantity)
    }

    @Test
    fun `decreaseItem xoa item khi quantity la 1`() {
        val cartService = CartService(cartRepository, messagingTemplate)

        val existing = CartEntity(tableId = 1, menuItemId = 2, quantity = 1)
        `when`(cartRepository.findByTableIdAndMenuItemId(1, 2))
            .thenReturn(existing)

        `when`(cartRepository.findByTableId(1))
            .thenReturn(emptyList())

        val result = cartService.decreaseItem(1, 2)

        assertEquals(0, result.size)
    }

    @Test
    fun `decreaseItem giam so luong khi quantity lon hon 1`() {
        val cartService = CartService(cartRepository, messagingTemplate)

        val existing = CartEntity(tableId = 1, menuItemId = 2, quantity = 3)
        `when`(cartRepository.findByTableIdAndMenuItemId(1, 2))
            .thenReturn(existing)
            
        val updatedItem = existing.copy(quantity = 2)
        `when`(cartRepository.save(any(CartEntity::class.java))).thenReturn(updatedItem)

        `when`(cartRepository.findByTableId(1))
            .thenReturn(listOf(updatedItem))

        val result = cartService.decreaseItem(1, 2)

        assertEquals(2, result[0].quantity)
    }

    @Test
    fun `decreaseItem tra ve gio hang hien tai khi item khong ton tai`() {
        val cartService = CartService(cartRepository, messagingTemplate)

        `when`(cartRepository.findByTableIdAndMenuItemId(1, 2))
            .thenReturn(null)

        `when`(cartRepository.findByTableId(1))
            .thenReturn(emptyList())

        val result = cartService.decreaseItem(1, 2)

        assertEquals(0, result.size)
    }

    @Test
    fun `replaceItem thay the mon cu bang mon moi`() {
        val cartService = CartService(cartRepository, messagingTemplate)

        val oldItem = CartEntity(tableId = 1, menuItemId = 2, quantity = 1)
        `when`(cartRepository.findByTableIdAndMenuItemId(1, 2))
            .thenReturn(oldItem)

        `when`(cartRepository.findByTableIdAndMenuItemId(1, 3))
            .thenReturn(null)

        `when`(cartRepository.findByTableId(1))
            .thenReturn(listOf(CartEntity(tableId = 1, menuItemId = 3, quantity = 1)))

        val result = cartService.replaceItem(
            CartReplaceRequest(tableId = 1, oldMenuItemId = 2, newMenuItemId = 3)
        )

        assertEquals(1, result.size)
        assertEquals(3, result[0].menuItemId)
    }
}