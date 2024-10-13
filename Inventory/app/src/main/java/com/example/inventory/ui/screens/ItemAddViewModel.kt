package com.example.inventory.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemAddViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
) : ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    fun updateUiState(itemDetails: ItemDetails){
        itemUiState = ItemUiState(
            itemDetails = itemDetails,
            isSavable = validInput(itemDetails)
        )
    }

    fun saveItem(){
        val item = itemUiState.itemDetails.toItem()
        viewModelScope.launch {
            itemsRepository.insertItem(item)
        }
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

fun ItemDetails.toItem() = Item(
        id = id,
        name = name,
        price = price.toDoubleOrNull() ?: 0.0,
        quantity = quantity.toIntOrNull() ?: 0
    )

fun Item.toItemUiState(isSavable: Boolean = false): ItemUiState{
    return ItemUiState(ItemDetails(
        id = id,
        name = name,
        price = price.toString(),
        quantity = quantity.toString()
    ),
        isSavable = isSavable
    )
}