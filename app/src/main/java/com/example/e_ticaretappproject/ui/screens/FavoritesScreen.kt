package com.example.e_ticaretappproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.e_ticaretappproject.data.entity.Products
import com.example.e_ticaretappproject.ui.components.CustomTopAppBar
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.example.e_ticaretappproject.R

@Composable
fun FavoritesScreen(viewModel: MainScreenViewModel = viewModel()){
    val products = viewModel.productsList.observeAsState(listOf())
    val favoritesList = products.value.filter { it.isFavorite }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "FAVORİLER",
                showSearch = false,
                showCart = false,
                onCartClick = {}
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(favoritesList){ product ->
                FavoriteCustomCard(product)
            }

        }

    }
}

@Composable
fun FavoriteCustomCard(products: Products){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(
                width = 3.dp,
                color = colorResource(R.color.pink),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            GlideImage(
                imageModel = "http://kasimadalan.pe.hu/urunler/resimler/${products.resim}",
                modifier = Modifier
                    .size(96.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column (
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = products.marka.uppercase(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.black)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = products.ad,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(R.color.black)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${products.fiyat} TL",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.gray)
                )
            }
        }
    }
}