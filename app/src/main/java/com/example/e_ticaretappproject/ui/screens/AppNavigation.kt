package com.example.e_ticaretappproject.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.e_ticaretappproject.ui.viewmodels.CartScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.ProductDetailsScreenViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel,
    cartScreenViewModel: CartScreenViewModel,
    productDetailsScreenViewModel: ProductDetailsScreenViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            MainScreen(viewModel = mainScreenViewModel,
                navController = navController)
        }

        composable(
            "productDetail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductsDetailScreen(
                productId = productId,
                navController = navController,
                viewModel = mainScreenViewModel,
                cartScreenViewModel = cartScreenViewModel,
                detailsScreenViewModel = productDetailsScreenViewModel
            )
        }
        composable("favorites") { FavoritesScreen(navController, viewModel = mainScreenViewModel) }
        composable("cart") { CartScreen(viewModel = cartScreenViewModel,navController = navController) }
        //composable("profile") { CartScreen() }
    }
}
