package br.com.taprecisando.ePlantsDAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class SQLiteHelper extends SQLiteOpenHelper {

	private static final String CATEGORIA = "dados";

	private String[] scriptSQLCreate;
	private String scriptSQLDelete;

	/**
	 * Cria uma inst�ncia de SQLiteHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco vers�o do banco de dados (se for diferente � para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	SQLiteHelper(Context context,
			     String nomeBanco, 
			     int versaoBanco, 
			     String[] scriptSQLCreate, 
			     String scriptSQLDelete) {
		
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		int qtdeScripts = scriptSQLCreate.length;

		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			Log.i(CATEGORIA, sql);
			db.execSQL(sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		
		Log.w(CATEGORIA, "Atualizando da vers�o " + versaoAntiga + " para " + novaVersao + ". Todos os registros ser�o deletados.");
		Log.i(CATEGORIA, scriptSQLDelete);
		db.execSQL(scriptSQLDelete);
		onCreate(db);
	}

}
