package br.com.taprecisando.ePlantsDAO;

import android.content.Context;

public class RepositorioPlantaScript extends RepositorioPlanta {
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS planta";
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		
		"create table planta ( _id integer primary key autoincrement, nome text not null,cientifico text not null,familia text not null,utiliza text,local_coleta text,coletor text,data_coleta text,determinador text,formacaoVegetal text,observacao text);",
		
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canaf�stula','Peltophorum dubium','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Cancorosa','Maytenus ilicifolia','Celastraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canela amarela','Nectandra lanceolata','Lauraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canela de porco','Cryptocarya aschersoniana','Lauraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canela de veado','Helietta apiculata','Rutaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canela do brejo','Machaerium paraguariense','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canela guaic�','Machaerium stipitatum','Lauraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canela preta','Ocotea puberula','Lauraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canela sassafr�s','Nectandra megapotamica','Lauraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Canjerana','Cabralea canjerana','Meliaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Capororoca','Rapanea ferruginea','Myrsinaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Carne de vaca','Clethra scabra','Clethraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Caroba','Combretum leprosum','Bignoniaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Caroba louca','Jacaranda micrantha','Bignoniaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Cedro','Tecoma stans','Meliaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Cerejeira','Cedrela fissilis ','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Ch� de bugre','Eugenia involucrata','Flacourtiaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Cinamomo','Casearia sylvestris','Meliaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Coc�o','Melia azedarach','Erythroxylaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Congonha mi�da','Erythroxylum deciduum','Aquifoliaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Corticeira banhado','Ilex dumosa','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Corticeira da serra','Erythrina crista-galli','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Dedaleiro','Erythrina falcata','Lythraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Erva mate','Lafoensia pacari','Aquifoliaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Falso barbatim�o','Ilex paraguariensis','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Figueira','Cassia leptophylla','Moraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Figueira do mato','Ficus insipida','Moraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Flamboyant','Ficus luschnathiana','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Goiaba serrana','Delonix regia','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Goiabeira','Acca sellowiana','Rubiaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Guabij�','Psidium guajava','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Guabiroba','Myrcianthes pungens','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Guajuvira','Campomanesia xanthocarpa','Boraginaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Guamirim','Cordia americana','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Guaraper�','Blepharocalyx salicifolius','Cunoniaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Guapuruv�','Lamanonia ternata','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Guatamb�','Schizolobium parahyba','Rutaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Imbuia','Balfourodendron riedelianum','Lauraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Indai�','Ocotea porosa','Arecaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Ing� feij�o','Attalea dubia','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Ing� ferradura','Inga marginata','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Ip� amarelo','Inga sessilis','Bignoniaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Ip� roxo','Tabebuia chrysotricha','Bignoniaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Jaboticaba','Tabebuia heptaphylla','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Jeriv�','Myrciaria trunciflora','Arecaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Leiteiro','Syagrus romanzoffiana','Apocynaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Leucena','Peschiera fuchsiaefolia','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Ligustro','Leucaena leucocephala','Oleaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Louro mole','Ligustrum lucidum','Boraginaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Louro pardo','Cordia ecalyculata','Boraginaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Mamica de cadela','Cordia trichotoma','Rutaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Manduirana','Zanthoxylum rhoifolium','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Maria preta','Senna macranthera','Ebenaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Maric�','Diospyros inconstans','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Marmeleiro do mato','Mimosa bimucronata','Polygonaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Murta','Ruprechtia laxiflora','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Olho de cabra','Blepharocalyx salicifolius','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Paineira','Ormosia arborea','Bombacaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Palmeira real','Ceiba speciosa','Arecaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Palmiteiro','Archontophoenix alexandrae','Arecaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pata de vaca','Euterpe edulis','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pau canela','Bauhinia forficata','Lauraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pau cigarra','Cinnamomum zeylanicum','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pau ferro','Senna mutijuga','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pau leiteiro','Caesalpinia ferrea','Euphorbiaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Peroba','Sapium glandulatum','Apocynaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Perta guela','Aspidosperma olivaceum','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pessegueiro bravo','Gomidesia affinis','Rosaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pimenteira','Prunus sellowii','Canellaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pinheiro brasileiro','Capsicodendron dinisii','Araucariaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Pitangueira','Araucaria angustifolia','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Sarandi','Eugenia uniflora','Euphorbiaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Sesbania','Sebastiania membranifolia','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Sete capotes','Sesbania virgata','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Sibipiruna','Campomanesia guazumifolia','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Tarum�','Caesalpinia peltophoroides','Verbenaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Tarum� de espinho','Vitex megapotamica','Verbenaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Timba�va','Citharexylum montevidense','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Timb�','Enterolobium contortisiliquum','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Tipuana','Ateleia glazioveana','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Tucaneira','Tipuana tipu','Verbenaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Umb�','Citharexylum myrianthum','Phytolaccaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Unha de gato','Phytolacca dioica','Fabaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Urucum','Acacia bonariensis','Bixaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Uva do jap�o','Bixa orellana','Rhamnaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Uvaia','Hovenia dulcis','Myrtaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Vacum','Eugenia pyriformis','Sapindaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Varaneira','Allophylus edulis','Liliaceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Vassour�o branco','Cordyline dracaenoides','Asteraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Vassour�o preto','Piptocarpha angustifolia','Asteraceae','','','','05-10-2014','','','');",
		"insert into planta(nome,cientifico,familia,utiliza,local_coleta,coletor,data_coleta,determinador,formacaoVegetal,observacao)values('Vassoura vermelha','Vernonia discolor','Sapindaceae','','','','05-10-2014','','','');"
		
};
	
	private static final String NOME_BANCO = "ePlantsDB";
	private static final int VERSAO_BANCO = 1;
	public static final String TABELA_PLANTA = "planta";
	private SQLiteHelper dbHelper;

	public RepositorioPlantaScript(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, RepositorioPlantaScript.NOME_BANCO,
				RepositorioPlantaScript.VERSAO_BANCO,
				RepositorioPlantaScript.SCRIPT_DATABASE_CREATE,
				RepositorioPlantaScript.SCRIPT_DATABASE_DELETE);
		db = dbHelper.getWritableDatabase();
	}
	// Fecha o banco
	// @Override
	 //public void fechar() {
	 //super.fechar();
	 //if (dbHelper != '') {
	 //dbHelper.close();
	// }
	 //}
}