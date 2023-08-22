package pokemon;

import capacités.Capacite;
import capacités.Echange;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IEspece;
import interfaces.IPokemon;
import joueur.Dresseur;
import testPokae.Main;

/**
 * Classe Pokemon du package pokemon, elle implémente l'interface IPokemon, cette classe permet de définir chaque pokémon
 * @author Lucas Schiavetti, Sacha Hassan, Logan Brunet, Logan Laporte, Arthur Bailleul
 */
public class Pokemon implements IPokemon {
	public final int id;
	public String nom;
	public int niveau;
	public double points_experience;
	public Stat stats;
	public Capacite[] capacitesApprises = new Capacite[4];
	public Espece espece;
	public int nombre_echanges;

	private Stat EV;
	private final Stat DV = new Stat();
	public static int compteur = 1;

	/**
	 * Constructeur unique de la classe Pokemon
	 * @param nom, le nom du pokemon
	 * @param espece, le nom de son espèce
	 */
	public Pokemon(String nom, Espece espece) {
		id = compteur;
		compteur++;

		this.espece = espece;
		this.nom = nom;
		niveau = espece.getNiveauDepart();
		points_experience = espece.getNiveauDepart();

		stats = new Stat();

		// Initialisation des EV à 0 pour le pokemon
		EV = new Stat();
		EV.setPV(0);
		EV.setForce(0);
		EV.setDefense(0);
		EV.setVitesse(0);
		EV.setSpecial(0);


		// Initialisation des DV au hasard pour le pokemon
		DV.setForce(1 + (int) (Math.random() * 15));
		DV.setDefense(1 + (int) (Math.random() * 15));
		DV.setSpecial(1 + (int) (Math.random() * 15));
		DV.setVitesse(1 + (int) (Math.random() * 15));
		int valeurDV = ((DV.getForce()%2) * 8) + ((DV.getDefense()%2) * 4) + ((DV.getVitesse()%2) * 2) + (DV.getSpecial()%2);
		DV.setPV(valeurDV);

		misAJourStats();
	}

	// Getters
	/**
	 * @return les statistiques du pokemon
	 */
	@Override
	public Stat getStat() {
		return stats;
	}

	/**
	 * @return les points d'experience du pokemon
	 */
	@Override
	public double getExperience() {
		return points_experience;
	}

	/**
	 * @return le niveau du pokemon
	 */
	@Override
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @return l'id du pokemon
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * @return le nom du pokemon
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/**
	 * @return le pourcentage de vie du pokemon en effectuant un calcul par rapport aux point de vie du Pokémon
	 */
	@Override
	public double getPourcentagePV() {
		int PvActuels = getStat().getPV();
		int PvBaseNiveauPok = this.getPVMaxPokemonNiveau();
		return (PvActuels * 100) / PvBaseNiveauPok;
	}

	/**
	 * Permet de définir l'espece du pokemon
	 * @param espece, l'espèce en question
	 */
	public void setEspece(Espece espece) {
		this.espece = espece;
	}

	/**
	 * @return les PV maximums d'un pokemon par rapport à son niveau.
	 */
	public int getPVMaxPokemonNiveau() {
		return ((2 * (getEspece().getBaseStat().getPV() + DV.getPV())
				+ EV.getPV()/4) * this.getNiveau())/100 + this.getNiveau() + 10;
	}

	/**
	 * @return l'espèce du pokemon
	 */
	@Override
	public Espece getEspece() {
		return espece;
	}

	/**
	 * Définit l'evolution du pokemon comme étant esp
	 * @param esp, l'évolution du pokemon
	 */
	@Override
	public void vaMuterEn(IEspece esp) {
		espece = (Espece) esp;
	}

	/**
	 * @return les capacités qui ont été apprisent par le pokemon
	 */
	@Override
	public Capacite[] getCapacitesApprises() {
		return capacitesApprises;
	}

	/**
	 * @param caps, le tableau de capacités
	 * On apprend des capacités au Pokemon
	 */
	@Override
	public void apprendCapacites(ICapacite[] caps) {
		for (int i = 0; i < 4; i++) {
			capacitesApprises[i] = (Capacite) caps[i];
		}
	}

	/**
	 * Remplace une capacité par une autre si le pokémon est en capacité et veut apprendre une attaque
	 */
	@Override
	public void remplaceCapacite(int i, ICapacite cap) {
		capacitesApprises[i] = (Capacite) cap;
	}

	/**
	 * On calcul le nombre d'experience que le pokémon gagne pour chaque combat et niveau
	 */
	@Override
	public void gagneExperienceDe(IPokemon pok) {
		// Augmentation des points d'experience en fonction des stats de l'adversaire vaincu
		int GainsExperience = (int) (1.5 * pok.getNiveau() * pok.getEspece().getBaseExp())/7;
		this.points_experience = points_experience + GainsExperience;

		while (aChangeNiveau() == true) {
			misAJourStats();
		}

		// Augmentation des EV en fonction des stats de l'adversaire vaincu
		this.EV.setForce(this.EV.getForce() + pok.getEspece().getGainsStat().getForce());
		this.EV.setDefense(this.EV.getDefense() + pok.getEspece().getGainsStat().getDefense());
		this.EV.setVitesse(this.EV.getVitesse() + pok.getEspece().getGainsStat().getVitesse());
		this.EV.setSpecial(this.EV.getSpecial() + pok.getEspece().getGainsStat().getSpecial());
		this.EV.setPV(this.EV.getPV() + pok.getEspece().getGainsStat().getPV());
	}

	/**
	 * Permet de changer les statistiques du pokemon
	 */
	public void misAJourStats() {
		int PV = this.getPVMaxPokemonNiveau();
		this.stats.setPV(PV);

		int Force = (((2 * (this.getEspece().getBaseStat().getForce() + DV.getForce()) + EV.getForce()/4)) * this.getNiveau()) / 100 + 5;
		this.stats.setForce(Force);

		int Defense = (((2 * (this.getEspece().getBaseStat().getDefense() + DV.getDefense()) + EV.getDefense()/4)) * this.getNiveau()) / 100 + 5;
		this.stats.setDefense(this.stats.getDefense() + Defense);

		int Vitesse = (((2 * (this.getEspece().getBaseStat().getVitesse() + DV.getVitesse()) + EV.getVitesse()/4)) * this.getNiveau()) / 100 + 5;
		this.stats.setVitesse(Vitesse);

		int Special = (((2 * (this.getEspece().getBaseStat().getSpecial() + DV.getSpecial()) + EV.getSpecial()/4)) * this.getNiveau()) / 100 + 5;
		this.stats.setSpecial(Special);
	}

	/**
	 * @param pok, le pokemon qui attaque
	 * @param atk, la capacité qu'il utilise
	 * On met à jour les degats du pokemon courant après qu'il ait subit une attaque
	 */
	@Override
	public void subitAttaqueDe(IPokemon pok, IAttaque atk) {
		int dommageAttaque = atk.calculeDommage(pok, this);

		if (this.getStat().getPV() - dommageAttaque < 0) {
			this.getStat().setPV(0);
		} else {
			this.stats.setPV(this.getStat().getPV() - dommageAttaque);
		}

		if (atk instanceof Echange) {
			((Pokemon) pok).nombre_echanges ++;
		}

		atk.utilise();
	}

	/**
	 * Calcule si le pokémon est évanoui
	 * @return true si le pokemon est évanoui ou false sinon
	 */
	@Override
	public boolean estEvanoui() {
		return (stats.getPV() <= 0);
	}


	/**
	 * @return true si le pokemon a change de niveau ou false si il n'a pas changé
	 */
	@Override
	public boolean aChangeNiveau() {
		double expPourLevelUp = Math.pow(this.getNiveau()+1, 3);
		if (points_experience >= expPourLevelUp) {
			points_experience -= expPourLevelUp;
			this.niveau++;
			System.out.println(this.getNom() + " est passé niveau " + this.getNiveau());
			if (peutMuter() == true) {
				vaMuterEn(espece.getEspeceEvolution());
			}
			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * Regarde si le pokemon peut évoluer ou pas
	 * @return true si le pokemon peut évoluer ou false sinon
	 */
	@Override
	public boolean peutMuter() {
		if (getEspece().getNiveauEvolution() != 0) {
			return (this.niveau >= ((Espece) this.getEspece()).getNiveauEvolution());
		}
		else return false;
	}


	/**
	 * Si les PV du pokemon ne sont pas à 100% alors on soigne le pokémon
	 */
	@Override
	public void soigne() {
		int PvBaseNiveauPok = ((2 * (Main.pokedexGeneral.getInfo(this.getEspece().getNom()).getBaseStat().getPV() + DV.getPV())
				+ EV.getPV()/4) * this.getNiveau())/100 + this.getNiveau() + 10;
		this.stats.setPV(PvBaseNiveauPok);
	}


	/**
	 * Affiche tous les elements de la classe
	 */
	@Override
	public String toString() {
		if (getCapacitesApprises()[0] == null) {
			return "Le Pokemon " + id + " est " + nom + ", il est niveau " + getNiveau()
					+ " et possède actuellement " + stats.getPV() + " PV."
					+ "\r\nIl ne possède pas de capacités actuellement";
		}

		else if (getCapacitesApprises()[1] == null) {
			return "Le Pokemon " + id + " est " + nom + ", il est niveau " + getNiveau()
					+ " et possède actuellement " + stats.getPV() + " PV."
					+ "\r\nLa seule capacité qu'il peut utiliser est :\r\n| "
					+ getCapacitesApprises()[0].getNom()  + " |";
		}

		else if (getCapacitesApprises()[2] == null) {
			return "Le Pokemon " + id + " est " + nom + ", il est niveau " + getNiveau()
					+ " et possède actuellement " + stats.getPV() + " PV."
					+ "\r\nLes 2 capacités qu'il peut utiliser sont :\r\n| "
					+ getCapacitesApprises()[0].getNom()
					+ " | " + getCapacitesApprises()[1].getNom() + " |";
		}

		else if (getCapacitesApprises()[3] == null) {
			return "Le Pokemon " + id + " est " + nom + ", il est niveau " + getNiveau()
					+ " et possède actuellement " + stats.getPV() + " PV."
					+ "\r\nLes 3 capacités qu'il peut utiliser sont :\r\n| "
					+ getCapacitesApprises()[0].getNom()
					+ " | " + getCapacitesApprises()[1].getNom()
					+ " | " + getCapacitesApprises()[2].getNom() + " |";
		}

		return "Le Pokemon " + id + " est " + nom + ", il est niveau " + getNiveau()
				+ " et possède actuellement " + stats.getPV() + " PV."
				+ "\r\nLes 4 capacités qu'il peut utiliser sont :\r\n| "
				+ getCapacitesApprises()[0].getNom()
				+ " | " + getCapacitesApprises()[1].getNom()
				+ " | " + getCapacitesApprises()[2].getNom()
				+ " | " + getCapacitesApprises()[3].getNom() + " |";

	}

	/**
	 * Si le pokemon n'a plus de PV il est hors combat.
	 * @return true si le pokemon est evanoui
	 */
	public boolean horsCombat() {
		int ppPokemon = 0;


		for (int i = 0;i<4;i++) {
			try {
				ppPokemon += getCapacitesApprises()[i].getPP();
			} catch (NullPointerException e ) {}
		}

		return (estEvanoui() == true || ppPokemon == 0);
	}



}