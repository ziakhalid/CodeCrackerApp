package com.khalid.codecracker.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codecracker.R
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import com.khalid.codecracker.CCApplication
import com.khalid.codecracker.activity.HomeActivity
import com.khalid.codecracker.extensions.inflate
import com.khalid.codecracker.model.ICLoginResponse
import com.khalid.codecracker.services.JavaCrackerServices
import kotlinx.android.synthetic.main.login_fragment.*
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class LoginFragment : BaseFragment() {

    @Inject lateinit var javaCrackerServices: JavaCrackerServices

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
                        editUsername.setTextColor(context.resources.getColor(android.R.color.holo_green_dark))
                        editPassword.isEnabled = true
                    } else {
                        editUsername.setTextColor(context.resources.getColor(android.R.color.black))
                        editPassword.isEnabled = false
                    }
                }

        val editUserPasswordSubscripton = RxTextView.textChanges(editPassword)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it ->
                    if (it.length >= 6) {
                        editPassword.setTextColor(context.resources.getColor(android.R.color.holo_green_dark))
                        signInButton.isEnabled = true
                    } else {
                        editPassword.setTextColor(context.resources.getColor(android.R.color.black))
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
        subscriptions.add(javaCrackerServices.authenticateLogin(getLoginObserver()))
    }

    fun getLoginObserver(): Observer<ICLoginResponse> {
        val loginObserver = object : Observer<ICLoginResponse> {
            override fun onNext(response: ICLoginResponse?) {
                val status = response?.status
                val message = response?.message

                if (status.equals("ACCEPTED") && message.equals("Success")) {
                    startActivity(Intent(context, HomeActivity::class.java))
                    com.khalid.codecracker.util.SettingUtils.save(context, getString(R.string.preference_is_user_login), true)
                    activity.finish()
                }
            }

            override fun onError(e: Throwable?) {
            }

            override fun onCompleted() {
            }

        }
        return loginObserver
    }

}