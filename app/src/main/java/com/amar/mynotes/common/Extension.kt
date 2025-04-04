package com.amar.mynotes.common

import android.view.View
import androidx.core.content.ContextCompat
import com.amar.mynotes.R
import com.google.android.material.snackbar.Snackbar

fun View.show() {
     visibility = View.VISIBLE
}

fun View.hide() {
     visibility = View.GONE
}

fun View.showSnackBar(
     message: String,
     duration: Int = Snackbar.LENGTH_SHORT,
     actionLabel: String? = null,
     actionCallback: (() -> Unit)? = null,
     actionTextColorRes: Int = R.color.light_orange
) {
     val snackBar = Snackbar.make(this, message, duration)

     actionLabel?.let {
          snackBar.setAction(it){
               actionCallback?.invoke()
          }
     }

     snackBar.setActionTextColor(ContextCompat.getColor(context, actionTextColorRes))
     snackBar.show()
}