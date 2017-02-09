package br.com.taprecisando.ePlantsModel;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class Planta {

	public static String[] colunas = new String[] { 
		Plantas._ID,
		Plantas.NOME,
		Plantas.CIENTIFICO,
		Plantas.FAMILIA,
		Plantas.UTILIZA,
		Plantas.LOCAL_COLETA,
		Plantas.COLETOR,
		Plantas.DATA_COLETA,
		Plantas.DETERMINADOR,
		Plantas.FORMACAOVEGETAL,
		Plantas.OBSERVACAO
	};

	public static final String AUTHORITY = "br.com.taprecisando.ePlants.provider.planta";

	public long id;
	public String nome;
	public String cientifico;
	public String familia;
	public String utiliza;
	public String local_coleta;
	public String coletor;
	public String data_coleta;
	public String determinador;
	public String formacaoVegetal;
	public String observacao;

	public Planta() {

	}

	public Planta(String nome, 
			      String cientifico,
			      String familia,
			      String utiliza,
			      String local_coleta,
			      String coletor,
			      String data_coleta,
			      String determinador,
			      String formacaoVegetal,
			      String observacao) {
		
		super();
		
		this.nome = 	 	   nome;
		this.cientifico = 	   cientifico;
		this.familia = 		   familia;
		this.utiliza = 		   utiliza;
		this.local_coleta =    local_coleta;
		this.coletor = 		   coletor;
		this.data_coleta = 	   data_coleta;
		this.determinador =    determinador;
		this.formacaoVegetal = formacaoVegetal;
		this.observacao = 	   observacao;
	}

	public Planta(long id, 
			      String nome,
			      String cientifico,
			      String familia,
			      String utiliza,
			      String local_coleta,
			      String coletor,
			      String data_coleta,
			      String determinador,
			      String formacaoVegetal,
			      String observacao) {
		
		super();
		
		this.id = 			   id;
		this.nome = 		   nome;
		this.cientifico = 	   cientifico;
		this.familia = 		   familia;
		this.utiliza = 		   utiliza;
		this.local_coleta =	   local_coleta;
		this.coletor =  	   coletor;
		this.data_coleta = 	   data_coleta;
		this.determinador =    determinador;
		this.formacaoVegetal = formacaoVegetal;
		this.observacao = 	   observacao;
	}

	/**
	 * Classe interna para representar as colunas e ser utilizada por um Content
	 * Provider
	 * 
	 * Filha de BaseColumns que já define (_id e _count), para seguir o padrão
	 * Android
	 */
	public static final class Plantas implements BaseColumns {

		private Plantas() {
		}

		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/plantas");
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.plantas";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.plantas";
		public static final String DEFAULT_SORT_ORDER = "_id ASC";

		public static final String NOME = 		 	 "nome";
		public static final String CIENTIFICO = 	 "cientifico";
		public static final String FAMILIA = 		 "familia";
		public static final String UTILIZA = 		 "utiliza";
		public static final String LOCAL_COLETA =    "local_coleta";
		public static final String COLETOR = 		 "coletor";
		public static final String DATA_COLETA = 	 "data_coleta";
		public static final String DETERMINADOR = 	 "determinador";
		public static final String FORMACAOVEGETAL = "formacaoVegetal";
		public static final String OBSERVACAO = 	 "observacao";

		public static Uri getUriId(long id) {
			Uri uriPlantas = ContentUris.withAppendedId(Plantas.CONTENT_URI, id);
			return uriPlantas;
		}
	}

	@Override
	public String toString() {
		return "  Nome: "       	 + nome +
			   ", Cientifico: " 	 + cientifico + 
			   ", Família: "    	 + familia + 
			   ", Utiliza: "    	 + utiliza +
			   ", Local_coleta: "  	 + local_coleta +
			   ", Coletor: "    	 + coletor +
			   ", Data_coleta: "	 + data_coleta +
			   ", Determinador: "	 + determinador +
			   ", FormacaoVegetal: " + formacaoVegetal +
			   ", Observacao: " 	 + observacao;
	}
}
