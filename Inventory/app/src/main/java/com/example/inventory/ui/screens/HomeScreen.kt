package com.example.inventory.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.inventory.InventoryTitleBar
import com.example.inventory.R
import com.example.inventory.navigation.NavigationDestination

object HomeDestination : NavigationDestination{
    override val route  = "home"
    override val titleRes  = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
//    onItemClicked: (Int) -> Unit,
    onAddItemClicked: () -> Unit,
    modifier: Modifier = Modifier,
){
    Scaffold(
        topBar = {InventoryTitleBar(
            title = stringResource(HomeDestination.titleRes),
            canNavigateBack = false,
            modifier = modifier,
        )} ,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddItemClicked
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "item add"
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier.padding(innerPadding)
        ){
            Text("This Home Screen")
        }
    }
}