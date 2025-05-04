package com.example.gymmembership.viewmodel

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymmembership.MyApp
import com.example.gymmembership.repository.MembershipRepository

@Composable
fun generateMemberListViewModel(sharedViewModel: SharedViewModel): MemberListViewModel {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    return viewModel(
        viewModelStoreOwner = activity, // Use the activity as the owner
        factory = viewModelFactory {
            MemberListViewModel(
                sharedViewModel,
                MyApp.appModule.membershipRepository
            )
        }
    )
}