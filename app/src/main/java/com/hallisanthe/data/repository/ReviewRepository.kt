package com.hallisanthe.data.repository

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.hallisanthe.data.model.Review
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepository @Inject constructor(@ApplicationContext private val context: Context) {
    suspend fun reviewsFor(productId: String): List<Review> {
        if (FirebaseApp.getApps(context).isNotEmpty()) {
            return FirebaseFirestore.getInstance().collection("reviews").whereEqualTo("productId", productId).get().await().toObjects(Review::class.java)
        }
        return listOf(
            Review("r1", productId, "c1", "Ananya", 5, "Authentic product and very kind seller."),
            Review("r2", productId, "c2", "Rahul", 4, "Good quality, packing could improve.")
        )
    }

    suspend fun addReview(review: Review) {
        if (FirebaseApp.getApps(context).isNotEmpty()) FirebaseFirestore.getInstance().collection("reviews").document(review.reviewId).set(review).await()
    }
}
