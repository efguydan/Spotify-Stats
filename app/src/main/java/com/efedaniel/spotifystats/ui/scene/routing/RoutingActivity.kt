package com.efedaniel.spotifystats.ui.scene.routing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoutingActivity : ComponentActivity() {

    private val viewModel: RoutingViewModel by viewModels()
    @Inject lateinit var navigator: RoutingNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        navigateToNextScreen()
    }

    private fun navigateToNextScreen() {
        if (viewModel.isUserAuthenticated()) {
            navigator.navigateToMain(this)
        } else {
            navigator.navigateToLogin(this)
        }
    }
}