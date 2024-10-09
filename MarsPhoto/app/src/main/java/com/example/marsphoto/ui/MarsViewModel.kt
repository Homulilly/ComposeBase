package com.example.marsphoto.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphoto.data.MarsPhotosRepository
import com.example.marsphoto.MarsPhotosApplication
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState{
    data class Success(val photo: String): MarsUiState
    object Error: MarsUiState
    object Loading: MarsUiState
}

class MarsViewModel(private val marsPhotosRepository: MarsPhotosRepository): ViewModel(){
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init{
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch{
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val listResult = marsPhotosRepository.getMarsPhotos()
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (_: IOException){
                MarsUiState.Error
            } catch (_: HttpException){
                MarsUiState.Error
            }
        }
    }

    /**
     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}