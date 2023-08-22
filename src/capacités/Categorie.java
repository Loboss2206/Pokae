package capacités;

import interfaces.ICategorie;


/**
 * Classe Categorie du package capacités, elle implémente l'interface ICategorie, cette classe permet de savoir la catégorie d'une capacité.
 * @author Logan Brunet, Schiavetti, Sacha Hassan, Logan Laporte, Arthur Bailleul
 *
 */
public class Categorie implements ICategorie {
	private String nom;
	
	/**
	 * Constructeur de la classe
	 * @param nom de la catégorie
	 */
	public Categorie(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Permet de dire si une attaque est de catégorie spécial, si elle ne l'est pas elle est physique.
	 * @return true si la catégorie est Special, false sinon.
	 */
	@Override
	public boolean isSpecial() {
		return (nom == "Special");
	}

	/**
	 * @return le nom de la catégorie
	 */
	@Override
	public String getNom() {
		return nom;
	}
	
	/**
	 * Affiche tous les attributs de la classe
	 */
	@Override
	public String toString() {
		return nom;
	}
	

}