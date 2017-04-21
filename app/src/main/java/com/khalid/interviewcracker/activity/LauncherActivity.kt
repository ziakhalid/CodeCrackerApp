package com.khalid.interviewcracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.interviewcracker.R
import com.khalid.interviewcracker.util.SettingUtils
import kotlinx.android.synthetic.main.launcher_activity.*
import rx.Observable
import java.util.concurrent.TimeUnit

class LauncherActivity : BaseActivity() {

    val welcomeText: TextView by lazy { welcome_message }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        welcomeText.text = resources.getString(R.string.app_name)
        val isUserLoggedIn = SettingUtils.get(this@LauncherActivity, getString(R.string.preference_is_user_login), false)

        val subscription = Observable.timer(1, TimeUnit.SECONDS).subscribe({
            if (isUserLoggedIn) {
                startActivity(Intent(this@LauncherActivity, HomeActivity::class.java))
            } else {
                startActivity(Intent(this@LauncherActivity, LoginActivity::class.java))
            }
            finish()
        })

        subscriptions.add(subscription)
    }
}
