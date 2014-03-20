package armor;

public class Weapon extends Equipement {

	public Weapon(String name) {
		super(name);
	}

	int damage;

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		return "Weapon [name : " + name + ", damage=" + damage + "]";
	}
	
}
