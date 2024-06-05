package com.jhostinluna.sprint4.core.extensions

import android.view.View

fun View.gone() {
    this.visibility = View.GONE
}
fun View.visible() {
    this.visibility = View.VISIBLE
}