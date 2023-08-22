package pokedex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import capacités.Capacite;
import capacités.Categorie;
import interfaces.IPokedex;
import interfaces.IType;
import pokemon.EType;
import pokemon.Espece;
import pokemon.Pokemon;
import testPokae.Main;

/**
 * Classe Pokedex du package pokedex, elle implémente l'interface IPokedex, cette classe contient les données des pokémons et des capacités de la 1ère génération.
 * @author Logan Brunet, Lucas Schiavetti, Sacha Hassan, Logan Laporte, Arthur Bailleul
 *
 */
public class Pokedex implements IPokedex {
	static HashMap<String,Espece> pokemonInfo = new HashMap<String,Espece>();
	static HashMap<String,Capacite> capacitesInfo = new HashMap<String,Capacite>();
	static HashMap<Integer,Capacite> capacitesInfoId = new HashMap<Integer,Capacite>();
	static HashMap<Integer,Espece> pokemonInfoId = new HashMap<Integer,Espece>();

	Double[][] tabEfficacite = new Double[16][16];

	/**
	 * Initialise les informations sur les capacites
	 */
	public void initialisationCapacites() throws FileNotFoundException {
		// Initialisation des capacités dans la HashMap
		File recupCSV = new File("Files/listeCapacites.csv");
		Scanner sc = new Scanner(recupCSV);
		sc.useDelimiter(";");

		sc.nextLine();
		while (sc.hasNext())
		{
			String n = sc.next();
			int puissanceC = sc.nextInt();
			double precisionC = Double.parseDouble(sc.next());
			int PPC = sc.nextInt();
			int id = sc.nextInt();
			String categorieC = sc.next();
			String typeC = sc.next();

			Categorie cat = new Categorie(categorieC);

			Capacite c = new Capacite(id, n, precisionC, puissanceC,
					PPC, cat, EType.valueOf(typeC));

			capacitesInfo.put(n,c);
			capacitesInfoId.put(id,c);

			sc.nextLine();
		}
		sc.close();
	}

	/**
	 * Initialise les informations sur les espèces
	 */
	public void initialisationEspeces() throws IOException {
		// Initialisation des especes dans la HashMap
		File recupCSVPok = new File("Files/listePokemon1G.csv");
		Scanner scPok = new Scanner(recupCSVPok);
		scPok.useDelimiter(";");
		scPok.nextLine();
		while (scPok.hasNext())
		{
			int id = scPok.nextInt();
			String n = scPok.next();

			int pvBase = scPok.nextInt();
			int forceBase = scPok.nextInt();
			int defenseBase = scPok.nextInt();
			int specialBase = scPok.nextInt();
			int vitesseBase = scPok.nextInt();
			int expBase = scPok.nextInt();


			int EV_PV = scPok.nextInt();
			int EV_Force = scPok.nextInt();
			int EV_Defense = scPok.nextInt();
			int EV_Special = scPok.nextInt();
			int EV_Vitesse = scPok.nextInt();

			String type1 = scPok.next();
			String type2 = scPok.next();

			int niveauBase = scPok.nextInt();
			int niveauMutation = scPok.nextInt();

			scPok.next();
			int nombreCapacites = scPok.nextInt();

			Capacite[] tab = new Capacite[nombreCapacites];
			int[] nivTab = new int[nombreCapacites];

			for (int j = 0;j<tab.length;j++) {
				try {
					scPok.next();
					tab[j] = (Capacite) getCapacite(scPok.next());
					nivTab[j] = scPok.nextInt();
				} catch (Exception e) {}
			}
			Espece e = new Espece(id, n, pvBase, forceBase, defenseBase, specialBase,
					vitesseBase, expBase, EV_PV, EV_Force, EV_Defense,
					EV_Special, EV_Vitesse, type1, type2, niveauBase,
					niveauMutation, tab, nivTab);

			pokemonInfo.put(n,e);
			pokemonInfoId.put(id, e);
			try {
				scPok.nextLine();
			} catch (Exception ex) {}
		}
		scPok.close();
	}

	/**
	 * Initialise informations sur les efficacites des attaques
	 */
	public void initialisationEfficacites() throws FileNotFoundException {
		// Initialisation des efficacités
		File recupCSVEfficacites = new File("Files/efficacites.csv");
		Scanner scEff = new Scanner(recupCSVEfficacites);
		scEff.useDelimiter(";");
		int i = 0;
		scEff.nextLine();
		while (scEff.hasNext())
		{
			scEff.next();
			tabEfficacite[0][i] = Double.parseDouble(scEff.next());
			tabEfficacite[1][i] = Double.parseDouble(scEff.next());
			tabEfficacite[2][i] = Double.parseDouble(scEff.next());
			tabEfficacite[3][i] = Double.parseDouble(scEff.next());
			tabEfficacite[4][i] = Double.parseDouble(scEff.next());
			tabEfficacite[5][i] = Double.parseDouble(scEff.next());
			tabEfficacite[6][i] = Double.parseDouble(scEff.next());
			tabEfficacite[7][i] = Double.parseDouble(scEff.next());
			tabEfficacite[8][i] = Double.parseDouble(scEff.next());
			tabEfficacite[9][i] = Double.parseDouble(scEff.next());
			tabEfficacite[10][i] = Double.parseDouble(scEff.next());
			tabEfficacite[11][i] = Double.parseDouble(scEff.next());
			tabEfficacite[12][i] = Double.parseDouble(scEff.next());
			tabEfficacite[13][i] = Double.parseDouble(scEff.next());
			tabEfficacite[14][i] = Double.parseDouble(scEff.next());
			i++;
			scEff.nextLine();

		}
		scEff.close();
	}

	/**
	 * Initialise les evolutions des especes
	 */
	public void initialisationEvolution() throws FileNotFoundException {
		// Initialisation des especes dans la HashMap
		File recupCSVPok = new File("Files/listePokemon1G.csv");
		Scanner scPok = new Scanner(recupCSVPok);
		scPok.useDelimiter(";");
		scPok.nextLine();
		while (scPok.hasNext())
		{
			scPok.nextInt();
			String nomPokemon = scPok.next();
			for (int i = 0;i<11;i++) {
				scPok.nextInt();
			}
			scPok.next();
			scPok.next();
			scPok.nextInt();
			scPok.nextInt();
			String nomEvolution = scPok.next();
			getInfo(nomPokemon).setEspeceEvolution(getInfo(nomEvolution));

			scPok.nextLine();
		}
		scPok.close();
	}

	/**
	 * On engendre le ranch d'un joueur, c'est son équipe de 6 pokémon.
	 * @return Le tableau avec le ranch de l'équipe.
	 */
	@Override
	public Pokemon[] engendreRanch() {
		Pokemon[] RanchDresseur = new Pokemon[6];
		for (int i=0; i<6; i++){
			Random r = new Random();
			int n = r.nextInt(151) + 1;
			if (pokemonInfoId.get(n).getNiveauDepart() == 1) {
				Pokemon p = new Pokemon(pokemonInfoId.get(n).getNom() +i + (10-(n%10)), pokemonInfoId.get(n));
				RanchDresseur[i] = p;
			}
			else i--;
		}
		return RanchDresseur;
	}


	/**
	 * On donne les informations d'un pokémon selon son identifiant d'espèce
	 * @param idEspece, c'est un entier distinct pour chaque espèce
	 * @return les informations du pokémon
	 */
	public Espece getPokemonInfoId(int idEspece) {
		return pokemonInfoId.get(idEspece);
	}

	/**
	 * On donne les informations d'un pokémon selon le nom de son espèce
	 * @param nomEspece est le nom de l'espèce
	 * @return les informations du pokémon
	 */
	@Override
	public Espece getInfo(String nomEspece) {
		return pokemonInfo.get(nomEspece);
	}

	/**
	 * Calcule l'efficacité d'un type d'attaque sur un type de cible
	 * @param attaque est l'attaque pris en compte pour savoir l'efficacité
	 * @param defense est la défense pris en compte pour savoir l'efficacité de l'attaque
	 * @return un tableau 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Double getEfficacite(IType attaque, IType defense) {
		return tabEfficacite[((Enum<EType>) attaque).ordinal()][((Enum<EType>) defense).ordinal()];
	}

	/**
	 * Renvoie une instance de la capacité appelée nomCapacite selon un nom
	 * @param nom de la capacité
	 * @return les informations de la capacité
	 */
	@Override
	public Capacite getCapacite(String nom) {
		return capacitesInfo.get(nom);
	}


	/**
	 * Renvoie une capacité appelée nomCapacite selon un identifiant
	 * @param n,identifiant de la capacité
	 * @return la capacité que l'on cherche
	 */
	@Override
	public Capacite getCapacite(int n) {
		return capacitesInfoId.get(n);
	}

}