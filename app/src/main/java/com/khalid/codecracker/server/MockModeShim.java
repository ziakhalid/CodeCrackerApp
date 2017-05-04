package com.khalid.codecracker.server;

import java.util.concurrent.CountDownLatch;

import android.content.Context;

import com.codecracker.R;
import com.khalid.codecracker.dispatcher.ICDispatcher;
import com.khalid.codecracker.util.SettingUtils;


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
				server = new ICMockWebServer();
				server.start();
				SettingUtils.save(context, R.string.preference_mock_server_address, server.getHostWithPort());
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

	public static void shutdownMockWebServer(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (server != null){
					server.shutdown();
					server = null;
				}
			}
		}).start();
	}

	public static ICDispatcher getDispatcher() {
		return server.getDispatcher();
	}
}
