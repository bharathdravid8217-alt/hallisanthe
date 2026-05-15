package com.hallisanthe.ui.artisan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hallisanthe.databinding.FragmentUploadProductBinding

class EditProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentUploadProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.formTitle.text = "Edit product"
    }
}
