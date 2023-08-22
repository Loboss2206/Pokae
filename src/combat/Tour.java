package combat;

import capacités.Capacite;
import capacités.Echange;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.ITour;

/**
 * Classe Tour du package combat, elle implémente l'interface ITour, cette classe permet de gérer les tours de combat.
 * @author Logan Brunet, Lucas Schiavetti, Sacha Hassan, Logan Laporte, Arthur Bailleul
 */
public class Tour implements ITour {
	private IPokemon pokemon1;
	private IPokemon pokemon2;
	private IAttaque attaquePokemon1;
	private IAttaque attaquePokemon2;
	
	/**
	 * Constructeur unique de la classe Tour
	 * @param pok1, le pokemon du dresseur 1
	 * @param pok2, le pokemon du dresseur 2
	 * @param attaqueDresseur1, le dresseur 1
	 * @param attaqueDresseur2,  le dresseur 2
	 */
	public Tour(IPokemon pok1, IPokemon pok2, IAttaque attaqueDresseur1, IAttaque attaqueDresseur2) {
		pokemon1 = pok1;
		pokemon2 = pok2;
		attaquePokemon1 = attaqueDresseur1;
		attaquePokemon2 = attaqueDresseur2;
	}
	
	/**
	 * Lance un tour de combat.
	 */
	@Override
	public void commence() {
		// Si la capacité est Riposte
		if (attaquePokemon1.toString().equals("Riposte") || attaquePokemon2.toString().equals("Riposte") ) {

			// Si les 2 pokemons utilisent Riposte
			if (attaquePokemon1.toString().equals("Riposte") && attaquePokemon2.toString().equals("Riposte")) {
				if (pokemon1.getStat().getVitesse() >= pokemon2.getStat().getVitesse()) {
					System.out.println(pokemon1.getNom() + " attaque " + pokemon2.getNom() + " avec Riposte");
					System.out.println("Cela n'a aucun effet");
					System.out.println(pokemon2.getNom() + " attaque " + pokemon1.getNom() + " avec Riposte");
					System.out.println("Cela n'a aucun effet");
				}
				else {
					System.out.println(pokemon2.getNom() + " attaque " + pokemon1.getNom() + " avec Riposte");
					System.out.println("Cela n'a aucun effet");
					System.out.println(pokemon1.getNom() + " attaque " + pokemon2.getNom() + " avec Riposte");
					System.out.println("Cela n'a aucun effet");
				}
			}

			// Si le pokemon 1 utilise Riposte
			else if (attaquePokemon1.toString().equals("Riposte")) {

				try {
					Capacite testCapacite = (Capacite) attaquePokemon2;

					if (testCapacite.getCategorie().isSpecial() == true) {
						int PvPokemonAvant = pokemon1.getStat().getPV();
						pokemon1.subitAttaqueDe(pokemon2, attaquePokemon2);
						int degatsSubit = PvPokemonAvant - pokemon1.getStat().getPV();

						System.out.println(pokemon2.getNom() + " attaque " + pokemon1.getNom() + " avec " + attaquePokemon2.toString());
						if (degatsSubit == 0) {
							System.out.println(pokemon1.getNom() + " esquive l'attaque");
						}

						System.out.println(pokemon1.getNom() + " attaque" + pokemon2.getNom() + " avec Riposte");
						System.out.println("Cela n'a aucun effet");

					} else {
						int PvPokemonAvant = pokemon1.getStat().getPV();
						pokemon1.subitAttaqueDe(pokemon2, attaquePokemon2);
						int degatsSubit = PvPokemonAvant - pokemon1.getStat().getPV();


						System.out.println(pokemon2.getNom() + " attaque " + pokemon1.getNom() + " avec " + attaquePokemon2.toString());
						if (degatsSubit == 0) {
							System.out.println(pokemon1.getNom() + " esquive l'attaque");
						}

						pokemon2.getStat().setPV(pokemon2.getStat().getPV() - (degatsSubit * 2));
						System.out.println(pokemon1.getNom() + " attaque" + pokemon2.getNom() + " avec Riposte");
						if (degatsSubit == 0) {
							System.out.println("Cela n'a aucun effet");
						}
					}
				} catch (ClassCastException e) {
					pokemon1.subitAttaqueDe(pokemon2, attaquePokemon2);
					System.out.println(pokemon2.getNom() + " est echangé avec " + attaquePokemon2.toString());
					System.out.println(pokemon1.getNom() + " attaque" + pokemon2.getNom() + " avec Riposte");
					System.out.println("Cela n'a aucun effet");
				}
			}

			// Si le pokemon 2 utilise Riposte
			else {
				try {
					Capacite testCapacite = (Capacite) attaquePokemon1;

					if (testCapacite.getCategorie().isSpecial() == true) {
						int PvPokemonAvant = pokemon2.getStat().getPV();
						pokemon2.subitAttaqueDe(pokemon1, attaquePokemon1);
						int degatsSubit = PvPokemonAvant - pokemon2.getStat().getPV();

						System.out.println(pokemon1.getNom() + " attaque " + pokemon2.getNom() + " avec " + attaquePokemon1.toString());
						if (degatsSubit == 0) {
							System.out.println(pokemon2.getNom() + " esquive l'attaque");
						}

						System.out.println(pokemon2.getNom() + " attaque" + pokemon1.getNom() + " avec Riposte");
						System.out.println("Cela n'a aucun effet");

					}
					else {
						int PvPokemonAvant = pokemon2.getStat().getPV();
						pokemon2.subitAttaqueDe(pokemon1, attaquePokemon1);
						int degatsSubit = PvPokemonAvant - pokemon2.getStat().getPV();


						System.out.println(pokemon1.getNom() + " attaque " + pokemon2.getNom() + " avec " + attaquePokemon1.toString());
						if (degatsSubit == 0) {
							System.out.println(pokemon2.getNom() + " esquive l'attaque");
						}

						pokemon1.getStat().setPV(pokemon1.getStat().getPV() - (degatsSubit * 2));
						System.out.println(pokemon2.getNom() + " attaque" + pokemon1.getNom() + " avec Riposte");
						if (degatsSubit == 0) {
							System.out.println("Cela n'a aucun effet");
						}
					}
				} catch (ClassCastException e) {
					pokemon2.subitAttaqueDe(pokemon1, attaquePokemon1);
					System.out.println(pokemon1.getNom() + " est echangé avec " + attaquePokemon1.toString());
					System.out.println(pokemon2.getNom() + " attaque" + pokemon1.getNom() + " avec Riposte");
					System.out.println("Cela n'a aucun effet");
				}
			}

		}
		else {
			double rand = 0.5;

			// On regarde si les vitesses des 2 pokemons sont égales, si c'est le cas on choisit qui attaque au hasard
			if (pokemon1.getStat().getVitesse() == pokemon2.getStat().getVitesse()) {
				rand = Math.random();
			}

			// Le cas où le pokemon 1 est plus rapide que le pokemon 2
			if (pokemon1.getStat().getVitesse() > pokemon2.getStat().getVitesse() || rand < 0.5) {

				int PvPokemonAvant = pokemon2.getStat().getPV();
				pokemon2.subitAttaqueDe(pokemon1, attaquePokemon1);
				int degatsSubit = PvPokemonAvant - pokemon2.getStat().getPV();


				// On regarde si l'attaque est une capacité ou un echange pour faire un affichage différent
				if (attaquePokemon1 instanceof Capacite) {
					System.out.println("\r\n" + pokemon1.getNom() + " attaque " + pokemon2.getNom() + " avec " + attaquePokemon1.toString());
					if (degatsSubit == 0) {
						System.out.println(pokemon2.getNom() + " esquive l'attaque");
					}
				} else {
					System.out.println("\r\n" + pokemon1.getNom() + " est echangé avec " + attaquePokemon1.toString());
					pokemon1 = ((Echange) attaquePokemon1).getDresseurEchange().getCombattant();
				}


				// Si le pokemon 2 n'est pas evanoui, on le fait attaquer
				if (pokemon2.estEvanoui() == false) {
					int PvPokemonAvant1 = pokemon1.getStat().getPV();
					pokemon1.subitAttaqueDe(pokemon2, attaquePokemon2);
					int degatsSubit1 = PvPokemonAvant1 - pokemon1.getStat().getPV();

					// On regarde si l'attaque est une capacité ou un echange pour faire un affichage différent
					if (attaquePokemon2 instanceof Capacite) {
						System.out.println(pokemon2.getNom() + " attaque " + pokemon1.getNom() + " avec " + attaquePokemon2.toString());
						if (degatsSubit1 == 0) {
							System.out.println(pokemon1.getNom() + " esquive l'attaque");
						}
					} else {
						System.out.println(pokemon2.getNom() + " est echangé avec " + attaquePokemon2.toString());
					}
				}
			}

			// Le cas où le pokemon 2 est plus rapide que le pokemon 1
			else if (pokemon1.getStat().getVitesse() < pokemon2.getStat().getVitesse() || rand > 0.5) {

				int PvPokemonAvant1 = pokemon1.getStat().getPV();
				pokemon1.subitAttaqueDe(pokemon2, attaquePokemon2);
				int degatsSubit1 = PvPokemonAvant1 - pokemon1.getStat().getPV();

				// On regarde si l'attaque est une capacité ou un echange pour faire un affichage différent
				if (attaquePokemon2 instanceof Capacite) {
					System.out.println("\r\n" + pokemon2.getNom() + " attaque " + pokemon1.getNom() + " avec " + attaquePokemon2.toString());
					if (degatsSubit1 == 0) {
						System.out.println(pokemon1.getNom() + " esquive l'attaque");
					}
				} else {
					System.out.println("\r\n" + pokemon2.getNom() + " est echangé avec " + attaquePokemon2.toString());
					pokemon2 = ((Echange) attaquePokemon2).getDresseurEchange().getCombattant();
				}

				if (pokemon1.estEvanoui() == false) {

					int PvPokemonAvant = pokemon2.getStat().getPV();
					pokemon2.subitAttaqueDe(pokemon1, attaquePokemon1);
					int degatsSubit = PvPokemonAvant - pokemon2.getStat().getPV();


					// On regarde si l'attaque est une capacité ou un echange pour faire un affichage différent
					if (attaquePokemon1 instanceof Capacite) {
						System.out.println(pokemon1.getNom() + " attaque " + pokemon2.getNom() + " avec " + attaquePokemon1.toString());
						if (degatsSubit == 0) {
							System.out.println(pokemon2.getNom() + " esquive l'attaque");
						}
					} else {
						System.out.println(pokemon1.getNom() + " est echangé avec " + attaquePokemon1.toString());
					}
				}
			}
		}
	}
}		
