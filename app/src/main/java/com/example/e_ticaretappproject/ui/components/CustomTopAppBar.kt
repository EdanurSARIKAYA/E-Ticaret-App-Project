package com.example.e_ticaretappproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e_ticaretappproject.R
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel

@Composable
fun CustomTopAppBar(
    title: String,
    showSearch: Boolean,
    showCart: Boolean,
    onCartClick: () -> Unit = {},
    onSearchChange: ((String) -> Unit)? = null
){
    val searchQuery = remember { mutableStateOf("") }
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFD53131),Color(0xFF780000))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
            .background(brush = gradient)
            .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = title,
                color = Color.White,
                fontSize = 28.sp,
                style = MaterialTheme.typography.titleLarge
            )
            if(showCart) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = CircleShape
                        )
                        .clickable { onCartClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.White
                    )
                }
            }

        }

        if(showSearch && onSearchChange != null){
            val searchText = remember { mutableStateOf(TextFieldValue("")) }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = searchQuery.value,
                onValueChange = {searchQuery.value = it
                                 onSearchChange(it)},
                placeholder = {Text("Ürün veya kategori ara", color = colorResource(id = R.color.gray) )},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(24.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.white),
                    unfocusedContainerColor = colorResource(id = R.color.white),
                    disabledContainerColor = colorResource(id = R.color.white),
                    focusedLabelColor = colorResource(id = R.color.red2),
                    cursorColor = colorResource(id = R.color.red2),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                )
                )


        }
    }
}