package pokemon;

import java.io.IOException;
import java.util.Arrays;

import capacités.Capacite;
import interfaces.IEspece;

/**
 * Classe Espece du package pokemon, elle implémente l'interface IEspace, cette classe permet de garder les informations sur une espèce.
 * @author Logan Brunet, Lucas Schiavetti, Sacha Hassan, Logan Laporte, Arthur Bailleul
 */
public class Espece implements IEspece {
	// Attributs visibles
	public String nom;

	// Types
	private EType[] types = new EType[2];

	// Stats
	private Stat baseStat;
	private int niveauDepart;

	// Evolution
	private Espece especeEvolution;
	private int niveauEvolution; // null si pas d'evolution

	// Capacites apprenables
	private Capacite[] capSet;
	public int[] nivCapSet;


	// Attributs non-visibles
	private int baseExp;
	private Stat gainsStat;


	/**
	 * Constructeur unique de la classe Espèce
	 * @param id, l'identifiant unique de l'espèce
	 * @param nom, le nom de l'espèce
	 * @param pvBase, les Points de Vie de base de l'espèce
	 * @param forceBase, la force de base de l'espèce
	 * @param defenseBase, la defense de base de l'espèce
	 * @param specialBase, l'attaque spéciale de base de l'espèce
	 * @param vitesseBase, la vitesse de base de l'espèce
	 * @param expBase, l'expérience de base de l'espèce
	 * @param EV_PV, le gain de PV pour le pokemon qui vaincra un pokemon de cette espèce
	 * @param EV_Force, le gain de force pour le pokemon qui vaincra un pokemon de cette espèce
	 * @param EV_Defense, le gain de defense pour le pokemon qui vaincra un pokemon de cette espèce
	 * @param EV_Special, le gain d'attaque spéciale pour le pokemon qui vaincra un pokemon de cette espèce
	 * @param EV_Vitesse, le gain de vitesse pour le pokemon qui vaincra un pokemon de cette espèce
	 * @param t1, le premier type de l'espèce
	 * @param t2, le deuxième type de l'espèce si elle en a un
	 * @param niveauBase, le niveau de base de l'espèce
	 * @param nivMutation, le niveau auquel l'espèce mute
	 * @param tabCapacites, le tableau des capacités que l'espèce peut apprendre
	 * @param niveauTabCapacites, le tableau des niveaux auxquels ces capacités peuvent être apprises
	 */
	public Espece(int id, String nom, int pvBase, int forceBase, int defenseBase, int specialBase,
				  int vitesseBase, int expBase, int EV_PV, int EV_Force, int EV_Defense, int EV_Special,
				  int EV_Vitesse, String t1,String t2, int niveauBase, int nivMutation,
				  Capacite[] tabCapacites, int[] niveauTabCapacites) {

		this.nom = nom;

		// Stats de base
		baseStat = new Stat();
		baseStat.setPV(pvBase);
		baseStat.setForce(forceBase);
		baseStat.setDefense(defenseBase);
		baseStat.setSpecial(specialBase);
		baseStat.setVitesse(vitesseBase);

		// Experience de base
		baseExp = expBase;

		// Gains de stats
		gainsStat = new Stat();
		gainsStat.setPV(EV_PV);
		gainsStat.setForce(EV_Force);
		gainsStat.setDefense(EV_Defense);
		gainsStat.setSpecial(EV_Special);
		gainsStat.setVitesse(EV_Vitesse);

		// Types
		types[0] = EType.valueOf(t1);

		try {
			types[1] = EType.valueOf(t2);
		} catch (IllegalArgumentException e) {}

		// Niveaux
		niveauDepart = niveauBase;
		niveauEvolution = nivMutation;

		capSet = tabCapacites;
		nivCapSet = niveauTabCapacites;
	}

	/**
	 * @return le niveau requis a une espèce pour évoluer
	 */
	public int getNiveauEvolution() {
		return niveauEvolution;
	}

	/**
	 * @return l'évolution de l'espèce
	 */
	public Espece getEspeceEvolution() {
		return especeEvolution;
	}

	/**
	 * @return les statistiques de base d'une espèce
	 */
	@Override
	public Stat getBaseStat() {
		return baseStat;
	}

	/**
	 * @return le nom d'une espèce
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/**
	 * @return le niveau de départ d'une espèce
	 */
	@Override
	public int getNiveauDepart() {
		return niveauDepart;
	}

	/**
	 * @return l'expérience de base d'une espèce
	 */
	@Override
	public int getBaseExp() {
		return baseExp;
	}

	/**
	 * @return les gains de statistiques pour le pokemon qui vaincra un pokemon de cette espèce
	 */
	@Override
	public Stat getGainsStat() {
		return gainsStat;
	}

	/**
	 * @return l'ensemble des capacités apprenables par cette espèce
	 */
	@Override
	public Capacite[] getCapSet() {
		return capSet;
	}

	/**
	 * Permet de savoir si l'espèce a une evolution par rapport à un certain niveau
	 * @param niveau, le niveau auquel on veut savoir si il y'a une évolution en-dessous de ce dernier
	 * @return especeEvolution si il y'a une evolution en dessous du niveau entré en paramètre
	 * @return null si aucune evolution possible
	 */
	@Override
	public Espece getEvolution(int niveau) {
		if (niveauEvolution <= niveau) return especeEvolution;
		else return null;
	}

	/**
	 * @return types, le tableau contenant les types de l'espèce.
	 */
	@Override
	public EType[] getTypes() {
		return types;
	}

	/**
	 * Renvoie un tableau de capacités que le pokemon peut apprendre en dessous d'un certain niveau
	 * @param niveau, niveau de l'espèce pour lequel on veut connaître toutes les capacités apprenables en dessous de ce niveau
	 * @return toutes les capacités disponibles en dessous d'un niveau pour une espèce
	 */
	public Capacite[] getCapacitesSousNiveau(int niveau) {
		int j = 0;
		for (int i = 0;i<getCapSet().length;i++) {
			if (nivCapSet[i] <= niveau && nivCapSet[i] != 0) {
				j++;
			}
		}

		Capacite[] capacitesPokemonNiveau = new Capacite[j];
		int k = 0;
		try {
			for (int i = 0;i<getCapSet().length;i++) {
				if (nivCapSet[i] <= niveau && nivCapSet[i] != 0) {
					capacitesPokemonNiveau[k] = getCapSet()[i];
					k++;
				}
			}

		} catch (Exception e) {}
		return capacitesPokemonNiveau;
	}

	/**
	 * Définit l'espèce en laquelle notre espèce va evoluer
	 * @param especeEvolution, l'espèce en laquelle notre espèce va évoluer
	 */
	public void setEspeceEvolution(Espece especeEvolution) {
		this.especeEvolution = especeEvolution;
	}

	/**
	 * @return tous les attributs de la classe
	 */
	@Override
	public String toString() {
		return "Espece [nom=" + nom + ", types=" + Arrays.toString(types) + ", baseStat=" + baseStat + ", niveauDepart="
				+ niveauDepart + ", especeEvolution=" + especeEvolution + ", niveauEvolution=" + niveauEvolution
				+ ", baseExp="
				+ baseExp + ", gainsStat=" + gainsStat + "]";
	}

}
