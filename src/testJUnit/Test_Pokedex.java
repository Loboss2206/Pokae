package testJUnit;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import capacités.Capacite;
import pokedex.Pokedex;
import pokemon.EType;
import testPokae.Main;


class Test_Pokedex {


    Pokedex p;

    @BeforeEach
    void setUp() throws Exception {
        p = new Pokedex();
        p.initialisationEspeces();
        p.initialisationCapacites();
        p.initialisationEvolution();
        p.initialisationEfficacites();
    }


    @Test
    final void testGetPokemonInfoId() {
        assertEquals(p.getPokemonInfoId(1).getNom(), "Bulbizarre");
        assertNotNull(p.getPokemonInfoId(1));
        assertNull(p.getPokemonInfoId(152));
        assertEquals(p.getPokemonInfoId(62).getNom(), "Tartard");
        assertEquals(p.getPokemonInfoId(151).getNom(), "Mew");
        
        
        File recupCSVPok = new File("Files/listePokemon1G.csv");
		Scanner scPok = null;
		int i = 1;
		try {
			scPok = new Scanner(recupCSVPok);
		} catch (FileNotFoundException e1) {}
		scPok.useDelimiter(";");
		scPok.nextLine();
		while (scPok.hasNext())
		{
			
			scPok.nextInt(); 
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
					tab[j] = (Capacite) Main.pokedexGeneral.getCapacite(scPok.next());
					nivTab[j] = scPok.nextInt();
				} catch (Exception e) {}
			}

				assertEquals(p.getPokemonInfoId(i).getNom(),n);
				assertEquals(p.getPokemonInfoId(i).getBaseStat().getPV(),pvBase);
				assertEquals(p.getPokemonInfoId(i).getBaseStat().getForce(),forceBase);
				assertEquals(p.getPokemonInfoId(i).getBaseStat().getDefense(),defenseBase);
				assertEquals(p.getPokemonInfoId(i).getBaseStat().getSpecial(),specialBase);
				assertEquals(p.getPokemonInfoId(i).getBaseStat().getVitesse(),vitesseBase);
				assertEquals(p.getPokemonInfoId(i).getBaseExp(),expBase);
				assertEquals(p.getPokemonInfoId(i).getGainsStat().getPV(),EV_PV);
				assertEquals(p.getPokemonInfoId(i).getGainsStat().getForce(),EV_Force);
				assertEquals(p.getPokemonInfoId(i).getGainsStat().getDefense(),EV_Defense);
				assertEquals(p.getPokemonInfoId(i).getGainsStat().getSpecial(),EV_Special);
				assertEquals(p.getPokemonInfoId(i).getGainsStat().getVitesse(),EV_Vitesse);
				assertEquals(p.getPokemonInfoId(i).getTypes()[0].getNom(),type1);
				try {
				assertEquals(p.getPokemonInfoId(i).getTypes()[1].getNom(),type2);
				} catch (NullPointerException e) {}
				assertEquals(p.getPokemonInfoId(i).getNiveauDepart(),niveauBase);
				assertEquals(p.getPokemonInfoId(i).getNiveauEvolution(),niveauMutation);
				assertEquals(p.getPokemonInfoId(i).getCapSet().length,nombreCapacites);
				
				try {
					scPok.nextLine();
				} catch (Exception ex) {}
				i++;
		}	
				
			

		
    }

    @Test
    final void testGetInfo() {
        assertEquals(p.getInfo("Herbizarre").getTypes()[0].getNom(), "Plante");
        assertEquals(p.getInfo("Pikachu").getEspeceEvolution().getNom(),"Raichu");
        assertEquals(p.getInfo("Bulbizarre").getEspeceEvolution().getNom(),"Herbizarre");
        assertNotNull(p.getInfo("Bulbizarre"));
        assertNotNull(p.getInfo("Mew"));
        assertNull(p.getInfo("Pikasiette"));
        
        
        
        File recupCSVPok = new File("Files/listePokemon1G.csv");
		Scanner scPok = null;
		try {
			scPok = new Scanner(recupCSVPok);
		} catch (FileNotFoundException e1) {}
		scPok.useDelimiter(";");
		scPok.nextLine();
		while (scPok.hasNext())
		{

			scPok.nextInt();
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
					tab[j] = (Capacite) Main.pokedexGeneral.getCapacite(scPok.next());
					nivTab[j] = scPok.nextInt();
				} catch (Exception e) {}
			}
			
			assertEquals(p.getInfo(n).getNom(),n);
			assertEquals(p.getInfo(n).getBaseStat().getPV(),pvBase);
			assertEquals(p.getInfo(n).getBaseStat().getForce(),forceBase);
			assertEquals(p.getInfo(n).getBaseStat().getDefense(),defenseBase);
			assertEquals(p.getInfo(n).getBaseStat().getSpecial(),specialBase);
			assertEquals(p.getInfo(n).getBaseStat().getVitesse(),vitesseBase);
			assertEquals(p.getInfo(n).getBaseExp(),expBase);
			assertEquals(p.getInfo(n).getGainsStat().getPV(),EV_PV);
			assertEquals(p.getInfo(n).getGainsStat().getForce(),EV_Force);
			assertEquals(p.getInfo(n).getGainsStat().getDefense(),EV_Defense);
			assertEquals(p.getInfo(n).getGainsStat().getSpecial(),EV_Special);
			assertEquals(p.getInfo(n).getGainsStat().getVitesse(),EV_Vitesse);
			assertEquals(p.getInfo(n).getTypes()[0].getNom(),type1);
			try {
			assertEquals(p.getInfo(n).getTypes()[1].getNom(),type2);
			} catch (NullPointerException e) {}
			assertEquals(p.getInfo(n).getNiveauDepart(),niveauBase);
			assertEquals(p.getInfo(n).getNiveauEvolution(),niveauMutation);
			assertEquals(p.getInfo(n).getCapSet().length,nombreCapacites);
			
			
			try {
				scPok.nextLine();
			} catch (Exception ex) {}

		}
		


    }

	@Test
    final void testGetEfficacite() {
        assertEquals(p.getEfficacite(EType.Feu, EType.Plante),2);
        assertEquals(p.getEfficacite(EType.Eau, EType.Feu),2);
        assertEquals(p.getEfficacite(EType.Plante, EType.Eau),2);
        assertEquals(p.getEfficacite(EType.Dragon, EType.Dragon),2);
        assertEquals(p.getEfficacite(EType.Plante, EType.Feu),0.5);
        
		
        
    }

    @Test
    final void testGetCapaciteString() {
         assertFalse(p.getCapacite("Vol").id == 2);
         assertFalse(p.getCapacite("Ultimawashi").id == 34);
         assertNotNull(p.getCapacite("Ultimapoing"));

        File recupCSV = new File("Files/listeCapacites.csv");
 		Scanner sc = null;
 		try {
 			sc = new Scanner(recupCSV);
 		} catch (FileNotFoundException e) {}
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

 			assertEquals(p.getCapacite(n).getNom(),n);
 			assertEquals(p.getCapacite(n).getPuissance(),puissanceC);
 			assertEquals(p.getCapacite(n).getPrecision(),precisionC);
 			assertEquals(p.getCapacite(n).getPP(),PPC);
 			assertEquals(p.getCapacite(n).id, id);
 			assertEquals(p.getCapacite(n).getCategorie().getNom(),categorieC);
 			assertEquals(p.getCapacite(n).getType().getNom(), typeC);
 			
 			sc.nextLine();
 		}
 		sc.close();
    }

    @Test
    final void testGetCapaciteInt() {
        assertEquals(p.getCapacite(5).getNom(), "Ultimapoing");
        assertFalse(p.getCapacite(4).getNom() == "Vol");
        
        int i = 1;
        File recupCSV = new File("Files/listeCapacites.csv");
 		Scanner sc = null;
 		try {
 			sc = new Scanner(recupCSV);
 		} catch (FileNotFoundException e) {}
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

 			assertEquals(p.getCapacite(i).getNom(),n);
 			assertEquals(p.getCapacite(i).getPuissance(),puissanceC);
 			assertEquals(p.getCapacite(i).getPrecision(),precisionC);
 			assertEquals(p.getCapacite(i).getPP(),PPC);
 			assertEquals(p.getCapacite(i).id, id);
 			assertEquals(p.getCapacite(i).getCategorie().getNom(),categorieC);
 			assertEquals(p.getCapacite(i).getType().getNom(), typeC);
 			
 			sc.nextLine();
 			i++;
 		}
 		sc.close();
    }

}

