package com.example.inventory.repository

import com.example.inventory.data.Item
import com.example.inventory.data.ItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalItemsRepository @Inject constructor(
    private val itemDao: ItemDao
) : ItemsRepository {
    override fun getAllItems(): Flow<List<Item>> = itemDao.getAllItems()

    override suspend fun insertItem(item: Item) = itemDao.insertItem(item)

    override suspend fun updateItem(item: Item) = itemDao.updateItem(item)

    override suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)
}

