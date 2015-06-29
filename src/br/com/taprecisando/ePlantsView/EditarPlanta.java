package br.com.taprecisando.ePlantsView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import br.com.taprecisando.ePlantsController.Mask;
import br.com.taprecisando.ePlantsController.Planta;
import br.com.taprecisando.ePlantsController.Planta.Plantas;
import br.com.taprecisando.ePlantsDAO.RepositorioPlanta;
import br.com.taprecisando.ePlantsDAO.RepositorioPlantaScript;
import br.com.taprecisando.ePlantsExport.MontaArquivo;
import br.com.taprecisando.ePlantsExport.SdCardUtils;
import br.com.taprecisando.ePlantsView.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
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
public class EditarPlanta extends Activity {

	private Long id;
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
	private String tipoArquivo;

	private static final String PASTA = "ePlants";
	private static final String CATEGORIA = "planta";
	public static RepositorioPlanta repositorio;
	
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		//Instância do banco de dados
		repositorio = new RepositorioPlantaScript(this);
		setContentView(R.layout.form_editar_planta);

		// Desabilita subida automática do teclado
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		campoNome = (EditText) findViewById(R.id.imput_text_nome_form_editar);
		campoCientifico = (EditText) findViewById(R.id.imput_text_nome_cientifico_form_editar);
		campoFamilia = (EditText) findViewById(R.id.imput_text_familia_form_editar);
		campoUtiliza = (EditText) findViewById(R.id.imput_text_utilizacao_form_editar);
		campoLocal_coleta = (EditText) findViewById(R.id.imput_text_local_coleta_form_editar);
		campoColetor = (EditText) findViewById(R.id.imput_text_coletor_form_editar);
		campoData_coleta = (EditText) findViewById(R.id.imput_text_data_coleta_form_editar);
		campoDeterminador = (EditText) findViewById(R.id.imput_text_determinador_form_editar);
		campoFormacaoVegetal = (EditText) findViewById(R.id.imput_text_formacao_vegetal_form_editar);
		campoObservacao = (EditText) findViewById(R.id.imput_text_observacao_form_editar);
		
		id = null;
		
		//Máscara
		campoData_coleta.addTextChangedListener(Mask.insert("##/##/####", campoData_coleta));
		// Seta a data atual ao inserir
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		campoData_coleta.setText(format.format(new Date()));		

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(Plantas._ID);

			if (id != null) {
				Planta p = buscarPlanta(id);
				campoNome.setText(p.nome);
				campoCientifico.setText(p.cientifico);
				campoFamilia.setText(p.familia);
				campoUtiliza.setText(p.utiliza);
				campoLocal_coleta.setText(p.local_coleta);
				campoColetor.setText(p.coletor);
				campoData_coleta.setText(p.data_coleta);
				campoDeterminador.setText(p.determinador);
				campoFormacaoVegetal.setText(p.formacaoVegetal);
				campoObservacao.setText(p.observacao);
				
			}
		}

		// Evento que chama o método mostraImagem(), para abrir o Google imagens.
		ImageButton btImagem = (ImageButton) findViewById(R.id.ic_action_foto_form_editar);
		btImagem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				if (testConnection()) {
					mostraImagem();
				} else {
					AlertDialog.Builder alerta = new AlertDialog.Builder(EditarPlanta.this);
					alerta.setTitle(R.string.msg_erro); //Msg: Erro!
					//Msg: Você não está conectado a internet!Por favor tente mais tarde.
					alerta.setMessage(R.string.msg01_form_editar);
					alerta.setPositiveButton("OK",new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int whichButton) {
									// EditarPlanta.this.finish();
								}
							});
					alerta.show();
					return;
				}
			}
		});

		// Evento do botão cancelar ou voltar
		ImageButton btCancelar = (ImageButton) findViewById(R.id.ic_action_cancelar_form_editar);
		btCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				finish();
			}
		});

		// Evento do botão Salvar, chama o método Salvar()
		ImageButton btSalvar = (ImageButton) findViewById(R.id.ic_action_salvar_form_editar);
		btSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				salvar();
			}
		});

		ImageButton btExcluir = (ImageButton) findViewById(R.id.ic_action_excluir_form_editar);
		if (id == null) {
			btExcluir.setVisibility(View.INVISIBLE);
		} else {
			btExcluir.setOnClickListener(new OnClickListener() {
				public void onClick(View view) {
					excluir();
					//Msg: Exemplar excluído com sucesso!
					Toast.makeText(EditarPlanta.this, R.string.msg03_form_editar, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	/*-----------------------Criação do Menu ----------------------------------------*/
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
		planta.local_coleta =	 campoLocal_coleta.getText().toString().trim();
		planta.coletor = 	 	 campoColetor.getText().toString().trim();
		planta.data_coleta = 	 campoData_coleta.getText().toString().trim();
		planta.determinador = 	 campoDeterminador.getText().toString().trim();
		planta.formacaoVegetal = campoFormacaoVegetal.getText().toString().trim();
		planta.observacao = 	 campoObservacao.getText().toString().trim();
		
		switch (item.getItemId()) {
			case R.id.ic_action_export:
	
				if (planta.nome.equals("")) {
					AlertDialog.Builder alerta = new AlertDialog.Builder(
							EditarPlanta.this);
					alerta.setTitle(R.string.msg_erro); //Erro!
					//Msg: Obrigatório informar o nome comum da planta para a exportação!
					alerta.setMessage(R.string.msg04_form_editar);
					alerta.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
	
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							});
					alerta.show();
					return false;
				} else {
	
					ArrayList<String> itens = new ArrayList<String>();
					itens.add(".txt");
					itens.add(".csv");
	
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.xml.alerta, itens);
	
					final AlertDialog.Builder alerta = new AlertDialog.Builder(EditarPlanta.this);
					alerta.setTitle(R.string.msg05_form_editar); //Msg: Escolha o formado de exportação
	
					alerta.setSingleChoiceItems(adapter, 0,new DialogInterface.OnClickListener() {
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
					alerta.show();
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	

	// Método para salvar planta no banco de dados
	public void salvar() {
		Planta planta = new Planta();

		if (id != null) {
			// É uma atualização
			planta.id = id;
		}
		planta.nome = campoNome.getText().toString().trim();
		planta.cientifico = campoCientifico.getText().toString().trim();
		planta.familia = campoFamilia.getText().toString().trim();
		planta.utiliza = campoUtiliza.getText().toString().trim();
		planta.local_coleta = campoLocal_coleta.getText().toString().trim();
		planta.coletor = campoColetor.getText().toString().trim();
		planta.data_coleta = campoData_coleta.getText().toString().trim();
		planta.determinador = campoDeterminador.getText().toString().trim();
		planta.formacaoVegetal = campoFormacaoVegetal.getText().toString().trim();
		planta.observacao = campoObservacao.getText().toString().trim();
		
		if (planta.nome.equals("")) {
			
			AlertDialog.Builder alerta = new AlertDialog.Builder(EditarPlanta.this);
			alerta.setTitle(R.string.msg_erro); //Msg: Erro!
			//Msg: Por favor! informe o nome da planta antes de gravar.
			alerta.setMessage(R.string.msg06_form_editar);
			alerta.setPositiveButton("OK",new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					});
			alerta.show();
			return;
		} else if (planta.cientifico.equals("") 
				|| (planta.familia.equals(""))
				|| (planta.utiliza.equals(""))
				|| (planta.local_coleta.equals(""))
				|| (planta.coletor.equals(""))
				|| (planta.data_coleta.equals(""))
				|| (planta.determinador.equals(""))
				|| (planta.formacaoVegetal.equals(""))) {

			//Msg: Planta gravada com sucesso!
			Toast.makeText(EditarPlanta.this, R.string.msg07_form_editar,Toast.LENGTH_SHORT).show();
			//Msg: Algumas informações importantes referente a planta não foram informadas!
			Toast.makeText(EditarPlanta.this,R.string.msg08_form_editar,Toast.LENGTH_LONG).show();
		}
		// Após qualquer inserção retorna para a lista
		salvarPlanta(planta);
		setResult(RESULT_OK, new Intent());
		finish();
	}

	// Metodo para salvar arquivo no SDCard
	protected void salvarSdCard() {
		
		Planta planta = new Planta();
		MontaArquivo arq = new MontaArquivo(); 
		
		planta.nome = campoNome.getText().toString().trim();
		planta.cientifico = campoCientifico.getText().toString().trim();
		planta.familia = campoFamilia.getText().toString().trim();
		planta.utiliza = campoUtiliza.getText().toString().trim();
		planta.local_coleta = campoLocal_coleta.getText().toString().trim();
		planta.coletor = campoColetor.getText().toString().trim();
		planta.data_coleta = campoData_coleta.getText().toString().trim();
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
			File f = SdCardUtils.getSdCardFile(PASTA, NomeArquivo);
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
				//Msg: Algumas informações estão faltando na geração do arquivo!
				Toast.makeText(EditarPlanta.this,R.string.msg10_form_editar, Toast.LENGTH_LONG).show();
			}//Msg: Arquivo gerado com sucesso!
			Toast.makeText(EditarPlanta.this,R.string.msg09_form_editar, Toast.LENGTH_SHORT).show();

			Log.i(CATEGORIA, texto + " - Escrito com sucesso");
		} catch (FileNotFoundException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(CATEGORIA, e.getMessage(), e);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
		// finish();
	}
	
	// Método verifica se possui conexão com a internet
	private boolean testConnection() {
		ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conectivtyManager.getActiveNetworkInfo() != null
				&& conectivtyManager.getActiveNetworkInfo().isAvailable()
				&& conectivtyManager.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	// Método para mostrar imagens do Google Imagens
	public void mostraImagem() {
		Planta planta = new Planta();
		planta.nome = campoNome.getText().toString().trim();

		String NomePlanta = planta.nome.toString();
		String GoogleImagem = "https://www.google.com.br/search?site=imghp&tbm=isch&source=hp&biw=1366&bih=657&q=planta+";

		if (NomePlanta.equals("")) {
			AlertDialog.Builder alerta = new AlertDialog.Builder(
					EditarPlanta.this);
			alerta.setTitle(R.string.msg_erro); //Msg: Erro!
			//Msg: Por favor! informe o nome de uma planta para visualizar a imagem.
			alerta.setMessage(R.string.msg11_form_editar);
			alerta.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					});
			alerta.show();
			return;
		}
		// Concatena a URL com o nome da planta que está na tela
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(GoogleImagem).append(NomePlanta);
		String URLCompleta = strBuilder.toString();

		Uri uri = Uri.parse(URLCompleta);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(it);

	}

	public void excluir() {
		if (id != null) {
			excluirPlanta(id);
		}
		setResult(RESULT_OK, new Intent());
		finish();
	}

	// Buscar a planta pelo id
	protected Planta buscarPlanta(long id) {
		return EditarPlanta.repositorio.buscarPlanta(id);
	}

	// Salvar a planta
	protected void salvarPlanta(Planta planta) {
		EditarPlanta.repositorio.salvar(planta);
	}

	// Excluir a planta
	protected void excluirPlanta(long id) {
		EditarPlanta.repositorio.deletar(id);
	}

}
