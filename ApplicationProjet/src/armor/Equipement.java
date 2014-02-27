package armor;

public class Equipement {
	String name;
	Energy energyNeeded;
	Position pos;
	
    public Equipement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Equipement(String name) {
		super();
		this.name = name;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Energy getEnergyNeeded() {
        return energyNeeded;
    }
    public void setEnergyNeeded(Energy energyNeeded) {
        this.energyNeeded = energyNeeded;
    }
    public Position getPos() {
        return pos;
    }
    public void setPos(Position pos) {
        this.pos = pos;
    }
    
	@Override
	public String toString() {
		return "Equipement [name=" + name + ", energyNeeded=" + energyNeeded
				+ ", pos=" + pos + "]";
	}
}
