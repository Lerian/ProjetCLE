package armor;

import java.util.List;

public interface IArmor {

	public abstract String getName();

	public abstract void setName(String name);

	public abstract Energy getEnergyAvailable();

	public abstract void setEnergyAvailable(Energy energyAvailable);

	public abstract List<Equipement> getEquipements();

}