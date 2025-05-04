package com.example.gymmembership.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymmembership.data.model.Membership
import com.example.gymmembership.data.model.MembershipCategory
import com.example.gymmembership.repository.MembershipRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.Locale

data class MemberListScreenState(
    val isLoading: Boolean = false,
    val members: List<Membership> = emptyList(),
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val filterCategory: MembershipCategory? = null
)

sealed class MemberListScreenEvent {
    data object LoadMembers : MemberListScreenEvent()
    data class OnMemberClicked(val membership: Membership) : MemberListScreenEvent()
    data class OnSearchQueryChange(val query: String) : MemberListScreenEvent()
    data class OnFilterCategoryChange(val category: MembershipCategory?) :
        MemberListScreenEvent()
}

class MemberListViewModel(
    private val sharedViewModel: SharedViewModel,
    private val membershipRepository: MembershipRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MemberListScreenState())
    val state: StateFlow<MemberListScreenState> = _state

    private val _members =
        MutableStateFlow<List<Membership>>(emptyList())

    init {
        loadMembers()
    }

    fun onEvent(event: MemberListScreenEvent) {
        when (event) {
            is MemberListScreenEvent.LoadMembers -> loadMembers()
            is MemberListScreenEvent.OnMemberClicked -> handleMemberClick(event.membership)
            is MemberListScreenEvent.OnSearchQueryChange -> handleSearchQueryChange(event.query)
            is MemberListScreenEvent.OnFilterCategoryChange -> handleFilterCategoryChange(event.category)
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
                    _members.value =
                        members
                    _state.value =
                        _state.value.copy(isLoading = false, members = members)
                }
        }
    }

    private fun handleMemberClick(membership: Membership) {
        sharedViewModel.selectMembership(membership)
    }

    private fun handleSearchQueryChange(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        updateFilteredMembers()
    }

    private fun handleFilterCategoryChange(category: MembershipCategory?) {
        _state.value = _state.value.copy(filterCategory = category)
        updateFilteredMembers()
    }

    private fun updateFilteredMembers() {
        val searchQuery = _state.value.searchQuery.lowercase(Locale.getDefault())
        val filterCategory = _state.value.filterCategory

        val filteredMembers = _members.value.filter { member ->
            (searchQuery.isEmpty() || member.name.lowercase(Locale.getDefault()).contains(searchQuery)) &&
                    (filterCategory == null || member.membershipCategory == filterCategory)
        }
        _state.value = _state.value.copy(members = filteredMembers)
    }
}
