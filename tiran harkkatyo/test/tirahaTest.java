/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
}
