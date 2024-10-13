package com.example.simpleroom.repo

import com.example.simpleroom.data.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)

    fun getAllUsers(): Flow<List<User>>
}