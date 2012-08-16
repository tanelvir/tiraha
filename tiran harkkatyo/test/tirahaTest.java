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
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(2, 3, 5);
        System.out.println(G.palautaVerkko().size());
        System.out.println(G.palautaVerkko().get(0).palautaKaaret().peek().paino);
        assertTrue(G.palautaVerkko().size() == 2);
        assertTrue(G.palautaVerkko().get(0).palautaKaaret().peek().paino == 3);
    }

    @Test
    public void kaarenLisays() {
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(3, 4, 1);
        G.palautaVerkko().get(0).lisaaKaari(4, 3);
        System.out.println(G.palautaVerkko().get(0).palautaKaaret().size());
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
        verkko.lisaaSolmu(1, 2, 1);
        verkko.palautaVerkko().get(0).lisaaKaari(3, 3);
        verkko.lisaaSolmu(2, 5, 4);
        verkko.palautaVerkko().get(1).lisaaKaari(6, 5);
        verkko.palautaVerkko().get(1).lisaaKaari(1, 1);
        verkko.lisaaSolmu(3, 1, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(6, 1);
        verkko.palautaVerkko().get(2).lisaaKaari(7, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(4, 2);
        verkko.lisaaSolmu(4, 3, 2);
        verkko.palautaVerkko().get(3).lisaaKaari(7, 5);
        verkko.lisaaSolmu(5, 2, 4);
        verkko.palautaVerkko().get(4).lisaaKaari(6, 1);
        verkko.lisaaSolmu(6, 5, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(2, 5);
        verkko.palautaVerkko().get(5).lisaaKaari(3, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(7, 4);
        verkko.lisaaSolmu(7, 6, 4);
        verkko.palautaVerkko().get(6).lisaaKaari(3, 3);
        verkko.palautaVerkko().get(6).lisaaKaari(4, 5);
        
        Kruskal kruskal = new Kruskal(verkko);
        
        assertTrue(kruskal.T.palautaVerkko().size() == 7);
        
    }
    
    @Test
    public void PriminTesti() {
        Painotettuverkko verkko = new Painotettuverkko();
        verkko.lisaaSolmu(1, 2, 1);
        verkko.palautaVerkko().get(0).lisaaKaari(3, 3);
        verkko.lisaaSolmu(2, 5, 4);
        verkko.palautaVerkko().get(1).lisaaKaari(6, 5);
        verkko.palautaVerkko().get(1).lisaaKaari(1, 1);
        verkko.lisaaSolmu(3, 1, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(6, 1);
        verkko.palautaVerkko().get(2).lisaaKaari(7, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(4, 2);
        verkko.lisaaSolmu(4, 3, 2);
        verkko.palautaVerkko().get(3).lisaaKaari(7, 5);
        verkko.lisaaSolmu(5, 2, 4);
        verkko.palautaVerkko().get(4).lisaaKaari(6, 1);
        verkko.lisaaSolmu(6, 5, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(2, 5);
        verkko.palautaVerkko().get(5).lisaaKaari(3, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(7, 4);
        verkko.lisaaSolmu(7, 6, 4);
        verkko.palautaVerkko().get(6).lisaaKaari(3, 3);
        verkko.palautaVerkko().get(6).lisaaKaari(4, 5);
        
        Prim prim = new Prim(verkko, verkko.palautaVerkko().get(0));
        
        assertTrue(prim.kaikkikaaret.size() == 2);
        
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
        assertTrue(testi.returnVerkko().palautaVerkko().get(2).numero == 4);
        assertTrue(testi.returnVerkko().palautaVerkko().get(3).numero == 3);
    }
}
