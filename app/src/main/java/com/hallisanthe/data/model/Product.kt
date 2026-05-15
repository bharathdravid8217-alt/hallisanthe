package com.hallisanthe.data.model

data class Product(
    val productId: String = "",
    val artisanId: String = "",
    val artisanName: String = "",
    val artisanPhone: String = "",
    val artisanWhatsapp: String = "",
    val artisanLocation: String = "",
    val productName: String = "",
    val price: Double = 0.0,
    val category: String = "",
    val subCategory: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val isAvailable: Boolean = true,
    val stockCount: Int = 0,
    val averageRating: Double = 0.0,
    val totalReviews: Int = 0,
    val searchKeywords: List<String> = emptyList()
)
