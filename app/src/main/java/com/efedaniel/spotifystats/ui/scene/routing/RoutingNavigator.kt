package com.efedaniel.spotifystats.ui.scene.routing

import android.app.Activity
import android.content.Intent
import com.efedaniel.spotifystats.ui.scene.auth.LoginActivity
import com.efedaniel.spotifystats.ui.scene.main.MainActivity
import javax.inject.Inject

class RoutingNavigator @Inject constructor() {

    fun navigateToMain(activity: Activity) {
        activity.run {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun navigateToLogin(activity: Activity) {
        activity.run {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}