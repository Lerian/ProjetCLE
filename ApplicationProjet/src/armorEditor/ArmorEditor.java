package armorEditor;

import java.util.ArrayList;

import armor.*;
import interfaces.IAfficheur;
import interfaces.IComplexPlugin;
import interfaces.IPluginManager;

public class ArmorEditor implements IComplexPlugin {

	private ArrayList<Armor> armors;
	private IPluginManager pluginLoader;
	private IAfficheur pluginAfficheur;
	
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
		pluginAfficheur = loadAfficheur();
		//pluginAfficheur.affiche(armors.get(0));
	}
	
	@Override
	public void receivePluginManager(IPluginManager pluginManager) {
		pluginLoader = pluginManager;
	}
	
	public IAfficheur loadAfficheur() {
		ArrayList<String> args = new ArrayList<String>();
		args.add("simple");
		return (IAfficheur) pluginLoader.loadPlugin("affichageConsole.Afficheur", args);
	}

}
