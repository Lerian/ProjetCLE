package creationArmure;

import armor.Armor;
import armor.Energy;
import interfaces.ICreateur;
import interfaces.IPlugin;

public class Createur implements IPlugin, ICreateur{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Armor cree(String nomArmure) {
		// TODO Auto-generated method stub
		Armor nouvelleArmure = new Armor();
		nouvelleArmure.setName(nomArmure);
		
		return nouvelleArmure;
	}


}
