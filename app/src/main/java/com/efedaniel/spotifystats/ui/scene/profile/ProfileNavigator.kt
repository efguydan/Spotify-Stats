package com.efedaniel.spotifystats.ui.scene.profile

import android.app.Activity
import android.content.Intent
import com.efedaniel.spotifystats.ui.scene.auth.LoginActivity
import javax.inject.Inject

class ProfileNavigator @Inject constructor() {

    fun navigateToAuth(activity: Activity) {
        activity.run {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}