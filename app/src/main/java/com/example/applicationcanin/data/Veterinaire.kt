package com.example.applicationcanin.data

import java.io.Serializable

data class Veterinaire(
    val nom: String,
    val cabinet: String,
    val adresse: String,
    val ville: String,
    val googleMapsUrl: String,
    val siteWeb: String? = null
) : Serializable


//data class Veterinaire(
//    val nom: String = "",
//    val cabinet: String = "",
//    val adresse: String = "",
//    val ville: String = "",
//    val googleMapsUrl: String = "",
//    val siteWeb: String? = null,
//    val telephone: String? = null,
//    val horaires: String? = null
//)