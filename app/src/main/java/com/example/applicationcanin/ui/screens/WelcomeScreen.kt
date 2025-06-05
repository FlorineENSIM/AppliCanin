package com.example.applicationcanin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcanin.data.UserPreferences
import com.example.applicationcanin.ui.components.HeaderConnexion
import com.example.applicationcanin.ui.components.MainButton
import com.example.applicationcanin.ui.components.RectangleFond
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking

@Composable
fun WelcomeScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    var stayLoggedIn by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        stayLoggedIn = userPrefs.getStayLoggedIn()
    }

    LaunchedEffect(stayLoggedIn) {
        if (stayLoggedIn == true && FirebaseAuth.getInstance().currentUser != null) {
            navController.navigate("main") {
                popUpTo("welcome") { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    if (stayLoggedIn == null) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderConnexion(
            title = "Application Canin",
            showBack = false,
            navController = navController,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))
//        Spacer(modifier = Modifier.align(Alignment.Center.Vertical))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RectangleFond {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    MainButton(
                        text = "Se connecter",
                        onClick = { navController.navigate("login") }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    MainButton(
                        text = "Créer un compte",
                        onClick = { navController.navigate("register") }
                    )
                }
            }

        }
    }
}
