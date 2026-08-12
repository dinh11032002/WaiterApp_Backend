package com.truongdinh.waiterapp.controller

import com.truongdinh.waiterapp.dto.cart.CartActionRequest
import com.truongdinh.waiterapp.dto.cart.CartDto
import com.truongdinh.waiterapp.dto.cart.CartReplaceRequest
import com.truongdinh.waiterapp.service.CartService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/carts")
class CartController(
    private val cartService: CartService
) {
    @GetMapping
    fun getCarts(@RequestParam tableId: Int): ResponseEntity<List<CartDto>> {
        return ResponseEntity.ok(cartService.getCarts(tableId))
    }
    
    @PostMapping("/add")
    fun addItem(@RequestBody request: CartActionRequest): ResponseEntity<List<CartDto>> {
        return ResponseEntity.ok(cartService.addItem(request.tableId, request.menuItemId))
    }
    
    @PostMapping("/decrease")
    fun decreaseItem(@RequestBody request: CartActionRequest): ResponseEntity<List<CartDto>> {
        return ResponseEntity.ok(cartService.decreaseItem(request.tableId, request.menuItemId))
    }
    
    @DeleteMapping
    fun deleteItem(
        @RequestParam tableId: Int,
        @RequestParam menuItemId: Int
    ): ResponseEntity<List<CartDto>> {
        return ResponseEntity.ok(cartService.deleteItem(tableId, menuItemId))
    }
    
    @PostMapping("/replace")
    fun replaceItem(@RequestBody request: CartReplaceRequest): ResponseEntity<List<CartDto>> {
        return ResponseEntity.ok(cartService.replaceItem(request))
    }
    
    @DeleteMapping("/clear")
    fun clearCart(@RequestParam tableId: Int): ResponseEntity<List<CartDto>> {
        return ResponseEntity.ok(cartService.clearCart(tableId))
    }
}