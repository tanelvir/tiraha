/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.util.Scanner;

public class TiranHarkkatyo {

    /**
     * @param args the command line arguments
     */
    /*public static int parseInt(String sana) {
     int luku;
     try {
     return luku = Integer.parseInt(sana);
     } catch (Exception e) {
     System.out.println("Syötä numero!");
     }
     return -1;
     }
     private static Scanner lukija = new Scanner(System.in);

     public static void main(String[] args) {
     Painotettuverkko verkko = new Painotettuverkko();
     int solmu;
     int kaari;
     int naapuri;
     int x = 0;
     while (x < 5) {
     System.out.println("Tervetuloa! Minkä solmun haluaisit lisätä verkkoon:");
     solmu = parseInt(lukija.nextLine());
     System.out.println("Entäs minkäpainoinen kaari sille?:");
     kaari = parseInt(lukija.nextLine());
     System.out.println("Ja naapuri sille:");
     naapuri = parseInt(lukija.nextLine());
     verkko.lisaaSolmu(solmu, naapuri, kaari);
     System.out.println(verkko);
     x++;
     }

     }*/
    public static void main(String[] args) {
        Painotettuverkko verkko = new Painotettuverkko();
        verkko.lisaaSolmu(1, 2, 2);
        verkko.lisaaSolmu(2, 3, 3);
        verkko.lisaaSolmu(3, 1, 1);
        verkko.lisaaSolmu(4, 2, 5);
        verkko.palautaVerkko().get(3).lisaaKaari(3, 4);
        Kruskal kruskal;
        kruskal = new Kruskal(verkko);
        
        System.out.println(kruskal.palautaT().palautaVerkko().get(0).palautaKaaret());
        System.out.println(kruskal.palautaT().palautaVerkko().get(1).palautaKaaret());
        System.out.println(kruskal.palautaT().palautaVerkko().get(2).palautaKaaret());
        System.out.println(kruskal.palautaT().palautaVerkko().get(3).palautaKaaret());



    }
}
