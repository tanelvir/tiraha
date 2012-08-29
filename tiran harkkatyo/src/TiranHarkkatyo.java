/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.io.File;
import java.io.FileNotFoundException;
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
     * Lisätään käyttäjän numerona syöttämä solmu verkkoon ja tarkistetaan että
     * samaa solmua ei lisätä.
     *
     * @param G
     * @return G
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
     * Lisätään käyttäjän haluama kaari verkkoon ja katsotaan että samaa kaarta
     * ei ole.
     *
     * @param G
     * @return G
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
        while (!G.sisaltaakoSolmun(naapuri) || (naapuri == solmu)) {
            System.out.println("Syötä solmu joka on jo verkossa. " + "Et voi laittaa kaarta solmuun itseenä!");
            System.out.println(G.palautaVerkko());
            naapuri = parseInt(lukija.nextLine().trim());
        }
        System.out.println("Syötä kaaren paino:");
        paino = parseInt(lukija.nextLine().trim());
        if (G.palautaSolmu(solmu).sisaltaakoKaaren(naapuri) == false) {
            G.lisaaKaari(solmu, naapuri, paino);
            System.out.println("Solmusta: " + solmu + " solmuun: " + naapuri + " painolla: " + paino + " lisätty.");
            System.out.println(G);
            return G;
        } else {
            Kaari vaara = new Kaari(solmu, naapuri, paino);
            System.out.println(vaara + " on jo verkossa! Syötä uusi kaari!");
            return G;
        }
    }

    /**
     * Ajetaan algoritmit Prim ja Kruskal käyttäjän syöttämällä verkolla.
     * Mitataan aika Systemistä nanosekuntteina. Prosentuaalinen vertaus myös.
     *
     * @param G
     */
    public static void ajaAlgoritmit(Painotettuverkko G) {
        if (G.palautaVerkko().size() < 2) {
            System.out.println("Täytä verkkoa enemmän solmuilla ja kaarilla!");
            return;
        }
        Tarkastaja tarkastaja = new Tarkastaja();
        if (tarkastaja.syotaVerkko(G.palautaVieruslista()) == false) {
            System.out.println("Verkko ei ole yhtenäinen! Tee se loppuun");
            lukija.nextLine();
            return;
        }

        long Kruskal = ajaKruskal(G);
        long Prim = ajaPrim(G);




        if (Prim > Kruskal) {
            double prosenttiK = (1 - Kruskal / 1.0 / Prim) * 100;
            System.out.println("Kruskalin algoritmi oli nopeampi " + prosenttiK + " prosenttia");
        } else {
            double prosenttiP = (1 - Prim / 1.0 / Kruskal) * 100;
            System.out.println("Primin algoritmi oli nopeampi " + prosenttiP + " prosenttia");
        }

        lukija.nextLine();

    }

    /**
     * Ajaa Kruskalin algoritmin kyseiselle verkolle
     *
     * @param G
     * @return tulosK
     */
    public static long ajaKruskal(Painotettuverkko G) {
        Kruskal kruskal;
        Long starttiK;
        Long tulosK;
        Painotettuverkko K;
        System.out.println("Ajetaan Kruskalin algoritmi verkolle: " + G.palautaVerkko());
        lukija.nextLine();
        starttiK = System.nanoTime();
        kruskal = new Kruskal(G);
        K = kruskal.palautaVirittavapuu();
        tulosK = System.nanoTime() - starttiK;
        System.out.println("Tulos Kruskalilla on: " + tulosK + " nanosekunttia");
        System.out.println(K);
        lukija.nextLine();
        return tulosK;
    }

    /**
     * Ajaa Primin algoritmin kyseiselle verkolle.
     *
     * @param G
     * @return tulosP
     */
    public static long ajaPrim(Painotettuverkko G) {
        Prim prim;
        Long starttiP;
        Long tulosP;
        Painotettuverkko P;

        System.out.println("Ajetaan Primin algoritmi verkolle: " + G.palautaVerkko());
        System.out.println("Anna lähtösolmu:");
        int solmu = parseInt(lukija.nextLine().trim());
        while (!G.sisaltaakoSolmun(solmu)) {
            System.out.println("Syötä solmu joka on jo verkossa.");
            System.out.println(G.palautaVerkko());
            solmu = parseInt(lukija.nextLine().trim());
        }
        Solmu v = G.palautaSolmu(solmu);
        starttiP = System.nanoTime();
        prim = new Prim(G, v);
        tulosP = System.nanoTime() - starttiP;
        P = prim.palautaVirittavapuu();
        System.out.println("Tulos Primillä on: " + tulosP + " nanosekunttia");
        System.out.println(P);
        lukija.nextLine();
        return tulosP;
    }

    /**
     * Mikäli käyttäjä haluaa ottaa jonkin valmiin verkon tiedostona. Vanha
     * verkko poistuu ja tiedoston verkko tulee tilalle
     *
     * @param G
     * @return G
     * @throws FileNotFoundException
     */
    public static Painotettuverkko lueTiedostosta(Painotettuverkko G) throws FileNotFoundException {
        File source = new File("src/verkot");
        File uusiverkko = listaaTiedostot(source);
        Tiedostotestaus lukija = new Tiedostotestaus(uusiverkko);
        G.setVerkko2(lukija.returnVerkko().palautaVerkko());
        System.out.println(G);
        return G;
    }

    /**
     * Listaa kansiossa olevat tiedostot.
     *
     * @param polku
     */
    public static File listaaTiedostot(File polku) {

        File tiedostot[];

        tiedostot = polku.listFiles();

        for (int i = 0; i < tiedostot.length; i++) {
            System.out.println(i + 1 + ": " + tiedostot[i].toString());
            if (tiedostot[i].isDirectory()) {
                listaaTiedostot(tiedostot[i]);
            }
        }

        int numero = -1;

        while (numero > tiedostot.length || numero <= 0) {
            System.out.println("Syötä verkon edessä oleva numero, jonka haluat valita:");
            String vastaus = lukija.nextLine().trim();
            numero = parseInt(vastaus);
        }

        File uusiverkko = tiedostot[numero - 1];
        return uusiverkko;
    }

    /**
     * Katsotaan että käyttäjä syöttää varmasti numeron.
     *
     * @param sana
     * @return int
     */
    public static int parseInt(String sana) {
        int luku;
        if (sana.length() == 0) {
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
     * Katsotaan että käyttäjä syöttää kirjaimen a, b, c, d, e tai q.
     *
     * @param sana
     * @return char
     */
    public static char parseChar(String sana) {
        String kirjain = sana;
        while (true) {
            if (kirjain.length() > 1) {
                System.out.println("Syötä vain 1 kirjain a, b, c, d, e tai q");
                kirjain = lukija.nextLine().trim();
            } else if (kirjain.length() == 0) {
                System.out.println("Syötä kirjain a, b, c, d, e tai q");
                kirjain = lukija.nextLine().trim();
            } else {
                char ekaKirjain = sana.charAt(0);
                //Katsotaan onko kirjain a,b,c
                if (ekaKirjain >= 97 || ekaKirjain <= 101) {
                    return ekaKirjain;
                } else {
                    System.out.println("Vain a, b, c, d, e tai q!");
                    return 'k';
                }
            }
        }

    }
    private static Scanner lukija = new Scanner(System.in);

    /**
     * Käyttöliittymä pyörii mainissa. Kirjainta painamalla tehdään valinta.
     *
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        Painotettuverkko verkko = new Painotettuverkko();
        char vastaus = 'k';
        String varmistus;
        while (vastaus != 'q') {
            System.out.println("Tervetuloa! Minkä valinnan haluaisit tehdä? a = lisää solmu verkkoon, ");
            System.out.println("b = lisää kaari verkossa olevaan solmuun, c = testaa algoritmit kyseiselle verkolle, ");
            System.out.println("d = näytä nykyinen verkko, e = testaa tiedostoissa valmiiksi olevaa verkkoa, q = lopeta");
            varmistus = lukija.nextLine();
            vastaus = parseChar(varmistus.trim());
            if (vastaus == 'a') {
                lisaaSolmu(verkko);
            } else if (vastaus == 'b') {
                lisaaKaari(verkko);
            } else if (vastaus == 'c') {
                ajaAlgoritmit(verkko);
            } else if (vastaus == 'd') {
                System.out.println(verkko);
            } else if (vastaus == 'e') {
                lueTiedostosta(verkko);
            }
        }
    }
}