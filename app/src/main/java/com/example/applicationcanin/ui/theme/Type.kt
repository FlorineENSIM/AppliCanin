package com.example.applicationcanin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle( // body grand
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle( // body petit
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle( // bouton grand
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = TextStyle( // HEADER PAGE
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp
    ),
    displayLarge = TextStyle( // Header
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp
    ),
    headlineLarge = TextStyle( // NOM BLOC
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = TextStyle( // Titre formulaire
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline
    )

)