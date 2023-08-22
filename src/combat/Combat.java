package combat;

import capacités.Attaque;
import capacités.Capacite;
import capacités.Categorie;
import capacités.Echange;
import interfaces.IAttaque;
import interfaces.ICombat;
import interfaces.IDresseur;
import interfaces.IPokemon;
import interfaces.ITour;
import joueur.Dresseur;
import joueur.DresseurHumain;
import pokemon.EType;
import pokemon.Pokemon;
import testPokae.Main;

/**
 * Classe Combat du package combat, elle implémente l'interface IEspace, cette classe permet de gérer les combats.
 * @author Logan Brunet, Lucas Schiavetti, Sacha Hassan, Logan Laporte, Arthur Bailleul
 */
public class Combat implements ICombat {
	private Dresseur dresseur1;
	private Dresseur dresseur2;
	private Dresseur vainqueur;
	public int nombreTours = 0;

	/**
	 * Constructeur unique de la classe Combat
	 * @param dresseur1, le dresseur numero 1 pour le combat
	 * @param dresseur2, le dresseur numero 2 pour le combat
	 */
	public Combat(Dresseur dresseur1, Dresseur dresseur2) {
		this.dresseur1 = dresseur1;
		dresseur1.setCombatActuel(this);
		this.dresseur2 = dresseur2;
		dresseur2.setCombatActuel(this);
	}

	/**
	 * Lance le combat.
	 */
	@Override
	public void commence() {
		dresseur1.soigneRanch();
		dresseur2.soigneRanch();

		System.out.println("Le combat entre " + dresseur1.getNom()
				+ " et " + dresseur2.getNom() + " est sur le point de commencer.");

		// 1er Tour
		dresseur1.setCombattant(dresseur1.choisitCombattant());
		dresseur2.setCombattant(dresseur2.choisitCombattant());

		// Autres Tours
		while (dresseur1.ranchEvanoui() == false && dresseur2.ranchEvanoui() == false) {
			// On vérifie si les 2 pokemons peuvent encore combattre, si ce n'est
			// pas le cas, on remplace celui qui ne peut plus combattre
			if (dresseur1.getCombattant().horsCombat() == true
					|| dresseur2.getCombattant().horsCombat() == true) {
				if (dresseur1.getCombattant().horsCombat() == true) {

					// On dit que le pokemon ne peut plus combattre
					if (dresseur1.getCombattant().estEvanoui() == true) {
						System.out.println("\r\nLe " + dresseur1.getCombattant().getNom() + " de " +dresseur1.getNom() + " est KO.");
					}
					else {
						System.out.println("\r\nLe " + dresseur1.getCombattant().getNom() + " de " +dresseur1.getNom() + " n'a plus de PP sur toutes ses capacités");
					}

					Pokemon combattantKO = dresseur1.getCombattant();

					Echange echangeDresseur1 = new Echange(dresseur1);
					echangeDresseur1.setPokemon(dresseur1.choisitCombattantContre(dresseur2.getCombattant()));
					echangeDresseur1.utilise();
					System.out.println(echangeDresseur1.toString() + " entre sur le terrain.");

					int nivDresseurTest = dresseur2.getCombattant().getNiveau();
					dresseur2.getCombattant().gagneExperienceDe(combattantKO);
					if (nivDresseurTest < dresseur2.getCombattant().getNiveau()) {
						try {
							dresseur2.peutEnseignerCapacité(dresseur2.getCombattant());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}


				}
				else {

					// On dit que le pokemon ne peut plus combattre
					if (dresseur2.getCombattant().estEvanoui() == true) {
						System.out.println("\r\nLe " + dresseur2.getCombattant().getNom() + " de " +dresseur2.getNom() + " est KO.");
					}
					else {
						System.out.println("\r\nLe " + dresseur2.getCombattant().getNom() + " de " +dresseur2.getNom() + " n'a plus de PP sur toutes ses capacités");
					}

					Pokemon combattantKO = dresseur2.getCombattant();

					Echange echangeDresseur2 = new Echange(dresseur2);
					echangeDresseur2.setPokemon(dresseur2.choisitCombattantContre(dresseur1.getCombattant()));
					echangeDresseur2.utilise();
					System.out.println(echangeDresseur2.toString() + " entre sur le terrain.");
					int nivDresseurTest = dresseur1.getCombattant().getNiveau();
					dresseur1.getCombattant().gagneExperienceDe(combattantKO);
					if (nivDresseurTest < dresseur1.getCombattant().getNiveau()) {
						try {
							dresseur1.peutEnseignerCapacité(dresseur1.getCombattant());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			afficheInterfaceTour();

			IAttaque attaquePokemonDresseur1 = dresseur1.choisitAttaque(dresseur1.getCombattant(), dresseur2.getCombattant());
			IAttaque attaquePokemonDresseur2 = dresseur2.choisitAttaque(dresseur2.getCombattant(), dresseur1.getCombattant());

			Tour TourI = nouveauTour(dresseur1.getCombattant(),attaquePokemonDresseur1,dresseur2.getCombattant(),attaquePokemonDresseur2);
			TourI.commence();

			nombreTours++;
			System.out.println("\r\n\r\n|-----------------------------------------------|");
		}

		// Fin du combat
		if (dresseur1.ranchEvanoui() == true) {
			vainqueur = dresseur2;
			System.out.println("\r\n" + dresseur1.getCombattant().getNom() + " est KO, " + dresseur1.getNom() + " n'a plus de pokemon en état de combattre.");
		}
		else {
			vainqueur = dresseur1;
			System.out.println("\r\n" + dresseur2.getCombattant().getNom() + " est KO, " + dresseur2.getNom() + " n'a plus de pokemon en état de combattre.");
		}
		termine();

	}

	/**
	 * @return le dresseur 1 du combat
	 */
	@Override
	public Dresseur getDresseur1() {
		return dresseur1;
	}

	/**
	 * @return le dresseur 2 du combat
	 */
	@Override
	public Dresseur getDresseur2() {
		return dresseur2;
	}

	/**
	 * Affiche les infos du tour actuel : nombre de tours, les infos du dresseur 1 et du dresseur 2
	 */
	public void afficheInterfaceTour() {
		System.out.println("\r\n|-----------------------------------------------|"
				+ "\r\n\r\nTour " + (nombreTours+1) );


		System.out.println("\r\nDresseur : " + dresseur1.getNom()
				+ "\r\nPokemon en jeu : " + dresseur1.getCombattant().getNom()
				+ "\r\nPV : " + dresseur1.getCombattant().getPourcentagePV() + "%");

		System.out.println("\r\nDresseur : " + dresseur2.getNom()
				+ "\r\nPokemon en jeu : " + dresseur2.getCombattant().getNom()
				+ "\r\nPV : " + dresseur2.getCombattant().getPourcentagePV() + "%");
	}

	/**
	 * @param pok1, le pokemon du dresseur 1 durant le tour
	 * @param atk1, l'attaque du pokemon 1 durant le tour
	 * @param pok2, le pokemon du dresseur 2 durant le tour
	 * @param atk2, l'attaque du pokemon 2 durant le tour
	 * @return t, l'instance d'un nouveau tour de jeu.
	 */
	@Override
	public Tour nouveauTour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {
		Tour t = new Tour(pok1,pok2,atk1,atk2);
		return t;
	}

	/**
	 * Affiche la fin du combat avec le nom du vainqueur et le nombre de tours.
	 */
	@Override
	public void termine() {
		System.out.println("Le combat s'est terminé en "
				+ nombreTours + " tours et a été remporté par "
				+vainqueur.getNom() + ".");
	}

}
