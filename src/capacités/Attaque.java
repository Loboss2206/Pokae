package capacités;

import interfaces.IAttaque;
import interfaces.IPokemon;

/**
 * Classe Attaque du package capacités, elle implémente l'interface IAttaque, cette classe permet de gerer les attaques.
 * @author Logan Brunet, Lucas Schiavetti, Sacha Hassan, Logan Laporte, Arthur Bailleul
 */
public class Attaque implements IAttaque {
	
	/**
	 * @return le nombre de dommage d'une attaque d'un attaquant sur un receveur.
	 */
	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		return 0;
	}
	
	/**
	 * Fait diminuer de 1 le nombre restant de fois où l'attaque peut être utilisée, arrivé à 0.
	 */
	@Override
	public void utilise() {
		
	}

}
