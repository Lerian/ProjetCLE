package modificationArmure;

import java.util.Properties;

import armor.Armor;
import armor.Body;
import armor.Energy;
import armor.Equipement;
import armor.Position;
import armor.Weapon;
import interfaces.IModificateur;
import interfaces.IPlugin;
import interfaces.PluginTypes;

public class Modificateur implements IPlugin, IModificateur{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void modifieNomArmure(Armor armure, String nomArmure) {
		
		armure.setName(nomArmure);
	}

	@Override
	public void modifieNomEnergie(Energy energie, String nomEnergie) {
		
		energie.setName(nomEnergie);
	}

	@Override
	public void modifieValEnergie(Energy energie, int valeurEnergie) {
		
		energie.setValue(valeurEnergie);
	}

	@Override
	public void modifieNomEquipement(Equipement equipement, String nomEquipement) {
		
		equipement.setName(nomEquipement);
	}

	@Override
	public void modifieEnergieEquipement(Equipement equipement, Energy energie) {

		equipement.setEnergyNeeded(energie);
	}

	@Override
	public void mofifiePosEquipement(Equipement equipement, Position position) {
		
		equipement.setPos(position);
	}

	@Override
	public void modifieColorBody(Body body, String couleur) {
		
		body.setColor(couleur);
	}

	@Override
	public void modifieProtectBody(Body body, int protection) {
		
		body.setProtection(protection);
	}

	@Override
	public void modifieDamageWeapon(Weapon weapon, int damage) {
		
		weapon.setDamage(damage);
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return PluginTypes.MODIFICATEUR.toString();
	}

	@Override
	public void receiveProperties(Properties prop) {
		// TODO Auto-generated method stub
		
	}
	
}
