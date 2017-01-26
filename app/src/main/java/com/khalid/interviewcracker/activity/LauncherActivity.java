package com.khalid.interviewcracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.interviewcracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LauncherActivity extends AppCompatActivity {

	@BindView(R.id.welcome_message)
	TextView welcomeText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher_activity);
		ButterKnife.bind(this);

	}
}
