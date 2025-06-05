plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.applicationcanin"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.applicationcanin"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    val navVersion = "2.8.9"

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    // Icons Material (utile pour une UI propre et intuitive)
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // Retrofit pour les appels réseau à ton futur backend
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coil pour charger les images (profil du chien, etc.)
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Kotlinx serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    // Plateforme Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))

    // Authentification par email/mot de passe
    implementation("com.google.firebase:firebase-auth-ktx")

    // Base de données Firestore (pour stocker profils, RDV, etc.)
    implementation("com.google.firebase:firebase-firestore-ktx")

    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation("androidx.datastore:datastore-preferences:1.0.0")





    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}