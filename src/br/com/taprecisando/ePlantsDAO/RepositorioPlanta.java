package br.com.taprecisando.ePlantsDAO;

import java.util.ArrayList;
import java.util.List;

import br.com.taprecisando.ePlantsModel.Planta;
import br.com.taprecisando.ePlantsModel.Planta.Plantas;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class RepositorioPlanta {

	private static final String CATEGORIA = "dados";
	private static final String NOME_BANCO = "ePlantsDB";
	public static final String NOME_TABELA = "planta";

	protected SQLiteDatabase database;

	public RepositorioPlanta(Context context) {
		database = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}	

	public RepositorioPlanta() {
	}

	public long salvar(Planta planta) {
		long id = planta.id;
		if (id != 0) {
			atualizar(planta);
		} else {
			id = inserir(planta);
		}
		return id;
	}

	public long inserir(Planta planta) {
		ContentValues values = new ContentValues();
		values.put(Plantas.NOME, planta.nome);
		values.put(Plantas.CIENTIFICO, planta.cientifico);
		values.put(Plantas.FAMILIA, planta.familia);
		values.put(Plantas.UTILIZA, planta.utiliza);
		values.put(Plantas.LOCAL_COLETA, planta.local_coleta);
		values.put(Plantas.COLETOR, planta.coletor);
		values.put(Plantas.DATA_COLETA, planta.data_coleta);
		values.put(Plantas.DETERMINADOR, planta.determinador);
		values.put(Plantas.FORMACAOVEGETAL, planta.formacaoVegetal);
		values.put(Plantas.OBSERVACAO, planta.observacao);
		
		long id = inserir(values);
		return id;
	}

	public long inserir(ContentValues valores) {
		long id = database.insert(NOME_TABELA, "", valores);
		return id;
	}

	public int atualizar(Planta planta) {
		ContentValues values = new ContentValues();		
		values.put(Plantas.NOME, planta.nome);
		values.put(Plantas.CIENTIFICO, planta.cientifico);
		values.put(Plantas.FAMILIA, planta.familia);
		values.put(Plantas.UTILIZA, planta.utiliza);
		values.put(Plantas.LOCAL_COLETA, planta.local_coleta);
		values.put(Plantas.COLETOR, planta.coletor);
		values.put(Plantas.DATA_COLETA, planta.data_coleta);
		values.put(Plantas.DETERMINADOR, planta.determinador);
		values.put(Plantas.FORMACAOVEGETAL, planta.formacaoVegetal);
		values.put(Plantas.OBSERVACAO, planta.observacao);

		String _id = String.valueOf(planta.id);
		String where = Plantas._ID + "=?";
		String[] whereArgs = new String[] { _id };

		int count = atualizar(values, where, whereArgs);
		return count;
	}

	public int atualizar(ContentValues valores, String where, String[] whereArgs) {
		int count = database.update(NOME_TABELA, valores, where, whereArgs);
		
		return count;
	}

	public int deletar(long id) {
		String where = Plantas._ID + "=?";
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };

		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = database.delete(NOME_TABELA, where, whereArgs);

		return count;
	}

	public Planta buscarPlanta(long id) {
		Cursor c = database.query(true, NOME_TABELA, Planta.colunas, Plantas._ID
				+ "=" + id, null, null, null, null, null);

		if (c.getCount() > 0) {
			c.moveToFirst();
			Planta planta = new Planta();

			planta.id = 			 c.getLong(0);
			planta.nome = 		  	 c.getString(1);
			planta.cientifico = 	 c.getString(2);
			planta.familia = 		 c.getString(3);
			planta.utiliza = 		 c.getString(4);
			planta.local_coleta = 	 c.getString(5);
			planta.coletor = 		 c.getString(6);
			planta.data_coleta = 	 c.getString(7);
			planta.determinador = 	 c.getString(8);
			planta.formacaoVegetal = c.getString(9);
			planta.observacao = 	 c.getString(10);
			return planta;
		}
		return null;
	}

	public Cursor getCursor() {
		try {
			return database.query(NOME_TABELA, Planta.colunas, null,null,null,null,null,null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar as plantas: " + e.toString());
			return null;
		}
	}

	public List<Planta> listarPlantas() {
		Cursor lista = getCursor();

		List<Planta> plantas = new ArrayList<Planta>();

		if (lista.moveToFirst()) {
			int idxId = 			 lista.getColumnIndex(Plantas._ID);
			int idxNome = 			 lista.getColumnIndex(Plantas.NOME);
			int idxCientifico = 	 lista.getColumnIndex(Plantas.CIENTIFICO);
			int idxFamilia = 		 lista.getColumnIndex(Plantas.FAMILIA);
			int idxUtiliza = 		 lista.getColumnIndex(Plantas.UTILIZA);
			int idxLocal_coleta = 	 lista.getColumnIndex(Plantas.LOCAL_COLETA);
			int idxColetor = 	 	 lista.getColumnIndex(Plantas.COLETOR);
			int idxData_coleta = 	 lista.getColumnIndex(Plantas.DATA_COLETA);
			int idxDeterminador = 	 lista.getColumnIndex(Plantas.DETERMINADOR);
			int idxFormacaoVegetal = lista.getColumnIndex(Plantas.FORMACAOVEGETAL);
			int idxObservacao = 	 lista.getColumnIndex(Plantas.OBSERVACAO);

			do {
				Planta planta = new Planta();
				plantas.add(planta);

				planta.id = 			 lista.getLong(idxId);
				planta.nome = 			 lista.getString(idxNome);
				planta.cientifico = 	 lista.getString(idxCientifico);
				planta.familia = 		 lista.getString(idxFamilia);
				planta.utiliza = 		 lista.getString(idxUtiliza);
				planta.local_coleta =	 lista.getString(idxLocal_coleta);
				planta.coletor = 		 lista.getString(idxColetor);
				planta.data_coleta = 	 lista.getString(idxData_coleta);
				planta.determinador = 	 lista.getString(idxDeterminador);
				planta.formacaoVegetal = lista.getString(idxFormacaoVegetal);
				planta.observacao = 	 lista.getString(idxObservacao);

			} while (lista.moveToNext());
		}
		return plantas;
	}

	public Planta buscarPlantaPeloNome(String nomePlanta) {
		Planta planta = null;

		try {
			Cursor busca = database.query(NOME_TABELA, Planta.colunas, Plantas.NOME + " LIKE '%" + nomePlanta + "%'", null, null, null, null, null);
			if (busca.moveToNext()) {

				planta = new Planta();
				planta.id = 			 busca.getLong(0);
				planta.nome = 			 busca.getString(1).trim();
				planta.cientifico = 	 busca.getString(2).trim();
				planta.familia = 		 busca.getString(3).trim();
				planta.utiliza = 		 busca.getString(4).trim();
				planta.local_coleta =	 busca.getString(5).trim();
				planta.coletor = 		 busca.getString(6).trim();
				planta.data_coleta = 	 busca.getString(7).trim();
				planta.determinador = 	 busca.getString(8).trim();
				planta.formacaoVegetal = busca.getString(9).trim();
				planta.observacao = 	 busca.getString(10).trim();
			}
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar a planta pelo nome: " + e.toString());
			return null;
		}

		return planta;
	}

	public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {		
		
		return queryBuilder.query(this.database, projection, selection, selectionArgs, groupBy, having,orderBy);
	}

	public void fechar() {
		if (database != null) {
			database.close();
		}
	}
}
