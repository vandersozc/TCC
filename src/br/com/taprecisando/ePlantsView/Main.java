package br.com.taprecisando.ePlantsView;

import br.com.taprecisando.ePlantsView.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.form_main);

		// A��o do Bot�o Buscar do main
		ImageButton BotaoBuscar = (ImageButton) findViewById(R.id.ic_action_buscar_form_main);
		BotaoBuscar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, BuscarPlanta.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});

		// A��o do Bot�o Listar do main
		ImageButton BotaoListar = (ImageButton) findViewById(R.id.ic_action_listar_form_main);
		BotaoListar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, ListarPlanta.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});

		// A��o do Bot�o Salvar do main
		ImageButton BotaoSalvar = (ImageButton) findViewById(R.id.ic_action_salvar_form_main);
		BotaoSalvar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, EditarPlanta.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}
		});
		// A��o do Bot�o Sobre do main
		ImageButton BotaoSobre = (ImageButton) findViewById(R.id.ic_action_sobre_form_main);
		BotaoSobre.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(Main.this, Sobre.class);
				startActivity(intent);
				overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			}

		});
	}

}