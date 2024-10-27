import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.efedaniel.spotifystats"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.efedaniel.spotifystats"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        addManifestPlaceholders(mapOf(
            "redirectHostName" to "",
            "redirectSchemeName" to "",
        ))

    }

    buildTypes {
        debug {
            buildConfigField("String", "CLIENT_ID", getSecret("CLIENT_ID"))
            buildConfigField("String", "CLIENT_SECRET", getSecret("CLIENT_SECRET"))
        }
        release {
            buildConfigField("String", "CLIENT_ID", getSecret("CLIENT_ID"))
            buildConfigField("String", "CLIENT_SECRET", getSecret("CLIENT_SECRET"))
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}


dependencies {
    // AndroidX
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.startup:startup-runtime:1.1.1")
    implementation("androidx.browser:browser:1.8.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Network
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.8")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Test
    testImplementation("junit:junit:4.13.2")

    // Android Test
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    //Spotify Auth
    implementation("com.spotify.android:auth:2.1.2")
}

// Fixme: Move to different file
fun getSecret(key: String): String {
    val isCi = System.getenv("CI") == "true"

    return if (isCi) {
        System.getenv(key)
    } else {
        val properties = Properties()
        properties.load(FileInputStream(rootProject.file("secret.properties")))
        properties.getProperty(key)
    }
}
