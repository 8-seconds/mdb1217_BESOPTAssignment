package org.sopt.util

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun setFadeInAnim(context: Context, id : Int): Animation? = AnimationUtils.loadAnimation(context, id)