package com.hallisanthe.ui.customer

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hallisanthe.R
import com.hallisanthe.databinding.ActivityDashboardBinding
import com.hallisanthe.ui.auth.RoleSelectionActivity
import com.hallisanthe.ui.customer.fragment.CategoriesFragment
import com.hallisanthe.ui.customer.fragment.HomeFragment
import com.hallisanthe.ui.customer.fragment.ProfileFragment
import com.hallisanthe.ui.customer.fragment.SearchFragment
import com.hallisanthe.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.title.text = "Halli-Santhe"
        
        binding.logoutButton.setOnClickListener {
            authViewModel.logout()
            val intent = Intent(this, RoleSelectionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        show(HomeFragment())
        binding.bottomNav.inflateMenu(R.menu.customer_bottom_nav)
        binding.bottomNav.setOnItemSelectedListener {
            show(when (it.itemId) {
                R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()
            })
            true
        }
    }
    private fun show(fragment: Fragment) = supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
}
