package com.example.lifecyclelog

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifeCycleLog(private val lifecycle: Lifecycle, private val className : String) : LifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun createLog() {
        Log.d(className, lifecycle.currentState.toString())
    }


}