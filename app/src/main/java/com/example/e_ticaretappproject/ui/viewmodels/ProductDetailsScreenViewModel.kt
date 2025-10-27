package com.example.e_ticaretappproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.e_ticaretappproject.data.entity.CartProducts
import com.example.e_ticaretappproject.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsScreenViewModel @Inject constructor(var productsRepository: ProductsRepository): ViewModel() {
    var kullaniciAdi = "edanur_sarikaya"

    fun insertToCart(cartProducts: CartProducts){
        CoroutineScope(Dispatchers.Main).launch {
            productsRepository.insertToCart(
                cartProducts.ad,
                cartProducts.resim,
                cartProducts.kategori,
                cartProducts.fiyat,
                cartProducts.marka,
                cartProducts.siparisAdeti,
                cartProducts.kullaniciAdi,
            )
        }
    }
}