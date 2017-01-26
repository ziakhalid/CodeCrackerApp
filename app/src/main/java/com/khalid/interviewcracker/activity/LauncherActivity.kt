package com.khalid.interviewcracker.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.interviewcracker.R

class LauncherActivity : AppCompatActivity() {

    val welcomeText: TextView by lazy { findViewById(R.id.welcome_message) as TextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        welcomeText.text = resources.getString(R.string.app_name)
    }
}
