package com.hallisanthe.ui.customer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hallisanthe.databinding.ActivityReviewBinding
import com.hallisanthe.viewmodel.ReviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewActivity : AppCompatActivity() {
    private val viewModel: ReviewViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val productId = intent.getStringExtra("PRODUCT_ID").orEmpty()
        binding.submitReviewButton.setOnClickListener {
            viewModel.add(productId, binding.ratingBar.rating.toInt().coerceAtLeast(1), binding.commentInput.text.toString()) {
                Toast.makeText(this, "Review submitted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
