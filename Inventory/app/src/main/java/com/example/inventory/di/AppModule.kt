package com.example.inventory.di

import android.content.Context
import androidx.room.Room
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.repository.ItemsRepository
import com.example.inventory.repository.LocalItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule{
    @Binds
    @Singleton
    abstract fun bindsItemsRepository(
        localItemsRepository: LocalItemsRepository
    ): ItemsRepository

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): InventoryDatabase =
            Room.databaseBuilder(
                context,
                InventoryDatabase::class.java,
                "items_database"
            ).build()

        @Provides
        @Singleton
        fun provideItemDao(database: InventoryDatabase) = database.itemDao()
    }
}
