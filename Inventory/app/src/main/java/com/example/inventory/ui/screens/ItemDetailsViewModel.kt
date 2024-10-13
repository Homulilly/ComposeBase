package com.example.inventory.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.repository.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ItemDetailsScreen
 * Show item with id -> get item with id
 * SELL Button
 * EDIT Button(Float Action Button) -> Go to ItemEditScreen
 * DELETE Button
 */
@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    savedStateHandler: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel(){
    private val _item = MutableStateFlow<Item?>(null)
    val item: StateFlow<Item?> = _item.asStateFlow()

    init {
        val itemId: Int? = savedStateHandler["itemId"]
        itemId?.let {
            viewModelScope.launch{
                itemsRepository.getItemById(it).collect { fetchItem ->
                    _item.value = fetchItem
                }
            }
        }
    }
}