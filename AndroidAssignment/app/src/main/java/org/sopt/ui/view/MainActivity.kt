package org.sopt.ui.view

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.R
import org.sopt.databinding.ActivityMainBinding
import org.sopt.ui.base.BaseActivity
import org.sopt.ui.view.adapter.MainViewPagerAdapter
import org.sopt.ui.view.contribution.ContributeFragment
import org.sopt.ui.view.mypage.MyPageActivity
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
        initToolbar()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_setting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_modify -> {
                startMyPageActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(binding.tbMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun startMyPageActivity() {
        startActivity(Intent(this, MyPageActivity::class.java))
    }
}