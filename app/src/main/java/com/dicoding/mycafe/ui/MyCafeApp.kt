package com.dicoding.mycafe.ui

import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.mycafe.ui.cart.CartScreen
import com.dicoding.mycafe.ui.component.AppBar
import com.dicoding.mycafe.ui.component.BottomBar
import com.dicoding.mycafe.ui.history.HistoryScreen
import com.dicoding.mycafe.ui.home.HomeScreen
import com.dicoding.mycafe.ui.navigation.Screen

@Composable
fun MyCafeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            if(currentRoute == Screen.Home.route) {
                AppBar(title = "Menu")
            } else if(currentRoute == Screen.Cart.route) {
                AppBar(title = "Cart")
            } else if(currentRoute == Screen.History.route) {
                AppBar(title = "Activity")
            }
                 },
        bottomBar = {
            if (currentRoute != Screen.DetailMenu.route) {
                BottomBar(navController = navController)
            }
        }
    ){innerPadding ->
        NavHost(
            modifier = modifier.padding(top = innerPadding.calculateTopPadding()/2, bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    paddingValues = innerPadding,
                    navigateToDetail = {id ->
                    navController.navigate(Screen.DetailMenu.createRoute(id))
                })
            }

            composable(Screen.Cart.route) {
                CartScreen()
            }

            composable(Screen.History.route) {
                HistoryScreen()
            }
        }
    }
}