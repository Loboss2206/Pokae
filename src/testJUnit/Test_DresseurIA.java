package testJUnit;
	
	import static org.junit.jupiter.api.Assertions.assertEquals;
	import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
	
	import java.io.IOException;
	
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

import interfaces.IAttaque;
import joueur.DresseurIARandom;
	import pokedex.Pokedex;
import pokemon.Pokemon;
	
	public class Test_DresseurIA {
	    DresseurIARandom dresseur;
	    DresseurIARandom dresseur2;
	
	    @BeforeEach
	    protected void setUp() {
	        testPokae.Main.pokedexGeneral = new Pokedex();
	            try {
	                testPokae.Main.pokedexGeneral.initialisationEspeces();
	                testPokae.Main.pokedexGeneral.initialisationEvolution();
	                testPokae.Main.pokedexGeneral.initialisationCapacites();
	                testPokae.Main.pokedexGeneral.initialisationEfficacites();
	            } catch (IOException e) {}
	        
	            dresseur = new DresseurIARandom();
	            dresseur2 = new DresseurIARandom();
	            
	    }
	
	
	    @Test
	    final void testChoisitAttaque() {
	    	dresseur.setCombattant(dresseur.choisitCombattant());
	    	dresseur2.setCombattant(dresseur2.choisitCombattant());
	    	
	    	IAttaque attaqueChoisit = dresseur.choisitAttaque(dresseur.getCombattant(),dresseur2.getCombattant());
	        assertFalse(attaqueChoisit==null);
	    }
	
	    
	    //Test si le pke;on est pas hors combat, test si il fait bien parti du ranch, 

	    @Test
	    final void testChoisitCombattant() {
	    	dresseur.initCapacitesRanch();
	    	if (dresseur.ranchEvanoui() == false) {
		    	Pokemon combattantChoisi = dresseur.choisitCombattant();
		    	dresseur.setCombattant(combattantChoisi);
		    	
	            int k = 0;
	            int j = 6;
	            boolean pokemonDansRanch = false;
	            while (pokemonDansRanch == false && k < 6) {
	                assertNotNull(dresseur.getRanch()[k]);
	                if (combattantChoisi == dresseur.getRanch()[k]) {
	                    pokemonDansRanch = true;
	                    j = k;
	                }
	                k++;
	            }
	            assertTrue(pokemonDansRanch);
	            assertEquals(dresseur.getRanch()[j],combattantChoisi);
	            assertEquals(dresseur.getCombattant(),combattantChoisi);
	    	}
	    	else {
	    		assertTrue(dresseur.getRanch()[0].horsCombat());
	    		assertTrue(dresseur.getRanch()[2].horsCombat());
	    		assertTrue(dresseur.getRanch()[4].horsCombat());
	    	}
	    }


	    @Test
	    final void testChoisitCombattantContre() {
	    	dresseur.setCombattant(dresseur.choisitCombattant());
	    	dresseur2.setCombattant(dresseur2.choisitCombattant());
	    	dresseur.initCapacitesRanch();
	    	dresseur2.initCapacitesRanch();
	    	Pokemon combattantChoisi = dresseur.choisitCombattantContre(dresseur2.getCombattant());
	    	dresseur.setCombattant(combattantChoisi);
	    	
            int k = 0;
            int j = 6;
            boolean pokemonDansRanch = false;
            while (pokemonDansRanch == false && k < 6) {
                assertNotNull(dresseur.getRanch()[k]);
                if (combattantChoisi == dresseur.getRanch()[k]) {
                    pokemonDansRanch = true;
                    j = k;
                }
                k++;
            }
            assertTrue(pokemonDansRanch);
            assertEquals(dresseur.getRanch()[j],combattantChoisi);
            assertEquals(dresseur.getCombattant(),combattantChoisi);
            assertFalse(combattantChoisi.horsCombat());
	    }
	}