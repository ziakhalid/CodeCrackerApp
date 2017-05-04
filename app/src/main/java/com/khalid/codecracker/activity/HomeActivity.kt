package com.khalid.codecracker.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.codecracker.R
import com.khalid.codecracker.fragment.HomeFragment
import com.khalid.codecracker.fragment.QAFragment
import rx.Observer


class HomeActivity : BaseActivity() {

    val toolbar by lazy {findViewById(R.id.toolbar) as Toolbar}

    val buttonClickObserver = object : Observer<String>{
        override fun onError(e: Throwable?) {
        }

        override fun onNext(topic: String?) {
            Toast.makeText(this@HomeActivity, "Topic: $topic Selected", Toast.LENGTH_LONG).show()
            changeFragment(QAFragment(), QAFragment::class.java.simpleName, isReplaceFragment = false)
        }

        override fun onCompleted() {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initToolbar()
        setNormalToolbar()
        changeFragment(HomeFragment.getInstance(buttonClickObserver), HomeFragment::class.java.simpleName)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.showOverflowMenu()
    }

    fun setNormalToolbar() {
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        toolbar.title = "TOPICS"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_settings -> startActivity(Intent(this@HomeActivity, ICPreferenceActivity::class.java))
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
        if (isReplaceFragment) ft.replace(R.id.activity_base_content, f) else ft.add(R.id.activity_base_content, f)
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
