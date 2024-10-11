package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.amphibians.data.AmphibianRepository
import com.example.amphibians.model.Amphibian
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

sealed interface AmpUiState {
    data class Success(val amphibiansList: List<Amphibian>) : AmpUiState
    object Loading: AmpUiState
    object Error: AmpUiState
}

@HiltViewModel
class AmphibianViewModel @Inject constructor(
    private val amphibianRepository : AmphibianRepository
) : ViewModel(){
//    private val _uiState = MutableStateFlow(AmpUiState.Loading)
//    var uiState = _uiState.asStateFlow()

    var ampUiState : AmpUiState by mutableStateOf(AmpUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    private fun getAmphibians(){
        viewModelScope.launch{
            ampUiState = AmpUiState.Loading
            ampUiState = try {
                val listResult = amphibianRepository.getAmphibians()
                AmpUiState.Success(listResult)
            } catch ( _: IOException ) {
                AmpUiState.Error
            } catch ( _: HttpException){
                AmpUiState.Error
            }
        }
    }
}