package com.example.applicationcanin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.applicationcanin.ui.screens.*


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("professionnel") { ProfessionelScreen(navController) }
        composable("listVeterinaire") { ListVeterinaireScreen(navController) }
        composable("veterinaire") { VeterinaireScreen(navController) }

    }
}
