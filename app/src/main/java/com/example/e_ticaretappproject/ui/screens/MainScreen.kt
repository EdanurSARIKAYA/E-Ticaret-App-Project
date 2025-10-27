package com.example.e_ticaretappproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_ticaretappproject.ui.components.CustomBottomBar
import com.example.e_ticaretappproject.ui.components.CustomProduct
import com.example.e_ticaretappproject.ui.components.CustomTopAppBar
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel,navController: NavController){
    val products = viewModel.filteredProductsList.observeAsState(listOf())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
            CustomTopAppBar(
                title = "Hoş Geldiniz",
                showSearch = true,
                showCart = true,
                onCartClick = { /* Sepet sayfasına yönlendirme */ },
                onSearchChange = { viewModel.searchProducts(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products.value) { product ->
                    CustomProduct(products = product,
                        onProductClick = {
                            navController.navigate("productDetail/${product.id}")
                        },
                        onFavoriteToggle = {
                            viewModel.toggleFavorite(it.id)
                        })
                }
            }
        }
}







