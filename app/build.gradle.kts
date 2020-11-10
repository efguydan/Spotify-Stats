plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Config.App.SdkVersions.compile)

    defaultConfig {
        applicationId = Config.App.Misc.appID
        minSdkVersion(Config.App.SdkVersions.min)
        targetSdkVersion(Config.App.SdkVersions.target)
        versionCode = Config.App.Versions.code
        versionName = Config.App.Versions.name
        testInstrumentationRunner = Config.App.Misc.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }

    androidExtensions {
        isExperimental = true
    }

    lintOptions {
        isAbortOnError = false
        isIgnoreWarnings = true
        isQuiet = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.Versions.compose
        kotlinCompilerVersion = Config.Versions.kotlin
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Config.Libs.Core.kotlinStd)
    implementation(Config.Libs.AndroidX.core)
    implementation(Config.Libs.AndroidX.appCompat)
    implementation(Config.Libs.Compose.ui)
    implementation(Config.Libs.Compose.material)
    implementation(Config.Libs.Compose.uiTooling)
    implementation(Config.Libs.Compose.foundation)
    implementation(Config.Libs.Compose.materialsIconsCore)
    implementation(Config.Libs.Compose.materialsIconsExtended)
    implementation(Config.Libs.Compose.runtimeLivedata)
    implementation(Config.Libs.AndroidX.lifecycle)
    implementation(Config.Libs.Core.material)

    testImplementation(Config.Libs.Test.jUnit)

    androidTestImplementation(Config.Libs.AndroidTest.jUnit)
    androidTestImplementation(Config.Libs.AndroidTest.espresso)
}

apply(from = "../spotless.gradle")
