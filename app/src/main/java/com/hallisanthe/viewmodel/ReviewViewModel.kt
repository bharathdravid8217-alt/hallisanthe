package com.hallisanthe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hallisanthe.data.model.Review
import com.hallisanthe.data.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(private val repository: ReviewRepository) : ViewModel() {
    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews

    fun load(productId: String) = viewModelScope.launch { _reviews.value = repository.reviewsFor(productId) }
    fun add(productId: String, rating: Int, comment: String, onDone: () -> Unit) = viewModelScope.launch {
        repository.addReview(Review(UUID.randomUUID().toString(), productId, "demo-customer", "Guest Buyer", rating, comment))
        onDone()
    }
}
