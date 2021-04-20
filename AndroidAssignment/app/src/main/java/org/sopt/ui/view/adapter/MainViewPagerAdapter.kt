package org.sopt.ui.view.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.ui.view.MainActivity

class MainViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    var fragmentList = listOf<Fragment>()

    override fun getItemCount(): Int {
        return fragmentList.count()
    }

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}