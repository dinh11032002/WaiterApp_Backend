package com.truongdinh.waiterapp.repository

import com.truongdinh.waiterapp.entity.TableEntity
import com.truongdinh.waiterapp.entity.TableStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TableRepository : JpaRepository<TableEntity, Int> {
    fun findByStatus(status: TableStatus): List<TableEntity> 
    @Query("SELECT * FROM tables WHERE name REGEXP CONCAT('^', :name, '([^0-9]|$)')", nativeQuery = true)
    fun findByNameContainingIgnoreCase(name: String): List<TableEntity>
}