package pluginManager;

import java.io.FileReader;
import java.util.Properties;

import interfaces.IPluginManager;

public class PluginManager implements IPluginManager {
	
	public static void main(String[] args) {
		// Lecture du fichier de conf indiquant les plugins à lancer
			// lancement des plugins "simples"
			// lancement des plugins "compliqués" (référence au PluginManager nécessaire)
		readConfigFile("resources/init");
	}
	
	// Lit le fichier donné et le traite
	private static void readConfigFile(String filename) {
		// Chargement du fichier
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Traitement des données chargées
		String[] pluginsToLoad = prop.getProperty("loadAtStart").split(",\\s*");
		
		if(pluginsToLoad.length > 0) {
			for(String s : pluginsToLoad) {
				String[] argsForPlugin = prop.getProperty(s).split(",\\s*");
				
				loadPlugin(s,argsForPlugin);
			}
		}
	}
	
	// Charge un plugin donné selon les arguments donnés
	private static void loadPlugin(String pluginName, String[] args) {
		System.out.println(pluginName);
		for(String s: args) {
			System.out.println("arg: "+s);
		}
	}
}
