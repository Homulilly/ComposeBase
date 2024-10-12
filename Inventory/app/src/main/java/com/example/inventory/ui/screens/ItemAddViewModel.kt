package com.example.inventory.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ItemAddViewModel : ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    fun updateUiState(itemDetails: ItemDetails){
        itemUiState = ItemUiState(
            itemDetails = itemDetails,
            isSavable = validInput(itemDetails)
        )
    }

    suspend fun saveItem(itemDetails: ItemDetails){

    }

    private fun validInput(uiState: ItemDetails = itemUiState.itemDetails) : Boolean{
        return with(uiState){
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

data class ItemUiState (
    val itemDetails: ItemDetails = ItemDetails(),
    val isSavable: Boolean = false
)


data class ItemDetails(
    val id : Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = ""
)