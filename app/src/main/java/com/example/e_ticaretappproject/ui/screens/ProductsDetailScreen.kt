package com.example.e_ticaretappproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
    var quantity by remember { mutableStateOf(1) }

    product?.let { currentProduct ->
        val totalPrice = currentProduct.fiyat * quantity
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    title = "${currentProduct.kategori.uppercase()} / ${currentProduct.ad.uppercase()}",
                    showSearch = false,
                    showCart = false,
                    onCartClick = {}
                )
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$totalPrice TL",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(R.color.red2)
                    )
                    Button(
                        onClick = {
                            val cartProduct = CartProducts(
                                sepetId = 0,
                                ad = currentProduct.ad,
                                resim = currentProduct.resim,
                                kategori = currentProduct.kategori,
                                fiyat = currentProduct.fiyat,
                                marka = currentProduct.marka,
                                siparisAdeti = quantity,
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
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.red2),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Sepete Ekle", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding( 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Ödül",
                        tint = colorResource(R.color.yellow),
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = colorResource(R.color.red2),
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(currentProduct.kategori)
                            }
                            append(" Kategorisinde En Çok Favorilenen")
                        },
                        fontSize = 28.sp,
                        fontFamily = FontFamily.Cursive,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(216.dp)
                        .border(
                            width = 2.dp,
                            color = colorResource(R.color.pink),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        GlideImage(
                            imageModel = "http://kasimadalan.pe.hu/urunler/resimler/${product.resim}",
                            modifier = Modifier.fillMaxSize().padding(vertical = 24.dp),
                            contentScale = ContentScale.Fit
                        )

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
                            .padding(12.dp)
                            .background(colorResource(R.color.green), shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "Kargo Bedava",
                            color = colorResource(R.color.white),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = currentProduct.marka.uppercase(),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = currentProduct.ad,
                                fontSize = 24.sp,
                                color = Color.DarkGray
                            )
                        }

                        Text(
                            text = "${currentProduct.fiyat} TL",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = colorResource(R.color.black)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                        Button(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier.size(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.red2),
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) { Text("-", fontSize = 32.sp) }

                        Text(
                            "$quantity",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = 40.sp
                        )

                        Button(
                            onClick = { quantity++ },
                            modifier = Modifier.size(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.red2),
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) { Text("+", fontSize = 32.sp) }
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        )
    }
}

