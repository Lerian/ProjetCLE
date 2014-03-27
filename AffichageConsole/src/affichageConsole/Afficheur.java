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
		// TODO Auto-generated method stub
		System.out.println(armure.toString());
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return PluginTypes.AFFICHEUR.toString();
	}

	@Override
	public void receiveProperties(Properties prop) {
		// TODO Auto-generated method stub
		
	}
}
