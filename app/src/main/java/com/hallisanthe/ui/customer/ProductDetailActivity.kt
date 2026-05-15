package com.hallisanthe.ui.customer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hallisanthe.data.repository.ProductRepository
import com.hallisanthe.databinding.ActivityProductDetailBinding
import com.hallisanthe.utils.WhatsAppHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {
    @Inject lateinit var repository: ProductRepository
    private lateinit var binding: ActivityProductDetailBinding
    private var productId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productId = intent.getStringExtra("PRODUCT_ID").orEmpty()
        lifecycleScope.launch {
            val product = repository.getProduct(productId) ?: return@launch
            binding.productName.text = product.productName
            binding.productPrice.text = "Rs. %.0f".format(product.price)
            binding.productDescription.text = product.description
            binding.artisanInfo.text = "${product.artisanName}\n${product.artisanLocation}\nRating %.1f (%d reviews)".format(product.averageRating, product.totalReviews)
            binding.whatsappButton.setOnClickListener { WhatsAppHelper.open(this@ProductDetailActivity, product.artisanWhatsapp, product.productName) }
            binding.callButton.setOnClickListener { startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${product.artisanPhone}"))) }
            binding.reviewButton.setOnClickListener { startActivity(Intent(this@ProductDetailActivity, ReviewActivity::class.java).putExtra("PRODUCT_ID", product.productId)) }
        }
    }
}
