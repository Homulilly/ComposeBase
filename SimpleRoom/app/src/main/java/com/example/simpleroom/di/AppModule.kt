package com.example.simpleroom.di

import android.content.Context
import androidx.room.Room
import com.example.simpleroom.data.AppDatabase
import com.example.simpleroom.data.UserDao
import com.example.simpleroom.repo.OfflineUsersRepository
import com.example.simpleroom.repo.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        offlineUsersRepository: OfflineUsersRepository
    ) : UsersRepository

    companion object {
        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase =
           Room.databaseBuilder(
               context,
               AppDatabase::class.java,
               "app_database"
           ).build()

        @Provides
        fun provideUserDao(database: AppDatabase): UserDao = database.userDao()
    }
}