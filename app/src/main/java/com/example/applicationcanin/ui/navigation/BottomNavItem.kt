package com.example.applicationcanin.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Accueil", Icons.Default.Home)
    object Profile : BottomNavItem("profile", "Profil", Icons.Default.Person)
    object Messages : BottomNavItem("messages", "Messages", Icons.Default.Email)
}
