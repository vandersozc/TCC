package br.com.taprecisando.ePlantsView;

import br.com.taprecisando.ePlantsView.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class Splash extends Activity {
	private Thread SplashThread;
	private boolean Click_touch = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_splash);

		SplashThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(3000);
						Click_touch = true;
					}
				} catch (InterruptedException ex) {
				}

				if (Click_touch) {
					finish();
					overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
					Intent intent = new Intent();
					intent.setClass(Splash.this, Main.class);
					startActivity(intent);
					overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
				}
			}
		};

		SplashThread.start();
	}

	@Override
	public void onPause() {
		super.onPause();
		SplashThread.interrupt();
	}

	/* Se clicar na tela abre a tela principal */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			synchronized (SplashThread) {

				Click_touch = true;
				SplashThread.notifyAll();
			}
		}
		return true;
	}
}