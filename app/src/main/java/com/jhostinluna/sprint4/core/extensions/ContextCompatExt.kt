package com.jhostinluna.sprint4.core.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.checkLocationPermission() = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)