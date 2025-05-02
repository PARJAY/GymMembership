package com.example.gymmembership.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembership.data.model.Membership
import com.example.gymmembership.repository.MembershipRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

data class MemberListScreenState(
    val isLoading: Boolean = false,
    val members: List<Membership> = emptyList(),
    val errorMessage: String? = null
)

sealed class MemberListScreenEvent {
    data object LoadMembers : MemberListScreenEvent()
    data class OnMemberClicked(val membership: Membership) : MemberListScreenEvent()
    data object AddNewMemberClicked : MemberListScreenEvent()
}

class MemberListViewModel(private val membershipRepository: MembershipRepository) : ViewModel() {

    private val _state = MutableStateFlow(MemberListScreenState())
    val state: StateFlow<MemberListScreenState> = _state

    init {
        loadMembers()
    }

    fun onEvent(event: MemberListScreenEvent) {
        when (event) {
            is MemberListScreenEvent.LoadMembers -> loadMembers()
            is MemberListScreenEvent.OnMemberClicked -> handleMemberClick(event.membership)
            is MemberListScreenEvent.AddNewMemberClicked -> handleAddNewMemberClick()
        }
    }

    private fun loadMembers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)
            membershipRepository.allMemberships
                .catch { error ->
                    _state.value = _state.value.copy(isLoading = false, errorMessage = error.message)
                }
                .collect { members ->
                    _state.value = _state.value.copy(isLoading = false, members = members)
                }
        }
    }

    private fun handleMemberClick(membership: Membership) {
        // TODO: Implement logika ketika member diklik, misalnya navigasi ke detail
        println("Member clicked: ${membership.name}")
    }

    private fun handleAddNewMemberClick() {
        // TODO: Implement logika ketika tombol tambah member diklik, misalnya navigasi ke layar tambah
        println("Add new member clicked")
    }
}