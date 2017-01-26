package com.khalid.interviewcracker.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.interviewcracker.R
import com.khalid.interviewcracker.ICApplication
import javax.inject.Inject

class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var context: Context

    @BindView(R.id.welcome_message)
    internal var welcomeText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        ButterKnife.bind(this)

       val myApplication = application as ICApplication
        myApplication.appComponent.inject(this)

//        welcomeText!!.text = context.resources.getString(R.string.app_name)
        Toast.makeText(context, "Welcome sir !!!", Toast.LENGTH_LONG).show()
    }
}
