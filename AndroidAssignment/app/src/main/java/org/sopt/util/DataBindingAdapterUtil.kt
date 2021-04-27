package org.sopt.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.sopt.R
import org.sopt.ui.view.profile.ProfileFragment.Companion.INFORMATION
import org.sopt.ui.view.profile.ProfileFragment.Companion.STUDY
import org.sopt.ui.view.profile.ProfileFragment.Companion.TEAM_PLAY

object DataBindingAdapterUtil {
    @JvmStatic
    @BindingAdapter("imgResId")
    fun setCircleImage(image: ImageView, resId: Int) {
        Glide.with(image.context).load(resId).circleCrop().into(image)
    }

    @JvmStatic
    @BindingAdapter("imgType")
    fun setRepoTypeImage(image: ImageView, type: Int) {
        with(image) {
            when (type) {
                STUDY -> setBackgroundResource(R.drawable.ic_book_mint)
                TEAM_PLAY -> setBackgroundResource(R.drawable.ic_people_mint)
                INFORMATION -> setBackgroundResource(R.drawable.ic_bulb_mint)
            }
        }
    }
}