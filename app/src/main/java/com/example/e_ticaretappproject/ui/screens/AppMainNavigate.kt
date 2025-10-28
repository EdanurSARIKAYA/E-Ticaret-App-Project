package com.example.e_ticaretappproject.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.e_ticaretappproject.ui.components.CustomBottomBar
import com.example.e_ticaretappproject.ui.viewmodels.CartScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.ProductDetailsScreenViewModel

@Composable
fun AppMainNavigate(
    mainScreenViewModel: MainScreenViewModel,
    productDetailsScreenViewModel: ProductDetailsScreenViewModel,
    cartScreenViewModel: CartScreenViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { CustomBottomBar(navController) }
    ) { innerPadding ->
        AppNavigation(
            navController = navController,
            mainScreenViewModel = mainScreenViewModel,
            cartScreenViewModel= cartScreenViewModel,
            productDetailsScreenViewModel= productDetailsScreenViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}