package interfaces;

import java.util.Properties;

public interface IPlugin {

	/**
	 * Méthode de lancement simple, sans référence au PluginManager nécessaire
	 */
	void run();
	
	/**
	 * Méthode renseignant le type du plugin
	 * @return
	 */
	public String type();
	
	public void receiveProperties(Properties prop);
}
