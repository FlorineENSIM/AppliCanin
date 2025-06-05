package com.example.applicationcanin.ui.screens

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.applicationcanin.data.FirestoreRepository
import com.example.applicationcanin.ui.components.BigHomeButton
import com.example.applicationcanin.ui.components.HeaderConnexion
import com.example.applicationcanin.data.Veterinaire
import com.example.applicationcanin.ui.components.VeterinaireCard


@Composable
fun ListVeterinaireScreen(navController: NavHostController) {
    val repository = remember { FirestoreRepository() }
    var veterinaires by remember { mutableStateOf<List<Veterinaire>>(emptyList()) }

    LaunchedEffect(Unit) {
        veterinaires = repository.getAllVeterinaires()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderConnexion(
            title = "Vétérinaires",
            showBack = true,
            navController = navController,
            modifier = Modifier.fillMaxWidth()
        )


        val veterinaires = listOf(
            Veterinaire(
                nom = "Dr Legrand",
                cabinet = "Clinique vétérinaire Voltaire",
                adresse = "1 Rue Voltaire",
                ville = "72000 Le Mans",
                googleMapsUrl = "https://maps.google.com/?q=1+Rue+Voltaire+Le+Mans",
                siteWeb = "http://vetforest.fr/"
            ),
            Veterinaire(
                nom = "Dr Leclerc",
                cabinet = "Clinique vétérinaire Leclerc",
                adresse = "168 Avenue Georges Durand",
                ville = "72000 Le Mans",
                googleMapsUrl = "https://maps.google.com/?q=168+Avenue+Georges+Durand+Le+Mans"
            ),
            Veterinaire(
                nom = "Dr Dupont",
                cabinet = "Clinique vétérinaire Saint-Martin",
                adresse = "308 Rue Saint-Martin",
                ville = "84120 Pertuis",
                googleMapsUrl = "https://maps.google.com/?q=308+Rue+Saint-Martin+Pertuis",
                siteWeb = "https://www.vetosaintmartin.fr"
            )

        )

        Column(modifier = Modifier.padding(16.dp)) {
            veterinaires.forEach { vet ->
                VeterinaireCard(veterinaire = vet) {
                    navController.currentBackStackEntry?.savedStateHandle?.set("vet", vet)
                    navController.navigate("veterinaire")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
//        Column(modifier = Modifier.padding(16.dp)) {
//            veterinaires.forEach { vet ->
//                VeterinaireCard(
//                    veterinaire = vet,
//                    onClick = {
//                        navController.currentBackStackEntry?.savedStateHandle?.set("vet", vet)
//                        navController.navigate("veterinaire")
//                    }
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//            }
//        }


    }
}
