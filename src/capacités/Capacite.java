package capacités;

import interfaces.ICapacite;
import interfaces.ICategorie;
import interfaces.IPokemon;
import interfaces.IType;
import pokemon.EType;
import testPokae.Main;

import java.util.Random;

/**
 * Classe Capacite du package capacités, elle implémente l'interface ICapacite, cette classe permet de calculer les dégats de combat, d'utiliser une attaque, de restaurer les PP et elle contient des getters.
 * @author Lucas Schiavetti, Sacha Hassan, Logan Brunet, Logan Laporte, Arthur Bailleul
 *
 */
public class Capacite implements ICapacite {
	public int id;
	public String nom;
	private double precision;
	private int puissance;
	private int PP;
	private Categorie categorie;
	private EType type;

	public Capacite(int id, String nom, double prec, int puissance, int pp, Categorie categorie, EType type) {
		this.id = id;
		this.nom = nom;
		this.precision = prec;
		this.puissance = puissance;
		this.PP = pp;
		this.categorie = categorie;
		this.type = type;
	}

	/**
	 * La fonction calcule les dégats d'une attaque
	 *
	 * @param lanceur  le IPokemon qui utilise l'attaque
	 * @param receveur le IPokemon qui reçoit l'attaque
	 * @return les dégats fait par l'attaque.
	 */
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {

		// test pour voir si l'attaque touche
		double testPrecision = Math.random();
		if (testPrecision <= this.getPrecision()) {

			// si la puissance est égale à -1, l'attaque met KO le pokemon en 1 coup
			if (this.getPuissance() == -1) {
				return receveur.getStat().getPV();
			}
			// Sonic-Boom
			else if (this.getPuissance() == -2) {
				return 20;
			}
			// Frappe Atlas
			else if (this.getPuissance() == -4) {
				return lanceur.getNiveau();
			}
			// Draco-Rage
			else if (this.getPuissance() == -5) {
				return 40;
			}
			// Ombre Nocturne
			else if (this.getPuissance() == -6) {
				return lanceur.getNiveau();
			}
			// Vague Psy
			else if (this.getPuissance() == -8) {
				Random rand = new Random();
				int x = rand.nextInt(11);
				return (lanceur.getNiveau() * (x + 5)) / 10;
			}
			// Croc Fatal
			else if (this.getPuissance() == -9) {
				return (receveur.getStat().getPV() / 2) + (receveur.getStat().getPV() % 2);
			}
			else {
				double dommage, attaque, defense, puissance, stab, cm, nombreRandom = Math.random() * 0.15 + 0.85;
				stab = 1;
				puissance = this.getPuissance();

				if (categorie.isSpecial()) {
					attaque = lanceur.getStat().getSpecial();
					defense = receveur.getStat().getSpecial();
				} else {
					attaque = lanceur.getStat().getForce();
					defense = receveur.getStat().getForce();
				}


				if (this.getType() == lanceur.getEspece().getTypes()[0] || getType() == lanceur.getEspece().getTypes()[1]) {
					stab = 1.5;
				}

				cm = nombreRandom * stab * Main.pokedexGeneral.getEfficacite(this.getType(), receveur.getEspece().getTypes()[0]);

				dommage = (((((lanceur.getNiveau() * 0.4 + 2) * attaque * puissance)
						/ (defense * 50)) + 2) * cm);
				;
				return (int) dommage;
			}
		}
		return 0;
	}

	
	/**
	 * Si les PP sont supérieur à 0, alors le nombre de PP est diminuer de 1, sinon l'utilisateur est prévenue que l'attaque n'a plus de PP
	 */
	@Override
	public void utilise() {
		if (PP > 0) {
			PP--;
		}

	}

	/**
	 * @return le nom de la capacité
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/** 
	 * @return la précision de la capacité
	 */
	@Override
	public double getPrecision() {
		return precision;
	}

	/**
	 * @return la puissance de la capacité
	 */
	@Override
	public int getPuissance() {
		return puissance;
	}

	/**
	 * @return le nombre de PP restant de la capacité
	 */
	@Override
	public int getPP() {
		return PP;
	}
	
	/**
	 * @return la catégorie de la capacité
	 */
	@Override
	public ICategorie getCategorie() {
		return categorie;
	}

	/**
	 * @return le type de la capacité
	 */
	@Override
	public IType getType() {
		return type;
	}

	/**
	 * Cette fonction remet au maximun le nombre de PP de la capacité
	 */
	@Override
	public void resetPP() {
		this.PP = Main.pokedexGeneral.getCapacite(this.getNom()).getPP();
	}
	
	/**
	 * Affiche tous les attributs de la classe
	 */
	@Override
	public String toString() {
		return nom;
	}
}