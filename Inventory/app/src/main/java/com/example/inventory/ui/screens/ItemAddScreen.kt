package com.example.inventory.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.inventory.InventoryTitleBar
import com.example.inventory.R
import com.example.inventory.navigation.NavigationDestination

object ItemAddDestination : NavigationDestination{
    override val route: String = "item_add"

    override val titleRes: Int = R.string.add_item
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemAddScreen(
    canNavigateBack: Boolean = true,
    navigateUp : () -> Unit,
    modifier : Modifier = Modifier
){
    val viewModel = ItemAddViewModel()

    Scaffold(
        topBar = {
            InventoryTitleBar(
            title = stringResource(ItemAddDestination.titleRes),
            modifier = modifier,
            canNavigateBack = canNavigateBack,
            navigateUp = navigateUp
        )}
    ) {
        Column(
            modifier = modifier.padding(it)
        ){
            ItemEntry(
                viewModel.itemUiState.itemDetails,
                onValueChange = viewModel::updateUiState
            )

            Button(
                onClick = { },
                enabled = viewModel.itemUiState.isSavable,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )  { Text("Save") }
        }
    }
}

@Composable
fun ItemEntry(
    itemDetails: ItemDetails,
    onValueChange : (ItemDetails) -> Unit = { },
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = itemDetails.name,
            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.item_name))},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = itemDetails.price,
            onValueChange = { onValueChange(itemDetails.copy(price = it)) },
            label = { Text(stringResource(R.string.item_price))},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        )
        OutlinedTextField(
            value = itemDetails.quantity,
            onValueChange = { onValueChange(itemDetails.copy(quantity = it)) },
            label = { Text(stringResource(R.string.item_quantity))},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

