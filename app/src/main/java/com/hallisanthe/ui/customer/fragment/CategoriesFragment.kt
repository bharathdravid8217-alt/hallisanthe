package com.hallisanthe.ui.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hallisanthe.databinding.FragmentSimpleListBinding
import com.hallisanthe.utils.Constants

class CategoriesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentSimpleListBinding.inflate(inflater, container, false)
        binding.sectionTitle.text = "Browse categories"
        Constants.categories.forEach { category ->
            binding.stack.addView(TextView(requireContext()).apply {
                text = category
                textSize = 18f
                setPadding(24, 24, 24, 24)
            })
        }
        return binding.root
    }
}
