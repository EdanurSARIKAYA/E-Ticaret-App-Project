package com.example.e_ticaretappproject.data.entity

data class CartProductsResponse(
    var urunler_sepeti: List<CartProducts>,
    var success: Int) {
}