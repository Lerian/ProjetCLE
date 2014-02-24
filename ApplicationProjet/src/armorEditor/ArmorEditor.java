package armorEditor;

import java.util.ArrayList;

import armor.*;
import interfaces.IComplexPlugin;
import interfaces.IPluginManager;

public class ArmorEditor implements IComplexPlugin {

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
	@Override
	public void receivePluginManager(IPluginManager pluginManager) {
		System.out.println("Réception du plugin manager par l'éditeur d'armure");		
	}

}
