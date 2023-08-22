package capacités;

import interfaces.IEchange;
import interfaces.IPokemon;
import pokemon.Pokemon;
import joueur.Dresseur;

/**
 * Classe Echange du package capacités, elle implémente l'interface IEchange, cette classe permet de changer de pokémon au combat.
 * @author Lucas Schiavetti, Sacha Hassan, Logan Brunet, Logan Laporte, Arthur Bailleul
 *
 */
public class Echange implements IEchange {
	private Pokemon pokemonRemplacant;
	private Dresseur dresseurEchange;
	
	public Echange(Dresseur dresseur) {
		dresseurEchange = dresseur;
	}
	
	/**
	 * Le lanceur est celui qui envoie l'attaque et le receveur la reçoit c'est a ce dernier que l'on va échanger le pokémon.
	 * @param lanceur est un IPokemon
	 * @param receveur est un IPokemon
	 * @return les points de vie perdus (ici 0 car on ne fait pas de dégats on échange juste le pokémon adverse)
	 */
	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		return 0;
	}

	/**
	 * On choisit l'echange qui va être fait.
	 */
	@Override
	public void utilise() {
		echangeCombattant();
	}

	/**
	 * On choisit le pokémon remplaçant.
	 * @param pok est un Pokemon
	 */
	@Override
    public void setPokemon(IPokemon pok) {
		this.pokemonRemplacant = (Pokemon) pok;
    }

	public Dresseur getDresseurEchange() {
		return dresseurEchange;
	}

	public Pokemon getPokemonRemplacant() {
		return pokemonRemplacant;
	}

	/**
	 * Activation du remplacement et renvoie l'ancien pokémon dans le ranch
	 */
	@Override
	public IPokemon echangeCombattant() {
		Pokemon ancienPokemon = dresseurEchange.getCombattant();
		dresseurEchange.setCombattant(pokemonRemplacant);
		return ancienPokemon;
	}
	
	@Override
	public String toString() {
		return pokemonRemplacant.getNom();
	}

}
