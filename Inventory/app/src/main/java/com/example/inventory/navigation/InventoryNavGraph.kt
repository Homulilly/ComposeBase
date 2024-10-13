package com.example.inventory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventory.ui.screens.HomeDestination
import com.example.inventory.ui.screens.HomeScreen
import com.example.inventory.ui.screens.ItemAddDestination
import com.example.inventory.ui.screens.ItemAddScreen
import com.example.inventory.ui.screens.ItemDetailsDestination
import com.example.inventory.ui.screens.ItemDetailsScreen
import com.example.inventory.ui.screens.ItemEditDestination
import com.example.inventory.ui.screens.ItemEditScreen

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ){
        composable(HomeDestination.route){
            HomeScreen(
                onAddItemClicked = {
                    navController.navigate(ItemAddDestination.route)
                },
                /**
                 * to ItemDetailsScreen with item.id
                 */
                onItemClicked = {
                    navController.navigate("${ItemDetailsDestination.route}/$it" )
                }
            )
        }

        composable(ItemAddDestination.route){
            ItemAddScreen(
                navigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArgs){
                type = NavType.IntType
            })
        ){
            ItemEditScreen()
        }

        composable(route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArgs){
                type = NavType.IntType
            })){
            ItemDetailsScreen()
        }
    }
}