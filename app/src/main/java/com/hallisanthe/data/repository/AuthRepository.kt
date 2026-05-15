package com.hallisanthe.data.repository

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hallisanthe.data.model.User
import com.hallisanthe.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(@ApplicationContext private val context: Context) {
    private val prefs = context.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
    private val firebaseReady get() = FirebaseApp.getApps(context).isNotEmpty()

    fun saveSelectedRole(role: String) = prefs.edit().putString(Constants.KEY_ROLE, role).apply()
    fun selectedRole(): String = prefs.getString(Constants.KEY_ROLE, Constants.ROLE_CUSTOMER) ?: Constants.ROLE_CUSTOMER

    suspend fun currentUserType(): String? {
        try {
            val uid = if (firebaseReady) FirebaseAuth.getInstance().currentUser?.uid else null
            if (uid == null) return prefs.getString("SIGNED_IN_ROLE", null)
            return FirebaseFirestore.getInstance().collection("users").document(uid).get().await().getString("userType")
                ?: prefs.getString("SIGNED_IN_ROLE", null)
        } catch (e: Exception) {
            android.util.Log.e("AuthRepo", "Error getting user type", e)
            return prefs.getString("SIGNED_IN_ROLE", null)
        }
    }

    suspend fun saveProfile(user: User) {
        prefs.edit().putString("SIGNED_IN_ROLE", user.userType).putString("USER_NAME", user.name).apply()
        if (firebaseReady && FirebaseAuth.getInstance().currentUser != null) {
            try {
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).set(user).await()
            } catch (e: Exception) {
                android.util.Log.e("AuthRepo", "Save profile failed", e)
            }
        }
    }

    fun logout() {
        if (firebaseReady) FirebaseAuth.getInstance().signOut()
        prefs.edit().remove("SIGNED_IN_ROLE").remove("USER_NAME").apply()
    }
}
