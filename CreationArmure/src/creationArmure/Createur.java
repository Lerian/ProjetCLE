package creationArmure;

import java.util.List;

import armor.Armor;
import armor.Body;
import armor.Energy;
import armor.Equipement;
import armor.Position;
import armor.Weapon;
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
		
		//Ajout energie de base
		Energy baseEnergy = new Energy();
		baseEnergy.setName("baseEnergy");
		baseEnergy.setValue(42);
		
		//Création d'équipement body
		Body casque = new Body("casque");
		Body torse = new Body("torse");
		Body brasG = new Body("brasG");
		Body brasD = new Body("brasD");
		Body jambeG = new Body("jambeG");
		

		
		//Création d'équipement weapon
			Weapon laser = new Weapon();
			laser.setName("laser");
			laser.setPos(Position.LHAND);
			laser.setEnergyNeeded(baseEnergy);
			laser.setDamage(338);
			
			Weapon feu = new Weapon();
			feu.setName("feu");
			feu.setPos(Position.RHAND);
			feu.setEnergyNeeded(baseEnergy);
			feu.setDamage(18);
		
		//Ajout équipement basique
		nouvelleArmure.add(laser);
		nouvelleArmure.add(feu);
		
		nouvelleArmure.setEnergyAvailable(baseEnergy);
		
		return nouvelleArmure;
	}


}
