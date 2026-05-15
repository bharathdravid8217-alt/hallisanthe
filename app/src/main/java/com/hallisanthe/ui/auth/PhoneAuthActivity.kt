package com.hallisanthe.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.hallisanthe.databinding.ActivityPhoneAuthBinding
import com.hallisanthe.utils.Constants
import java.util.concurrent.TimeUnit

class PhoneAuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backButton.setOnClickListener { finish() }
        binding.sendOtpButton.setOnClickListener {
            val phone = binding.phoneInput.text.toString()
            if (phone.length != 10) {
                Toast.makeText(this, "Enter a valid 10-digit mobile number", Toast.LENGTH_SHORT).show()
            } else {
                sendOtp("+91$phone")
            }
        }

        binding.skipAuthButton.setOnClickListener {
            val phone = binding.phoneInput.text.toString().ifEmpty { "9999999999" }
            Toast.makeText(this, "Entering Demo Mode...", Toast.LENGTH_SHORT).show()
            openOtp("+91$phone", null)
        }
    }

    private fun sendOtp(phone: String) {
        if (FirebaseApp.getApps(this).isEmpty()) {
            openOtp(phone, null)
            return
        }
        binding.sendOtpButton.isEnabled = false
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) = openOtp(phone, null)
                override fun onVerificationFailed(exception: FirebaseException) {
                    binding.sendOtpButton.isEnabled = true
                    android.util.Log.e("PhoneAuth", "Verification failed: ${exception.message}", exception)
                    
                    var message = exception.localizedMessage ?: "OTP failed"
                    if (message.contains("BILLING_NOT_ENABLED", ignoreCase = true)) {
                        message = "Billing not enabled in Firebase. Please check your Blaze plan."
                    } else if (message.contains("unusual activity", ignoreCase = true) || message.contains("blocked", ignoreCase = true)) {
                        message = "Device blocked due to too many attempts. Use the 'Skip' button for testing."
                    }

                    Toast.makeText(this@PhoneAuthActivity, message, Toast.LENGTH_LONG).show()
                }
                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) = openOtp(phone, verificationId)
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun openOtp(phone: String, verificationId: String?) {
        startActivity(Intent(this, OtpVerificationActivity::class.java)
            .putExtra("PHONE", phone)
            .putExtra("VERIFICATION_ID", verificationId)
            .putExtra(Constants.KEY_ROLE, intent.getStringExtra(Constants.KEY_ROLE)))
    }
}
