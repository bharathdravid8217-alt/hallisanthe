package com.hallisanthe.ui.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hallisanthe.databinding.FragmentSearchBinding
import com.hallisanthe.ui.common.adapter.ProductAdapter
import com.hallisanthe.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: ProductViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        val adapter = ProductAdapter { }
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch { viewModel.products.collect { products -> adapter.submitList(products) } }
        binding.searchInput.doAfterTextChanged { query ->
            val q = query.toString().trim()
            adapter.submitList(viewModel.products.value.filter { q.length < 2 || it.productName.contains(q, true) || it.category.contains(q, true) })
        }
        viewModel.loadProducts()
        return binding.root
    }
}
