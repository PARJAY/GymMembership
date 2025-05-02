package com.example.gymmembership

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymmembership.ui.navigation.AddEditMembershipScreenNavigation
import com.example.gymmembership.ui.navigation.AppNavigation
import com.example.gymmembership.ui.navigation.DisplayMemberQRScreenNavigation
import com.example.gymmembership.ui.navigation.MemberListScreenNavigation
import com.example.gymmembership.ui.navigation.ScanMemberQRScreenNavigation
import com.example.gymmembership.ui.screen.AddEditMembershipScreen
import com.example.gymmembership.ui.screen.DisplayMemberQRScreen
import com.example.gymmembership.ui.screen.MemberListScreen
import com.example.gymmembership.ui.screen.ScanMemberQRScreen
import com.example.gymmembership.ui.theme.GymMembershipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymMembershipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}