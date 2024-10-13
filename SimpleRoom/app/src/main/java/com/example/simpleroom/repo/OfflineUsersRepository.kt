package com.example.simpleroom.repo

import com.example.simpleroom.data.User
import com.example.simpleroom.data.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineUsersRepository @Inject constructor (
    private val userDao: UserDao
) : UsersRepository{
    override suspend fun insertUser(user: User) = userDao.insertUser(user)

    override suspend fun updateUser(user: User) = userDao.updateUser(user)

    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()
}