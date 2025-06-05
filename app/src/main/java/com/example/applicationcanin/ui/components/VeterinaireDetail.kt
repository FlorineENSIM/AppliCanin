package com.example.applicationcanin.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.applicationcanin.data.Veterinaire

@Composable
fun VeterinaireDetail(vet: Veterinaire) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = vet.nom, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Cabinet : ${vet.cabinet}", style = MaterialTheme.typography.bodyMedium)
            Text("Adresse : ${vet.adresse}", style = MaterialTheme.typography.bodyMedium)
            Text("Ville : ${vet.ville}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(vet.googleMapsUrl))
                context.startActivity(intent)
            }) {
                Text("📍 Ouvrir dans Google Maps")
            }
        }
    }
}