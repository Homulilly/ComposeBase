package com.example.inventory.ui.screens

import androidx.compose.runtime.Composable
import com.example.inventory.R
import com.example.inventory.navigation.NavigationDestination

object ItemEditDestination : NavigationDestination{
    override val route: String = "item_edit"
    override val titleRes: Int = R.string.edit_item
    const val itemIdArgs = "itemId"
    val routeWithArgs = "$route/{$itemIdArgs}"
}

@Composable
fun ItemEditScreen(){

}