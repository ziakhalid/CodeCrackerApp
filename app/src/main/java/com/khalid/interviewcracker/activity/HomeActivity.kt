package com.khalid.interviewcracker.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.interviewcracker.R

class HomeActivity : AppCompatActivity() {

    val toolbar by lazy{findViewById(R.id.toolbar) as Toolbar}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
    }
}
