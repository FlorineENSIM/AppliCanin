package com.example.applicationcanin.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcanin.ui.components.HeaderConnexion
import com.example.applicationcanin.ui.components.MainButton
import com.example.applicationcanin.ui.components.RectangleFond
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegisterScreen(navController: NavController) {
    // Variables compte
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var identifiant by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Variables propriétaire
    var prenomProprio by remember { mutableStateOf("") }
    var nomProprio by remember { mutableStateOf("") }
    var adresseProprio by remember { mutableStateOf("") }
    var villeProprio by remember { mutableStateOf("") }

    // Variables chien
    var prenomChien by remember { mutableStateOf("") }
    var ageChien by remember { mutableStateOf("") }
    var raceChien by remember { mutableStateOf("") }
    var chienCalme by remember { mutableStateOf(false) }
    var chienAgressif by remember { mutableStateOf(false) }
    var chienJoueur by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderConnexion(title = "Créer un compte", showBack = true, navController = navController, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(32.dp))

        RectangleFond(
            modifier = Modifier.widthIn(max = 400.dp).padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                Text("Création du compte", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = identifiant,
                    onValueChange = { identifiant = it },
                    label = { Text("Identifiant") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mot de passe") },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmer le mot de passe") },
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Informations du propriétaire", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = prenomProprio,
                    onValueChange = { prenomProprio = it },
                    label = { Text("Prénom du propriétaire") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = nomProprio,
                    onValueChange = { nomProprio = it },
                    label = { Text("Nom du propriétaire") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = adresseProprio,
                    onValueChange = { adresseProprio = it },
                    label = { Text("Adresse du propriétaire") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = villeProprio,
                    onValueChange = { villeProprio = it },
                    label = { Text("Ville du propriétaire") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Informations du chien", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = prenomChien,
                    onValueChange = { prenomChien = it },
                    label = { Text("Nom du chien") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = ageChien,
                    onValueChange = { ageChien = it },
                    label = { Text("Age du chien") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = raceChien,
                    onValueChange = { raceChien = it },
                    label = { Text("Race du chien") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = chienCalme,
                        onCheckedChange = { chienCalme = it }
                    )
                    Text("Mon chien est calme", modifier = Modifier.padding(start = 8.dp))
                    Checkbox(
                        checked = chienJoueur,
                        onCheckedChange = { chienJoueur = it }
                    )
                    Text("Mon chien est joueur", modifier = Modifier.padding(start = 8.dp))
                    Checkbox(
                        checked = chienAgressif,
                        onCheckedChange = { chienAgressif = it }
                    )
                    Text("Mon chien est agressif", modifier = Modifier.padding(start = 8.dp))
                }

                Spacer(modifier = Modifier.height(24.dp))

                MainButton(
                    text = "Créer le compte",
                    onClick = {
                        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank() || identifiant.isBlank()) {
                            Toast.makeText(context, "Tous les champs sont requis", Toast.LENGTH_SHORT).show()
                        } else if (password != confirmPassword) {
                            Toast.makeText(context, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show()
                        } else {
                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val uid = FirebaseAuth.getInstance().currentUser?.uid
                                        val db = FirebaseFirestore.getInstance()
                                        val userData = hashMapOf(
                                            "identifiant" to identifiant,
                                            "email" to email,
                                            "prenomProprio" to prenomProprio,
                                            "nomProprio" to nomProprio,
                                            "adresseProprio" to adresseProprio,
                                            "villeProprio" to villeProprio,
                                            "prenomChien" to prenomChien,
                                            "ageChien" to ageChien,
                                            "raceChien" to raceChien,
                                            "chienCalme" to chienCalme,
                                            "chienJoueur" to chienJoueur,
                                            "chienAgressif" to chienAgressif
                                        )

                                        uid?.let {
                                            db.collection("users").document(uid).set(userData)
                                                .addOnSuccessListener {
                                                    Toast.makeText(context, "Compte créé avec succès", Toast.LENGTH_SHORT).show()
                                                    navController.navigate("login") {
                                                        popUpTo("register") { inclusive = true }
                                                    }
                                                }
                                                .addOnFailureListener { e ->
                                                    Toast.makeText(context, "Erreur BDD: ${e.message}", Toast.LENGTH_LONG).show()
                                                }
                                        }
                                    } else {
                                        Toast.makeText(context, "Erreur Auth: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                    }
                                }
                        }
                    }
                )
            }
        }
    }
}
