package com.khalid.codecracker.fragment

import android.app.AlarmManager
import android.app.LauncherActivity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.ListPreference
import com.codecracker.BuildConfig
import com.codecracker.R

class CCPreferenceFragment : BasePreferenceFragment() {

    override fun onStart() {
        super.onStart()
        activity.setTitle(R.string.settings)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            addPreferencesFromResource(R.xml.preferences_dev)
            val apiKey = getString(R.string.preference_which_api_to_use_key)
            val apiPref = findPreference(apiKey) as ListPreference
            apiPref.setOnPreferenceChangeListener { preference, newValue ->

                if (newValue.equals("Mock Mode")) {
                    com.khalid.codecracker.util.SettingUtils.save(activity, getString(R.string.preference_which_api_to_use_key), "Mock Mode")
                    restartApp()
                }
                true
            }
        }

    }

    private fun restartApp() {
        val mStartActivity = Intent(activity, LauncherActivity::class.java)
        val mPendingIntentId = 123456
        val mPendingIntent = PendingIntent
                .getActivity(activity, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT)
        val mgr = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
        System.exit(0)
    }

}