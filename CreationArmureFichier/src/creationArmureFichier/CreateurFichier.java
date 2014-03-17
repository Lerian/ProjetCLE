package creationArmureFichier;

import java.io.FileReader;
import java.util.Properties;

import armor.Armor;
import interfaces.ICreateur;
import interfaces.IPlugin;

public class CreateurFichier implements ICreateur, IPlugin{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Armor cree(String filename) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(filename + ".txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
