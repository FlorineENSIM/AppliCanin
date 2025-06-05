package com.example.applicationcanin.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.applicationcanin.ui.components.BigHomeButton
import com.example.applicationcanin.ui.components.BlocText
import com.example.applicationcanin.ui.components.HeaderConnexion

@Composable
fun ProfessionelScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderConnexion(
            title = "Professionnel",
            showBack = true,
            navController = navController,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        BlocText(
            text = "Choix du type de professionnel dont vous souhaitez connaître les informations et/ou contacter",
        )
        Spacer(modifier = Modifier.height(32.dp))
        BigHomeButton(
            text = "Vétérinaires",
            icon = Icons.Filled.MedicalServices,
            onClick = { navController.navigate("listVeterinaire") }
        )
        Spacer(modifier = Modifier.height(32.dp))
        BigHomeButton(
            text = "Educateur canin",
            icon = Icons.Filled.MedicalServices,
            onClick = { navController.navigate("listVeterinaire") }
        )


    }
}
