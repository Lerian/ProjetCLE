package armorEditor;

import java.util.ArrayList;
import java.util.List;

import armor.*;
import interfaces.IAfficheur;
import interfaces.IComplexPlugin;
import interfaces.ICreateur;
import interfaces.IModificateur;
import interfaces.IPlugin;
import interfaces.IPluginManager;
import interfaces.PluginTypes;

public class ArmorEditor implements IComplexPlugin {

	private ArrayList<Armor> armors = new ArrayList<Armor>();
	private IPluginManager pluginLoader;
	private IAfficheur pluginAfficheur;
	private ICreateur pluginCreateur;
	private IModificateur pluginModificateur;
	
	public ArmorEditor() {
		System.out.println("Lancement de l'éditeur d'armure");
	}
	
	public void loadData() {
		//armors.add(pluginCreateur.cree("ArmureTest"));
		armors.add(pluginCreateur.cree("IronM4nu"));
	}
	
	@Override
	public void run() {
		//Appel des plugins Afficheur
		//pluginAfficheur = loadAfficheur("affichageConsole.Afficheur", new ArrayList<String>());
		pluginAfficheur = loadAfficheur("affichageGraphique.Afficheur", /*new ArrayList<String>()*/"");
		
		//Appel des plugins createur
		//pluginCreateur = loadCreateur("creationArmure.Createur", new ArrayList<String>());
		pluginCreateur = loadCreateur("creationArmureFichier.CreateurFichier", /*new ArrayList<String>()*/"");
		
		pluginModificateur = loadModificateur("modificationArmure.Modificateur", /*new ArrayList<String>()*/"");
		loadData();
		
		
		pluginAfficheur.affiche(armors.get(armors.size()-1));
				
		//test du plugin modificateur et de toutes ses fonctions
		/*
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
		*/
	}
	
	@Override
	public void receivePluginManager(IPluginManager pluginManager) {
		pluginLoader = pluginManager;
	}
	
	public IAfficheur loadAfficheur(String nomAfficheur, /*List<*/String/*>*/ args) {
		return (IAfficheur) pluginLoader.loadPlugin(nomAfficheur, args);
	}
	
	public ICreateur loadCreateur(String nomCreateur, /*List<*/String/*>*/ args) {
		return (ICreateur) pluginLoader.loadPlugin(nomCreateur, args);
	}	
	
	public IModificateur loadModificateur(String nomModificateur, /*List<*/String/*>*/ args) {
		return (IModificateur) pluginLoader.loadPlugin(nomModificateur, args);
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return PluginTypes.MAIN.toString();
	}	

}
