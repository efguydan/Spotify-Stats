object Config {
    object App {

        object SdkVersions {
            const val min = 21
            const val compile = 30
            const val target = 30
        }

        object Versions {
            const val code = 1
            const val name = "1.0.0"
        }

        object Misc {
            const val appID = "com.efedaniel.spotifystats"
            const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            const val keysPath = "keystore.properties"
            const val envsPath = "envs.properties"
        }

    }

    object Versions {
        const val gradle = "7.0.0-alpha08"
        const val kotlin = "1.4.10"
        const val spotless = "5.7.0"
        const val hilt = "2.29.1-alpha"
        const val androidxHilt = "1.0.0-alpha02"
        const val compose = "1.0.0-alpha07"
        const val composeNavigation = "1.0.0-alpha02"
        const val androidXCore = "1.5.0-alpha05"
        const val appCompat = "1.2.0"
        const val material = "1.2.1"
        const val lifecycle = "2.3.0-beta01"
        const val jUnit = "4.13.1"
        const val androidJUnit = "1.1.2"
        const val espresso = "3.3.0"
        const val timber = "4.7.1"
        const val accompanist = "0.3.3.1"
        const val datastore = "1.0.0-alpha05"
        const val startup = "1.0.0"
        const val retrofit = "2.9.0"
        const val okhttp = "4.9.0"
        const val coroutines = "1.3.9"
        const val rxJava = "3.0.9"
        const val rxKotlin = "3.0.1"
        const val rxAndroid = "3.0.0"
        const val rxRetrofitAdapter = "2.9.0"
    }

    object Plugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless}"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Libs {

        object Core {
            const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val material = "com.google.android.material:material:${Versions.material}"
            const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }

        object AndroidX {
            const val core = "androidx.core:core-ktx:${Versions.androidXCore}"
            const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
            const val startup = "androidx.startup:startup-runtime:${Versions.startup}"
        }

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val uiTooling = "androidx.ui:ui-tooling:${Versions.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
            const val animation = "androidx.compose.animation:animation:${Versions.compose}"
            const val materialsIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
            const val materialsIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
            const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        }

        object DI {
            const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
            const val hiltCommons = "androidx.hilt:hilt-common:${Versions.androidxHilt}"
            const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.androidxHilt}"
            const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.androidxHilt}"

        }

        object Accompanist {
            const val insets = "dev.chrisbanes.accompanist:accompanist-insets:${Versions.accompanist}"
            const val coil = "dev.chrisbanes.accompanist:accompanist-coil:${Versions.accompanist}"
        }

        object Network {
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
            const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        }

        object Reactive {
            const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
            const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"
            const val rxKotlin = "io.reactivex.rxjava3:rxkotlin:${Versions.rxKotlin}"
            const val rxRetrofitAdapter = "com.squareup.retrofit2:adapter-rxjava3:${Versions.rxRetrofitAdapter}"
        }

        object Misc {
            const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        }

        object Test {
            const val jUnit = "junit:junit:${Versions.jUnit}"
        }

        object AndroidTest {
            const val jUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
            const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        }

    }
}