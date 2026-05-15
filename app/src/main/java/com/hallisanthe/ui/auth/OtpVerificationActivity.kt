package com.hallisanthe.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.hallisanthe.databinding.ActivityOtpVerificationBinding
import com.hallisanthe.utils.Constants

class OtpVerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.verifyButton.setOnClickListener {
            val code = binding.otpInput.text.toString()
            val verificationId = intent.getStringExtra("VERIFICATION_ID")
            
            if (verificationId == null) {
                // Demo Mode
                Toast.makeText(this, "Testing Mode: Verified with any code", Toast.LENGTH_SHORT).show()
                openProfile()
                return@setOnClickListener
            }

            if (code.length < 6) {
                Toast.makeText(this, "Enter 6 digit OTP", Toast.LENGTH_SHORT).show()
            } else if (FirebaseApp.getApps(this).isNotEmpty() && !verificationId.isNullOrBlank()) {
                val credential = PhoneAuthProvider.getCredential(verificationId, code)
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) openProfile() else Toast.makeText(this, task.exception?.localizedMessage ?: "Invalid OTP", Toast.LENGTH_LONG).show()
                }
            } else {
                openProfile()
            }
        }
    }

    private fun openProfile() {
        startActivity(Intent(this, ProfileSetupActivity::class.java)
            .putExtra("PHONE", intent.getStringExtra("PHONE"))
            .putExtra(Constants.KEY_ROLE, intent.getStringExtra(Constants.KEY_ROLE)))
    }
}
