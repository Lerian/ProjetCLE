ProjetCLE
=========

Le projet de conception de logiciels extensibles, M1 ALMA université de Nantes.

Fonctionnement:
===============

Pour paramétrer le projet, il faut éditer le fichier d'initialisations (GestionnairePlugins/resources/init):

    loadAtStart: les classes à instancier au démarrage (actuellement l'éditeur d'armure)
  
    homePath: le chemin commun à toutes les URLs permettant d'accéder aux .class à charger. Peut être absolu ou relatif à partir du home. Si plusieurs sont présents, le dernier valide dans la liste sera sélectionné.
  
    binPaths: tous les chemins qui, concaténés au homePath, permettent d'accéder aux dossiers contenant les .class à charger.
  
    Une cible peut ensuite être définie pour chaque valeur de loadAtStart, contenant les paramètres utilisés lors de son instanciation (non pris en compte actuellement).
  
  Pour ces 4 cibles, en cas de valeurs multiples, chacune doit être séparée de la suivante par une virgule (les espaces et tabulations autour de celle-ci sont ignorés). Pour marquer ces valeurs sur plusieurs lignes, mettre un \ à la fin de toutes les lignes sauf la dernière.

Pour lancer le projet, il faut exécuter le main contenu dans la classe main.PluginsPlatform du projet GestionnairePlugins.

Le fichier d'initialisation est lu, les plugins indiqués dans loadAtStart sont chargés et instanciés.

Types de plugins:
=================

2 types de plugins sont présents:
  - Les plugins simples, qui implémentent l'interface IPlugin. Ils sont faits pour être utilisés par le deuxième type de plugin.
  - Les plugins complexes, qui implémentent l'interface IComplexPlugin. Lors de leur instanciation, leur méthode run() est exécutée automatiquement.
  
Modification du comportement de l'éditeur d'armure:
===================================================

Le comportement de l'éditeur d'armure est actuellement déterminé par le code présent dans la méthode run() de la classe armorEditor.ArmorEditor (projet ArmorEditor).

Fonctionnement de l'éditeur d'armure:
=====================================

L'éditeur doit charger des plugins avant de pouvoir les utiliser, grâce aux méthodes mise à disposition:

  - IAfficheur loadAfficheur(String nomAfficheur, List<String> args)
  - ICreateur loadCreateur(String nomCreateur, List<String> args)
  - IModificateur loadModificateur(String nomModificateur, List<String> args)
  
Ces plugins doivent respecter les interfaces définies dans le package interfaces du projet ArmorEditor.

Les plugins ainsi chargés peuvent ensuite être utilisés via les 3 attributs de la classe:

  - IAfficheur pluginAfficheur
	- ICreateur pluginCreateur
	- IModificateur pluginModificateur

Plugins disponibles actuellement:
=================================

Création:
  - une création automatique via des valeurs par défaut (creationArmure.Createur).
  - une création via le contenu d'un fichier (creationArmureFichier.CreateurFichier).

Affichage:
  - un affichage console (affichageConsole.Afficheur).
  - un affichage graphique (affichageGraphique.Afficheur).

Modification:
  - un plugin offrant plusieurs options de modification (modificationArmure.Modificateur).
