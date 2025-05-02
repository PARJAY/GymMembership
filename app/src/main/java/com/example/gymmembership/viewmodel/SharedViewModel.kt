package com.example.gymmembership.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gymmembership.data.model.Membership
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {
    private val _selectedMembership = MutableStateFlow<Membership?>(null)
    val selectedMembership: StateFlow<Membership?> = _selectedMembership

    fun selectMembership(membership: Membership) {
        _selectedMembership.value = membership
    }

    fun clearSelectedMembership() {
        _selectedMembership.value = null
    }
}