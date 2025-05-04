package com.example.gymmembership.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymmembership.ui.screen.AddEditMembershipScreen
import com.example.gymmembership.ui.screen.DisplayMemberQRScreen
import com.example.gymmembership.ui.screen.MemberListScreen
import com.example.gymmembership.ui.screen.ScanMemberQRScreen
import com.example.gymmembership.viewmodel.SharedViewModel
import com.example.gymmembership.viewmodel.generateMemberListViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = MemberListScreenNavigation,
    ) {
        composable<MemberListScreenNavigation> {
            val memberListViewModel = generateMemberListViewModel(sharedViewModel)
            val memberListState by memberListViewModel.state.collectAsState()

            MemberListScreen(
                state = memberListState,
                onEvent = memberListViewModel::onEvent,
                onNavigateToAddEdit = {
                    navController.navigate(AddEditMembershipScreenNavigation)
                },
            )
        }
        composable<AddEditMembershipScreenNavigation> {
            AddEditMembershipScreen()
        }
        composable<ScanMemberQRScreenNavigation> {
            ScanMemberQRScreen()
        }
        composable<DisplayMemberQRScreenNavigation> {
            DisplayMemberQRScreen()
        }
    }
}