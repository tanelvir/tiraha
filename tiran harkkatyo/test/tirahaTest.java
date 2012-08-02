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
        System.out.println(G.palautaVerkko().first().palautaKaaret().get(0).paino);
        assertTrue(G.palautaVerkko().size() == 2);
        assertTrue(G.palautaVerkko().first().palautaKaaret().get(0).paino == 3);
    }

    @Test
    public void kaarenLisays() {
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(3, 4, 1);
        G.palautaVerkko().first().lisaaKaari(4, 3);
        System.out.println(G.palautaVerkko().first().palautaKaaret().size());
        assertTrue(G.palautaVerkko().first().palautaKaaret().size() == 2);
    }

    @Test
    public void solmunVertaus() {
        G.lisaaSolmu(1, 2, 3);
        if (G.palautaVerkko().first().compareTo(G.palautaVerkko().last()) == -1) {
            System.out.println("Solmu " + G.palautaVerkko().first() + " on pienempi kuin " + G.palautaVerkko().last());
        }
        assertTrue(G.palautaVerkko().first().compareTo(G.palautaVerkko().last()) == -1);
    }

    @Test
    public void kaarenVertaus() {
        G.lisaaSolmu(1, 2, 4);
        G.lisaaSolmu(3, 4, 1);
        G.palautaVerkko().first().lisaaKaari(4, 3);
        if (G.palautaVerkko().first().palautaKaaret().get(0).compareTo(G.palautaVerkko().first().palautaKaaret().get(1)) == 1) {
            System.out.println("" + G.palautaVerkko().first().palautaKaaret().get(0) + " on suurempi kuin " + G.palautaVerkko().first().palautaKaaret().get(1));
        }
        assertTrue(G.palautaVerkko().first().palautaKaaret().get(0).compareTo(G.palautaVerkko().first().palautaKaaret().get(1)) == 1);
    }
}
