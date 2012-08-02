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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void oikeinVerkko() {
        G = new Painotettuverkko();
        G.lisaaSolmu(1, 2, 3);
        System.out.println(G.palautaVerkko().size());
        System.out.println(G.palautaVerkko().get(0).palautaKaaret().get(0).paino);
        assertTrue(G.palautaVerkko().size() == 2);
        assertTrue(G.palautaVerkko().get(0).palautaKaaret().get(0).paino == 3);
    }
}
