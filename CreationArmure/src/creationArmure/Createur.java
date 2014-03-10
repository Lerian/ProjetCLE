package creationArmure;

import armor.Armor;
import armor.Body;
import armor.Energy;
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
		Armor nouvelleArmure = new Armor();
		nouvelleArmure.setName(nomArmure);
		
		//Ajout energie de base
		Energy baseEnergy = new Energy();
		baseEnergy.setName("baseEnergy");
		baseEnergy.setValue(42);
		
		//Création d'équipement body
		Body casque = new Body("casque");
			casque.setEnergyNeeded(baseEnergy);
			casque.setPos(Position.HEAD);
			casque.setColor("violet");
			casque.setProtection(11);
		Body torse = new Body("torse");
			torse.setEnergyNeeded(baseEnergy);
			torse.setPos(Position.BODY);
			torse.setColor("rose");
			torse.setProtection(45);
		Body brasG = new Body("brasG");
			brasG.setEnergyNeeded(baseEnergy);
			brasG.setPos(Position.LARM);
			brasG.setColor("cyan");
			brasG.setProtection(48);
		Body brasD = new Body("brasD");
			brasD.setEnergyNeeded(baseEnergy);
			brasD.setPos(Position.RARM);
			brasD.setColor("jaune");
			brasD.setProtection(85);
		Body jambeG = new Body("jambeG");
			jambeG.setEnergyNeeded(baseEnergy);
			jambeG.setPos(Position.LLEG);
			jambeG.setColor("vert");
			jambeG.setProtection(14);
		Body jambeD = new Body("jambeD");
			jambeD.setEnergyNeeded(baseEnergy);
			jambeD.setPos(Position.RLEG);
			jambeD.setColor("bleu");
			jambeD.setProtection(33);
		
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
		nouvelleArmure.add(casque);
		nouvelleArmure.add(torse);
		nouvelleArmure.add(brasG);
		nouvelleArmure.add(brasD);
		nouvelleArmure.add(jambeG);
		nouvelleArmure.add(jambeD);
		
		nouvelleArmure.setEnergyAvailable(baseEnergy);
		
		return nouvelleArmure;
	}
}
