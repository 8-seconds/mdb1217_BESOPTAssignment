package org.sopt.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.data.local.SOPTSharedPreference.setName
import org.sopt.data.remote.model.request.ReqSignIn
import org.sopt.data.remote.model.request.ReqSignUp
import org.sopt.data.repository.UserRepo
import org.sopt.util.Event
import javax.inject.Inject

@HiltViewModel
class UserViewModel@Inject constructor(private val userRepo: UserRepo) : ViewModel() {
    private val _signInEvent = MutableLiveData<Event<Boolean>>()
    val signInEvent: LiveData<Event<Boolean>> = _signInEvent

    private val _signUpEvent = MutableLiveData<Event<Boolean>>()
    val signUpEvent: LiveData<Event<Boolean>> = _signUpEvent


    fun postSignUp(reqSignUp: ReqSignUp) = viewModelScope.launch {
        runCatching { userRepo.postSignUp(reqSignUp) }
            .onSuccess { _signUpEvent.postValue(Event(true)) }
            .onFailure {
                _signUpEvent.postValue(Event(false))
                it.printStackTrace()
            }
    }

    fun postSignIn(reqSignIn: ReqSignIn) = viewModelScope.launch {
        runCatching { userRepo.postSignIn(reqSignIn) }
            .onSuccess {
                _signInEvent.postValue(Event(true))
                setName(it.data.user_nickname)
            }
            .onFailure {
                _signInEvent.postValue(Event(false))
                it.printStackTrace()
            }
    }
}