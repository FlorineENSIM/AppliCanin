package com.example.applicationcanin.ui.screens

import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.applicationcanin.data.FirestoreRepository
import com.example.applicationcanin.data.UserPreferences
import com.example.applicationcanin.data.UserProfile
import com.example.applicationcanin.ui.components.HeaderConnexion
import com.example.applicationcanin.ui.components.MainButton
import com.example.applicationcanin.ui.components.ProfileSection
import com.example.applicationcanin.ui.theme.ColorText
import com.example.applicationcanin.ui.theme.HeaderColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProfileScreen(navController: NavHostController) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val repository = remember { FirestoreRepository() }
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val photoUri = remember { mutableStateOf<Uri?>(null) }

    val launcherGallery = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            saveImageToCache(context, uri)?.let { localUri ->
                updateFirestorePhoto("photoChienUri", localUri, userId, repository, scope) {
                    userProfile = it
                }
            }

        }
    }

    val launcherCamera = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && photoUri.value != null) {
            val uri = photoUri.value!!
            MediaScannerConnection.scanFile(context, arrayOf(uri.path), arrayOf("image/jpeg"), null)
            updateFirestorePhoto("photoChienUri", uri, userId, repository, scope) {
                userProfile = it
            }
        }
    }

    val launcherMultiGallery = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        scope.launch {
            val uriList = uris.mapIndexedNotNull { index, uri ->
                saveImageToCache(context, uri, index)
            }.map { it.toString() }

            FirebaseFirestore.getInstance()
                .collection("users")
                .document(userId ?: "")
                .set(mapOf("photosChienUris" to uriList), SetOptions.merge())
                .addOnSuccessListener {
                    scope.launch {
                        userProfile = repository.getUserProfile(userId ?: "")
                    }
                }
        }
    }
    val userPrefs = remember { UserPreferences(context) }
    var shouldNavigate by remember { mutableStateOf(false) }



    fun createImageFile(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.getDefault()).format(Date())
        val fileName = "JPEG_${timeStamp}_"
        val dir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera")
        if (!dir.exists()) dir.mkdirs()
        val file = File.createTempFile(fileName, ".jpg", dir)
        return FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
    }

    LaunchedEffect(userId) {
        if (userId != null) {
            userProfile = repository.getUserProfile(userId)
        }
        isLoading = false
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderConnexion("Profil", showBack = true, navController = navController, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(32.dp))

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            if (isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                userProfile?.let { profile ->
                    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        ProfileSection(
                            title = "Propriétaire",
                            items = listOf(
                                "Nom" to profile.nomProprio,
                                "Prénom" to profile.prenomProprio,
                                "Adresse" to profile.adresseProprio,
                                "Ville" to profile.villeProprio,
                                "Email" to profile.email
                            )
                        )

                        ProfileSection(
                            title = "Chien",
                            items = listOf(
                                "Prénom" to profile.prenomChien,
                                "Race" to profile.raceChien,
                                "Âge" to profile.ageChien,
                                "Calme" to if (profile.chienCalme) "Oui" else "Non"
                            )
                        )

                        // Affichage photo principale
                        Text("Photo principale du chien", style = MaterialTheme.typography.titleMedium)
                        profile.photoChienUri.takeIf { it.isNotBlank() }?.let { uriStr ->
                            val uri = Uri.parse(uriStr)
                            Card(
                                border = BorderStroke(3.dp, HeaderColor),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth().height(300.dp).padding(horizontal = 16.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(uri),
                                    contentDescription = "Photo principale du chien",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { launcherGallery.launch("image/*") }) {
                                Text("Galerie")
                            }
                            Button(onClick = {
                                val uri = createImageFile()
                                photoUri.value = uri
                                launcherCamera.launch(uri)
                            }) {
                                Text("Appareil photo")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        if (!profile.photosChienUris.isNullOrEmpty()) {
                            Text("Galerie personnelle", style = MaterialTheme.typography.titleMedium)
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(3.dp, HeaderColor),
                                modifier = Modifier.fillMaxWidth().height(160.dp).padding(horizontal = 8.dp)
                            ) {
                                LazyRow(
                                    contentPadding = PaddingValues(0.dp),
                                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(profile.photosChienUris) { uriStr ->
                                        val uri = Uri.parse(uriStr)
                                        Image(
                                            painter = rememberAsyncImagePainter(uri),
                                            contentDescription = "Photo sélectionnée",
                                            modifier = Modifier.width(140.dp).fillMaxHeight().padding(1.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Button(onClick = {
                                launcherMultiGallery.launch("image/*")
                            }) {
                                Text("Ajouter plusieurs photos")
                            }
                        }

                        MainButton(
                            text = "Se déconnecter",
                            onClick = {
                                FirebaseAuth.getInstance().signOut()
                                scope.launch {
                                    userPrefs.clearPreferences()
                                    shouldNavigate = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                        )

                        LaunchedEffect(shouldNavigate) {
                            if (shouldNavigate) {
                                navController.navigate("welcome") {
                                    popUpTo(0)
                                    launchSingleTop = true
                                }
                            }
                        }

                    }
                } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Aucune donnée utilisateur trouvée.")
                }
            }
        }
    }
}

private fun saveImageToCache(context: android.content.Context, uri: Uri, index: Int = 0): Uri? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.getDefault()).format(Date())
        val fileName = "multi_photo_${index}_$timeStamp.jpg"
        val file = File(context.cacheDir, fileName)
        FileOutputStream(file).use { output -> inputStream.copyTo(output) }
        Uri.fromFile(file)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

private fun updateFirestorePhoto(
    field: String,
    uri: Uri,
    userId: String?,
    repository: FirestoreRepository,
    scope: CoroutineScope,
    onResult: (UserProfile?) -> Unit
) {
    FirebaseFirestore.getInstance()
        .collection("users")
        .document(userId ?: "")
        .update(field, uri.toString())
        .addOnSuccessListener {
            scope.launch {
                onResult(repository.getUserProfile(userId ?: ""))
            }
        }
}
