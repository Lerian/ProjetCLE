package interfaces;

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
}
