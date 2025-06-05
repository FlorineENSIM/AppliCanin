package com.example.applicationcanin.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.applicationcanin.data.UserPreferences
import com.example.applicationcanin.ui.navigation.BottomNavItem

@Composable
fun MainScreen(navController: NavHostController) {
    val localNavController = rememberNavController()
    val items = listOf(
        BottomNavItem.Profile,
        BottomNavItem.Home,
        BottomNavItem.Messages
    )
        Scaffold(

            bottomBar = {
                NavigationBar {
                    val currentRoute =
                        localNavController.currentBackStackEntryAsState().value?.destination?.route
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = currentRoute == item.route,
                            onClick = {
                                localNavController.navigate(item.route) {
                                    popUpTo(localNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = localNavController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(BottomNavItem.Profile.route) { ProfileScreen(localNavController) }
                composable(BottomNavItem.Home.route) { HomeScreen(navController) }
                composable(BottomNavItem.Messages.route) { MessagesScreen(navController) }
            }
        }

}
