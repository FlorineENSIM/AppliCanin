package com.example.applicationcanin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import com.example.applicationcanin.R

@Composable
fun HeaderConnexion(
    title: String,
    showBack: Boolean = false,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        // Bouton retour à gauche
        if (showBack) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Retour",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 24.dp)
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
        }

        // Titre centré
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
