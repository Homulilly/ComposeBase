package com.example.inventory.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.inventory.InventoryTitleBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.navigation.NavigationDestination

object HomeDestination : NavigationDestination{
    override val route  = "home"
    override val titleRes  = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAddItemClicked: () -> Unit,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
){
    val homeUiState by viewModel.homeUiState.collectAsState()
    Log.d("HomeScreen", "HomeUiState: $homeUiState")
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
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
        Homebody(
            itemList = homeUiState.itemsList,
            onItemClicked = onItemClicked,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        )
    }
}

@Composable
fun Homebody(
    itemList : List<Item>,
    onItemClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    if (itemList.isEmpty()){
        Text("Nothing is Here")
    } else {
        LazyColumn(
            modifier = modifier.padding(8.dp)
        ) {
            items(itemList, key={ it.id }) { item ->
                HomeItem(
                    item,
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}

@Composable
fun HomeItem(
    item: Item,
    onItemClicked: (Int) -> Unit,
    modifier : Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClicked(item.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp), // 添加内边距，使内容不贴边
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween, // 两端对齐
                verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis // 超出部分省略
                )

                Text(
                    text = "$${item.price}", // 格式化价格（根据需求调整）
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = "Quantity: ${item.quantity}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}