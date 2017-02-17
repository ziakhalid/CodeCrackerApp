package com.khalid.interviewcracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.interviewcracker.R
import kotlinx.android.synthetic.main.launcher_activity.*
import rx.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class LauncherActivity : AppCompatActivity() {

    val welcomeText: TextView by lazy { welcome_message }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        welcomeText.text = resources.getString(R.string.app_name)
        Observable.timer(5, TimeUnit.SECONDS).subscribe({ it -> startActivity(Intent(this@LauncherActivity, HomeActivity::class.java)) })

    }
}
