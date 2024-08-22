import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.serialization)
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use {
        localProperties.load(it)
    }
}

android {
    namespace = "com.hotmail.arehmananis.composedemo.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.hotmail.arehmananis.composedemo.android"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = localProperties["API_KEY"].toString()
        )
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.browser)

    // API Client - Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    implementation(libs.logback.classic)

    // DI - Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Custom Views
    // Image from URL - Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    debugImplementation(libs.compose.ui.tooling)
}