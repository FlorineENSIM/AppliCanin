package com.example.applicationcanin.data

data class UserProfile(
    val nomProprio: String = "",
    val prenomProprio: String = "",
    val email: String = "",
    val adresseProprio: String = "",
    val villeProprio: String = "",
    val prenomChien: String = "",
    val raceChien: String = "",
    val ageChien: String = "",
    val chienCalme: Boolean = false,
    val identifiant: String = "",
    val photoChienUri: String = "",
    val photosChienUris: List<String> = emptyList()

)
