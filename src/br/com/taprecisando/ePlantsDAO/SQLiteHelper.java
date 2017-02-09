package br.com.taprecisando.ePlantsDAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {

	private String[] scriptSQLCreate;
	private String scriptSQLDelete;

	/**
	 * Cria uma instância de SQLiteHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versão do banco de dados (se for diferente é para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, 
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

			db.execSQL(sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL(scriptSQLDelete);
		//onCreate(db);
	}
}