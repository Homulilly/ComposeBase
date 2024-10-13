package com.example.inventory.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.inventory.R
import com.example.inventory.navigation.NavigationDestination

object ItemDetailsDestination : NavigationDestination{
    override val route: String = "item_detail"
    override val titleRes: Int = R.string.item_detail
    const val itemIdArgs = "itemId"
    val routeWithArgs = "$route/{$itemIdArgs}"
}

@Composable
fun ItemDetailsScreen() {
    Text("This is ItemDetails Screen")
}