package joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import capacités.Capacite;
import capacités.Categorie;
import capacités.Echange;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.IStrategy;
import pokemon.EType;
import pokemon.Pokemon;

public class DresseurIARandom extends Dresseur implements IStrategy {


	public DresseurIARandom() {
		super();
		String[] dresseurNoms = {"Jean","Claude","Alexis","Alexandre",
				"Theo","Leo","Logan","Vincent","Arthur","Clement","Dimitri",
				"Sacha","Paul","Natasha","Ondine","Pierre","Lucas","Philippe",
				"Maxime","Blue","Red","Inconnu","Mystère","Marina","Marc","Soraya",
				"Antoine","Yugi","Nicolas","Anne","Henry","Zinedine","Tamas","Titouneeee",
				"Raphaël","Clement","Cop","WallE","Eve","Luffy","Zoro","Gabin",
				"Sanji","Guts", "Jotaro","Lana","Flash","SuperMan","Mathieu",
				"Simon","Abella","Christopher","Elsa","Winnie","Dwayne"};
		Random randNom = new Random();
		int indexRandom = randNom.nextInt(dresseurNoms.length);
		String nomDresseurIA = dresseurNoms[indexRandom];
		Random randNumero = new Random();
		int numero = randNumero.nextInt(100);
		this.setNom("Robot-Aléatoire-" + nomDresseurIA + numero);
	}
	
	public Pokemon choisitCombattant() {
		int numeroCombattant = 6;
		boolean choixDefinitif = false;
		while (choixDefinitif == false) {
			Random rand = new Random();
			numeroCombattant = rand.nextInt(6);
			if(this.getRanch()[numeroCombattant].horsCombat()==false) {
				choixDefinitif = true;
			}
		}
		return this.getRanch()[numeroCombattant];
	}

	public Pokemon choisitCombattantContre(IPokemon pok) {
		int numeroCombattant = 6;
		boolean choixDefinitif = false;
		while (choixDefinitif == false) {
			Random rand = new Random();
			numeroCombattant = rand.nextInt(6);
			if(this.getRanch()[numeroCombattant].horsCombat() ==false && this.getRanch()[numeroCombattant] != getCombattant()) {
				choixDefinitif = true;
			}
		}
		return this.getRanch()[numeroCombattant];
	}

	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {

		IAttaque attaque = null;
		Random random = new Random();

		int typeAttaque = random.nextInt(10);

		// Si le combattant n'a pas de capacité, on ne peut pas utiliser de capacités
		int nombreCapacites = 0;
		for (int i = 0;i<4;i++) {
			try {
				if (attaquant.getCapacitesApprises()[i] != null) nombreCapacites++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (nombreCapacites == 0) typeAttaque = 2;

		if (dernierCombattant() == true) {
			typeAttaque = 3;
		}


		// L'attaque est un échange
		if (typeAttaque < 3) {

			int pokemonSelected = 6;
			boolean echangeValable = false;

			while (echangeValable == false) {
				pokemonSelected = random.nextInt(6);
				if (getRanch()[pokemonSelected].horsCombat() == false && getRanch()[pokemonSelected] != getCombattant()) {
					echangeValable = true;
				}
			}

			Echange echangePokemon = new Echange(this);
			echangePokemon.setPokemon(getRanch()[pokemonSelected]);
			attaque = echangePokemon;
		}

		// L'attaque est une capacité
		else if (typeAttaque > 2) {
			int nbCapacite = attaquant.getCapacitesApprises().length;
			int numeroCapacitePokemon = 0;
			boolean choixDefinitif = false;


			while(choixDefinitif==false) {
				numeroCapacitePokemon = random.nextInt(nbCapacite);
				if(attaquant.getCapacitesApprises()[numeroCapacitePokemon]!=null && attaquant.getCapacitesApprises()[numeroCapacitePokemon].getPP()!=0) {
					choixDefinitif = true;
				}
			}
			attaque = attaquant.getCapacitesApprises()[numeroCapacitePokemon];
		}
		return attaque;
	}

	/**
	 *
	 */
	@Override
	public void initCapacitesRanch() {
		for (int i=0; i<6; i++){

			Capacite[] capacitesSousLeNiveau1 = getRanch()[i].getEspece().getCapacitesSousNiveau(1);
			
			int k = 0;
			while (k < capacitesSousLeNiveau1.length && k < 4) {
				Random random = new Random();
				int choixCapacites;
				try {
					choixCapacites = random.nextInt(capacitesSousLeNiveau1.length - 2);
				} catch (IllegalArgumentException e) {
					choixCapacites = 0;
				}
				
				Capacite c = capacitesSousLeNiveau1[choixCapacites];
				
				try {
					Capacite capacitePokemon = new Capacite(c.id,c.getNom(),c.getPrecision(),c.getPuissance(),c.getPP(),(Categorie) c.getCategorie(),(EType) c.getType());
					getRanch()[i].getCapacitesApprises()[k] = capacitePokemon;
					enleverElementTabCapacites(capacitesSousLeNiveau1,choixCapacites);
				} catch (NullPointerException e) {}
		
				k++;
			}
		}
	}

	public void peutEnseignerCapacité(Pokemon pok) {

		Capacite[] tabCapacitePokemon = pok.getEspece().getCapacitesSousNiveau(pok.getNiveau());

		List<Capacite> tabCapacitesPasApprises = new ArrayList<Capacite>();

		for (int i = 0;i<tabCapacitePokemon.length;i++) {
			boolean capValide = true;
			for (int k = 0;k<4;k++) {
				if (pok.getCapacitesApprises()[k] == tabCapacitePokemon[i]) {
					capValide = false;
				}
			}
			if (capValide == true) {
				tabCapacitesPasApprises.add(tabCapacitePokemon[i]);
			}
		}

		if (tabCapacitesPasApprises.size() != 0) {

			Random rand = new Random();
			int reponse = rand.nextInt(tabCapacitesPasApprises.size() + 1);

			if (reponse != 0) {
				reponse--;

				Random randCap = new Random();
				int numC = randCap.nextInt(4);

				pok.remplaceCapacite(numC, tabCapacitesPasApprises.get(reponse));
				System.out.println(pok.getNom() + " apprend " + tabCapacitesPasApprises.get(reponse) + " à la place de " + pok.getCapacitesApprises()[numC] + ".\r\n");
			}
		}
	}
	public String toString() {
		return "Dresseur IA : "+getNom();
	}

}