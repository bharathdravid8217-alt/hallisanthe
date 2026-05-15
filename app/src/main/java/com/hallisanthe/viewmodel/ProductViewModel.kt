package com.hallisanthe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hallisanthe.data.model.Product
import com.hallisanthe.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    @dagger.hilt.android.qualifiers.ApplicationContext private val context: android.content.Context
) : ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _uploadStatus = MutableStateFlow<String?>(null)
    val uploadStatus: StateFlow<String?> = _uploadStatus

    fun loadProducts() = viewModelScope.launch {
        try {
            repository.observeProducts().collect { _products.value = it }
        } catch (e: Exception) {
            android.util.Log.e("ProductVM", "Error loading products", e)
        }
    }

    fun loadArtisanProducts(artisanId: String = "demo-artisan") = viewModelScope.launch {
        repository.observeArtisanProducts(artisanId).collect { _products.value = it }
    }

    fun uploadImage(uri: android.net.Uri, onResult: (String?) -> Unit) = viewModelScope.launch {
        try {
            val storageRef = com.google.firebase.storage.FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("products/${UUID.randomUUID()}.jpg")

            // Compress image
            val file = getFileFromUri(uri)
            val uploadUri = if (file != null) {
                try {
                    val compressedFile = id.zelory.compressor.Compressor.compress(context, file)
                    android.net.Uri.fromFile(compressedFile)
                } catch (e: Exception) {
                    uri // Fallback to original
                }
            } else {
                uri
            }

            imageRef.putFile(uploadUri).await()
            val url = imageRef.downloadUrl.await().toString()
            onResult(url)
            
            // Clean up temp file
            file?.delete()
        } catch (e: Exception) {
            e.printStackTrace()
            onResult(null)
        }
    }

    private fun getFileFromUri(uri: android.net.Uri): java.io.File? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = java.io.File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
            val outputStream = java.io.FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            file
        } catch (e: Exception) {
            null
        }
    }

    fun saveProduct(name: String, price: Double, category: String, description: String, imageUrl: String, onDone: () -> Unit) = viewModelScope.launch {
        val id = UUID.randomUUID().toString()
        repository.saveProduct(Product(id, "demo-artisan", "Lakshmi Crafts", "9876543210", "9876543210", "Channapatna", name, price, category, "Handmade", description, imageUrl, true, 1, 0.0, 0))
        onDone()
    }

    fun saveDemoProduct(name: String, price: Double, category: String, description: String, onDone: () -> Unit) = viewModelScope.launch {
        saveProduct(name, price, category, description, "", onDone)
    }

    fun delete(id: String) = viewModelScope.launch { repository.deleteProduct(id) }
}
