package armor;

import interfaces.IArmor;

import java.util.ArrayList;
import java.util.List;

public class Armor implements IArmor {
	String name;
	Energy energyAvailable;
	List<Equipement> equipements;
	String image;
	
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
	
	public boolean add(Equipement arg0) {
		if(equipements ==  null){
			equipements = new ArrayList<Equipement>();
		}
		return equipements.add(arg0);
	}

	public boolean remove(Object arg0) {
		return equipements.remove(arg0);
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

	@Override
	public List<Body> getBodies() {

		List<Body> bodies = new ArrayList<Body>();
		for(Equipement e : equipements){
			if (Body.class.isAssignableFrom(e.getClass())){
				bodies.add((Body) e);
			}
		}
		return bodies;
	}

	@Override
	public List<Weapon> getWeapons() {

		List<Weapon> weapons = new ArrayList<Weapon>();
		for(Equipement e : equipements){
			if(Weapon.class.isAssignableFrom(e.getClass())){
				weapons.add((Weapon) e);
			}
		}
		return weapons;
	}

	@Override
	public String getImage() {

		return image;
	}

	@Override
	public void setImage(String image) {

		this.image = image;
	}

	@Override
	public Equipement getEquipementAt(Position pos) {
		for(Equipement e : getEquipements()) {
			if(e.getPos() == pos) {
				return e;
			}
		}
		return null;
	}
	
	
	
}
