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
					rightPathToHome = System.getProperty("user.home")+"/"+s;
				}
			} else {
				if(new File(s).exists()) {
					rightPathToHome = s;
				}
			}
		}
		
		for(String s: pathsToUse) {
			try {
				urls[i] = (new File(rightPathToHome+s)).toURI().toURL();
				System.out.println(urls[i].toExternalForm());
				i++;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		pluginClassLoader = new URLClassLoader(urls);
		
		scanPlugins(urls);
		
		if(pluginsToLoad.length > 0) {
			for(String s : pluginsToLoad) {
				//List<String> argsArray = Arrays.asList(prop.getProperty(s).split("\\s*,\\s*"));
				
				loadPlugin(s,prop.getProperty(s));
			}
		}
	}
	
	private void scanPlugins(URL[] urls) {
		for(URL currentURL : urls) {
			File currentFile = new File(currentURL.toString().substring(5));
			if(currentFile.isDirectory()) {
				// gestion de répertoire
				File[] subFiles = currentFile.listFiles();
				URL[] subURLs = new URL[subFiles.length];
				int currentIndex = 0;
				for(File f: subFiles) {
					try {
						subURLs[currentIndex] = f.toURI().toURL();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					currentIndex++;
				}
				scanPlugins(subURLs);
			} else {
				// gestion de fichier
				if(currentFile.toString().endsWith(".class")) {
					System.out.println(currentFile.toString());
					String[] pathTokens = currentFile.toString().split("/");
					String className = "";
					boolean binFound = false;
					for(String s: pathTokens) {
						if(binFound) {
							if(s.endsWith(".class")) {
								s = s.substring(0, s.length()-6);
								className = className.concat(s);
							} else {
								className = className.concat(s);
								className = className.concat(".");
							}
						} else {
							if(s.compareTo("bin") == 0) {
								binFound = true;
							}
						}
					}
					System.out.println(className);
					try {
						Class<?> classToTest = Class.forName(className,false,pluginClassLoader);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	// Charge un plugin donné selon les arguments donnés
	public IPlugin loadPlugin(String pluginName, /*List<*/String/*>*/ initPath) {
		IPlugin res = null;
		
		try {
			System.out.println("Chargement de "+pluginName);
			//for(String s: args) {
				System.out.println("arg: "+initPath);
			//}
			readPluginInit(initPath);
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

	private void readPluginInit(String initPath) {
		// TODO Auto-generated method stub
		
	}
}
