package com.khalid.interviewcracker.dagger;

import javax.inject.Singleton;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

	private Application application;

	public AppModule(Application application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Context provideContext() {
		return application;
	}

}
