package armorEditor;

import java.util.ArrayList;

import armor.*;
import interfaces.IAfficheur;
import interfaces.IComplexPlugin;
import interfaces.ICreateur;
import interfaces.IModificateur;
import interfaces.IPluginManager;

public class ArmorEditor implements IComplexPlugin {

	private ArrayList<Armor> armors = new ArrayList<Armor>();
	private IPluginManager pluginLoader;
	private IAfficheur pluginAfficheur;
	private ICreateur pluginCreateur;
	private IModificateur pluginModificateur;
	
	public ArmorEditor() {
		System.out.println("Lancement de l'éditeur d'armure");
	}
	// TODO récupérer le pluginManager
	
	public void loadData() {
		// TODO passer via un plugin
		armors.add(pluginCreateur.cree("ArmureTest"));
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pluginAfficheur = loadAfficheur();
		pluginCreateur = loadCreateur();
		pluginModificateur = loadModificateur();
		loadData();
		pluginAfficheur.affiche(armors.get(armors.size()-1));
		
		//test du plugin modificateur et de toutes ses fonctions
		pluginModificateur.modifieNomArmure(armors.get(armors.size()-1), "Dubidule");
		pluginModificateur.modifieColorBody(armors.get(armors.size()-1).getBodies().get(0), "Orange");
		pluginModificateur.modifieDamageWeapon(armors.get(armors.size()-1).getWeapons().get(0), 444);
		Energy nouvelleEnergie = new Energy();
		nouvelleEnergie.setName("nouvelleEnergy");
		nouvelleEnergie.setValue(222);
		pluginModificateur.modifieEnergieEquipement(armors.get(armors.size()-1).getEquipements().get(0), nouvelleEnergie);
		pluginAfficheur.affiche(armors.get(armors.size()-1));
		pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEnergyAvailable(), "SuperWarriorNRG");
		pluginModificateur.modifieNomEquipement(armors.get(armors.size()-1).getEquipements().get(0), "elementModifie");
		pluginModificateur.modifieProtectBody(armors.get(armors.size()-1).getBodies().get(0), 555);
		pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEnergyAvailable(), 666);
		pluginAfficheur.affiche(armors.get(armors.size()-1));
		
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
	
	public ICreateur loadCreateur() {
		ArrayList<String> args = new ArrayList<String>();
		args.add("simple");
		return (ICreateur) pluginLoader.loadPlugin("creationArmure.Createur", args);
	}	
	
	public IModificateur loadModificateur() {
		ArrayList<String> args = new ArrayList<String>();
		args.add("simple");
		return (IModificateur) pluginLoader.loadPlugin("modificationArmure.Modificateur", args);
	}	

}
