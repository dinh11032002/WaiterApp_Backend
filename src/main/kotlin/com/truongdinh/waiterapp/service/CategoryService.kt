package com.truongdinh.waiterapp.service

import com.truongdinh.waiterapp.dto.category.CategoryDto
import com.truongdinh.waiterapp.dto.category.CreateCategoryRequest
import com.truongdinh.waiterapp.entity.CategoryEntity
import com.truongdinh.waiterapp.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun getAllCategories(): List<CategoryDto> {
        return categoryRepository.findAll().map { it.toDto() }
    }
    
    fun createCategories(request: CreateCategoryRequest): List<CategoryDto> {
        val categories = request.categories.map { categories  ->
            CategoryEntity(
                id = categories.id,
                name = categories.name
            )
        }
        val saved = categoryRepository.saveAll(categories)
        return saved.map {
            it.toDto()
        }
    }
    
    private fun CategoryEntity.toDto() = CategoryDto(
        id = id,
        name = name
    )
}