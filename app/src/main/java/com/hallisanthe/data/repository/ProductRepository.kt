package com.hallisanthe.data.repository

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.hallisanthe.data.local.ProductDao
import com.hallisanthe.data.local.toEntity
import com.hallisanthe.data.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val productDao: ProductDao
) {
    private val firebaseReady get() = FirebaseApp.getApps(context).isNotEmpty()

    fun observeProducts(): Flow<List<Product>> = flow {
        if (firebaseReady) {
            try {
                val snapshot = FirebaseFirestore.getInstance().collection("products").get().await()
                val products = snapshot.toObjects(Product::class.java).ifEmpty { sampleProducts() }
                productDao.upsertAll(products.map { it.toEntity() })
                emit(products)
            } catch (e: Exception) {
                android.util.Log.e("ProductRepo", "Firestore error", e)
                // Fallback to local
                productDao.observeProducts().map { cached ->
                    cached.map { it.toProduct() }.ifEmpty { sampleProducts() }
                }.collect { emit(it) }
            }
        } else {
            productDao.observeProducts().map { cached ->
                cached.map { it.toProduct() }.ifEmpty { sampleProducts() }
            }.collect { emit(it) }
        }
    }

    fun observeArtisanProducts(artisanId: String): Flow<List<Product>> =
        productDao.observeProductsByArtisan(artisanId).map { it.map { entity -> entity.toProduct() }.ifEmpty { sampleProducts().take(2) } }

    suspend fun getProduct(id: String): Product? = productDao.getById(id)?.toProduct() ?: sampleProducts().find { it.productId == id }

    suspend fun saveProduct(product: Product) {
        productDao.upsert(product.toEntity())
        if (firebaseReady) {
            try {
                FirebaseFirestore.getInstance().collection("products").document(product.productId).set(product).await()
            } catch (e: Exception) {
                android.util.Log.e("ProductRepo", "Save to Firestore failed", e)
            }
        }
    }

    suspend fun deleteProduct(id: String) {
        productDao.delete(id)
        if (firebaseReady) {
            try {
                FirebaseFirestore.getInstance().collection("products").document(id).delete().await()
            } catch (e: Exception) {
                android.util.Log.e("ProductRepo", "Delete from Firestore failed", e)
            }
        }
    }

    private fun sampleProducts() = listOf(
        Product("p1", "demo-artisan", "Lakshmi Crafts", "9876543210", "9876543210", "Channapatna", "Hand-painted Wooden Toys", 450.0, "Gifts & Toys", "Toys", "Bright lacquered wooden toys made by traditional Channapatna artisans.", "", true, 12, 4.8, 18),
        Product("p2", "demo-artisan", "Kaveri Naturals", "9876543211", "9876543211", "Mysuru", "Cold Pressed Coconut Oil", 280.0, "Natural & Wellness", "Oil", "Small-batch coconut oil pressed locally with no additives.", "", true, 24, 4.6, 9),
        Product("p3", "demo-artisan-2", "Malnad Weaves", "9876543212", "9876543212", "Sagara", "Handloom Cotton Dupatta", 780.0, "Fashion", "Textiles", "Soft handloom cotton with a village-market color palette.", "", true, 7, 4.7, 14),
        Product("p4", "demo-artisan-3", "Terracotta Thota", "9876543213", "9876543213", "Dharwad", "Terracotta Planter Set", 620.0, "Home Decor", "Planters", "Three breathable terracotta planters for herbs and balcony greens.", "", true, 15, 4.5, 11)
    )
}
