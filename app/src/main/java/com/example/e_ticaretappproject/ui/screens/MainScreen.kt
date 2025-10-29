package com.example.e_ticaretappproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_ticaretappproject.ui.components.CustomProduct
import com.example.e_ticaretappproject.ui.components.CustomTopAppBar
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.example.e_ticaretappproject.R

@Composable
fun MainScreen(viewModel: MainScreenViewModel,navController: NavController) {
    val allProducts = viewModel.productsList.observeAsState(listOf())
    val products = viewModel.filteredProductsList.observeAsState(listOf())

    var orderExpanded by remember { mutableStateOf(false) }
    var filterExpanded by remember { mutableStateOf(false) }

    val categories = remember(allProducts.value) {
        allProducts.value.mapNotNull { it.kategori }.toSet().toList()
    }
    val brands =
        remember(allProducts.value) { allProducts.value.mapNotNull { it.marka }.toSet().toList() }

    val activeCategories =
        remember(products.value) { products.value.mapNotNull { it.kategori }.toSet() }
    val activeBrands = remember(products.value) { products.value.mapNotNull { it.marka }.toSet() }

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var selectedBrands by remember { mutableStateOf(setOf<String>()) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomTopAppBar(
                title = "HOŞ GELDİNİZ",
                showSearch = true,
                showCart = true,
                onCartClick = { navController.navigate("cart") },
                onSearchChange = { viewModel.searchProducts(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    Button(
                        onClick = { orderExpanded = true },
                        modifier = Modifier
                            .height(56.dp)
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.red2),
                        )
                    ) {
                        Text("Sırala", fontSize = 20.sp)
                    }

                    DropdownMenu(
                        expanded = orderExpanded,
                        onDismissRequest = { orderExpanded = false },
                        modifier = Modifier
                            .background(colorResource(R.color.white))
                            .padding(16.dp)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(width = 3.dp, color = colorResource(R.color.pink))
                                        .padding(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = null,
                                        tint = colorResource(R.color.red2)
                                    )
                                    Text(
                                        text = " En Düşük Fiyat",
                                        color = colorResource(R.color.red2),
                                        fontSize = 20.sp
                                    )

                                }
                            },
                            onClick = {
                                viewModel.sortProductsByPriceAscending()
                                orderExpanded = false
                            },
                            modifier = Modifier
                                .background(colorResource(R.color.white))
                                .padding(8.dp)
                        )
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(width = 3.dp, color = colorResource(R.color.pink))
                                        .padding(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = null,
                                        tint = colorResource(R.color.red2)
                                    )
                                    Text(
                                        text = " En Yüksek Fiyat",
                                        color = colorResource(R.color.red2),
                                        fontSize = 20.sp
                                    )

                                }
                            },
                            onClick = {
                                viewModel.sortProductsByPriceDescending()
                                orderExpanded = false
                            },
                            modifier = Modifier
                                .background(colorResource(R.color.white))
                                .padding(8.dp)
                        )
                    }
                }

                Button(
                    onClick = { filterExpanded = true },
                    modifier = Modifier
                        .height(56.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.red2),
                    )
                ) {
                    Text("Filtrele", fontSize = 20.sp)
                }
            }
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
                    CustomProduct(
                        products = product,
                        onProductClick = {
                            navController.navigate("productDetail/${product.id}")
                        },
                        onFavoriteToggle = {
                            viewModel.toggleFavorite(it.id)
                        })
                }
            }
        }
        if (filterExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white).copy(alpha = 0.75f)),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.9f)
                        .border(4.dp, colorResource(R.color.red), shape = RoundedCornerShape(16.dp))
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column {
                        Text(
                            "Kategori Seçin",
                            color = colorResource(R.color.red),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(8.dp)
                        )

                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(categories) { category ->
                                val isAvailable = activeCategories.contains(category)
                                val isChecked = selectedCategories.contains(category)
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, colorResource(R.color.pink))
                                    .padding(8.dp)) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = {
                                            selectedCategories = if (it)
                                                selectedCategories + category
                                            else
                                                selectedCategories - category
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = colorResource(R.color.red),
                                            uncheckedColor = Color.Gray,
                                            checkmarkColor = Color.White
                                        ),
                                        enabled = isAvailable
                                    )
                                    Text(category)
                                }
                            }
                        }

                        Text(
                            "Marka Seçin",
                            color = colorResource(R.color.red),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(8.dp)
                        )

                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(brands) { brand ->
                                val isAvailable = activeBrands.contains(brand)
                                val isChecked = selectedBrands.contains(brand)
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, colorResource(R.color.pink))
                                    .padding(8.dp)) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = {
                                            selectedBrands = if (it)
                                                selectedBrands + brand
                                            else
                                                selectedBrands - brand
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = colorResource(R.color.red),
                                            uncheckedColor = Color.Gray,
                                            checkmarkColor = Color.White
                                        ),
                                        enabled = isAvailable
                                    )
                                    Text(brand)
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Button(
                                onClick = {
                                    selectedCategories = emptySet()
                                    selectedBrands = emptySet()
                                    viewModel.applyFilters(emptySet(), emptySet())
                                },
                                modifier = Modifier.weight(1f)
                                        .height(56.dp)
                                    .padding(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(R.color.red),
                                )
                            ) {
                                Text("Tümünü Temizle")
                            }

                            Spacer(modifier = Modifier.width(8.dp))


                            Button(
                                onClick = {
                                    viewModel.applyFilters(
                                        selectedCategories,
                                        selectedBrands
                                    )
                                    filterExpanded = false
                                },
                                modifier = Modifier.weight(1f)
                                        .height(56.dp)
                                    .padding(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(R.color.red),
                                )
                            ) {
                                Text("Uygula")
                            }
                        }
                    }
                }
            }
        }
    }
}





