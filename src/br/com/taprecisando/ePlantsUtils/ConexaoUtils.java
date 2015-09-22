package br.com.taprecisando.ePlantsUtils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class ConexaoUtils extends Activity {

	public ConexaoUtils() {
		super();
	}

	protected boolean isConexaoValida() {
		ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conectivtyManager.getActiveNetworkInfo() != null
				&& conectivtyManager.getActiveNetworkInfo().isAvailable()
				&& conectivtyManager.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}

}