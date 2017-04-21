package com.khalid.interviewcracker.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.interviewcracker.R
import com.khalid.interviewcracker.fragment.ICPreferenceFragment

open class ICPreferenceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, ICPreferenceFragment())
                .commit()
    }

    override fun setTitle(title: CharSequence) {
        val ab = supportActionBar
        if (ab != null) {
            ab.title = title
        } else {
            super.setTitle(title)
        }
    }

    override fun onBackPressed() {
        //		Log.e("onBackPressed. back stack = " + getFragmentManager().getBackStackEntryCount());
        val fragmentManager = fragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}