package br.com.taprecisando.ePlantsView;

import br.com.taprecisando.ePlantsController.Mask;
import br.com.taprecisando.ePlantsController.Planta;
import br.com.taprecisando.ePlantsDAO.RepositorioPlanta;
import br.com.taprecisando.ePlantsDAO.RepositorioPlantaScript;
import br.com.taprecisando.ePlantsView.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class BuscarPlanta extends Activity implements OnClickListener {
	private EditText campoNome;
	private EditText campoCientifico;
	private EditText campoFamilia;
	private EditText campoUtiliza;
	private EditText campoLocal_coleta;
	private EditText campoColetor;
	private EditText campoData_coleta;
	private EditText campoDeterminador;
	private EditText campoFormacaoVegetal;
	private EditText campoObservacao;
	
	public static RepositorioPlanta repositorio;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		//Instância do banco de dados
		repositorio = new RepositorioPlantaScript(this);
		setContentView(R.layout.form_buscar_planta);
		
		//Desabilita subida automática do teclado
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		campoNome =            (EditText) findViewById(R.id.imput_text_nome_form_buscar);
		campoCientifico =      (EditText) findViewById(R.id.imput_text_nome_cientifico_form_buscar);
		campoFamilia =         (EditText) findViewById(R.id.imput_text_familia_form_buscar);
		campoUtiliza =         (EditText) findViewById(R.id.imput_text_utilizacao_form_buscar);
		campoLocal_coleta =    (EditText) findViewById(R.id.imput_text_local_coleta_form_buscar);
		campoColetor = 	 	   (EditText) findViewById(R.id.imput_text_coletor_form_buscar);
		campoData_coleta = 	   (EditText) findViewById(R.id.imput_text_data_coleta_form_buscar);
		campoDeterminador =    (EditText) findViewById(R.id.imput_text_determinador_form_buscar);
		campoFormacaoVegetal = (EditText) findViewById(R.id.imput_text_formacao_vegetal_form_buscar);
		campoObservacao =      (EditText) findViewById(R.id.imput_text_observacao_form_buscar);
		
		//Máscara da data
		campoData_coleta.addTextChangedListener(Mask.insert("##/##/####", campoData_coleta));
		
		ImageButton btBuscar = (ImageButton) findViewById(R.id.ic_action_buscar_form_buscar);
		btBuscar.setOnClickListener(this);

		//Evento do botão cancelar ou voltar
		ImageButton btCancelar = (ImageButton) findViewById(R.id.ic_action_cancelar_form_buscar);
		btCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});	
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		//finish();
	}

	public void onClick(View view) {
		
		String nomePlanta = campoNome.getText().toString();
		Planta planta = buscarPlanta(nomePlanta);
		
		if (nomePlanta.equals("")){
			AlertDialog.Builder alerta = new AlertDialog.Builder(BuscarPlanta.this);
			alerta.setTitle(R.string.msg_erro); //Msg: Erro!
			alerta.setMessage(R.string.msg01_form_buscar); //Msg: Por favor! informe o nome de uma planta para a busca.
			alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int whichButton) {
				}
			});
			alerta.show();
			return;
		}
		
		if (planta != null) {
			campoCientifico.setText(planta.cientifico);
			campoFamilia.setText(planta.familia);
			campoUtiliza.setText(planta.utiliza);
			campoLocal_coleta.setText(planta.local_coleta);
			campoColetor.setText(planta.coletor);
			campoData_coleta.setText(planta.data_coleta);
			campoDeterminador.setText(planta.determinador);
			campoFormacaoVegetal.setText(planta.formacaoVegetal);
			campoObservacao.setText(planta.observacao);

		} else {
			campoCientifico.setText("");
			campoFamilia.setText("");
			campoUtiliza.setText("");
			campoLocal_coleta.setText("");
			campoColetor.setText("");
			campoData_coleta.setText("");
			campoDeterminador.setText("");
			campoFormacaoVegetal.setText("");
			campoObservacao.setText("");
			
			//Msg: Oops!\nNenhum exemplar encontrado!
			Toast.makeText(BuscarPlanta.this, R.string.msg03_form_buscar, Toast.LENGTH_SHORT).show();
		}
	}

	// Busca uma planta pelo nome
	protected Planta buscarPlanta(String nomePlanta) {
		Planta planta = BuscarPlanta.repositorio.buscarPlantaPorNome(nomePlanta);
		return planta;
	}
}