package org.sopt.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object DataBindingAdapterUtil {
    @JvmStatic
    @BindingAdapter("imgResId")
    fun setImage(image: ImageView, resId: Int) {
        Glide.with(image.context).load(resId).circleCrop().into(image)
    }
}