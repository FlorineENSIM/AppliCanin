package com.example.applicationcanin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcanin.ui.components.MainButton
import com.example.applicationcanin.ui.components.RectangleFond

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RectangleFond {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
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
