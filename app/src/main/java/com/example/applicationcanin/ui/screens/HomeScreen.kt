package com.example.applicationcanin.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcanin.data.FirestoreRepository
import com.example.applicationcanin.data.UserProfile
import com.example.applicationcanin.ui.components.BigHomeButton
import com.example.applicationcanin.ui.components.HeaderConnexion
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomeScreen(navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser
    val repository = remember { FirestoreRepository() }
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }

    LaunchedEffect(user?.uid) {
        user?.uid?.let { uid ->
            userProfile = repository.getUserProfile(uid)
        }
    }

    if (user == null) {
        // Redirection automatique vers l’écran de connexion
        LaunchedEffect(Unit) {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    } else {
        // Si utilisateur connecté : afficher l’accueil
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderConnexion(title = "Accueil", showBack = false, navController = navController,modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = if (userProfile == null) {
                    "Bienvenue 🐶"

                } else {
                    "Bienvenue ${userProfile?.prenomProprio} 🐶"
                },
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))

            BigHomeButton(
                text = "👨‍⚕️ Professionnels",
                onClick = { navController.navigate("professionnel") }
            )
            Spacer(modifier = Modifier.height(32.dp))
            BigHomeButton(
                text = "🎉 Événements collectifs",
                onClick = { navController.navigate("evenements") }
            )
            Spacer(modifier = Modifier.height(32.dp))
            BigHomeButton(
                text = "🐾 Balades",
                onClick = { navController.navigate("balades") }
            )

        }

    }
}