package com.example.inventory.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemEditViewModel @Inject constructor (
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
): ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArgs])

    init {
        viewModelScope.launch {
            itemUiState = itemsRepository.getItemById(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(true)
        }
    }
    fun updateUiState(itemDetails: ItemDetails){
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isSavable = validateInput(itemDetails))
    }

    suspend fun saveItem() = itemsRepository.updateItem(
        itemUiState.itemDetails.toItem()
    )

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}