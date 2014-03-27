package creationArmureFichier;

import java.io.FileReader;
import java.util.Properties;

import armor.Armor;
import armor.Body;
import armor.Energy;
import armor.Position;
import armor.Weapon;
import interfaces.ICreateur;
import interfaces.IPlugin;
import interfaces.PluginTypes;

public class CreateurFichier implements ICreateur, IPlugin{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Armor cree(String filename) {
		// Récupération du fichier
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("resources/" + filename + ".txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Armor nouvelleArmure = new Armor();
		nouvelleArmure.setName(prop.getProperty("nomArmure"));
		
		//ajout de l'énergie
		Energy energie = new Energy();
		energie.setName(prop.getProperty("nomEnergie"));
		energie.setValue(Integer.parseInt(prop.getProperty("valEnergie")));
		nouvelleArmure.setEnergyAvailable(energie);
		
		//ajout d'équipement body
		Body casque = new Body(prop.getProperty("casqueNom"));
			casque.setEnergyNeeded(energie);
			casque.setPos(Position.HEAD);
			casque.setColor(prop.getProperty("casqueCouleur"));
			casque.setProtection(Integer.parseInt(prop.getProperty("casqueProtection")));
		nouvelleArmure.add(casque);
			
		Body torse = new Body(prop.getProperty("torseNom"));
			torse.setEnergyNeeded(energie);
			torse.setPos(Position.BODY);
			torse.setColor(prop.getProperty("torseCouleur"));
			torse.setProtection(Integer.parseInt(prop.getProperty("torseProtection")));
		nouvelleArmure.add(torse);
		
		Body brasG = new Body(prop.getProperty("brasGNom"));
			brasG.setEnergyNeeded(energie);
			brasG.setPos(Position.LARM);
			brasG.setColor(prop.getProperty("brasGCouleur"));
			brasG.setProtection(Integer.parseInt(prop.getProperty("brasGProtection")));
		nouvelleArmure.add(brasG);
		
		Body brasD = new Body(prop.getProperty("brasDNom"));
			brasD.setEnergyNeeded(energie);
			brasD.setPos(Position.RARM);
			brasD.setColor(prop.getProperty("brasDCouleur"));
			brasD.setProtection(Integer.parseInt(prop.getProperty("brasDProtection")));
		nouvelleArmure.add(brasD);
		
		Body jambeG = new Body(prop.getProperty("jambeGNom"));
			jambeG.setEnergyNeeded(energie);
			jambeG.setPos(Position.LLEG);
			jambeG.setColor(prop.getProperty("jambeGCouleur"));
			jambeG.setProtection(Integer.parseInt(prop.getProperty("jambeGProtection")));
		nouvelleArmure.add(jambeG);
		
		Body jambeD = new Body(prop.getProperty("jambeDNom"));
			jambeD.setEnergyNeeded(energie);
			jambeD.setPos(Position.RLEG);
			jambeD.setColor(prop.getProperty("jambeDCouleur"));
			jambeD.setProtection(Integer.parseInt(prop.getProperty("jambeDProtection")));
		nouvelleArmure.add(jambeD);
		
		//ajout d'équipement weapon
		Weapon armeG = new Weapon(prop.getProperty("armeGNom"));
			armeG.setPos(Position.LHAND);
			armeG.setEnergyNeeded(energie);
			armeG.setDamage(Integer.parseInt(prop.getProperty("armeGAtq")));
		nouvelleArmure.add(armeG);
		
		Weapon armeD = new Weapon(prop.getProperty("armeDNom"));
			armeD.setPos(Position.RHAND);
			armeD.setEnergyNeeded(energie);
			armeD.setDamage(Integer.parseInt(prop.getProperty("armeDAtq")));
		nouvelleArmure.add(armeD);

		//ajout de l'image
		nouvelleArmure.setImage(prop.getProperty("image","stormtrooper.png"));	
		
		return nouvelleArmure;
		
	}

	@Override
	public String type() {
		return PluginTypes.CREATEUR.toString();
	}

	@Override
	public void receiveProperties(Properties prop) {
		// TODO Auto-generated method stub
		
	}

}
