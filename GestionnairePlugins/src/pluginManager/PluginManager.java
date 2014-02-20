package pluginManager;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import interfaces.IPlugin;
import interfaces.IPluginManager;

public class PluginManager implements IPluginManager {
	
	private static ClassLoader pluginClassLoader;
	
	public static void main(String[] args) {
		// Lecture du fichier de conf indiquant les plugins à lancer
			// lancement des plugins "simples"
			// lancement des plugins "compliqués" (référence au PluginManager nécessaire)
		// Création d'un classloader pour les plugins
		try {
			File file = new File("resources/pluginClasses/"); 
			URL url = file.toURI().toURL(); 
			URL[] urls = new URL[]{url}; 
			pluginClassLoader = new URLClassLoader(urls);	
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		String[] pluginsToLoad = prop.getProperty("loadAtStart").split("\\s*,\\s*");
		
		if(pluginsToLoad.length > 0) {
			for(String s : pluginsToLoad) {
				String[] argsForPlugin = prop.getProperty(s).split("\\s*,\\s*");
				
				loadPlugin(s,argsForPlugin);
			}
		}
	}
	
	// Charge un plugin donné selon les arguments donnés
	private static void loadPlugin(String pluginName, String[] args) {
		try {	
			System.out.println("Chargement de "+pluginName);
			for(String s: args) {
				System.out.println("arg: "+s);
			}
			Class<?> pluginToLoad = Class.forName(pluginName,false,pluginClassLoader);
			if(IPlugin.class.isAssignableFrom(pluginToLoad)) {
				pluginToLoad.newInstance();
			} else {
				System.out.println("Erreur d'interface");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
