package com.example.applicationcanin.ui.screens


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.applicationcanin.R
import com.example.applicationcanin.data.Veterinaire
import com.example.applicationcanin.ui.components.HeaderConnexion

@Composable
fun VeterinaireScreen(navController: NavHostController) {
    val vet = navController.previousBackStackEntry?.savedStateHandle?.get<Veterinaire>("vet")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderConnexion(
            title = "Vétérinaire",
            showBack = true,
            navController = navController,
            modifier = Modifier.fillMaxWidth()
        )

        vet?.let {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.vet_placeholder),
                        contentDescription = "Image vétérinaire",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = it.nom, style = MaterialTheme.typography.headlineSmall)
                    Text("Cabinet : ${it.cabinet}", style = MaterialTheme.typography.bodyMedium)
                    Text("Adresse : ${it.adresse}", style = MaterialTheme.typography.bodyMedium)
                    Text("Ville : ${it.ville}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("📞 Téléphone : 02 43 85 15 30", style = MaterialTheme.typography.bodyMedium)
                    Text("⏰ Horaires : Lundi au Vendredi - 8h à 19h", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.googleMapsUrl))
                                context.startActivity(intent)
                            },
                        ) {
                            Text("📍 Itinéraire Google Maps")
                        }
                        if (!it.siteWeb.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    val siteIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.siteWeb))
                                    context.startActivity(siteIntent)
                                },
                            ) {
                                Text("🌐 Visiter le site web")
                            }
                        }
                    }

                }
            }
        } ?: Text("Aucun vétérinaire sélectionné.")
    }
}