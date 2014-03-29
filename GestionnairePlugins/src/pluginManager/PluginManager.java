package pluginManager;

import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import interfaces.IComplexPlugin;
import interfaces.IPlugin;
import interfaces.IPluginManager;

public class PluginManager implements IPluginManager {
	
	private URLClassLoader pluginClassLoader;
	private String rightPathToHome = "";
	private Hashtable<String,ArrayList<String>> availablePlugins = new Hashtable<String,ArrayList<String>>();
	
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

		System.out.println("================Scan================");
		scanPlugins(urls);
		System.out.println("====================================");
		System.out.println(availablePlugins);
		System.out.println("====================================");
		
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
					//System.out.println(currentFile.toString());
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
					//System.out.println(className);
					try {
						Class<?> classToTest = Class.forName(className,false,pluginClassLoader);
						if(IPlugin.class.isAssignableFrom(classToTest)) {
							try {
								Object obj = classToTest.newInstance();
								System.out.println(((IPlugin) obj).type()+" : "+classToTest.getName());
								
								// Ajout du plugin à la collection gérée par le pluginMananger
								if(availablePlugins.containsKey(((IPlugin) obj).type())) {
									ArrayList<String> existingValues = availablePlugins.get(((IPlugin) obj).type());
									existingValues.add(classToTest.getName());
									availablePlugins.put(((IPlugin) obj).type(), existingValues);
								} else {
									ArrayList<String> value = new ArrayList<String>();
									value.add(classToTest.getName());
									availablePlugins.put(((IPlugin) obj).type(), value);
								}
								
							} catch (InstantiationException
									| IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
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
			Properties pluginProperties = readPluginInit(rightPathToHome+initPath);
			Class<?> pluginToLoad = Class.forName(pluginName,false,pluginClassLoader);
			if(IPlugin.class.isAssignableFrom(pluginToLoad)) {
				if(IComplexPlugin.class.isAssignableFrom(pluginToLoad)) {
					res = (IComplexPlugin) pluginToLoad.newInstance();
					((IComplexPlugin)res).receivePluginManager(this);
				} else {
					res = (IPlugin) pluginToLoad.newInstance();
				}
				res.receiveProperties(pluginProperties);
				res.run();
			} else {
				System.out.println("Erreur d'interface");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	// Charge un plugin aléatoire du type donné
	public IPlugin loadRandomPlugin(String pluginType) {
		ArrayList<String> possiblePlugin = availablePlugins.get(pluginType);
		
		if(possiblePlugin != null && !possiblePlugin.isEmpty()) {
			Random r = new Random();
			int value = 0 + r.nextInt(possiblePlugin.size()-1);
			
			return loadPlugin(possiblePlugin.get(value), "");
		} else {
			return null;
		}
	}
	
	private Properties readPluginInit(String initPath) {
		Properties res = new Properties();
		
		if(new File(initPath).isFile()) {
			try {
				res.load(new FileReader(initPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		res.setProperty("pathToInit", initPath);
		res.setProperty("pathToHome", rightPathToHome);
		
		return res;
	}
}
