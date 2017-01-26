package com.khalid.interviewcracker.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.interviewcracker.R

import butterknife.BindView
import butterknife.ButterKnife

class LauncherActivity : AppCompatActivity() {

    @BindView(R.id.welcome_message)
    internal var welcomeText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        ButterKnife.bind(this)

    }
}
