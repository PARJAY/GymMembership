package com.example.gymmembership.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymmembership.R
import com.example.gymmembership.data.model.Membership
import com.example.gymmembership.data.model.MembershipCategory
import com.example.gymmembership.ui.component.MemberListItem
import com.example.gymmembership.ui.theme.GymMembershipTheme
import com.example.gymmembership.viewmodel.MemberListScreenEvent
import com.example.gymmembership.viewmodel.MemberListScreenState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberListScreen(
    state: MemberListScreenState,
    onEvent: (MemberListScreenEvent) -> Unit,
    onNavigateToAddEdit: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Daftar Member", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { /* TODO: Implement search */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* TODO: Implement filter */ }) {
                        Icon(painter = painterResource(id = R.drawable.ic_filter), contentDescription = "Filter")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToAddEdit() }) {
                Icon(Icons.Filled.Add, "Tambah Member")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                androidx.compose.material3.CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else if (state.errorMessage != null) {
                Text("Error: ${state.errorMessage}", color = MaterialTheme.colorScheme.error)
            } else {
                LazyColumn {
                    items(state.members) { member ->
                        MemberListItem(member = member) {
                            onEvent(MemberListScreenEvent.OnMemberClicked(member))
                            onNavigateToAddEdit()
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemberListScreenPreview() {
    val previewState = MemberListScreenState(
        isLoading = false,
        members = listOf(
            Membership(name = "John Doe", expiryDate = LocalDate.of(2025, 5, 10), membershipCategory = MembershipCategory.UMUM_PRIA),
            Membership(name = "Jane Smith", expiryDate = LocalDate.of(2025, 6, 15), membershipCategory = MembershipCategory.UMUM_WANITA),
            Membership(name = "Peter Jones", expiryDate = null, membershipCategory = MembershipCategory.COUPLE)
        ),
        errorMessage = null
    )
    GymMembershipTheme {
        MemberListScreen(
            state = previewState,
            onEvent = {},
            onNavigateToAddEdit = {},
        )
    }
}