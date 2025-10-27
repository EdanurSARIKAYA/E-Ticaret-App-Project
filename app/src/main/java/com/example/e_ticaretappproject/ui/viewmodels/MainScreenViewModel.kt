package com.example.e_ticaretappproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_ticaretappproject.data.entity.Products
import com.example.e_ticaretappproject.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(var productsRepository: ProductsRepository) : ViewModel() {
    var productsList = MutableLiveData<List<Products>>()
    var filteredProductsList = MutableLiveData<List<Products>>()
    init {
        loadAllProducts()
    }

    fun loadAllProducts(){
        CoroutineScope(Dispatchers.Main).launch {
            productsList.value = productsRepository.loadAllProducts().urunler
            filteredProductsList.value = productsRepository.loadAllProducts().urunler
        }
    }
    fun toggleFavorite(productId: Int) {
        val currentList = productsList.value ?: return
        val updatedList = currentList.map { product ->
            if (product.id == productId) product.copy(isFavorite = !product.isFavorite)
            else product
        }
        productsList.value = updatedList
        filteredProductsList.value = updatedList
    }
    fun searchProducts(searchText: String){
        val products = productsList.value ?: emptyList()
        if(searchText.isBlank()){
            filteredProductsList.value = products
        }
        else{
            val filtered = products.filter {
                it.ad.contains(searchText, ignoreCase = true) || it.kategori.contains(searchText, ignoreCase = true)
            }
            filteredProductsList.value = filtered
        }
    }


}