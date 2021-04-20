package org.sopt.ui.view

import android.view.MenuItem
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.ActivityMainBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.view.adapter.MainViewPagerAdapter
import org.sopt.ui.view.contribution.ContributeFragment
import org.sopt.ui.view.profile.ProfileFragment
import org.sopt.ui.view.star.StarFragment
import org.sopt.ui.viewmodel.HomeViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: HomeViewModel by viewModels()
    lateinit var mainViewPagerAdapter: MainViewPagerAdapter

    override fun initView() {
        initMainViewPager()
        initMainBottomNavigation()
    }

    private fun initMainViewPager() {
        mainViewPagerAdapter = MainViewPagerAdapter(this@MainActivity)
        with (mainViewPagerAdapter) {
            fragmentList = listOf(ProfileFragment(), StarFragment(), ContributeFragment())

            binding.vpMain.adapter = this
        }
    }

    private fun whichNavigationItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_profile -> {
                binding.vpMain.currentItem = 0
                binding.title = getString(R.string.profile_korea)
            }
            R.id.menu_star -> {
                binding.vpMain.currentItem = 1
                binding.title = getString(R.string.favorites)
            }
            R.id.menu_contribute -> {
                binding.vpMain.currentItem = 2
                binding.title = getString(R.string.grass)
            }
        }
    }

    private fun initMainBottomNavigation() {
        binding.bnvMain.setOnNavigationItemSelectedListener {
            whichNavigationItemSelected(it)
            true
        }
    }
}