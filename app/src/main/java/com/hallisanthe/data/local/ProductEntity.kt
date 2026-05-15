package com.hallisanthe.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hallisanthe.data.model.Product

@Entity(tableName = "products_table")
data class ProductEntity(
    @PrimaryKey val productId: String,
    val artisanId: String,
    val artisanName: String,
    val artisanPhone: String,
    val artisanWhatsapp: String,
    val artisanLocation: String,
    val productName: String,
    val price: Double,
    val category: String,
    val subCategory: String,
    val description: String,
    val imageUrl: String,
    val isAvailable: Boolean,
    val stockCount: Int,
    val averageRating: Double,
    val totalReviews: Int
) {
    fun toProduct() = Product(productId, artisanId, artisanName, artisanPhone, artisanWhatsapp, artisanLocation, productName, price, category, subCategory, description, imageUrl, isAvailable, stockCount, averageRating, totalReviews)
}

fun Product.toEntity() = ProductEntity(productId, artisanId, artisanName, artisanPhone, artisanWhatsapp, artisanLocation, productName, price, category, subCategory, description, imageUrl, isAvailable, stockCount, averageRating, totalReviews)
