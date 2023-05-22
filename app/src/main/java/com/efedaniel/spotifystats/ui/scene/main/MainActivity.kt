package com.efedaniel.spotifystats.ui.scene.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.efedaniel.spotifystats.ui.proton.theme.ProtonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProtonTheme {
                MainScreen()
            }
        }
    }

    companion object {

        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
