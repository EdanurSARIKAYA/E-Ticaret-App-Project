package com.example.e_ticaretappproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.e_ticaretappproject.ui.screens.AppMainNavigate
import com.example.e_ticaretappproject.ui.screens.AppNavigation
import com.example.e_ticaretappproject.ui.screens.MainScreen
import com.example.e_ticaretappproject.ui.theme.ETicaretAppProjectTheme
import com.example.e_ticaretappproject.ui.viewmodels.CartScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.MainScreenViewModel
import com.example.e_ticaretappproject.ui.viewmodels.ProductDetailsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainScreenViewModel: MainScreenViewModel by viewModels()
    val productDetailsScreenViewModel: ProductDetailsScreenViewModel by viewModels()
    val cartScreenViewModel: CartScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ETicaretAppProjectTheme {
                AppMainNavigate(
                    mainScreenViewModel,
                    productDetailsScreenViewModel,
                    cartScreenViewModel
                )
            }
        }
    }


}


