package testPokae;

import java.io.FileNotFoundException;
import java.io.IOException;

import combat.Combat;
import joueur.DresseurIAElaboree;
import joueur.DresseurIARandom;
import pokedex.Pokedex;

public class Main  {
	public static Pokedex pokedexGeneral;

	public static void main(String[] args) {
		// Initialisation du pokedex
		pokedexGeneral = new Pokedex();
		try {
			pokedexGeneral.initialisationCapacites();
			pokedexGeneral.initialisationEspeces();
			pokedexGeneral.initialisationEfficacites();
			pokedexGeneral.initialisationEvolution();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {}
		

		DresseurIAElaboree dresseurIA1 = new DresseurIAElaboree();

		DresseurIARandom dresseurIA2 = new DresseurIARandom();

//	Pour faire jouer un joueur il suffit d'enlever les commentaires
//		DresseurHumain dresseurHumain1 = new DresseurHumain();
	
//		Combat combatTest = new Combat(dresseurHumain1,dresseurIA1);
		Combat combatTest = new Combat(dresseurIA1,dresseurIA2);
		combatTest.commence();

	}

}
