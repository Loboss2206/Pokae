package pokemon;

import interfaces.IStat;

/**
 * Classe Stat du package pokemon, elle implémente l'interface IStat, cette classe permet de connaître les statistiques d'un pokémon
 * @author Lucas Schiavetti, Sacha Hassan, Logan Brunet, Logan Laporte, Arthur Bailleul
 *
 */
public class Stat implements IStat {
	private int pv;
	private int force;
	private int defense;
	private int vitesse;
	private int special;
	
	/**
	 * @return les point de vie du pokemon
	 */
	@Override
	public int getPV() {
		return pv;
	}

	/**
	 * @return la force d'un pokemon
	 */
	@Override
	public int getForce() {
		return force;
	}

	/**
	 * @return la défense d'un pokémon
	 */
	@Override
	public int getDefense() {
		return defense;
	}

	/**
	 * @return l'attaque spécial d'un pokémon
	 */
	@Override
	public int getSpecial() {
		return special;
	}
	
	/**
	 * @return la vitesse d'un pokémon
	 */
	@Override
	public int getVitesse() {
		return vitesse;
	}

	/**
	 * Permet de modifier les point de vie d'un pokémon
	 * @param i l'entier a modifier
	 */
	@Override
	public void setPV(int i) {
		pv = i;
	}

	/**
	 * permet de modifier la force d'un pokémon
	 * @param i l'entier a modifier
	 */
	@Override
	public void setForce(int i) {
		force = i;
	}

	/**
	 * permet de modifier la défense d'un pokémon
	 * @param i l'entier a modifier
	 */
	@Override
	public void setDefense(int i) {
		defense = i;
	}

	/**
	 * permet de modifier la vitesse d'un pokémon
	 * @param i l'entier a modifier
	 */
	@Override
	public void setVitesse(int i) {
		vitesse = i;
	}

	/**
	 * permet de modifier l'attaque spéciale d'un pokémon
	 * @param i l'entier a modifier
	 */
	@Override
	public void setSpecial(int i) {
		special = i;
	}
	
	/**
	 * Affiche tous les attributs de la classe
	 */
	@Override
	public String toString() {
		return " [pv=" + pv + ", force=" + force + ", defense=" + defense + ", vitesse=" + vitesse + ", special="
				+ special + "]";
	}

}
