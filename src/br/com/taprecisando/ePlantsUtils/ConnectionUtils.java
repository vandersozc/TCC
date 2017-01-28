package br.com.taprecisando.ePlantsUtils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionUtils extends Activity {

	public ConnectionUtils() {
		super();
	}

	protected boolean isValidConnection() {
		ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		
		return conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable()
				&& conectivtyManager.getActiveNetworkInfo().isConnected();
	}
}