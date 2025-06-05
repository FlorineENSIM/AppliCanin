package com.example.applicationcanin.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcanin.ui.components.*
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.applicationcanin.data.UserPreferences
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var stayLoggedIn by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderConnexion(title = "Connexion", showBack = true, navController = navController,modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(32.dp))

        RectangleFond(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

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

                Spacer(modifier = Modifier.height(24.dp))


                MainButton(
                    text = "Se connecter",
                    onClick = {
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    coroutineScope.launch {
                                        userPrefs.saveStayLoggedIn(stayLoggedIn)
                                        navController.navigate("main") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    }

                                } else {
                                    Log.e("Erreur", "Erreur: ${task.exception?.message}")
                                    Toast.makeText(context, "Erreur : ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                }
                            }
                    }
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = stayLoggedIn,
                        onCheckedChange = { stayLoggedIn = it }
                    )
                    Text(text = "Rester connecté")
                }

            }
        }
    }
}