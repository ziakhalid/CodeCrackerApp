package com.khalid.interviewcracker.dagger;

import javax.inject.Singleton;

import com.khalid.interviewcracker.activity.LauncherActivity;

import dagger.Component;

@Component(modules = { AppModule.class })
@Singleton
public interface AppComponent {
	void inject(LauncherActivity target);

}
