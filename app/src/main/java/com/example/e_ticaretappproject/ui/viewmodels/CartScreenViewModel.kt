package com.example.e_ticaretappproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_ticaretappproject.data.entity.CartProducts
import com.example.e_ticaretappproject.data.entity.Products
import com.example.e_ticaretappproject.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor (var productsRepository: ProductsRepository) : ViewModel() {
    var cartProduct = MutableLiveData<List<CartProducts>>()
    var kullaniciAdi= "edanur_sarikaya"

    init {
        loadCartProducts()
    }

    fun loadCartProducts(){
        CoroutineScope(Dispatchers.Main).launch {
            cartProduct.value = productsRepository.loadCartProducts(kullaniciAdi).urunler_sepeti
        }
    }

    fun deleteFromCart(sepetId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            productsRepository.deleteFromCart(sepetId, kullaniciAdi)
            loadCartProducts()
        }
    }
}