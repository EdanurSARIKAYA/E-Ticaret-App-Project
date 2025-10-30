package com.example.e_ticaretappproject.data.datasources

import com.example.e_ticaretappproject.data.entity.CartProductsResponse
import com.example.e_ticaretappproject.data.entity.ProductsResponse
import com.example.e_ticaretappproject.retrofit.ProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsDatasource(var productsDao : ProductsDao) {
    suspend fun loadAllProducts(): ProductsResponse = withContext(Dispatchers.IO) {
        try {
            return@withContext productsDao.loadAllProducts()
        }
        catch (e: Exception){
            return@withContext ProductsResponse(emptyList(),0)

        }
    }

    suspend fun loadCartProducts(kullaniciAdi: String): CartProductsResponse = withContext(Dispatchers.IO){
        try {
            return@withContext productsDao.loadCartProducts(kullaniciAdi)
        }
        catch (e: Exception){
            return@withContext CartProductsResponse(emptyList(),0)
        }
    }

    suspend fun insertToCart(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String
    ){
        productsDao.insertToCart(ad,resim,kategori,fiyat,marka,siparisAdeti,kullaniciAdi)
    }

    suspend fun deleteFromCart(
        sepetId: Int,
        kullaniciAdi: String
    ){
        productsDao.deleteFromCart(sepetId,kullaniciAdi)
    }

}