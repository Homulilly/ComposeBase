package com.example.inventory.repository

import com.example.inventory.data.Item
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    fun getAllItems(): Flow<List<Item>>

    fun getItemById(itemId: Int): Flow<Item>

    suspend fun insertItem(item: Item)

    suspend fun updateItem(item: Item)

    suspend fun deleteItem(item: Item)
}