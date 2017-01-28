package br.com.taprecisando.ePlantsView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import br.com.taprecisando.ePlantsDAO.RepositorioPlanta;
import br.com.taprecisando.ePlantsDAO.RepositorioPlantaScript;
import br.com.taprecisando.ePlantsExport.MontaArquivoExportacao;
import br.com.taprecisando.ePlantsModel.Planta;
import br.com.taprecisando.ePlantsModel.Planta.Plantas;
import br.com.taprecisando.ePlantsUtils.ConnectionUtils;
import br.com.taprecisando.ePlantsUtils.MaskUtils;
import br.com.taprecisando.ePlantsUtils.CardUtils;
import br.com.taprecisando.ePlantsView.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class EditarPlanta extends ConnectionUtils {

	private Long id;
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
	private String tipoArquivo;

	private static final String PASTA = "ePlants";
	private static final String CATEGORIA = "planta";
	public static RepositorioPlanta repositorio;
	
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		repositorio = new RepositorioPlantaScript(this);
		setContentView(R.layout.form_editar_planta);

		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		campoNome = (EditText) findViewById(R.id.imput_text_nome_form_editar);
		campoCientifico = (EditText) findViewById(R.id.imput_text_nome_cientifico_form_editar);
		campoFamilia = (EditText) findViewById(R.id.imput_text_familia_form_editar);
		campoUtiliza = (EditText) findViewById(R.id.imput_text_utilizacao_form_editar);
		campoLocalColeta = (EditText) findViewById(R.id.imput_text_local_coleta_form_editar);
		campoColetor = (EditText) findViewById(R.id.imput_text_coletor_form_editar);
		campoDataColeta = (EditText) findViewById(R.id.imput_text_data_coleta_form_editar);
		campoDeterminador = (EditText) findViewById(R.id.imput_text_determinador_form_editar);
		campoFormacaoVegetal = (EditText) findViewById(R.id.imput_text_formacao_vegetal_form_editar);
		campoObservacao = (EditText) findViewById(R.id.imput_text_observacao_form_editar);
		id = null;
		
		campoDataColeta.addTextChangedListener(MaskUtils.insert("##/##/####", campoDataColeta));
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		campoDataColeta.setText(format.format(new Date()));		

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(Plantas._ID);
			if (id != null) {
				Planta p = buscarPlanta(id);
				campoNome.setText(p.nome);
				campoCientifico.setText(p.cientifico);
				campoFamilia.setText(p.familia);
				campoUtiliza.setText(p.utiliza);
				campoLocalColeta.setText(p.local_coleta);
				campoColetor.setText(p.coletor);
				campoDataColeta.setText(p.data_coleta);
				campoDeterminador.setText(p.determinador);
				campoFormacaoVegetal.setText(p.formacaoVegetal);
				campoObservacao.setText(p.observacao);
			}
		}

		ImageButton botaoGoogleImagem = (ImageButton) findViewById(R.id.ic_action_foto_form_editar);
		botaoGoogleImagem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				if (isValidConnection()) {
					mostraImagemGoogle();
				} else {
					AlertDialog.Builder mensagem = new AlertDialog.Builder(EditarPlanta.this);
					mensagem.setTitle(R.string.text_value_erro); //Msg: Erro!
					mensagem.setMessage(R.string.text_value_msg_03);//Msg: Você não está conectado a internet!Por favor tente mais tarde.
					mensagem.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// EditarPlanta.this.finish();
						}
					});
					mensagem.show();
					return;
				}
			}
		});

		ImageButton botaoCancelar = (ImageButton) findViewById(R.id.ic_action_cancelar_form_editar);
		botaoCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});

		ImageButton botaoSalvar = (ImageButton) findViewById(R.id.ic_action_salvar_form_editar);
		botaoSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				salvar();
			}
		});

		ImageButton botaoExcluir = (ImageButton) findViewById(R.id.ic_action_excluir_form_editar);
		if (id == null) {
			botaoExcluir.setVisibility(View.INVISIBLE);
		} else {
			botaoExcluir.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					excluir();
					Toast.makeText(EditarPlanta.this, R.string.text_value_msg_04, Toast.LENGTH_SHORT).show();//Msg: Exemplar excluído com sucesso!
				}
			});
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.editar_planta, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		Planta planta = new Planta();
		
		planta.nome = 			 campoNome.getText().toString().trim();
		planta.cientifico = 	 campoCientifico.getText().toString().trim();
		planta.familia = 	 	 campoFamilia.getText().toString().trim();
		planta.utiliza = 		 campoUtiliza.getText().toString().trim();
		planta.local_coleta =	 campoLocalColeta.getText().toString().trim();
		planta.coletor = 	 	 campoColetor.getText().toString().trim();
		planta.data_coleta = 	 campoDataColeta.getText().toString().trim();
		planta.determinador = 	 campoDeterminador.getText().toString().trim();
		planta.formacaoVegetal = campoFormacaoVegetal.getText().toString().trim();
		planta.observacao = 	 campoObservacao.getText().toString().trim();
		
		switch (item.getItemId()) {
			case R.id.ic_action_export:
	
				if (planta.nome.equals("")) {
					AlertDialog.Builder mensagem = new AlertDialog.Builder(EditarPlanta.this);
					mensagem.setTitle(R.string.text_value_erro); //Erro!
					mensagem.setMessage(R.string.text_value_msg_05);//Msg: Obrigatório informar o nome comum da planta para a exportação!
					mensagem.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							});
					mensagem.show();
					return false;
				} else {
					ArrayList<String> itens = new ArrayList<String>();
					itens.add(".txt");
					itens.add(".csv");
	
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.xml.alerta_exportacao, itens);
	
					final AlertDialog.Builder mensagem = new AlertDialog.Builder(EditarPlanta.this);
					mensagem.setTitle(R.string.text_value_msg_06); //Msg: Escolha o formato de exportação
					mensagem.setSingleChoiceItems(adapter, 0,new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface arg0, int arg1) {
									switch (arg1) {
										case 0:
											tipoArquivo = ".txt";
											break;
										case 1:
											tipoArquivo = ".csv";
											break;
										default:
											break;
									}
									arg0.cancel();
									salvarSdCard();
								}
							});
					mensagem.show();
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	public void salvar() {
		Planta planta = new Planta();

		if (id != null) planta.id = id;
		planta.nome = campoNome.getText().toString().trim();
		planta.cientifico = campoCientifico.getText().toString().trim();
		planta.familia = campoFamilia.getText().toString().trim();
		planta.utiliza = campoUtiliza.getText().toString().trim();
		planta.local_coleta = campoLocalColeta.getText().toString().trim();
		planta.coletor = campoColetor.getText().toString().trim();
		planta.data_coleta = campoDataColeta.getText().toString().trim();
		planta.determinador = campoDeterminador.getText().toString().trim();
		planta.formacaoVegetal = campoFormacaoVegetal.getText().toString().trim();
		planta.observacao = campoObservacao.getText().toString().trim();
		
		if (planta.nome.equals("")) {
			AlertDialog.Builder mensagem = new AlertDialog.Builder(EditarPlanta.this);
			mensagem.setTitle(R.string.text_value_erro); //Msg: Erro!
			mensagem.setMessage(R.string.text_value_msg_07);//Msg: Por favor! informe o nome da planta antes de gravar.
			mensagem.setPositiveButton("OK",new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog,
						int whichButton) {
				}
			});
			mensagem.show();
			return;
		} else if (planta.cientifico.equals("") 
						|| (planta.familia.equals(""))
						|| (planta.utiliza.equals(""))
						|| (planta.local_coleta.equals(""))
						|| (planta.coletor.equals(""))
						|| (planta.data_coleta.equals(""))
						|| (planta.determinador.equals(""))
						|| (planta.formacaoVegetal.equals(""))) {
			Toast.makeText(EditarPlanta.this, R.string.text_value_msg_08,Toast.LENGTH_SHORT).show();//Msg: Planta gravada com sucesso!
			Toast.makeText(EditarPlanta.this,R.string.text_value_msg_09,Toast.LENGTH_LONG).show();//Msg: Algumas informações importantes referente a planta não foram informadas!
		}
		salvarPlanta(planta);
		setResult(RESULT_OK, new Intent());
		finish();
	}

	protected void salvarSdCard() {
		Planta planta = new Planta();
		MontaArquivoExportacao arq = new MontaArquivoExportacao(); 
		
		planta.nome = campoNome.getText().toString().trim();
		planta.cientifico = campoCientifico.getText().toString().trim();
		planta.familia = campoFamilia.getText().toString().trim();
		planta.utiliza = campoUtiliza.getText().toString().trim();
		planta.local_coleta = campoLocalColeta.getText().toString().trim();
		planta.coletor = campoColetor.getText().toString().trim();
		planta.data_coleta = campoDataColeta.getText().toString().trim();
		planta.determinador = campoDeterminador.getText().toString().trim();
		planta.formacaoVegetal = campoFormacaoVegetal.getText().toString().trim();
		planta.observacao = campoObservacao.getText().toString().trim();
		
		String texto = arq.montaTexto(planta.nome,
				  					  planta.cientifico,
									  planta.familia,
									  planta.utiliza,
									  planta.local_coleta,
									  planta.coletor,
									  planta.data_coleta,
									  planta.determinador,
									  planta.formacaoVegetal,
									  planta.observacao);

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(planta.nome.replaceAll(" ", "_")).append(tipoArquivo);
		String NomeArquivo = strBuilder.toString();

		try {
			File f = CardUtils.getSdCardFile(PASTA, NomeArquivo);
			FileOutputStream out = new FileOutputStream(f, true);
			
			out.write("\n".getBytes());
			out.write(texto.getBytes());
			out.close();

			if (planta.cientifico.equals("") 
					|| (planta.familia.equals(""))
					|| (planta.utiliza.equals(""))
					|| (planta.local_coleta.equals(""))
					|| (planta.coletor.equals(""))
					|| (planta.data_coleta.equals(""))
					|| (planta.determinador.equals(""))
					|| (planta.formacaoVegetal.equals(""))) {
				Toast.makeText(EditarPlanta.this,R.string.text_value_msg_10, Toast.LENGTH_LONG).show();//Msg: Algumas informações estão faltando na geração do arquivo!
			}
			Toast.makeText(EditarPlanta.this,R.string.text_value_msg_10, Toast.LENGTH_SHORT).show();//Msg: Arquivo gerado com sucesso!

			Log.i(CATEGORIA, texto + " - Escrito com sucesso");
		} catch (FileNotFoundException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}
	
	public void mostraImagemGoogle() {
		Planta planta = new Planta();
		planta.nome = campoNome.getText().toString().trim();

		String NomePlanta = planta.nome.toString();
		String GoogleImagem = "https://www.google.com.br/search?site=imghp&tbm=isch&source=hp&biw=1366&bih=657&q=planta+";

		if (NomePlanta.equals("")) {
			AlertDialog.Builder mensagem = new AlertDialog.Builder(EditarPlanta.this);
			mensagem.setTitle(R.string.text_value_erro); //Msg: Erro!
			mensagem.setMessage(R.string.text_value_msg_12);//Msg: Por favor! informe o nome de uma planta para visualizar a imagem.
			mensagem.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int whichButton) {
				}
			});
			mensagem.show();
			return;
		}
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(GoogleImagem).append(NomePlanta);
		String URLCompleta = strBuilder.toString();

		Uri uri = Uri.parse(URLCompleta);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);

	}

	public void excluir() {
		if (id != null) excluirPlanta(id);
		setResult(RESULT_OK, new Intent());
		finish();
	}

	protected Planta buscarPlanta(long id) {
		return EditarPlanta.repositorio.buscarPlanta(id);
	}

	protected void salvarPlanta(Planta planta) {
		EditarPlanta.repositorio.salvar(planta);
	}

	protected void excluirPlanta(long id) {
		EditarPlanta.repositorio.deletar(id);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// finish();
	}
}