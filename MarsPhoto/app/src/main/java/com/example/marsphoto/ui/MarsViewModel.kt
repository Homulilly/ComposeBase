package com.example.marsphoto.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphoto.data.MarsPhotosRepository
import com.example.marsphoto.network.MarsPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface MarsUiState{
    data class Success(val photo: MarsPhoto): MarsUiState
    object Error: MarsUiState
    object Loading: MarsUiState
}

@HiltViewModel
class MarsViewModel @Inject constructor(
    private val marsPhotosRepository: MarsPhotosRepository
) : ViewModel(){
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    init{
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch{
            marsUiState = MarsUiState.Loading
            marsUiState = try {
                val img = marsPhotosRepository.getMarsPhotos()[0]
                MarsUiState.Success(
                    img
                )
            } catch (_: IOException){
                MarsUiState.Error
            } catch (_: HttpException){
                MarsUiState.Error
            }
        }
    }
}