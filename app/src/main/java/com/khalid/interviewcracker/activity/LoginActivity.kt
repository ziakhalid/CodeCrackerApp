package com.khalid.interviewcracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.interviewcracker.R
import com.khalid.interviewcracker.fragment.LoginFragment

class LoginActivity: AppCompatActivity() {

    val toolbar:Toolbar by lazy { findViewById(R.id.toolbar) as Toolbar}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initToolbar()
        setNormalToolbar()
        changeFragment(LoginFragment.getInstance(), LoginFragment::class.java.simpleName)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.showOverflowMenu()
    }

    fun setNormalToolbar() {
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        toolbar.title = "LogIn"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_settings -> startActivity(Intent(this@LoginActivity, ICPreferenceActivity::class.java))
        }
        return true
    }

    fun changeFragment(f: Fragment, className: String? = null, cleanStack: Boolean = false, isReplaceFragment:Boolean = true) {
        val ft = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        if (isReplaceFragment) ft.replace(R.id.login_base_content, f) else ft.add(R.id.activity_base_content, f)
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

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}