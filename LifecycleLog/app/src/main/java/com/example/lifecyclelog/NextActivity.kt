package com.example.lifecyclelog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NextActivity : AppCompatActivity() {
    private lateinit var lifeCycleLog: LifeCycleLog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifeCycleLog = LifeCycleLog(this.lifecycle, localClassName)
    }
}