package br.com.taprecisando.ePlantsView;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

public class Main extends Activity {

	private AlertDialog alerta;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.form_main);

		ImageButton BotaoBuscar = (ImageButton) findViewById(R.id.ic_action_buscar_form_main);
		BotaoBuscar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, BuscarPlanta.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
			}
		});

		ImageButton BotaoListar = (ImageButton) findViewById(R.id.ic_action_listar_form_main);
		BotaoListar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, ListarPlanta.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
			}
		});

		ImageButton BotaoSalvar = (ImageButton) findViewById(R.id.ic_action_salvar_form_main);
		BotaoSalvar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, EditarPlanta.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
			}
		});

		ImageButton BotaoSobre = (ImageButton) findViewById(R.id.ic_action_sobre_form_main);
		BotaoSobre.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, Sobre.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	 @Override
	 public boolean onOptionsItemSelected(MenuItem menu) {
		 switch (menu.getItemId()) {
		 	case R.id.id_menu_idiomas:
		 		menuEscolhaIdiomas();
		 	default:
		 		 return super.onOptionsItemSelected(menu);
		 }
	 }

	private void menuEscolhaIdiomas() {
		ArrayList<String> idiomas = new ArrayList<String>();
		
		idiomas.add(getString(R.string.text_value_idioma_portugues));
		idiomas.add(getString(R.string.text_value_idioma_ingles));
		idiomas.add(getString(R.string.text_value_idioma_espanhol));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.xml.menu_idioma, idiomas);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.text_value_idiomas);
		builder.setSingleChoiceItems(adapter, 0,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						switch (arg1) {
						case 0:
							setLocale("pt");
							alerta.dismiss();
							exibeLoadingIdioma();
							
							break;
						case 1:
							setLocale("en");
							alerta.dismiss();
							exibeLoadingIdioma();
							break;
						case 2:
							setLocale("es");
							alerta.dismiss();
							exibeLoadingIdioma();
						default:
							break;
						}
					}
				});
		alerta = builder.create();
		alerta.show();
	}
	
	public void setLocale(String localeName) {
        Locale locale = new Locale(localeName);
        Locale.setDefault(locale);

        Configuration configuration = getResources().getConfiguration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }
	
	public void exibeLoadingIdioma() {
		final ProgressDialog progress = ProgressDialog.show(Main.this, "", getString(R.string.text_value_atualizar_idioma), true);
	    Runnable progressRunnable = new Runnable() {
	        @Override
	        public void run() {
	            progress.cancel();
	        }
	    };
	    Handler pdCanceller = new Handler();
	    pdCanceller.postDelayed(progressRunnable, 2500);
	}
}