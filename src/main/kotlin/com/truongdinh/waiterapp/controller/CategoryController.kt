package com.truongdinh.waiterapp.controller

import com.truongdinh.waiterapp.dto.category.CategoryDto
import com.truongdinh.waiterapp.dto.category.CreateCategoryRequest
import com.truongdinh.waiterapp.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/categories")
class CategoryController(
    private val categoryService: CategoryService
) {
    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryDto>> {
        val result = categoryService.getAllCategories()
        return ResponseEntity.ok(result)
    }
    
    @PostMapping
    fun createCategories(@RequestBody request: CreateCategoryRequest): ResponseEntity<List<CategoryDto>> {
        val categories = categoryService.createCategories(request)
        return ResponseEntity.ok(categories)
    }
}