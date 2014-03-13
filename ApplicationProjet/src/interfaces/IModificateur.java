package interfaces;

import armor.Armor;
import armor.Body;
import armor.Energy;
import armor.Equipement;
import armor.Position;
import armor.Weapon;

public interface IModificateur {
	//Modification de l'armure (Armor)
	public abstract void modifieNomArmure(Armor armure, String nomArmure);
	
	//Modification de l'énergie (Energy)
	public abstract void modifieNomEnergie(Energy energie, String nomEnergie);
	public abstract void modifieValEnergie(Energy energie, int valeurEnergie);
	
	//Modification de l'équipement (Equipement, Body, Weapon)
	public abstract void modifieNomEquipement(Equipement equipement, String nomEquipement);
	public abstract void modifieEnergieEquipement(Equipement equipement, Energy energie);
	public abstract void mofifiePosEquipement(Equipement equipement, Position position); //codée mais inutile, on ne met pas un casque sur un pied
	
	//Modification de l'équipement du corps (Body)
	public abstract void modifieColorBody(Body body, String couleur);
	public abstract void modifieProtectBody(Body body, int protection);
	
	//Modification de l'équipement arme (Weapon)
	public abstract void modifieDamageWeapon(Weapon weapon, int damage);
}
