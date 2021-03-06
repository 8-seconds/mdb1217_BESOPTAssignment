package org.sopt.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import org.sopt.R

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun shortToastRequireContext(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun makeDialog(context : Context) = Dialog(context, R.style.DialogCustom)

fun setDialog(dialog : Dialog, view : View) {
    dialog.apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        setContentView(view)

        with(window?.attributes) {
            this?.width = WindowManager.LayoutParams.MATCH_PARENT
            this?.height = WindowManager.LayoutParams.WRAP_CONTENT
            this?.verticalWeight = 1F
        }
    }
}

fun Context.startContact(url : String) {
    with(Intent(Intent.ACTION_VIEW)) {
        data = Uri.parse(url)
        startActivity(this)
    }
}

fun setFadeInAnim(context: Context, id : Int): Animation? = AnimationUtils.loadAnimation(context, id)