package com.example.simpleroom.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.simpleroom.data.User
import com.example.simpleroom.repo.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val usersRepository: UsersRepository
): ViewModel() {
    var uiState by mutableStateOf(UserUiState())

    suspend fun addUser(){
        usersRepository.insertUser(uiState.user)
        uiState = UserUiState()
    }

    fun updateUserUiState(user: User){
        uiState = UserUiState(
            user,
            isButtonEnable = isValidInput(user)
        )
    }

    private fun isValidInput(user: User): Boolean {
        return user.name.isNotEmpty() && user.bio.isNotEmpty()
    }
}

data class UserUiState (
    val user: User = User(
        name = "",
        bio = ""
    ),
    val isButtonEnable: Boolean = false
)