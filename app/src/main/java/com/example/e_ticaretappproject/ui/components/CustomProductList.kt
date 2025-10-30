package com.example.e_ticaretappproject.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_ticaretappproject.data.entity.Products
import com.example.e_ticaretappproject.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CustomProduct(
    products: Products,
    onProductClick: (Products) -> Unit = {},
    onFavoriteToggle: (Products) -> Unit = {}
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(208.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(208.dp)
                .border(
                    width = 2.dp,
                    color = colorResource(R.color.pink),
                    shape = RoundedCornerShape(16.dp)
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white)),
            onClick = { onProductClick(products) }
        ) {
            Text(
                text = products.kategori,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = colorResource(R.color.gray),
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                GlideImage(
                    imageModel = "http://kasimadalan.pe.hu/urunler/resimler/${products.resim}",
                    modifier = Modifier.size(112.dp)
                )


                Text(
                    text = products.ad,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "${products.fiyat} TL",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color.gray)
                )
            }
        }
                IconButton(
                    onClick = { onFavoriteToggle(products) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = if (products.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favori",
                        tint = if (products.isFavorite) colorResource(R.color.red) else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
    }

}