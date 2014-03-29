package armorEditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import armor.*;
import interfaces.IAfficheur;
import interfaces.IComplexPlugin;
import interfaces.ICreateur;
import interfaces.IModificateur;
import interfaces.IPlugin;
import interfaces.IPluginManager;
import interfaces.PluginTypes;

public class ArmorEditor implements IComplexPlugin {

	private ArrayList<Armor> armors = new ArrayList<Armor>();
	private IPluginManager pluginLoader;
	private IAfficheur pluginAfficheur;
	private ICreateur pluginCreateur;
	private IModificateur pluginModificateur;
	
	private Properties props;
	
	public ArmorEditor() {
		System.out.println("Lancement de l'éditeur d'armure");
	}
	
	public void loadData(String creatorArgument) {
		//armors.add(pluginCreateur.cree("ArmureTest"));
		armors.add(pluginCreateur.cree(creatorArgument));
	}
	
	@Override
	public void run() {
		
		int index = 0;
		String entryValue = props.getProperty(String.valueOf(index));
		String[] entryParts;
		
		while(entryValue != null) {
			entryParts = entryValue.split(":=");
			switch(entryParts[0].trim()) {
			case "AFFICHEUR":
				entryParts = entryParts[1].split("#");
				pluginAfficheur = loadAfficheur(entryParts[0].trim(), entryParts[1].trim());
				break;
			case "CREATEUR":
				entryParts = entryParts[1].split("#");
				pluginCreateur = loadCreateur(entryParts[0].trim(), entryParts[1].trim());
				break;
			case "MODIFICATEUR":
				entryParts = entryParts[1].split("#");
				pluginModificateur = loadModificateur(entryParts[0].trim(), entryParts[1].trim());
				break;
			case "cree":
				loadData(entryParts[1].trim());
				break;
			case "affiche":
				switch(entryParts[1].trim()) {
				case "FIRST":
					pluginAfficheur.affiche(armors.get(0));
					break;
				case "LAST":
					pluginAfficheur.affiche(armors.get(armors.size()-1));
					break;
				case "ALL":
					for(Armor a : armors) {
						pluginAfficheur.affiche(a);
					}
					break;
				default:
					pluginAfficheur.affiche(armors.get(Integer.parseInt(entryParts[1].trim())));
				}
				break;
			case "modifie":
				entryParts = entryParts[1].split("#");
				switch(entryParts[0].trim()) {
				case "ARMOR_NAME":
					switch(entryParts[1].trim()) {
					case "FIRST":
						pluginModificateur.modifieNomArmure(armors.get(0), entryParts[2].trim());
						break;
					case "LAST":
						pluginModificateur.modifieNomArmure(armors.get(armors.size()-1), entryParts[2].trim());
						break;
					case "ALL":
						for(int i =0; i<armors.size();i++) {
							pluginModificateur.modifieNomArmure(armors.get(i), entryParts[2].trim());
						}
						break;
					default:
						pluginModificateur.modifieNomArmure(armors.get(Integer.parseInt(entryParts[1].trim())), entryParts[2].trim());
					}
					break;
				case "ENERGY_NAME":
					switch(entryParts[1].trim()) {
					case "ARMOR":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEnergyAvailable(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEnergyAvailable(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEnergyAvailable(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEnergyAvailable(), entryParts[3].trim());
						}
						break;
					case "HEAD":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.HEAD).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.HEAD).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.HEAD).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.HEAD).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					case "RHAND":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.RHAND).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.RHAND).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.RHAND).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.RHAND).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					case "LHAND":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.LHAND).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.LHAND).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.LHAND).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.LHAND).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					case "RLEG":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.RLEG).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.RLEG).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.RLEG).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.RLEG).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					case "LLEG":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.LLEG).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.LLEG).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.LLEG).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.LLEG).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					case "RARM":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.RARM).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.RARM).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.RARM).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.RARM).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					case "LARM":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.LARM).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.LARM).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.LARM).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.LARM).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					case "BODY":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieNomEnergie(armors.get(0).getEquipementAt(Position.BODY).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "LAST":
							pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEquipementAt(Position.BODY).getEnergyNeeded(), entryParts[3].trim());
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieNomEnergie(armors.get(i).getEquipementAt(Position.BODY).getEnergyNeeded(), entryParts[3].trim());
							}
							break;
						default:
							pluginModificateur.modifieNomEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.BODY).getEnergyNeeded(), entryParts[3].trim());
						}
						break;
					default:
						;
					}
					break;
				case "ENERGY_VALUE":
					switch(entryParts[1].trim()) {
					case "ARMOR":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEnergyAvailable(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEnergyAvailable(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEnergyAvailable(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEnergyAvailable(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "HEAD":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.HEAD).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.HEAD).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.HEAD).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.HEAD).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "RHAND":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.RHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.RHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.RHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.RHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "LHAND":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.LHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.LHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.LHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.LHAND).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "RLEG":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.RLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.RLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.RLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.RLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "LLEG":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.LLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.LLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.LLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.LLEG).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "RARM":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.RARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.RARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.RARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.RARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "LARM":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.LARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.LARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.LARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.LARM).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					case "BODY":
						switch(entryParts[2].trim()) {
						case "FIRST":
							pluginModificateur.modifieValEnergie(armors.get(0).getEquipementAt(Position.BODY).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "LAST":
							pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEquipementAt(Position.BODY).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							break;
						case "ALL":
							for(int i =0; i<armors.size();i++) {
								pluginModificateur.modifieValEnergie(armors.get(i).getEquipementAt(Position.BODY).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
							}
							break;
						default:
							pluginModificateur.modifieValEnergie(armors.get(Integer.parseInt(entryParts[1].trim())).getEquipementAt(Position.BODY).getEnergyNeeded(), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					default:
						;
					}
					break;
				case "EQUIPEMENT_NAME":
					switch(entryParts[1].trim()) {
					case "FIRST":
						pluginModificateur.modifieNomEquipement(armors.get(0).getEquipements().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
						break;
					case "LAST":
						pluginModificateur.modifieNomEquipement(armors.get(armors.size()-1).getEquipements().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
						break;
					case "ALL":
						for(int i =0; i<armors.size();i++) {
							pluginModificateur.modifieNomEquipement(armors.get(i).getEquipements().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
						}
						break;
					default:
						pluginModificateur.modifieNomEquipement(armors.get(Integer.valueOf(entryParts[1].trim())).getEquipements().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
					}
					break;
				case "BODY_COLOR":
					switch(entryParts[1].trim()) {
					case "FIRST":
						pluginModificateur.modifieColorBody(armors.get(0).getBodies().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
						break;
					case "LAST":
						pluginModificateur.modifieColorBody(armors.get(armors.size()-1).getBodies().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
						break;
					case "ALL":
						for(int i =0; i<armors.size();i++) {
							pluginModificateur.modifieColorBody(armors.get(i).getBodies().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
						}
						break;
					default:
						pluginModificateur.modifieColorBody(armors.get(Integer.valueOf(entryParts[1].trim())).getBodies().get(Integer.valueOf(entryParts[2].trim())), entryParts[3].trim());
					}
					break;
				case "BODY_PROTECTION":
					switch(entryParts[1].trim()) {
					case "FIRST":
						pluginModificateur.modifieProtectBody(armors.get(0).getBodies().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
						break;
					case "LAST":
						pluginModificateur.modifieProtectBody(armors.get(armors.size()-1).getBodies().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
						break;
					case "ALL":
						for(int i =0; i<armors.size();i++) {
							pluginModificateur.modifieProtectBody(armors.get(i).getBodies().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					default:
						pluginModificateur.modifieProtectBody(armors.get(Integer.valueOf(entryParts[1].trim())).getBodies().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
					}
					break;
				case "WEAPON_DAMAGE":
					switch(entryParts[1].trim()) {
					case "FIRST":
						pluginModificateur.modifieDamageWeapon(armors.get(0).getWeapons().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
						break;
					case "LAST":
						pluginModificateur.modifieDamageWeapon(armors.get(armors.size()-1).getWeapons().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
						break;
					case "ALL":
						for(int i =0; i<armors.size();i++) {
							pluginModificateur.modifieDamageWeapon(armors.get(i).getWeapons().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
						}
						break;
					default:
						pluginModificateur.modifieDamageWeapon(armors.get(Integer.valueOf(entryParts[1].trim())).getWeapons().get(Integer.valueOf(entryParts[2].trim())), Integer.valueOf(entryParts[3].trim()));
					}
					break;
				default:
					;
				}
				break;
			default:
				System.out.println("Instruction inconnue : "+entryParts[0].trim());
			}
			index++;
			entryValue = props.getProperty(String.valueOf(index));
		}
		
		//Appel des plugins Afficheur
		//pluginAfficheur = loadAfficheur("affichageConsole.Afficheur", new ArrayList<String>());
			//pluginAfficheur = loadAfficheur("affichageGraphique.Afficheur", /*new ArrayList<String>()*/"AffichageGraphique/resources/afficheur.init");
		
		//Appel des plugins createur
			//pluginCreateur = loadCreateur("creationArmure.Createur", new ArrayList<String>());
			//pluginCreateur = loadCreateur("creationArmureFichier.CreateurFichier", /*new ArrayList<String>()*/"CreationArmureFichier/resources/createurFichier.init");
		
		//pluginModificateur = loadModificateur("modificationArmure.Modificateur", /*new ArrayList<String>()*/"ModificationArmure/resources/modificateur.init");
			//loadData("IronM4nu");
		
		
			//pluginAfficheur.affiche(armors.get(armors.size()-1));
				
		//test du plugin modificateur et de toutes ses fonctions
		
		/*pluginModificateur.modifieNomArmure(armors.get(armors.size()-1), "Dubidule");
		pluginModificateur.modifieColorBody(armors.get(armors.size()-1).getBodies().get(0), "orange");
		pluginModificateur.modifieDamageWeapon(armors.get(armors.size()-1).getWeapons().get(0), 444);
		Energy nouvelleEnergie = new Energy();
		nouvelleEnergie.setName("nouvelleEnergy");
		nouvelleEnergie.setValue(222);
		pluginModificateur.modifieEnergieEquipement(armors.get(armors.size()-1).getEquipements().get(0), nouvelleEnergie);
		pluginAfficheur.affiche(armors.get(armors.size()-1));
		pluginModificateur.modifieNomEnergie(armors.get(armors.size()-1).getEnergyAvailable(), "SuperWarriorNRG");
		pluginModificateur.modifieNomEquipement(armors.get(armors.size()-1).getEquipements().get(0), "elementModifie");
		pluginModificateur.modifieProtectBody(armors.get(armors.size()-1).getBodies().get(0), 555);
		pluginModificateur.modifieValEnergie(armors.get(armors.size()-1).getEnergyAvailable(), 666);
		pluginAfficheur.affiche(armors.get(armors.size()-1));*/
		
	}
	
	@Override
	public void receivePluginManager(IPluginManager pluginManager) {
		pluginLoader = pluginManager;
	}
	
	public IAfficheur loadAfficheur(String nomAfficheur, /*List<*/String/*>*/ args) {
		return (IAfficheur) pluginLoader.loadPlugin(nomAfficheur, args);
	}
	
	public ICreateur loadCreateur(String nomCreateur, /*List<*/String/*>*/ args) {
		return (ICreateur) pluginLoader.loadPlugin(nomCreateur, args);
	}	
	
	public IModificateur loadModificateur(String nomModificateur, /*List<*/String/*>*/ args) {
		return (IModificateur) pluginLoader.loadPlugin(nomModificateur, args);
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return PluginTypes.MAIN.toString();
	}

	@Override
	public void receiveProperties(Properties prop) {
		props = prop;
		// TODO Auto-generated method stub
		/*System.out.println("#=============================#");
		System.out.println(prop);
		System.out.println("#=============================#");
		System.out.println(prop.getProperty("1"));
		System.out.println("#=============================#");*/
	}	

}
