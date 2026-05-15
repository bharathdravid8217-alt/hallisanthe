package com.hallisanthe.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hallisanthe.databinding.ActivityRoleSelectionBinding
import com.hallisanthe.utils.Constants
import com.hallisanthe.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoleSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoleSelectionBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoleSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sellerCard.setOnClickListener { select(Constants.ROLE_ARTISAN) }
        binding.buyerCard.setOnClickListener { select(Constants.ROLE_CUSTOMER) }
    }

    private fun select(role: String) {
        viewModel.saveRole(role)
        startActivity(Intent(this, PhoneAuthActivity::class.java).putExtra(Constants.KEY_ROLE, role))
    }
}
