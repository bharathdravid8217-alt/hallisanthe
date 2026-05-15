package com.hallisanthe.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hallisanthe.databinding.ActivitySplashBinding
import com.hallisanthe.ui.artisan.ArtisanDashboardActivity
import com.hallisanthe.ui.auth.RoleSelectionActivity
import com.hallisanthe.ui.customer.CustomerDashboardActivity
import com.hallisanthe.utils.Constants
import com.hallisanthe.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logo.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(800).start()
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.loadCurrentUserType { role ->
                val target = when (role) {
                    Constants.ROLE_ARTISAN -> ArtisanDashboardActivity::class.java
                    Constants.ROLE_CUSTOMER -> CustomerDashboardActivity::class.java
                    else -> RoleSelectionActivity::class.java
                }
                startActivity(Intent(this, target))
                finish()
            }
        }, 1600)
    }
}
