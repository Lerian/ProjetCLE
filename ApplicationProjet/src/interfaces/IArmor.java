package interfaces;

import java.util.List;

import armor.Body;
import armor.Energy;
import armor.Equipement;
import armor.Weapon;

public interface IArmor {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract Energy getEnergyAvailable();

	public abstract void setEnergyAvailable(Energy energyAvailable);

	public abstract List<Equipement> getEquipements();
	
	public abstract List<Body> getBodies();
	
	public abstract List<Weapon> getWeapons();

}