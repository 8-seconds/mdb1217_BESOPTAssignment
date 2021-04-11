package com.example.lifecyclelog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var lifeCycleLog: LifeCycleLog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifeCycleLog = LifeCycleLog(this.lifecycle, localClassName)

        findViewById<Button>(R.id.btn_intent).setOnClickListener {
            startActivity(Intent(this@MainActivity, NextActivity::class.java))
        }
    }
}