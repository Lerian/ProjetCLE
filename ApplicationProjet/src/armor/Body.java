package armor;

public class Body extends Equipement {
	String color;
	int protection;
	
	public Body(String name) {
		super(name);
		
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getProtection() {
		return protection;
	}
	public void setProtection(int protection) {
		this.protection = protection;
	}
	
	@Override
	public String toString() {
		return "Body [name = " + name +", color=" + color + ", protection=" + protection + "]";
	}
}
