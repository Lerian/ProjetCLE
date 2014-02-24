package armorEditor;

import java.util.ArrayList;

import armor.*;

import interfaces.IPlugin;

public class ArmorEditor implements IPlugin {

	private ArrayList<Armor> armors;
	
	public ArmorEditor() {
		System.out.println("Lancement de l'éditeur d'armure");
	}
	// TODO récupérer le pluginManager
	
	public void loadData() {
		// TODO passer via un plugin
		armors.add(new Armor());
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
