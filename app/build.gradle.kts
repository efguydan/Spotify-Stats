import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
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

    lint {
        isAbortOnError = false
        isIgnoreWarnings = true
        isQuiet = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.Versions.compose
    }

    signingConfigs {
        if (!rootProject.file(Config.App.Misc.keysPath).exists()) return@signingConfigs

        val keystoreProperties = Properties()
        keystoreProperties.load(File(Config.App.Misc.keysPath).inputStream())
        create("config") {
            storeFile = rootProject.file(keystoreProperties.getProperty("storeFileName"))
            storePassword = keystoreProperties.getProperty("storePassword")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
        }
    }

    buildTypes {
        if (!rootProject.file(Config.App.Misc.keysPath).exists()) return@buildTypes

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            versionNameSuffix = "-release"
            signingConfig = signingConfigs.getByName("config")
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
            signingConfig = signingConfigs.getByName("config")
        }
    }

    flavorDimensions("implementation")

    productFlavors {
        if (!rootProject.file(Config.App.Misc.envsPath).exists()) return@productFlavors

        val envProperties = Properties()
        envProperties.load(File(Config.App.Misc.envsPath).inputStream())

        create("staging") {
            val clientID = envProperties.getProperty("stagingClientID").toString()
            val clientSecret = envProperties.getProperty("stagingClientSecret").toString()
            buildConfigField("String", "CLIENT_ID", clientID)
            buildConfigField("String", "CLIENT_SECRET", clientSecret)
            dimension = "implementation"
            versionNameSuffix = "-staging"
        }

        create("production") {
            val clientID = envProperties.getProperty("prodClientID").toString()
            val clientSecret = envProperties.getProperty("prodClientSecret").toString()
            buildConfigField("String", "CLIENT_ID", clientID)
            buildConfigField("String", "CLIENT_SECRET", clientSecret)
            dimension = "implementation"
            versionNameSuffix = "-production"
        }
    }

    applicationVariants.all(object : Action<ApplicationVariant> {
        override fun execute(variant: ApplicationVariant) {
            variant.outputs.map { it as BaseVariantOutputImpl }.forEach { output ->
                println("The variant is: ${variant.versionName}")
                output.outputFileName = "SpotifyStats-${variant.versionName}.apk"
            }
        }
    })
}

dependencies {
    implementation(Config.Libs.Core.kotlinStd)
    implementation(Config.Libs.Core.material)
    implementation(Config.Libs.Core.coroutinesCore)
    implementation(Config.Libs.Core.coroutinesAndroid)

    implementation(Config.Libs.AndroidX.core)
    implementation(Config.Libs.AndroidX.appCompat)
    implementation(Config.Libs.AndroidX.lifecycle)
    implementation(Config.Libs.AndroidX.datastore)
    implementation(Config.Libs.AndroidX.startup)

    implementation(Config.Libs.Compose.ui)
    implementation(Config.Libs.Compose.material)
    implementation(Config.Libs.Compose.uiTooling)
    implementation(Config.Libs.Compose.foundation)
    implementation(Config.Libs.Compose.materialsIconsCore)
    implementation(Config.Libs.Compose.materialsIconsExtended)
    implementation(Config.Libs.Compose.runtime)
    implementation(Config.Libs.Compose.navigation)
    implementation(Config.Libs.Compose.foundationLayout)
    implementation(Config.Libs.Compose.animation)

    implementation(Config.Libs.DI.hilt)
    kapt(Config.Libs.DI.hiltCompiler)
    implementation(Config.Libs.DI.hiltCommons)
    implementation(Config.Libs.DI.hiltViewModel)
    kapt(Config.Libs.DI.androidxHiltCompiler)

    implementation(Config.Libs.Network.retrofit)
    implementation(Config.Libs.Network.gsonConverter)
    implementation(Config.Libs.Network.retrofitMock)
    implementation(Config.Libs.Network.loggingInterceptor)

    implementation(Config.Libs.Reactive.rxJava)
    implementation(Config.Libs.Reactive.rxAndroid)
    implementation(Config.Libs.Reactive.rxKotlin)
    implementation(Config.Libs.Reactive.rxRetrofitAdapter)

    implementation(Config.Libs.Accompanist.coil)
    implementation(Config.Libs.Accompanist.insets)

    implementation(Config.Libs.Misc.timber)

    testImplementation(Config.Libs.Test.jUnit)

    androidTestImplementation(Config.Libs.AndroidTest.jUnit)
    androidTestImplementation(Config.Libs.AndroidTest.espresso)
}

apply(from = "../spotless.gradle")
