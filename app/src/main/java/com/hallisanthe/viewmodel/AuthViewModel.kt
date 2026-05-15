package com.hallisanthe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hallisanthe.data.model.User
import com.hallisanthe.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _userType = MutableStateFlow<String?>(null)
    val userType: StateFlow<String?> = _userType

    fun saveRole(role: String) = repository.saveSelectedRole(role)
    fun selectedRole(): String = repository.selectedRole()

    fun loadCurrentUserType(onLoaded: (String?) -> Unit) = viewModelScope.launch {
        val role = repository.currentUserType()
        _userType.value = role
        onLoaded(role)
    }

    fun saveProfile(user: User, onDone: () -> Unit) = viewModelScope.launch {
        repository.saveProfile(user)
        onDone()
    }

    fun logout() = repository.logout()
}
