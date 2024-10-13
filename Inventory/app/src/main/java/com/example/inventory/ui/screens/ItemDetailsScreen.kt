package com.example.inventory.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inventory.InventoryTitleBar
import com.example.inventory.R
import com.example.inventory.navigation.NavigationDestination

object ItemDetailsDestination : NavigationDestination{
    override val route: String = "item_detail"
    override val titleRes: Int = R.string.item_detail
    const val itemIdArgs = "itemId"
    val routeWithArgs = "$route/{$itemIdArgs}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(
    navigateUp : () -> Unit,
    onEditItemClick: (Int) -> Unit,
    viewModel: ItemDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.item.collectAsState()

    Scaffold(
        topBar = { InventoryTitleBar(
            title = "Item Details",
            canNavigateBack = true,
            navigateUp = navigateUp
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { uiState?.id?.let {
                    onEditItemClick(it)
                } },
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "item Edit"
                )
            }
        }
    ) { innerPadding ->
        uiState?.apply {
            Column(modifier = Modifier.padding(innerPadding),) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween, // 两端对齐
                        verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
                    ) {
                        Text(
                            text = "Name:",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween, // 两端对齐
                        verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
                    ) {
                        Text(
                            text = "Price:",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = "$$price",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween, // 两端对齐
                        verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
                    ) {
                        Text(
                            text = "Quantity:",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Text(
                            text = "$quantity",
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            }
                Button(
                    onClick = viewModel::sellItem,
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    enabled = quantity > 0
                ) { Text("Sell") }

                Button(
                    onClick = {
                        viewModel.deleteItem(this@apply)
                        navigateUp()
                    },
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                ) { Text("Delete") }
            }
        }
    }
}