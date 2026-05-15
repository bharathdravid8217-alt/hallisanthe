package com.hallisanthe.ui.customer.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.hallisanthe.databinding.FragmentProductListBinding
import com.hallisanthe.ui.common.adapter.ProductAdapter
import com.hallisanthe.ui.customer.ProductDetailActivity
import com.hallisanthe.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModels()
    private val adapter = ProductAdapter { 
        startActivity(Intent(requireContext(), ProductDetailActivity::class.java).putExtra("PRODUCT_ID", it.productId)) 
    }

    private val selectedCategory = MutableStateFlow("All")
    private val sortOrder = MutableStateFlow("None")
    private val searchQuery = MutableStateFlow("")

    private val categories = listOf("All", "Gifts & Toys", "Natural & Wellness", "Fashion", "Home Decor", "Organic Food", "Handicrafts")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupCategories()
        setupFilters()
        setupSearch()
        observeData()
        viewModel.loadProducts()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }

    private fun setupCategories() {
        categories.forEach { category ->
            val chip = Chip(requireContext()).apply {
                text = category
                isCheckable = true
                isChecked = category == "All"
                setOnClickListener { selectedCategory.value = category }
            }
            binding.categoryChipGroup.addView(chip)
        }
    }

    private fun setupFilters() {
        binding.chipPopularity.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) sortOrder.value = "Popularity"
            else if (!binding.chipPriceLowHigh.isChecked) sortOrder.value = "None"
        }
        binding.chipPriceLowHigh.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) sortOrder.value = "PriceLH"
            else if (!binding.chipPopularity.isChecked) sortOrder.value = "None"
        }
    }

    private fun setupSearch() {
        binding.searchInput.addTextChangedListener {
            searchQuery.value = it?.toString() ?: ""
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            combine(viewModel.products, selectedCategory, sortOrder, searchQuery) { products, category, sort, query ->
                var filtered = if (category == "All") products else products.filter { it.category == category }
                
                if (query.isNotEmpty()) {
                    filtered = filtered.filter { 
                        it.productName.contains(query, ignoreCase = true) || 
                        it.description.contains(query, ignoreCase = true) 
                    }
                }

                filtered = when (sort) {
                    "Popularity" -> filtered.sortedByDescending { it.totalReviews }
                    "PriceLH" -> filtered.sortedBy { it.price }
                    else -> filtered
                }
                filtered
            }.collect { 
                adapter.submitList(it)
                if (it.isEmpty()) {
                    android.widget.Toast.makeText(context, "No products found. Try changing categories.", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
