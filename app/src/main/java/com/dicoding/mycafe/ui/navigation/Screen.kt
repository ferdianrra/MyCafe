package com.dicoding.mycafe.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Cart: Screen("cart")
    object History: Screen("history")
    object DetailMenu: Screen("detail/{menuId}") {
        fun createRoute(menuId: Int) = "detail/$menuId"
    }
}