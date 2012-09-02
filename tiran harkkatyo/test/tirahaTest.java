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
    Painotettuverkko verkko;
    Kruskal kruskal;
    Prim prim;

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
        verkko = new Painotettuverkko();
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
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Testataan solmun lisäämistä ja pienemmän kaaren poimintaa.
     */

    @Test
    public void solmunLisays() {
        G.lisaaSolmu(1);
        G.lisaaSolmu(2);
        G.lisaaKaari(1, 2, 3);
        assertTrue(G.palautaVerkko().size() == 2);
        assertTrue(G.palautaVerkko().get(0).palautaKaaret().peek().paino == 3);
    }
    
    /**
     * Lisätään kaari ja katsotaan muuttuuko määrä.
     */

    @Test
    public void kaarenLisays() {
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(3, 4, 1);
        G.palautaVerkko().get(0).lisaaKaari(4, 3);
        assertTrue(G.palautaVerkko().get(0).palautaKaaret().size() == 2);
    }
    
    /**
     * Vertaillaan solmun numeroita keskenään.
     */

    @Test
    public void solmunVertaus() {
        G.lisaaSolmu(1, 2, 3);
        G.lisaaSolmu(2, 3, 4);
        if (G.palautaVerkko().get(1).numero >(G.palautaVerkko().get(0).numero)) {
            System.out.println("Solmu " + G.palautaVerkko().get(1) + " on suurempi kuin solmu " + G.palautaVerkko().get(0));
        }
        assertTrue(G.palautaVerkko().get(1).numero >(G.palautaVerkko().get(0).numero));
    }
    
    /**
     * Verrataan kaaren painoa keskenään.
     */

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
    
    /**
     * Testataan Kruskalin algoritmiä syötetyllä verkolla. Onko kaaria ja solmuja oikea määrä.
     */

    @Test
    public void KruskalinTesti() {
        int koko = verkko.palautaVerkko().size();
        int kaaria = koko * 2 - 2 * 1;
        kruskal = new Kruskal(verkko);

        assertTrue(kruskal.palautaVirittavapuu().palautaVerkko().size() == verkko.palautaVerkko().size());
        assertTrue(kruskal.palautaVirittavapuu().palautaKaikkikaaret().size() == kaaria); //virittävässä puussa on oltava kaaria solmujenmäärä-1

    }
    
    /**
     * Testataan Primin algoritmiä syötetyllä verkolla. Onko kaaria ja solmuja oikea määrä.
     */

    @Test
    public void PriminTesti() {

        int koko = verkko.palautaVerkko().size();
        int kaaria = koko * 2 - 2 * 1;
        prim = new Prim(verkko, verkko.palautaVerkko().get(0));

        assertTrue(prim.palautaVirittavapuu().palautaVerkko().size() == koko);
        assertTrue(prim.palautaVirittavapuu().palautaKaikkikaaret().size() == kaaria); //virittävässä puussa on oltava kaaria solmujenmäärä-1

    }
    
    /**
     * Testataan toimiiko asciikieli oikein.
     */

    @Test
    public void tiedostoTesti() {
        Tiedostotestaus testi;
        try {
            testi = new Tiedostotestaus("src/verkot/testi.txt");
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
    
    /**
     * Testataan onko tiedostota osattu lukea oikanlainen verkko.
     *
     * @throws FileNotFoundException 
     */
    
    @Test
    public void tiedostoTesti2() throws FileNotFoundException {
        Tiedostotestaus testi = new Tiedostotestaus("src/verkot/testi2.txt");
        Painotettuverkko Y = testi.returnVerkko();
        
        assertTrue(Y.yhteispaino() == 93);
        assertTrue(Y.palautaSolmu(1).numero==1);
    }
    
    /**
     * PriorityQueuen poll ei saa kadottaa kaaria.
     */

    @Test
    public void kaareteivatPoistutulostuksessa() {
        System.out.println(verkko);
        verkko.palautaKaikkikaaret();
        verkko.palautaKaikkikaaret2();
        verkko.palautaVieruslista();

        int kaariapitaisiolla = 10 * 2;

        assertTrue(verkko.palautaKaikkikaaret().size() == kaariapitaisiolla);
        assertTrue(verkko.palautaVerkko().size() == 7);


    }
    
    /**
     * Testataan painaako verkko oikean verran.
     */

    @Test
    public void yhteisPainotoimii() {
        kruskal = new Kruskal(verkko);
        prim = new Prim(verkko, verkko.palautaSolmu(3));

        assertTrue(prim.palautaVirittavapuu().yhteispaino() == 11);
        assertTrue(kruskal.palautaVirittavapuu().yhteispaino() == 11);

    }
    
    /**
     * Primi osaa poistaa solmun kaaret.
     */
    
    @Test
    public void kaarenSiirtotoimiiPrimissa() {
        prim = new Prim(verkko, verkko.palautaVerkko().get(0));
        Solmu v = new Solmu(1,2,3); 
        prim.siirraKaaret(v);
        
        assertTrue(v.palautaKaaret().isEmpty());
    }
    
    /**
     * Testataan osataanko metsät yhdistää oikein. Eli siis setin pitäisi olla solmuissa samat.
     */
    
    @Test
    public void metsänYhdistäminen() {
        kruskal = new Kruskal(verkko);
        Painotettuverkko Y = kruskal.palautaVirittavapuu();
        
        assertTrue(Y.palautaSolmu(1).setti == Y.poistaSolmu(2).setti);
    }
    
    /**
     * Virittävän puun pitää olla yhtä lyhyt kummallakin ahneella algoritmillä.
     */
    
    @Test
    public void virittavatPuutsamanpainoiset() {
        kruskal = new Kruskal(verkko);
        prim = new Prim(verkko, verkko.palautaSolmu(4));
        
        assertTrue(prim.palautaVirittavapuu().yhteispaino() == kruskal.palautaVirittavapuu().yhteispaino());
    }
    
    /**
     * Katsotaan että eri aloitussolmu ei luo samanlaista verkkoa.
     */
    
    @Test
    public void aloitussolmuLuoeriverkon() {
        Painotettuverkko kopio = new Painotettuverkko();
        kopio.setVerkko2(verkko.kopioiVerkko());
        Prim toinen = new Prim(kopio, kopio.palautaSolmu(5));
        setUp();
        prim = new Prim(verkko, verkko.palautaSolmu(2));
        
        assertTrue(prim.palautaVirittavapuu().palautaKaikkikaaret() != toinen.palautaVirittavapuu().palautaKaikkikaaret());
    }
    
    /**
     * Testataan ovatko tulokset (virittävät puut) yhtenäisiä.
     */
    
    @Test
    public void virittävätPuutovatyhtenaisia() {
        
        Tarkastaja tarkastaja;
        tarkastaja = new Tarkastaja();
        kruskal = new Kruskal(verkko);
        
        assertTrue(tarkastaja.syotaVerkko(verkko.palautaVieruslista())==true);
        
        prim = new Prim(verkko, verkko.palautaSolmu(4));    
        
        assertTrue(tarkastaja.syotaVerkko(prim.palautaVirittavapuu().palautaVieruslista())==true);
        assertTrue(tarkastaja.syotaVerkko(prim.palautaVirittavapuu().palautaVieruslista())==true);
    }

}
