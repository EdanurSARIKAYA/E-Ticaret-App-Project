package com.example.e_ticaretappproject.data.entity

import com.skydoves.landscapist.ImageLoadState

data class ProductsResponse(
    var urunler: List<Products>,
    var success: Int) {
}