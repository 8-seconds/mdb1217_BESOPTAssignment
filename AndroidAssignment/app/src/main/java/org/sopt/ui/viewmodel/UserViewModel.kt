package org.sopt.ui.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.data.repository.UserRepo
import org.sopt.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repo : UserRepo) : BaseViewModel() {

}