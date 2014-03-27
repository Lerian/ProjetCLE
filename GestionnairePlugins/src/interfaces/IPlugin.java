package interfaces;

import java.util.Properties;

public interface IPlugin {

	/**
	 * Méthode de lancement simple, sans référence au PluginManager nécessaire
	 */
	void run();
	
	/**
	 * Méthode renseignant le type du plugin
	 * @return Le type du plugin
	 */
	public String type();
	
	/**
	 * Méthode donnant accès à l'objet Properties contenant les informations du fichier de config
	 * @param prop Le Properties attaché au plugin
	 */
	public void receiveProperties(Properties prop);
}
