package com.hallisanthe.ui.customer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hallisanthe.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.profileText.text = "Buyer profile\nSaved locally until Firebase is connected."
        return binding.root
    }
}
