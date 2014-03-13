package pluginManager;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import interfaces.IComplexPlugin;
import interfaces.IPlugin;
import interfaces.IPluginManager;

public class PluginManager implements IPluginManager {
	
	//private ConfigurableURLClassLoader pluginClassLoader;
	private URLClassLoader pluginClassLoader;
	
	public PluginManager() {
		// Lecture du fichier de conf indiquant les plugins à lancer
			// lancement des plugins "simples"
			// lancement des plugins "compliqués" (référence au PluginManager nécessaire)
		// Création d'un classloader pour les plugins
		readConfigFile("resources/init");
	}
	
	// Lit le fichier donné et le traite
	private void readConfigFile(String filename) {
		// Chargement du fichier
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Traitement des données chargées
		String[] pluginsToLoad = prop.getProperty("loadAtStart").split("\\s*,\\s*");
		String[] pathsToUse = prop.getProperty("binPaths").split("\\s*,\\s*");
		String[] pathsToHome = prop.getProperty("homePath").split("\\s*,\\s*");
		
		URL[] urls = new URL[pathsToUse.length];
		String rightPathToHome = "";
		int i = 0;
		
		for(String s: pathsToHome) {
			if(!s.startsWith("/")) {
				if(new File(System.getProperty("user.home")+"/"+s).exists()) {
					rightPathToHome = s;
				}
			} else {
				if(new File(s).exists()) {
					rightPathToHome = s;
				}
			}
		}
		
		for(String s: pathsToUse) {
			try {
				if(!s.startsWith("/")) {
					urls[i] = (new File(System.getProperty("user.home")+"/"+rightPathToHome+s)).toURI().toURL();
				} else {
					urls[i] = (new File(rightPathToHome+s)).toURI().toURL();
				}
				System.out.println(urls[i].toExternalForm());
				i++;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		pluginClassLoader = new URLClassLoader(urls);
		
		if(pluginsToLoad.length > 0) {
			for(String s : pluginsToLoad) {
				List<String> argsArray = Arrays.asList(prop.getProperty(s).split("\\s*,\\s*"));
				
				loadPlugin(s,argsArray);
			}
		}
	}
	
	// Charge un plugin donné selon les arguments donnés
	public IPlugin loadPlugin(String pluginName, List<String> args) {
		IPlugin res = null;
		
		try {
			System.out.println("Chargement de "+pluginName);
			for(String s: args) {
				System.out.println("arg: "+s);
			}
			Class<?> pluginToLoad = Class.forName(pluginName,false,pluginClassLoader);
			if(IPlugin.class.isAssignableFrom(pluginToLoad)) {
				if(IComplexPlugin.class.isAssignableFrom(pluginToLoad)) {
					res = (IComplexPlugin) pluginToLoad.newInstance();
					((IComplexPlugin)res).receivePluginManager(this);
					res.run();		
				} else {
					res = (IPlugin) pluginToLoad.newInstance();
				}
			} else {
				System.out.println("Erreur d'interface");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
