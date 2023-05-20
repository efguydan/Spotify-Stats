package com.efedaniel.spotifystats.utility.extensions

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun findActivity(): Activity = LocalContext.current.findActivity()