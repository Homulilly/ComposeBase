package com.example.marsphoto.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel

class MarsViewModel: ViewModel(){
    var marsUiState: String by mutableStateOf("")
        private set

    init{
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        marsUiState = "Get Photos with Mars Api"
    }
}