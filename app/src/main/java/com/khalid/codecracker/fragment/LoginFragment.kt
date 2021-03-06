package com.khalid.codecracker.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codecracker.R
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import com.khalid.codecracker.CCApplication
import com.khalid.codecracker.activity.HomeActivity
import com.khalid.codecracker.extensions.inflate
import com.khalid.codecracker.server.NetManager
import kotlinx.android.synthetic.main.login_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class LoginFragment : BaseFragment() {

    @Inject lateinit var netManager: NetManager

    companion object {
        fun getInstance(): LoginFragment {
            val loginFragment = LoginFragment()
            return loginFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CCApplication.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.login_fragment)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        signInButton.isEnabled = false
        editPassword.isEnabled = false

        val editUserNameSubscription = RxTextView.textChanges(editUsername)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    if (it.length >= 6) {
                        editUsername.setTextColor(context.getColor(android.R.color.holo_green_dark))
                        editPassword.isEnabled = true
                    } else{
                        editUsername.setTextColor(context.getColor(android.R.color.black))
                        editPassword.isEnabled = false
                    }
                }

        val editUserPasswordSubscripton = RxTextView.textChanges(editPassword)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    if (it.length >= 6) {
                        editPassword.setTextColor(context.getColor(android.R.color.holo_green_dark))
                        signInButton.isEnabled = true
                    } else{
                        editPassword.setTextColor(context.getColor(android.R.color.black))
                        signInButton.isEnabled = false
                    }
                }

        val signInButtonSubscription = RxView.clicks(signInButton).subscribe {
            val userName = editUsername.text.toString()
            val userPassword = editPassword.text.toString()

            com.khalid.codecracker.util.SettingUtils.save(context, getString(R.string.preference_user_name), userName)
            com.khalid.codecracker.util.SettingUtils.save(context, getString(R.string.preference_user_password), userPassword)
            requestUserAuth()
        }

        subscriptions.add(editUserNameSubscription)
        subscriptions.add(editUserPasswordSubscripton)
        subscriptions.add(signInButtonSubscription)
    }

    private fun requestUserAuth() {

        val subscription = netManager.authenticateLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    if (it.equals("Success")) {
                        startActivity(Intent(context, HomeActivity::class.java))
                        com.khalid.codecracker.util.SettingUtils.save(context, getString(R.string.preference_is_user_login), true)
                        activity.finish()
                    }
                }, { Toast.makeText(context, "Oops! something wrong happned", Toast.LENGTH_SHORT).show() })
        subscriptions.add(subscription)
    }


}