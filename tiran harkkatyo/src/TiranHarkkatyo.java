/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.util.Scanner;
import java.lang.System;

/**
 * Käyttöliittymä jossa vertaillaan Primiä ja Kruskalia sekä luodaan oma verkko.
 * 
 * @author Taneli
 */

public class TiranHarkkatyo {

    /**
     * @param args the command line arguments
     */
    
    /**
     * Lisätään käyttäjän numerona syöttämä solmu verkkoon ja tarkistetaan että samaa solmua ei lisätä.
     * 
     * @param G
     * @return 
     */
    
    public static Painotettuverkko lisaaSolmu(Painotettuverkko G) {
        int solmu;
        System.out.println("Seuraavat solmut ovat jo verkossa: " + G.palautaVerkko());
        System.out.println("Et voi lisätä samaa solmua");
        System.out.println("Syötä solmun numero, jonka haluat lisätä:");
        solmu = parseInt(lukija.nextLine().trim());
        while (G.sisaltaakoSolmun(solmu)) {
            System.out.println("Seuraavat solmut ovat jo verkossa: " + G.palautaVerkko());
            System.out.println("Et voi lisätä samaa solmua");
            System.out.println("Syötä solmun numero, jonka haluat lisätä:");
            solmu = parseInt(lukija.nextLine().trim());
        }
        G.lisaaSolmu(solmu);
        System.out.println("Solmu " + solmu + " lisätty.");
        return G;
    }
    
    /**
     * Lisätään käyttäjän haluama kaari verkkoon ja katsotaan että samaa kaarta ei ole. KESKEN!
     * 
     * @param G
     * @return 
     */

    public static Painotettuverkko lisaaKaari(Painotettuverkko G) {
        int paino;
        int solmu;
        int naapuri;
        if (G.palautaVerkko().size() < 2) {
            System.out.println("Verkossa pitää olla vähintään 2 solmua, jotta niihin voidaan lisätä kaari");
            return G;
        }
        System.out.println("Seuraavat kaaret ovat jo verkossa: " + G.palautaKaikkikaaret());
        System.out.println("Et voi lisätä samaa kaarta");
        System.out.println("Syötä solmun numero, johon haluat lisätä kaaren");
        solmu = parseInt(lukija.nextLine().trim());
        while (!G.sisaltaakoSolmun(solmu)) {
            System.out.println("Syötä solmu joka on jo verkossa.");
            System.out.println(G.palautaVerkko());
            solmu = parseInt(lukija.nextLine().trim());
        }
        System.out.println("Syötä naapurin numero, johon kaari päättyy:");
        naapuri = parseInt(lukija.nextLine().trim());
        while (!G.sisaltaakoSolmun(naapuri)) {
            System.out.println("Syötä solmu joka on jo verkossa.");
            System.out.println(G.palautaVerkko());
            naapuri = parseInt(lukija.nextLine().trim());
        }
        while (naapuri == solmu) {
            System.out.println("Et voi laittaa kaarta solmuun itseenä!");
            naapuri = parseInt(lukija.nextLine().trim());
        }
        System.out.println("Syötä kaaren paino:");
        paino = parseInt(lukija.nextLine().trim());
        G.lisaaKaari(solmu, naapuri, paino);
        System.out.println("Solmusta: " + solmu + " solmuun: " + naapuri + " painolla: " + paino + " lisätty.");
        return G;
    }
    
    /**
     * Ajetaan algoritmit Prim ja Kruskal käyttäjän syöttämällä verkolla. Mitataan aika Systemistä. KESKEN!
     * 
     * @param G 
     */

    public static void ajaAlgoritmit(Painotettuverkko G) {
        if (G.palautaVerkko().size() < 2) {
            System.out.println("Täytä verkkoa enemmän solmuilla ja kaarilla!");
            return;
        }
        /*Tarkastaja tarkastaja = new Tarkastaja();
        if (tarkastaja.syotaVerkko(G.palautaVieruslista())==false){
            System.out.println("Verkko ei ole yhtenäinen! Tee se loppuun");
            lukija.nextLine();
            return;
        }*/
        Long starttiK;
        Long starttiP;
        Long tulosK;
        Long tulosP;
        Painotettuverkko K;
        Painotettuverkko P;
        Kruskal kruskal;
        Prim prim;
        System.out.println("Ajetaan Kruskalin algoritmi verkolle: " + G.palautaVerkko());
        starttiK = System.currentTimeMillis();
        kruskal = new Kruskal(G);
        K = kruskal.palautaVirittavapuu();
        tulosK = System.currentTimeMillis() - starttiK;
        System.out.println("Tulos Kruskalilla on: " + tulosK);
        System.out.println(K);
        lukija.nextLine();
        System.out.println("Ajetaan Primin algoritmi verkolle: " + G.palautaVerkko());
        System.out.println("Anna lähtösolmu:");
        int solmu = parseInt(lukija.nextLine().trim());
        while (!G.sisaltaakoSolmun(solmu)) {
            System.out.println("Syötä solmu joka on jo verkossa.");
            System.out.println(G.palautaVerkko());
            solmu = parseInt(lukija.nextLine().trim());
        }
        Solmu v = G.palautaSolmu(solmu);
        starttiP = System.currentTimeMillis();
        prim = new Prim(G, v);
        tulosP = System.currentTimeMillis() - starttiP;
        P = prim.palautaVirittavapuu();
        System.out.println("Tulos Primillä on: " + tulosP);
        System.out.println(P);
        lukija.nextLine();

        if (tulosP > tulosK) {
            System.out.println("Kruskalin algoritmi oli nopeampi");
        } else {
            System.out.println("Primin algoritmi oli nopeampi");
        }

    }
    
    /**
     * Katsotaan että käyttäjä syöttää varmasti numeron.
     * 
     * @param sana
     * @return int
     */

    public static int parseInt(String sana) {
        int luku;
        if (sana.length()==0) {
            return 0;
        }
        try {
            return luku = Integer.parseInt(sana);
        } catch (Exception e) {
            System.out.println("Syötä numero!");
        }
        return -1;
    }
    
    /**
     * Katsotaan että käyttäjä syöttää kirjaimen a, b, c, d tai q.
     * 
     * @param sana
     * @return char
     */

    public static char parseChar(String sana) {
        String kirjain = sana;
        while (true) {
            if (kirjain.length() > 1) {
                System.out.println("Syötä vain 1 kirjain a, b, c, d tai q");
                kirjain = lukija.nextLine().trim();
            } else if (kirjain.length() == 0) {
                System.out.println("Syötä kirjain a, b, c, d tai q");
                kirjain = lukija.nextLine().trim();
            } else {
                char ekaKirjain = sana.charAt(0);
                //Katsotaan onko kirjain a,b,c
                if (ekaKirjain >= 97 || ekaKirjain <= 100) {
                    return ekaKirjain;
                }
                else {
                    System.out.println("Vain a, b, c, d tai q!");
                    return 'k';
                }
            }
        }

    }
    private static Scanner lukija = new Scanner(System.in);
    
    /**
     * Käyttöliittymä pryörii mainissa.
     * 
     * @param args 
     */

    public static void main(String[] args) {
        Painotettuverkko verkko = new Painotettuverkko();
        char vastaus = 'k';
        String varmistus;
        while (vastaus != 'q') {
            System.out.println("Tervetuloa! Minkä valinnan haluaisit tehdä? a = lisää solmu verkkoon, "
                    + "b = lisää kaari verkossa olevaan solmuun, c = testaa algoritmit kyseiselle verkolle"
                    + ", d = näytä nykyinen verkko, q = lopeta");
            varmistus = lukija.nextLine();
            vastaus = parseChar(varmistus.trim());
            if (vastaus == 'a') {
                lisaaSolmu(verkko);
            }
            else if (vastaus == 'b') {
                lisaaKaari(verkko);
            }
            else if (vastaus == 'c') {
                ajaAlgoritmit(verkko);
            }
            else if (vastaus == 'd') {
                System.out.println(verkko);
            }
        }
    }
}