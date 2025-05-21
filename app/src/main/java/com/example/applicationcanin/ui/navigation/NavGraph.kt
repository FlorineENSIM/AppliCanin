package com.example.applicationcanin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import com.example.applicationcanin.ui.screens.LoginScreen
import com.example.applicationcanin.ui.screens.WelcomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        //composable("login") { LoginScreen(navController) }
        // composable("register") { RegisterScreen(navController) }  // bientôt
        // composable("home") { HomeScreen(navController) }         // bientôt
    }
}
