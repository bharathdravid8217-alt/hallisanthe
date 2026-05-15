package com.hallisanthe.ui.artisan

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hallisanthe.R
import com.hallisanthe.databinding.ActivityDashboardBinding
import com.hallisanthe.ui.artisan.fragment.MyProductsFragment
import com.hallisanthe.ui.artisan.fragment.ReviewsReceivedFragment
import com.hallisanthe.ui.artisan.fragment.UploadProductFragment
import com.hallisanthe.ui.auth.RoleSelectionActivity
import com.hallisanthe.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtisanDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.title.text = "Seller Dashboard"
        
        binding.logoutButton.setOnClickListener {
            authViewModel.logout()
            val intent = Intent(this, RoleSelectionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        show(MyProductsFragment())
        binding.bottomNav.inflateMenu(R.menu.artisan_bottom_nav)
        binding.bottomNav.setOnItemSelectedListener {
            show(when (it.itemId) {
                R.id.nav_upload -> UploadProductFragment()
                R.id.nav_reviews -> ReviewsReceivedFragment()
                else -> MyProductsFragment()
            })
            true
        }
    }
    private fun show(fragment: Fragment) = supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()
}
