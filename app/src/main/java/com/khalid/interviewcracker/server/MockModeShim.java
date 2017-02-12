package com.khalid.interviewcracker.server;

import java.util.concurrent.CountDownLatch;

import android.content.Context;

import com.interviewcracker.R;
import com.khalid.interviewcracker.dispatcher.ICDispatcher;
import com.khalid.interviewcracker.util.SettingUtils;


public class MockModeShim {

	private static ICMockWebServer server = null;

	public static void initMockWebServer(Context c) {
		final Context context = c.getApplicationContext();
		final CountDownLatch latch = new CountDownLatch(1);

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (server != null) {
					server.shutdown();
					server = null;
				}

				server = new ICMockWebServer(context);

				server.start();

				SettingUtils.save(context, R.string.preference_proxy_server_address, server.getHostWithPort());

				latch.countDown();
			}
		}).start();

		try {
			latch.await();
		}
		catch (Throwable e) {
			throw new RuntimeException("Problem waiting for mock web server to start", e);
		}
	}

	public static ICDispatcher getDispatcher() {
		return server.getDispatcher();
	}
}
