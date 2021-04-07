package org.sopt.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<T : ViewDataBinding, R : BaseViewModel> : Fragment(){
    lateinit var binding: T

    open val layoutResourceId: Int = 0

    open val viewModel: R by lazy { viewModel }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initBeforeBinding()
        initAfterBinding()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,layoutResourceId, container, false)

        return binding.root
    }
}