package com.hallisanthe.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hallisanthe.data.model.User
import com.hallisanthe.databinding.ActivityProfileSetupBinding
import com.hallisanthe.ui.artisan.ArtisanDashboardActivity
import com.hallisanthe.ui.customer.CustomerDashboardActivity
import com.hallisanthe.utils.Constants
import com.hallisanthe.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileSetupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileSetupBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val role = intent.getStringExtra(Constants.KEY_ROLE) ?: viewModel.selectedRole()
        binding.bioInput.hint = if (role == Constants.ROLE_ARTISAN) "Craft story / bio" else "Notes"
        binding.saveProfileButton.setOnClickListener {
            try {
                val uid = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid ?: "local-user"
                val user = User(
                    uid = uid,
                    phoneNumber = intent.getStringExtra("PHONE").orEmpty(),
                    userType = role,
                    name = binding.nameInput.text.toString().ifBlank { "Halli-Santhe User" },
                    location = binding.locationInput.text.toString(),
                    bio = binding.bioInput.text.toString(),
                    whatsappNumber = binding.whatsappInput.text.toString()
                )
                viewModel.saveProfile(user) {
                    val target = if (role == Constants.ROLE_ARTISAN) ArtisanDashboardActivity::class.java else CustomerDashboardActivity::class.java
                    startActivity(Intent(this, target).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
                }
            } catch (e: Exception) {
                android.util.Log.e("ProfileSetup", "Error saving profile", e)
                android.widget.Toast.makeText(this, "Error saving profile: ${e.message}", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
}
