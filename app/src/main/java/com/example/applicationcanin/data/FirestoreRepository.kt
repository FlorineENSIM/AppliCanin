package com.example.applicationcanin.data

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getUserProfile(userId: String): UserProfile? {
        return try {
            val snapshot = db.collection("users").document(userId).get().await()
            snapshot.toObject(UserProfile::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getAllVeterinaires(): List<Veterinaire> {
        return try {
            val snapshot = db.collection("veterinaires").get().await()
            snapshot.documents.mapNotNull { it.toObject(Veterinaire::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }


}
