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
        }

    }

    object Versions {
        const val gradle = "4.2.0-alpha15"
        const val kotlin = "1.4.10"
        const val spotless = "5.7.0"
        const val compose = "1.0.0-alpha06"
        const val composeNavigation = "1.0.0-alpha01"
        const val androidXCore = "1.3.2"
        const val appCompat = "1.2.0"
        const val material = "1.2.1"
        const val lifecycle = "2.3.0-beta01"
        const val jUnit = "4.13.1"
        const val androidJUnit = "1.1.2"
        const val espresso = "3.3.0"
        const val timber = "4.7.1"
        const val accompanist = "0.3.3.1"
    }

    object Plugins {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val spotless = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless}"
    }

    object Libs {

        object Core {
            const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val material = "com.google.android.material:material:${Versions.material}"
        }

        object AndroidX {
            const val core = "androidx.core:core-ktx:${Versions.androidXCore}"
            const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        }

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val uiTooling = "androidx.ui:ui-tooling:${Versions.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val materialsIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
            const val materialsIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
            const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        }

        object Accompanist {
            const val insets = "dev.chrisbanes.accompanist:accompanist-insets:${Versions.accompanist}"
            const val coil = "dev.chrisbanes.accompanist:accompanist-coil:${Versions.accompanist}"
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