package com.truongdinh.waiterapp.repository

import com.truongdinh.waiterapp.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Int>