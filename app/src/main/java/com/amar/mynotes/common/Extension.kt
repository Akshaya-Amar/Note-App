package com.amar.mynotes.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.show() {
     visibility = View.VISIBLE
}

fun View.hide() {
     visibility = View.GONE
}

fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
     Snackbar.make(this, message, duration).show()
}