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

    fun insertToCart(cartProducts: CartProducts, onSuccess: (() -> Unit)? = null) {
        CoroutineScope(Dispatchers.Main).launch {

            val currentCart = productsRepository.loadCartProducts(kullaniciAdi).urunler_sepeti
            val existingProducts = currentCart.filter {
                it.ad == cartProducts.ad && it.marka == cartProducts.marka
            }
            if (existingProducts.isNotEmpty()) {
                val totalQuantity = existingProducts.sumOf { it.siparisAdeti } + cartProducts.siparisAdeti

                existingProducts.forEach { oldProduct ->
                    productsRepository.deleteFromCart(oldProduct.sepetId, kullaniciAdi)
                }

                productsRepository.insertToCart(
                    ad = cartProducts.ad,
                    resim = cartProducts.resim,
                    kategori = cartProducts.kategori,
                    fiyat = cartProducts.fiyat,
                    marka = cartProducts.marka,
                    siparisAdeti = totalQuantity,
                    kullaniciAdi = kullaniciAdi
                )

            } else {
                productsRepository.insertToCart(
                    ad = cartProducts.ad,
                    resim = cartProducts.resim,
                    kategori = cartProducts.kategori,
                    fiyat = cartProducts.fiyat,
                    marka = cartProducts.marka,
                    siparisAdeti = cartProducts.siparisAdeti,
                    kullaniciAdi = kullaniciAdi
                )
            }

            onSuccess?.invoke()
        }
    }
}