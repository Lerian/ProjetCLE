package creationArmure;

import armor.Armor;
import armor.Body;
import armor.Energy;
import armor.Position;
import armor.Weapon;
import interfaces.ICreateur;
import interfaces.IPlugin;
import interfaces.PluginTypes;

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
		Energy energie = new Energy();
			energie.setName("énergie vitale");
			energie.setValue(42);
		nouvelleArmure.setEnergyAvailable(energie);

		//Création d'équipement body
		Body casque = new Body("casque");
			casque.setEnergyNeeded(energie);
			casque.setPos(Position.HEAD);
			casque.setColor("blanc");
			casque.setProtection(11);
		nouvelleArmure.add(casque);
		
		Body torse = new Body("torse");
			torse.setEnergyNeeded(energie);
			torse.setPos(Position.BODY);
			torse.setColor("blanc");
			torse.setProtection(45);
		nouvelleArmure.add(torse);
		
		Body brasG = new Body("brasG");
			brasG.setEnergyNeeded(energie);
			brasG.setPos(Position.LARM);
			brasG.setColor("blanc");
			brasG.setProtection(48);
		nouvelleArmure.add(brasG);
			
		Body brasD = new Body("brasD");
			brasD.setEnergyNeeded(energie);
			brasD.setPos(Position.RARM);
			brasD.setColor("blanc");
			brasD.setProtection(85);
		nouvelleArmure.add(brasD);
			
		Body jambeG = new Body("jambeG");
			jambeG.setEnergyNeeded(energie);
			jambeG.setPos(Position.LLEG);
			jambeG.setColor("blanc");
			jambeG.setProtection(14);
		nouvelleArmure.add(jambeG);
			
		Body jambeD = new Body("jambeD");
			jambeD.setEnergyNeeded(energie);
			jambeD.setPos(Position.RLEG);
			jambeD.setColor("blanc");
			jambeD.setProtection(33);
		nouvelleArmure.add(jambeD);
		
		//Création d'équipement weapon
		Weapon armeG = new Weapon("pistolet laser");
			armeG.setPos(Position.LHAND);
			armeG.setEnergyNeeded(energie);
			armeG.setDamage(338);
		nouvelleArmure.add(armeG);
			
		Weapon armeD = new Weapon("aucune");
			armeD.setPos(Position.RHAND);
			armeD.setEnergyNeeded(energie);
			armeD.setDamage(00);
		nouvelleArmure.add(armeD);
		
		//Ajout de l'image
		nouvelleArmure.setImage("stormtrooper.png");
		
		return nouvelleArmure;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return PluginTypes.CREATEUR.toString();
	}
}
