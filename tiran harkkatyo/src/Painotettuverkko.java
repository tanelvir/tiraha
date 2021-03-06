/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Painotettu verkko koostuu V määrästä solmuja ja solmut tuntevat kaarensa.
 * Yhdessä ne muodostavat verkon jossa kaarilla on painot ja kahden solmun
 * välillä on vain 2 kaarta, jotka ovat samanpainoiset. Eli siis Solmu on jonkin
 * kaaren lähtö- ja loppupiste.
 *
 */
public class Painotettuverkko {

    ArrayList<Solmu> solmut; //Solmut puu-rakenteen muodossa.

    /**
     * Luodaan kokonaan uusi verkko
     */
    public Painotettuverkko() {
        solmut = new ArrayList<Solmu>();
    }

    /**
     * Luodaan verkko valmiine solmuineen.
     *
     * @param verkko
     */
    public Painotettuverkko(ArrayList<Solmu> verkko) {
        this.solmut = verkko;
    }

    /**
     * Luo kaarettoman uuden verkon, jossa on samat solmut kuin parametrinä
     * annetuissa solmuissa.
     *
     * @param ArrayList verkko
     */
    public void setVerkko(ArrayList<Solmu> verkko) {
        for (int i = 0; i < verkko.size(); i++) {
            Solmu uusisolmu = verkko.get(i);
            lisaaSolmu(uusisolmu.numero);
        }
    }

    /**
     * Jättää viitteen tähän verkkoon.
     *
     * @param verkko
     */
    public void setVerkko2(ArrayList<Solmu> verkko) {
        this.solmut = verkko;
    }

    public ArrayList<Solmu> palautaVerkko() {
        return solmut;
    }

    /**
     * Kopioi solmut oliona poistamaalla viitteen alkuperäiseen verkkoon.
     *
     * @return ArrayList<Solmu>
     */
    public ArrayList<Solmu> kopioiVerkko() {
        Painotettuverkko G = new Painotettuverkko();
        for (int i = 0; i < solmut.size(); i++) {
            Solmu solmu = solmut.get(i);
            System.out.println(solmu.palautaKaaret());
            G.lisaaSolmu(solmu);
        }
        return G.palautaVerkko();
    }

    /**
     * Palauttaa koko verkon kaaret. Kaaret eivät poistu vaan säilyvät.
     *
     * @return kaaret
     */
    public PriorityQueue<Kaari> palautaKaikkikaaret() {
        PriorityQueue<Kaari> kaaret = new PriorityQueue<Kaari>();
        for (int i = 0; i < solmut.size(); i++) {
            Solmu v = solmut.get(i);
            v.kopioiKaaret();
            while (!(v.uudet.isEmpty())) {
                Kaari e = v.etsiUusipieninkaari();
                kaaret.add(e);
            }
        }
        return kaaret;
    }

    /**
     * Palauttaa koko verkon kaaret ArrayListinä
     *
     * @return kaaret
     */
    public ArrayList<Kaari> palautaKaikkikaaret2() {
        ArrayList<Kaari> kaaret = new ArrayList<Kaari>();
        for (int i = 0; i < solmut.size(); i++) {
            Solmu v = solmut.get(i);
            v.kopioiKaaret();
            while (!(v.uudet.isEmpty())) {
                Kaari e = v.etsiUusipieninkaari();
                kaaret.add(e);
            }
        }
        return kaaret;
    }

    /**
     * Laskee verkon yhteispainon. Eli siis paljon kaikki kaaret painavat
     * yhteensä.
     *
     * @return summa
     */
    public int yhteispaino() {
        int summa = 0;
        ArrayList<Kaari> kaaret;
        kaaret = palautaKaikkikaaret2();
        for (Kaari e : kaaret) {
            summa += e.paino;
        }
        return summa / 2;
    }

    /**
     * Palautetaan vieruslista, josta voidaan tarkistaa onko verkko yhtenäinen.
     *
     * @return boolean [][]
     */
    public boolean[][] palautaVieruslista() {
        boolean[][] taulu = new boolean[solmut.size()][solmut.size()];
        for (int i = 0; i < solmut.size(); i++) {
            PriorityQueue<Kaari> uusi = new PriorityQueue();
            Solmu v = solmut.get(i);
            v.kopioiKaaret();
            while (!(v.uudet.isEmpty())) {
                Kaari e = v.etsiUusipieninkaari();
                int j = e.Solmu2().numero - 1;
                taulu[i][j] = true;
                uusi.add(e);
            }
        }
        return taulu;
    }

    /**
     *
     * Lisätään solmu, jolla on oltava naapuri ja niiden välillä kaaren paino.
     * Vain itse solmu lisätään.
     *
     *
     * @param solmu
     * @param naapuri
     * @param paino
     */
    public void lisaaSolmu(int solmu, int naapuri, int paino) {
        Solmu uusi;
        Solmu viereinen;
        uusi = new Solmu(solmu);
        viereinen = new Solmu(naapuri);
        if (sisaltaakoSolmun(solmu) == false) {
            uusi.lisaaKaari(naapuri, paino);
            solmut.add(uusi);
        }
        if (sisaltaakoSolmun(naapuri) == false) {
            viereinen.lisaaKaari(solmu, paino);
            solmut.add(viereinen);
        }

    }

    /**
     * Lisätään vain solmu indeksillä. Kaaria ei siis lisätä.
     *
     * @param solmu
     */
    public void lisaaSolmu(int solmu) {
        Solmu v;
        if (sisaltaakoSolmun(solmu) == false) {
            v = new Solmu(solmu);
            solmut.add(v);
        }
    }

    /**
     * Lisätään solmu oliona. Kaaret riippuvat olion sisällöstä.
     *
     * @param solmu
     */
    public void lisaaSolmu(Solmu solmu) {
        if (sisaltaakoSolmun(solmu.numero) == false) {
            solmut.add(solmu);
        }
    }

    /**
     * Lisätään sama kaari kummallekkin solmulle, jotta kommunikaatio toimii.
     * Kaaria lisätään siis 2.
     *
     * @param solmu
     * @param naapuri
     * @param paino
     */
    public void lisaaKaari(Solmu solmu, Solmu naapuri, int paino) {
        Solmu ekasolmu = palautaSolmu(solmu.numero);
        Solmu tokasolmu = palautaSolmu(naapuri.numero);
        ekasolmu.lisaaKaari(naapuri.numero, paino);
        tokasolmu.lisaaKaari(solmu.numero, paino);
    }

    /**
     * Sama metodi, mutta viittaukset Integerin muodossa.
     *
     * @param solmu
     * @param naapuri
     * @param paino
     */
    public void lisaaKaari(int solmu, int naapuri, int paino) {
        Solmu ekasolmu = palautaSolmu(solmu);
        Solmu tokasolmu = palautaSolmu(naapuri);
        ekasolmu.lisaaKaari(naapuri, paino);
        tokasolmu.lisaaKaari(solmu, paino);
    }

    /**
     * Sisältääkö verkko solmun.
     *
     * @param numero
     * @return true / false
     */
    public boolean sisaltaakoSolmun(int numero) {
        for (Solmu v : solmut) {
            if (v.numero == numero) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauta kyseinen solmu oliona.
     *
     * @param numero
     * @return Solmu
     */
    public Solmu palautaSolmu(int numero) {
        for (int i = 0; i < solmut.size(); i++) {
            if (solmut.get(i).numero == numero) {
                return solmut.get(i);
            }
        }
        return null;
    }

    /**
     * Poistaa kyseisen solmun verkosta.
     *
     * @param numero
     */
    public Solmu poistaSolmu(int numero) {
        Solmu tama = palautaSolmu(numero);
        solmut.remove(tama);
        return tama;
    }

    /**
     * Tyhjätään verkko kaaristaan. (Äärettömyys)
     *
     *
     *
     * @return this
     */
    public Painotettuverkko nollaaKaaret() {
        for (int i = 0; i < solmut.size(); i++) {
            this.palautaVerkko().get(i).nollaaKaaret();
        }
        return this;
    }

    public String laskuri(int luku, Kaari kaari) {
        if (luku > 1) {
            return "  |||N:  " + kaari.solmu2 + "  |P: " + kaari.paino + " ";
        } else if (luku == 1) {
            return "  |||N:  " + kaari.solmu2 + "  |P: " + kaari.paino + "   |||  \n";
        } else {
            return "";
        }
    }

    /**
     * Tulostetaan itse verkko jossakin muodossa.
     *
     * @return solmut
     */
    @Override
    public String toString() {
        System.out.println("| Solmu | Naapuri| Paino  | Naapuri| Paino  | Naapuri| Paino ->");
        for (int i = 0; i < solmut.size(); i++) {
            Solmu tama = solmut.get(i);
            tama.kopioiKaaret();
            int laskin = tama.kaaret.size();
            System.out.print("\n|S: " + tama.numero);
            for (int j = 0; j < tama.kaaret.size(); j++) {
                System.out.print(laskuri(laskin, tama.etsiUusipieninkaari()));
                laskin--;
            }
        }
        return "\nYhteispaino: " + yhteispaino();
    }
    private static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {
        //Testiä
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


        Long starttiK;
        Long starttiP;
        Long tulosK;
        Long tulosP;
        Painotettuverkko K;
        Painotettuverkko P;
        Kruskal kruskal;
        Prim prim;

        System.out.println("Ajetaan Kruskalin algoritmi verkolle: " + verkko.palautaVerkko());
        starttiK = System.nanoTime();
        kruskal = new Kruskal(verkko);
        K = kruskal.palautaVirittavapuu();
        tulosK = System.nanoTime() - starttiK;
        System.out.println("Tulos Kruskalilla on: " + tulosK + " nanosekunttia");
        System.out.println(K);


        System.out.println("Ajetaan Primin algoritmi verkolle: " + verkko.palautaVerkko());
        System.out.println("Anna lähtösolmu:");
        int solmu = Integer.parseInt(lukija.nextLine().trim());
        while (!verkko.sisaltaakoSolmun(solmu)) {
            System.out.println("Syötä solmu joka on jo verkossa.");
            System.out.println(verkko.palautaVerkko());
            solmu = Integer.parseInt(lukija.nextLine().trim());
        }
        Solmu v = verkko.palautaSolmu(solmu);
        starttiP = System.nanoTime();
        prim = new Prim(verkko, v);
        tulosP = System.nanoTime() - starttiP;
        P = prim.palautaVirittavapuu();
        System.out.println("Tulos Primillä on: " + tulosP + " nanosekunttia");
        System.out.println(P);

        if (tulosP > tulosK) {
            double prosenttiK = (1 - tulosK / 1.0 / tulosP) * 100;
            System.out.println("Kruskalin algoritmi oli nopeampi " + prosenttiK + " prosenttia");
        } else {
            double prosenttiP = (1 - tulosP / 1.0 / tulosK) * 100;
            System.out.println("Primin algoritmi oli nopeampi " + prosenttiP + " prosenttia");
        }
    }
}
