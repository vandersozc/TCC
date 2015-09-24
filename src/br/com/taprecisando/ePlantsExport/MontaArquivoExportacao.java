package br.com.taprecisando.ePlantsExport;

import android.annotation.SuppressLint;
import java.sql.Date;
import java.text.SimpleDateFormat;

@SuppressLint("SimpleDateFormat")
public class MontaArquivoExportacao {

	private String texto;
	private String quebraDupla = "\n\n";
	private String labelNome = "NOME COMUM: ";
	private String labelCientifico = "NOME CIENTÍFICO: ";
	private String labelFamilia = "FAMILIA: ";
	private String labelUtiliza = "UTILIZAÇÃO: ";
	private String labelLocal_coleta = "LOCAL DA COLETA: ";
	private String labelColetor = "COLETOR: ";
	private String labelData_coleta = "DATA DA COLETA: ";
	private String labelDeterminador = "DETERMINADOR: ";
	private String labelFormacao_vegetal = "FORMAÇÃO VEGETAL: ";
	private String labelObservacao = "OBSERVAÇÃO: ";
	private String Geracao = "Geração do Arquivo: ";

	public String montaTexto(String nome,
			                 String cientifico,
			                 String familia,
			                 String utiliza,
			                 String local_coleta,
			                 String coletor,
			                 String data_coleta,
			                 String determinador,
			                 String formacao_vegetal,
			                 String observacao) {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		StringBuilder strBuilder = new StringBuilder();

		strBuilder.append(labelNome).append(nome).append(quebraDupla)
				  .append(labelCientifico).append(cientifico).append(quebraDupla)
				  .append(labelFamilia).append(familia).append(quebraDupla)
				  .append(labelUtiliza).append(utiliza).append(quebraDupla)
				  .append(labelLocal_coleta).append(local_coleta)
				  .append(quebraDupla).append(labelColetor).append(coletor)
				  .append(quebraDupla).append(labelData_coleta)
				  .append(data_coleta).append(quebraDupla)
				  .append(labelDeterminador).append(determinador)
				  .append(quebraDupla).append(labelFormacao_vegetal)
				  .append(formacao_vegetal).append(quebraDupla)
				  .append(labelObservacao).append(observacao).append(quebraDupla)
				  .append(quebraDupla).append(Geracao)
				  .append(formatarDate.format(data));

		texto = strBuilder.toString();
		return texto;

	}
}
