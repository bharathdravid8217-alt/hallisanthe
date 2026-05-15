package com.hallisanthe.ui.artisan.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hallisanthe.databinding.FragmentUploadProductBinding
import com.hallisanthe.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadProductFragment : Fragment() {
    private val viewModel: ProductViewModel by viewModels()
    private var selectedImageUri: android.net.Uri? = null
    private lateinit var binding: FragmentUploadProductBinding

    private val categories = listOf("Gifts & Toys", "Natural & Wellness", "Fashion", "Home Decor", "Organic Food", "Handicrafts", "Other")

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it
            binding.productImage.setImageURI(it)
            binding.productImage.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUploadProductBinding.inflate(inflater, container, false)
        
        setupCategoryDropdown()

        binding.selectImageButton.setOnClickListener { pickImage.launch("image/*") }

        binding.saveProductButton.setOnClickListener {
            validateAndSave()
        }
        return binding.root
    }

    private fun setupCategoryDropdown() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, categories)
        binding.categoryDropdown.setAdapter(adapter)
        binding.categoryDropdown.setOnItemClickListener { _, _, position, _ ->
            if (categories[position] == "Other") {
                binding.otherCategoryLayout.visibility = View.VISIBLE
            } else {
                binding.otherCategoryLayout.visibility = View.GONE
            }
        }
    }

    private fun validateAndSave() {
        val name = binding.productNameInput.text.toString().trim()
        val priceStr = binding.priceInput.text.toString().trim()
        val price = priceStr.toDoubleOrNull() ?: 0.0
        val selectedCat = binding.categoryDropdown.text.toString()
        val otherCat = binding.otherCategoryInput.text.toString().trim()
        val description = binding.descriptionInput.text.toString().trim()

        val category = if (selectedCat == "Other") otherCat else selectedCat

        if (name.isEmpty() || priceStr.isEmpty() || category.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "All fields marked with * are mandatory", Toast.LENGTH_SHORT).show()
            return
        }

        binding.saveProductButton.isEnabled = false
        binding.saveProductButton.text = "Publishing..."

        val onDone = {
            Toast.makeText(requireContext(), "Product published successfully!", Toast.LENGTH_SHORT).show()
            binding.saveProductButton.isEnabled = true
            binding.saveProductButton.text = "Publish Product"
            clearFields()
        }

        selectedImageUri?.let { uri ->
            viewModel.uploadImage(uri) { url ->
                if (url != null) {
                    viewModel.saveProduct(name, price, category, description, url, onDone)
                } else {
                    Toast.makeText(requireContext(), "Image upload failed. Check your connection.", Toast.LENGTH_SHORT).show()
                    binding.saveProductButton.isEnabled = true
                    binding.saveProductButton.text = "Publish Product"
                }
            }
        } ?: run {
            viewModel.saveProduct(name, price, category, description, "", onDone)
        }
    }

    private fun clearFields() {
        binding.productNameInput.text?.clear()
        binding.priceInput.text?.clear()
        binding.categoryDropdown.text?.clear()
        binding.otherCategoryInput.text?.clear()
        binding.descriptionInput.text?.clear()
        binding.otherCategoryLayout.visibility = View.GONE
        binding.productImage.visibility = View.GONE
        selectedImageUri = null
    }
}
