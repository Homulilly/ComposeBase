package com.example.inventory.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class InventoryDatabase : RoomDatabase(){
    abstract fun itemDao(): ItemDao
}