package br.com.taprecisando.ePlantsController;

import java.util.List;

import br.com.taprecisando.ePlantsModel.Planta;
import br.com.taprecisando.ePlantsView.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint({ "ViewHolder", "InflateParams" })
public class PlantaListAdapter extends BaseAdapter {
	private Context context;
	private List<Planta> lista;

	public PlantaListAdapter(Context context, List<Planta> lista) {
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Planta p = lista.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.form_listar_planta, null);
		
		TextView nome = (TextView) view.findViewById(R.id.imput_text_nome_form_lista);
		nome.setText(p.nome);
		
		TextView cientifico = (TextView) view.findViewById(R.id.imput_text_nome_cientifico_form_lista);
		cientifico.setText(p.cientifico);
		
		// TextView familia = (TextView) view.findViewById(R.id.familia);
		// familia.setText(p.familia);
		
		// TextView utiliza = (TextView) view.findViewById(R.id.utiliza);
		// utiliza.setText(p.utiliza);
		
		return view;
	}
}