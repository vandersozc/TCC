package br.com.taprecisando.ePlantsView;

import br.com.taprecisando.ePlantsDAO.RepositorioPlanta;
import br.com.taprecisando.ePlantsDAO.RepositorioPlantaScript;
import br.com.taprecisando.ePlantsModel.Planta;
import br.com.taprecisando.ePlantsUtils.MaskUtils;
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
	private EditText campoLocalColeta;
	private EditText campoColetor;
	private EditText campoDataColeta;
	private EditText campoDeterminador;
	private EditText campoFormacaoVegetal;
	private EditText campoObservacao;
	
	public static RepositorioPlanta repositorio;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		repositorio = new RepositorioPlantaScript(this);
		setContentView(R.layout.form_buscar_planta);
		
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		campoNome =            (EditText) findViewById(R.id.imput_text_nome_form_buscar);
		campoCientifico =      (EditText) findViewById(R.id.imput_text_nome_cientifico_form_buscar);
		campoFamilia =         (EditText) findViewById(R.id.imput_text_familia_form_buscar);
		campoUtiliza =         (EditText) findViewById(R.id.imput_text_utilizacao_form_buscar);
		campoLocalColeta =     (EditText) findViewById(R.id.imput_text_local_coleta_form_buscar);
		campoColetor = 	 	   (EditText) findViewById(R.id.imput_text_coletor_form_buscar);
		campoDataColeta = 	   (EditText) findViewById(R.id.imput_text_data_coleta_form_buscar);
		campoDeterminador =    (EditText) findViewById(R.id.imput_text_determinador_form_buscar);
		campoFormacaoVegetal = (EditText) findViewById(R.id.imput_text_formacao_vegetal_form_buscar);
		campoObservacao =      (EditText) findViewById(R.id.imput_text_observacao_form_buscar);
		
		campoDataColeta.addTextChangedListener(MaskUtils.insert("##/##/####", campoDataColeta));
		
		ImageButton botaoBuscar = (ImageButton) findViewById(R.id.ic_action_buscar_form_buscar);
		botaoBuscar.setOnClickListener(this);

		ImageButton botaoCancelar = (ImageButton) findViewById(R.id.ic_action_cancelar_form_buscar);
		botaoCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});	
	}
	
	public void onClick(View view) {
		String nomePlanta = campoNome.getText().toString();
		Planta planta = getPlantaPeloNome(nomePlanta);
		
		if (nomePlanta.equals("")){
			AlertDialog.Builder mensagem = new AlertDialog.Builder(BuscarPlanta.this);
			mensagem.setTitle(R.string.text_value_erro); //Msg: Erro!
			mensagem.setMessage(R.string.text_value_msg_01); //Msg: Por favor! informe o nome de uma planta para a busca.
			
			mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int whichButton) {
				}
			});
			mensagem.show();
			return;
		}
		
		if (planta == null) { //Msg: Oops!\nNenhum exemplar encontrado!
			Toast.makeText(BuscarPlanta.this, R.string.text_value_msg_02, Toast.LENGTH_SHORT).show();
		} else {
			campoCientifico.setText(planta != null ? planta.cientifico : "");
			campoFamilia.setText(planta != null ? planta.familia : "");
			campoUtiliza.setText(planta != null ? planta.utiliza : "");
			campoLocalColeta.setText(planta != null ? planta.local_coleta : "");
			campoColetor.setText(planta != null ? planta.coletor : "");
			campoDataColeta.setText(planta != null ? planta.data_coleta : "");
			campoDeterminador.setText(planta != null ? planta.determinador : "");
			campoFormacaoVegetal.setText(planta != null ? planta.formacaoVegetal : "");
			campoObservacao.setText(planta != null ? planta.observacao : "");
		}
	}

	protected Planta getPlantaPeloNome(String nomePlanta) {
		Planta planta = BuscarPlanta.repositorio.buscarPlantaPeloNome(nomePlanta);
		return planta;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		//finish();
	}
}