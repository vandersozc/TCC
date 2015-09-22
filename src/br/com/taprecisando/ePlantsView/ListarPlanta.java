package br.com.taprecisando.ePlantsView;

import java.util.List;

import br.com.taprecisando.ePlantsController.PlantaListAdapter;
import br.com.taprecisando.ePlantsDAO.RepositorioPlanta;
import br.com.taprecisando.ePlantsDAO.RepositorioPlantaScript;
import br.com.taprecisando.ePlantsModel.Planta;
import br.com.taprecisando.ePlantsModel.Planta.Plantas;
import br.com.taprecisando.ePlantsView.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ListarPlanta extends ListActivity {
	protected static final int INSERIR_EDITAR = 1;
	protected static final int BUSCAR = 2;

	public static RepositorioPlanta repositorio;
	private List<Planta> plantas;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		repositorio = new RepositorioPlantaScript(this);
		atualizarLista();

	}

	protected void atualizarLista() {
		plantas = repositorio.listarPlantas();
		setListAdapter(new PlantaListAdapter(this, plantas));
	}

	/*--------------------------- Criação do Menu da lista----------------------------*/

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.listar_planta, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.ic_action_menu_novo:
			startActivityForResult(new Intent(this, EditarPlanta.class),INSERIR_EDITAR);
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			return true;

		case R.id.ic_action_menu_buscar:
			startActivity(new Intent(this, BuscarPlanta.class));
			overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// ----------------------------------------------------------------------------------------------------------

	@Override
	protected void onListItemClick(ListView l, View v, int posicao, long id) {
		super.onListItemClick(l, v, posicao, id);
		editarPlanta(posicao);
	}

	protected void editarPlanta(int posicao) {

		Planta planta = plantas.get(posicao);
		Intent it = new Intent(this, EditarPlanta.class);
		// Passa o codigo da planta como parâmetro
		it.putExtra(Plantas._ID, planta.id);
		startActivityForResult(it, INSERIR_EDITAR);

		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
	}

	@Override
	protected void onActivityResult(int codigo, int codigoRetorno, Intent it) {
		super.onActivityResult(codigo, codigoRetorno, it);

		if (codigoRetorno == RESULT_OK) {
			atualizarLista();
		}
	}
	// @Override
	// protected void onDestroy() {
	// super.onDestroy();
	// repositorio.fechar();
	// }
}