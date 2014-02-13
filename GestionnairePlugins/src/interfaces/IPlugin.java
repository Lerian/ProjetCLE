package interfaces;

public interface IPlugin {

	/**
	 * Méthode de lancement simple, sans référence au PluginManager nécessaire
	 */
	void run();
	
	/**
	 * Méthode de lancement commpliquée, avec référence au PluginManager nécessaire
	 * ou même méthode avec test des paramètres du constructeur
	 */
}
