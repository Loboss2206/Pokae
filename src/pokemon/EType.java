package pokemon;

import interfaces.IType;

/**
* Enumeration EType du package pokemon, elle implemente l'interface IType, cette classe permet de sortir les Types de chaque pokémons
* @author Lucas Schiavetti, Sacha Hassan, Logan Brunet, Logan Laporte, Arthur Bailleul
*
*/
public enum EType implements IType {
	Combat("Combat"),
	Dragon("Dragon"),
	Eau("Eau"),
	Electrik("Electrik"),
	Feu("Feu"),
	Glace("Glace"),
	Insecte("Insecte"),
	Normal("Normal"),
	Plante("Plante"),
	Poison("Poison"),
	Psy("Psy"),
	Roche("Roche"),
	Sol("Sol"),
	Spectre("Spectre"),
	Vol("Vol");
	
	 /** 
	 * On déclare en final String le nom du type du pokémon
     *	@param nom, nom du type
     */
	private final String nom;
	
	/**
	 * On indique que le nom du string EType se réfère au nom en final déclaré au dessus 
	 * @param nom, definit le nom du type 
    */
	private EType(String nom) {
		this.nom = nom;
	}
	
	/**
     * Le getter getNom recupère le nom du type du pokémon
     * @return Le nom du Type du pokémon
     */
	@Override
	public String getNom() {
		return nom;
	}

}