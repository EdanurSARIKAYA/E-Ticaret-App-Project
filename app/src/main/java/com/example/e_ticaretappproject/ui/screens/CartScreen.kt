package com.example.e_ticaretappproject.ui.screens

import android.graphics.Paint
import com.example.e_ticaretappproject.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.e_ticaretappproject.data.entity.Products
import com.example.e_ticaretappproject.ui.components.CustomTopAppBar
import com.example.e_ticaretappproject.ui.viewmodels.CartScreenViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch


@Composable
fun CartScreen(viewModel: CartScreenViewModel,
               navController: NavController)
{
    val cartProducts = viewModel.cartProduct.observeAsState(listOf())

    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Sepetim",
                showSearch = false,
                showCart = false
            )
        },
        bottomBar = {
            if (cartProducts.value.isNotEmpty()) {
                val totalPrice = cartProducts.value.sumOf { it.fiyat }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Toplam Tutar",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "$totalPrice TL",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.red2)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            showDialog = true
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.height(50.dp).fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.red2),
                            contentColor = Color.White
                        ),
                    ) {
                        Text(text = "Sepeti Onayla", fontSize = 16.sp)
                    }
                }
            }
        },
        content = { innerPadding ->
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        items(cartProducts.value){ products ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(
                        width = 3.dp,
                        color = colorResource(R.color.pink),
                        shape = RoundedCornerShape(16.dp)
                ),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
                elevation = CardDefaults.cardElevation(16.dp)
            ){


                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp)
                ) {
                    Text(
                        text = products.kategori.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )

                    IconButton(
                        onClick = {
                            scope.launch {
                                viewModel.deleteFromCart(products.sepetId)
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 16.dp)
                            .size(40.dp)
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = colorResource(R.color.red),
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    GlideImage(
                        imageModel = "http://kasimadalan.pe.hu/urunler/resimler/${products.resim}",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f).padding(8.dp)
                    ) {
                        Text(
                            text = "${products.marka.uppercase()} ${products.ad}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Tahmini Kargoya Teslim Süresi: 3-4 gün",
                            fontSize = 14.sp
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp , vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row{
                        Text(
                            text = "Sipariş Adeti: ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.black)
                        )
                        Text(
                            text = "${products.siparisAdeti}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = colorResource(R.color.black)
                        )
                    }

                    Text(
                        text = "${products.fiyat} TL",
                        fontWeight = FontWeight.ExtraBold,
                        color = colorResource(R.color.gray),
                        fontSize = 24.sp
                    )


                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(colorResource(R.color.green), shape = RoundedCornerShape(8.dp) )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        imageVector = Icons.Default.LocalShipping,
                        contentDescription = "Kargo",
                        tint = colorResource(R.color.white),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Kargo Bedava", color = colorResource(R.color.white), fontSize = 16.sp)
                }

            }
        }

        }
            if(showDialog){
                AlertDialog(
                    onDismissRequest = {showDialog = false},
                    containerColor = colorResource(R.color.white),
                    title = {
                        Row(horizontalArrangement = Arrangement.Center) {
                            Icon(
                                imageVector = Icons.Default.CheckBox,
                                contentDescription = "Onaylandı",
                                tint = colorResource(R.color.green),
                                modifier = Modifier.size(32.dp)                            )
                            Text(
                                text = "SİPARİŞİNİZ ONAYLANDI",
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.red)
                            )
                        }
                    },
                    text = {
                            Text(
                                text = "Teşekkürler! Siparişiniz Alınmıştır.",
                                color = colorResource(R.color.red)
                            )
                        },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxHeight(0.3f)
                        .border(2.dp, colorResource(R.color.red))
                        .padding(8.dp),
                    confirmButton = {
                        Button(onClick = {
                            showDialog = false
                            scope.launch {
                                viewModel.clearCart()
                            }
                            navController.navigate("home"){
                                popUpTo("home") {inclusive = true}
                            }
                        },
                            modifier = Modifier.height(50.dp).fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.red2),
                                contentColor = Color.White
                            )
                        ) { Text("Anasayfaya Dön") }
                    }
                )
            }

    }
)
}
