package com.example.e_ticaretappproject.data.repository

import com.example.e_ticaretappproject.data.datasources.ProductsDatasource

class ProductsRepository(var productsDatasource: ProductsDatasource) {
    suspend fun loadAllProducts() = productsDatasource.loadAllProducts()
    suspend fun loadCartProducts(kullaniciAdi: String) = productsDatasource.loadCartProducts(kullaniciAdi)
    suspend fun insertToCart(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String
    ) = productsDatasource.insertToCart(ad,resim,kategori,fiyat,marka,siparisAdeti,kullaniciAdi)
    suspend fun deleteFromCart(sepetId: Int, kullaniciAdi: String) = productsDatasource.deleteFromCart(sepetId, kullaniciAdi)
}