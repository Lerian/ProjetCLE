package armor;

import interfaces.IArmor;

import java.util.List;

public class Armor implements IArmor {
	String name;
	Energy energyAvailable;
	List<Equipement> equipements;
	
	@Override
	public String toString() {
		return "Armor [name=" + name + ", energyAvailable=" + energyAvailable
				+ ", equipements=" + equipements + "]";
	}
	
	/* (non-Javadoc)
	 * @see armor.IArmor#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	/* (non-Javadoc)
	 * @see armor.IArmor#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see armor.IArmor#getEnergyAvailable()
	 */
	@Override
	public Energy getEnergyAvailable() {
		return energyAvailable;
	}
	/* (non-Javadoc)
	 * @see armor.IArmor#setEnergyAvailable(armor.Energy)
	 */
	@Override
	public void setEnergyAvailable(Energy energyAvailable) {
		this.energyAvailable = energyAvailable;
	}
	/* (non-Javadoc)
	 * @see armor.IArmor#getEquipements()
	 */
	@Override
	public List<Equipement> getEquipements() {
		return equipements;
	}
	
}
