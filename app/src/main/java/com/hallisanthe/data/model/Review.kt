package com.hallisanthe.data.model

data class Review(
    val reviewId: String = "",
    val productId: String = "",
    val customerId: String = "",
    val customerName: String = "",
    val rating: Int = 5,
    val comment: String = ""
)
