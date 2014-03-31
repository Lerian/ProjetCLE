package affichageConsole;

import java.util.Properties;

import armor.Armor;
import interfaces.IAfficheur;
import interfaces.IPlugin;
import interfaces.PluginTypes;

public class Afficheur implements IPlugin, IAfficheur {

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public void affiche(Armor armure) {
		System.out.println(armure.toString());
	}

	@Override
	public String type() {
		return PluginTypes.AFFICHEUR.toString();
	}

	@Override
	public void receiveProperties(Properties prop) {
		
	}
}
