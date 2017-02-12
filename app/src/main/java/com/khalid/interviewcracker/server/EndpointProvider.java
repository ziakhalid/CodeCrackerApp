package com.khalid.interviewcracker.server;


import java.util.EnumMap;

import android.content.Context;

import com.interviewcracker.R;
import com.khalid.interviewcracker.util.SettingUtils;

public class EndpointProvider {

	private Context context;

	public EndpointProvider(Context context) {
		this.context = context;
	}

	public String getCustomServerAddress() {
		String server = SettingUtils.get(context, R.string.preference_proxy_server_address, "localhost:3000");
		return server;
	}

/*
	public String getCustomServerAddress() {
		String server = SettingUtils.get(context, R.string.preference_proxy_server_address, "localhost:3000");
		return "https://" + server + "/";
	}
*/

}
