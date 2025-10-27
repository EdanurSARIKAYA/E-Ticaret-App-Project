package com.example.e_ticaretappproject.data.entity

data class Products(
    val id:Int,
    val ad:String,
    val resim:String,
    val kategori: String,
    val fiyat: Int,
    val marka: String,
    val isFavorite: Boolean = false) {
}