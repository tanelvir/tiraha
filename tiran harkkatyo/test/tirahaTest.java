/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Taneli
 */
public class tirahaTest {

    Painotettuverkko G;

    public tirahaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        G = new Painotettuverkko();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void solmunLisays() {
        G.lisaaSolmu(1);
        G.lisaaSolmu(2);
        G.lisaaKaari(1, 2, 3);
        assertTrue(G.palautaVerkko().size() == 2);
        assertTrue(G.palautaVerkko().get(0).palautaKaaret().peek().paino == 3);
    }

    @Test
    public void kaarenLisays() {
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(3, 4, 1);
        G.palautaVerkko().get(0).lisaaKaari(4, 3);
        assertTrue(G.palautaVerkko().get(0).palautaKaaret().size() == 2);
    }

    @Test
    public void solmunVertaus() {
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(2, 3, 4);
        if (G.palautaVerkko().get(1).compareTo(G.palautaVerkko().get(0)) == 1) {
            System.out.println("Solmu " + G.palautaVerkko().get(1) + " on suurempi kuin solmu " + G.palautaVerkko().get(0));
        }
        assertTrue(G.palautaVerkko().get(1).compareTo(G.palautaVerkko().get(0)) == 1);
    }

    @Test
    public void kaarenVertaus() {
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(3, 4, 1);
        G.palautaVerkko().get(0).lisaaKaari(4, 4);
        Kaari uno = G.palautaVerkko().get(0).palautaKaaret().poll();
        Kaari duo = G.palautaVerkko().get(0).palautaKaaret().peek();
        if (uno.compareTo(duo) == -1) {
            System.out.println("" + uno + " on pienempi kuin " + duo);
        }
        assertTrue(uno.compareTo(duo) == -1);
    }

    @Test
    public void KruskalinTesti() {
        Painotettuverkko verkko = new Painotettuverkko();
        verkko.lisaaSolmu(1);
        verkko.lisaaSolmu(2);
        verkko.lisaaSolmu(3);
        verkko.lisaaSolmu(4);
        verkko.lisaaSolmu(5);
        verkko.lisaaSolmu(6);
        verkko.lisaaSolmu(7);
        verkko.lisaaKaari(1, 2, 1);
        verkko.lisaaKaari(1, 3, 3);
        verkko.lisaaKaari(2, 5, 4);
        verkko.lisaaKaari(2, 6, 5);
        verkko.lisaaKaari(3, 6, 1);
        verkko.lisaaKaari(3, 7, 3);
        verkko.lisaaKaari(3, 4, 2);
        verkko.lisaaKaari(4, 7, 5);
        verkko.lisaaKaari(5, 6, 1);
        verkko.lisaaKaari(6, 7, 4);
        
        int koko = verkko.palautaVerkko().size();
        int kaaria = koko*2 - 2*1;
        
        
        
        Kruskal kruskal = new Kruskal(verkko);
        
        assertTrue(kruskal.palautaVirittavapuu().palautaVerkko().size() == verkko.palautaVerkko().size());
        assertTrue(kruskal.palautaVirittavapuu().palautaKaikkikaaret().size() == kaaria);
        
    }
    
    @Test
    public void PriminTesti() {
        Painotettuverkko verkko = new Painotettuverkko();
        verkko.lisaaSolmu(1);
        verkko.lisaaSolmu(2);
        verkko.lisaaSolmu(3);
        verkko.lisaaSolmu(4);
        verkko.lisaaSolmu(5);
        verkko.lisaaSolmu(6);
        verkko.lisaaSolmu(7);
        verkko.lisaaKaari(1, 2, 1);
        verkko.lisaaKaari(1, 3, 3);
        verkko.lisaaKaari(2, 5, 4);
        verkko.lisaaKaari(2, 6, 5);
        verkko.lisaaKaari(3, 6, 1);
        verkko.lisaaKaari(3, 7, 3);
        verkko.lisaaKaari(3, 4, 2);
        verkko.lisaaKaari(4, 7, 5);
        verkko.lisaaKaari(5, 6, 1);
        verkko.lisaaKaari(6, 7, 4);
        
        int koko = verkko.palautaVerkko().size();
        int kaaria = koko*2 - 2*1;
        
        Prim prim = new Prim(verkko, verkko.palautaVerkko().get(0));
        
        assertTrue(prim.palautaVirittavapuu().palautaVerkko().size() == koko);
        assertTrue(prim.palautaVirittavapuu().palautaKaikkikaaret().size() == kaaria); //virittävässä puussa on oltava kaaria solmujenmäärä-1
        
    }
    
    @Test
    public void tiedostoTesti() {
        Tiedostotestaus testi;
        try {
            testi = new Tiedostotestaus("src/testi.txt");
            System.out.println(testi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            testi = null;
        }
        assertTrue(testi.returnVerkko().palautaVerkko().get(0).numero == 1);
        assertTrue(testi.returnVerkko().palautaVerkko().get(1).numero == 2);
        assertTrue(testi.returnVerkko().palautaVerkko().get(2).numero == 3);
        assertTrue(testi.returnVerkko().palautaVerkko().get(3).numero == 4);
    }
}
