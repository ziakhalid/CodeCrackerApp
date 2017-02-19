package com.khalid.interviewcracker.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.interviewcracker.R
import com.khalid.interviewcracker.fragment.HomeFragment
import com.khalid.interviewcracker.fragment.QAFragment
import rx.Observer


class HomeActivity : AppCompatActivity() {

    val toolbar by lazy{findViewById(R.id.toolbar) as Toolbar}

    val buttonClickObserver = object : Observer<String>{
        override fun onError(e: Throwable?) {
        }

        override fun onNext(topic: String?) {
            Toast.makeText(this@HomeActivity, "Topic: $topic Selected", Toast.LENGTH_LONG).show()
            changeFragment(QAFragment(), QAFragment::class.java.simpleName)
        }

        override fun onCompleted() {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        changeFragment(HomeFragment(buttonClickObserver), HomeFragment::class.java.simpleName)
    }


    fun changeFragment(f: Fragment, className: String? = null, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.activity_base_content, f)
        ft.addToBackStack(className)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }

}
