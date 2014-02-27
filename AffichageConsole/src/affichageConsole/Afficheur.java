package affichageConsole;

import armor.Armor;
import interfaces.IAfficheur;
import interfaces.IPlugin;

public class Afficheur implements IPlugin, IAfficheur {

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	@Override
	public void affiche(Armor armure) {
		// TODO Auto-generated method stub
		armure.toString();
	}
}
