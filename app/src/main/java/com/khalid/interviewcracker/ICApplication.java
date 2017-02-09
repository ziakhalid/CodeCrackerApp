package com.khalid.interviewcracker;

import android.app.Application;

import com.interviewcracker.BuildConfig;
import com.khalid.interviewcracker.dagger.AppComponent;
import com.khalid.interviewcracker.dagger.AppModule;
import com.khalid.interviewcracker.dagger.DaggerAppComponent;

import timber.log.Timber;

public class ICApplication extends Application {

	private ICApplication instance;
	private AppComponent appComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}

		appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}

	public ICApplication getInstance() {
		return instance;
	}
}
