package com.example.e_ticaretappproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.e_ticaretappproject.data.entity.Products
import com.example.e_ticaretappproject.R
import com.example.e_ticaretappproject.data.entity.CartProducts
import com.example.e_ticaretappproject.ui.components.CustomTopAppBar
import com.example.e_ticaretappproject.ui.viewmodels.CartScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.ProductDetailsScreenViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductsDetailScreen(
    productId: Int,
    navController: NavController,
    viewModel: MainScreenViewModel,
    cartScreenViewModel: CartScreenViewModel,
    detailsScreenViewModel: ProductDetailsScreenViewModel
) {

    val product = viewModel.productsList.observeAsState(listOf()).value.find { it.id == productId }
    product?.let { currentProduct ->
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    title = currentProduct.ad,
                    showSearch = false,
                    showCart = true,
                    onCartClick = {}
                )
            },
            bottomBar = {
                Button(
                    onClick = {
                        val cartProduct = CartProducts(
                            sepetId = 0,
                            ad = currentProduct.ad,
                            resim = currentProduct.resim,
                            kategori = currentProduct.kategori,
                            fiyat = currentProduct.fiyat,
                            marka = currentProduct.marka,
                            siparisAdeti = 1,
                            kullaniciAdi = "edanur_sarikaya"
                        )
                        detailsScreenViewModel.insertToCart(cartProduct) {
                            cartScreenViewModel.loadCartProducts()
                            navController.navigate("cart")
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.red2),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Sepete Ekle", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp)
                            .border(
                                width = 2.dp,
                                color = colorResource(R.color.pink),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                    ) {
                        GlideImage(
                            imageModel = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}",
                            modifier = Modifier.fillMaxSize()
                        )

                    }
                    IconButton(
                        onClick = { viewModel.toggleFavorite(currentProduct.id) },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .size(36.dp)
                            .background(
                                Color.White.copy(alpha = 0.7f),
                                shape = RoundedCornerShape(18.dp)
                            )
                    ) {
                        Icon(
                            imageVector = if (currentProduct.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favori",
                            tint = if (currentProduct.isFavorite) Color.Red else Color.Gray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                            .background(Color(0x80D53131), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Kargo Bedava",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }


                }

                Spacer(modifier = Modifier.height(16.dp))

                // Ürün adı ve kategori
                Text(
                    text = currentProduct.ad,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = currentProduct.kategori,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Fiyat
                Text(
                    text = "${currentProduct.fiyat} TL",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color.red2)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Ürün açıklaması
                Text(
                    text = "${currentProduct.marka}",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    lineHeight = 22.sp
                )
            }
        }
    }
}
