package com.hallisanthe.ui.artisan.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hallisanthe.databinding.FragmentProductListBinding
import com.hallisanthe.ui.common.adapter.ProductAdapter
import com.hallisanthe.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyProductsFragment : Fragment() {
    private val viewModel: ProductViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentProductListBinding.inflate(inflater, container, false)
        val adapter = ProductAdapter { }
        binding.sectionTitle.visibility = View.VISIBLE
        binding.sectionTitle.text = "My products"
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch { viewModel.products.collect { adapter.submitList(it) } }
        viewModel.loadArtisanProducts()
        return binding.root
    }
}
