package com.khalid.interviewcracker.server;


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
		return "https://" + server + "/";
	}

	public String getMockServerAddress() {
		String server = SettingUtils.get(context, R.string.preference_mock_server_address, "localhost:3000");
		return server;
	}

	public EndPoint getEndPoint() {

		String which = SettingUtils.get(context, context.getString(R.string.preference_which_api_to_use_key), "Mock Mode");

		if (which.equals("Custom Server")) {
			return EndPoint.CUSTOM_SERVER;
		}
		else if (which.equals("Mock Mode")) {
			return EndPoint.MOCK_MODE;
		}
		else {
			return EndPoint.PRODUCTION;
		}
	}

	public String getJavaEndpointUrl() {
		String endpoint;
		switch (getEndPoint()) {
			case MOCK_MODE:
				endpoint = getMockServerAddress();
				break;
			case CUSTOM_SERVER:
				endpoint = getCustomServerAddress();
				break;
			case PRODUCTION:
				endpoint = "";
				break;
			default:
				endpoint = "";

		}
		return endpoint;
	}


}
