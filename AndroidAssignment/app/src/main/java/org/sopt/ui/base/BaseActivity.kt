package org.sopt.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

open class BaseActivity<T : ViewDataBinding, R : BaseViewModel> : AppCompatActivity() {
    lateinit var binding: T

    open val layoutResourceId: Int = 0

    open val viewModel: R = TODO()

    /**
     * initiate view and click event
     */
    open fun initView() {}

    /**
     * initiate viewmodel, lifecyclerowner and something else..
     */
    open fun initBeforeBinding() {}

    /**
     * initiate others (ex. observe Livedata)
     */
    open fun initAfterBinding() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutResourceId)

        initView()
        initBeforeBinding()
        initAfterBinding()
    }
}