package com.example.e_ticaretappproject.retrofit

import com.example.e_ticaretappproject.data.entity.CRUDResponse
import com.example.e_ticaretappproject.data.entity.CartProductsResponse
import com.example.e_ticaretappproject.data.entity.ProductsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDao {
    @GET("urunler/tumUrunleriGetir.php")
    suspend fun loadAllProducts() : ProductsResponse

    @POST("urunler/sepettekiUrunleriGetir.php")
    @FormUrlEncoded
    suspend fun loadCartProducts(
        @Field("kullaniciAdi") kullaniciAdi: String
    ): CartProductsResponse

    @POST("urunler/sepeteUrunEkle.php")
    @FormUrlEncoded
    suspend fun insertToCart(
        @Field("ad") ad: String,
        @Field("resim") resim: String,
        @Field("kategori") kategori: String,
        @Field("fiyat") fiyat: Int,
        @Field("marka") marka: String,
        @Field("siparisAdeti") siparisAdeti: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ): CRUDResponse

    @POST("urunler/sepettenUrunSil.php")
    @FormUrlEncoded
    suspend fun deleteFromCart(
        @Field("sepetId") sepetId: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ): CRUDResponse

}