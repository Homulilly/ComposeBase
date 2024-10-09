package com.example.marsphoto.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphoto.network.MarsApi
import kotlinx.coroutines.launch

class MarsViewModel: ViewModel(){
    var marsUiState: String by mutableStateOf("")
        private set

    init{
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch{
            val listResult = MarsApi.retrofitService.getPhotos()
            marsUiState = "There are ${listResult.size} MarPhotos"
        }
    }
}