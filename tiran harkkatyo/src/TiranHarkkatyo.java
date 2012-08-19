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

public class TiranHarkkatyo {

    /**
     * @param args the command line arguments
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
        return G;
    }

    public static Painotettuverkko lisaaKaari(Painotettuverkko G) {
        int paino;
        int solmu;
        int naapuri;
        int index;
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
        while (!G.sisaltaakoSolmun(solmu)) {
            System.out.println("Syötä solmu joka on jo verkossa.");
            System.out.println(G.palautaVerkko());
            naapuri = parseInt(lukija.nextLine().trim());
        }
        System.out.println("Syötä kaaren paino:");
        paino = parseInt(lukija.nextLine().trim());
        while (G.sisaltaakoKaaren(solmu, naapuri, paino)) {
            System.out.println("Tämä kaari on jo olemassa, syötä uusi naapuri");
            System.out.println(G.palautaVerkko());
            naapuri = parseInt(lukija.nextLine().trim());
        }
        G.palautaSolmu(solmu).lisaaKaari(naapuri, paino);
        return G;
    }

    public static void ajaAlgoritmit(Painotettuverkko G) {
        Long starttiK;
        Long starttiP;
        Long tulosK;
        Long tulosP;
        System.out.println("Ajetaan Kruskalin algoritmi verkolle: " + G.palautaVerkko());
        starttiK = System.currentTimeMillis();
        //ajetaan Kruskal
        tulosK = System.currentTimeMillis() - starttiK;
        System.out.println("Tulos Kruskalilla on: " + tulosK);
        lukija.nextLine();
        System.out.println("Ajetaan Primin algoritmi verkolle: " + G.palautaVerkko());
        starttiP = System.currentTimeMillis();
        //ajetaan Prim
        tulosP = System.currentTimeMillis() - starttiP;
        System.out.println("Tulos Primillä on: " + tulosP);
        lukija.nextLine();

        if (tulosP > tulosK) {
            System.out.println("Kruskalin algoritmi oli nopeampi");
        } else {
            System.out.println("Primin algoritmi oli nopeampi");
        }

    }

    public static int parseInt(String sana) {
        int luku;
        try {
            return luku = Integer.parseInt(sana);
        } catch (Exception e) {
            System.out.println("Syötä numero!");
        }
        return -1;
    }

    public static char parseChar(String sana) {
        while (true) {
            if (sana.length()>1) {
                System.out.println("Syötä vain 1 kirjain a, b, c tai q");
            }
            else if (sana.length()==0) {
                System.out.println("Syötä kirjain a, b, c tai q");
            }
            else {
                //if (sana.charAt(0)=='ab')
            }
        }

    }
    private static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {
        Painotettuverkko verkko = new Painotettuverkko();
        char vastaus = 'k';
        String varmistus;
        while (vastaus != 'q') {
            System.out.println("Tervetuloa! Minkä valinnan haluaisit tehdä? a = lisää solmu verkkoon, "
                    + "b = lisää kaari verkossa olevaan solmuun, c = testaa algoritmit kyseiselle verkolle, q = lopeta");
            varmistus = lukija.nextLine();
            vastaus = parseChar(varmistus.trim());
            if (vastaus == 'a') {
                lisaaSolmu(verkko);
            }
            if (vastaus == 'b') {
                lisaaKaari(verkko);
            }
            if (vastaus == 'c') {
            }
        }

    }
}