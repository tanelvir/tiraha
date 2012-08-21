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

/**
 * Painotettu verkko koostuu V määrästä solmuja ja solmut tuntevat kaarensa.
 * Yhdessä ne muodostavat verkon jossa kaarilla on painot ja kahden solmun
 * välillä on vain 1 kaari.
 *
 */
public class Painotettuverkko {

    ArrayList<Solmu> solmut; //Solmut puu-rakenteen muodossa.
    ArrayList<Integer> solmujennumerot;
    
    /**
     * Luodaan kokonaan uusi verkko
     */

    public Painotettuverkko() {
        solmut = new ArrayList<Solmu>();
        solmujennumerot = new ArrayList<Integer>();
    }
    
    /**
     * Luodaan verkko valmiine solmuineen.
     * 
     * @param verkko 
     */

    public Painotettuverkko(ArrayList<Solmu> verkko) {
        this.solmut = verkko;
    }

    public void setVerkko(ArrayList<Solmu> verkko) {
        this.solmut = verkko;
    }

    public ArrayList<Solmu> palautaVerkko() {
        return solmut;
    }
    
    /**
     * Palauttaa koko verkon kaaret.
     * 
     * @return Minimikeko
     */

    public Minimikeko palautaKaikkikaaret() {
        ArrayList<Kaari> kaaret;
        kaaret = new ArrayList<Kaari>();
        for (int i = 0; i < solmut.size(); i++) {
            Solmu v = solmut.get(i);
            while (!v.palautaKaaret().isEmpty()) {
                kaaret.add(v.palautaKaaret().poll());
            }
        }
        Minimikeko keko = new Minimikeko(kaaret);
        return keko;
    }
    
    /**
     * Tämä ei toimi... miksiköhän?
     * @return 
     */
    
    public int yhteispaino() {
        int summa = 0;
        Minimikeko keko;
        keko = palautaKaikkikaaret();
        while (!keko.onkoTyhja()) {
            summa =+ keko.poista().paino;
        }
        return summa;
    }
    
    /**
     * Eikä tämäkään...
     * @return 
     */
    
    public boolean[][] palautaVieruslista() {
        boolean[][] taulu = new boolean[solmut.size()][solmut.size()];
        //System.out.println("solmut kaikkineen :" + solmut);
        for (int i = 0; i < solmut.size(); i++) {
            Solmu v = solmut.get(i);
            Kaari kaari = v.palautaKaaret().poll();
            //System.out.println(v);
            while (!v.palautaKaaret().isEmpty()) {
                int j = kaari.Solmu2().numero-1;
                taulu[i][j] = true;
                kaari = v.palautaKaaret().poll();
                System.out.println(i + " " + j);
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
        if (sisaltaakoSolmun(solmu)==false) {
            uusi.lisaaKaari(naapuri, paino);
            System.out.println("uusi: " + uusi);
            solmut.add(uusi);
            solmujennumerot.add(uusi.numero);
        }
        if (sisaltaakoSolmun(naapuri)==false) {
            viereinen.lisaaKaari(solmu, paino);
            System.out.println("naapuri: " + viereinen);
            solmut.add(viereinen);
            solmujennumerot.add(viereinen.numero);
        }
        
    }
    
    /**
     * Lisätään vain solmu indeksillä.
     * @param solmu 
     */

    public void lisaaSolmu(int solmu) {
        Solmu v;
        if (sisaltaakoSolmun(solmu)==false) {
            v = new Solmu(solmu);
            solmut.add(v);
            solmujennumerot.add(v.numero);
        }
    }
    
    /**
     * Lisätään solmu oliona.
     * @param solmu 
     */
    
    public void lisaaSolmu(Solmu solmu) {
        if (sisaltaakoSolmun(solmu.numero)==false) {
            solmut.add(solmu);
            solmujennumerot.add(solmu.numero);
        }
    }
    
    /**
     * Lisätään sama kaari kummallekkin solmulle, jotta kommunikaatio toimii.
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
     * Sisältääkö koko verkko kaaren.
     * 
     * @param numero
     * @param naapuri
     * @param paino
     * @return 
     */
    
    public boolean sisaltaakoKaaren(int numero, int naapuri, int paino) {
        Kaari e = new Kaari(numero, naapuri, paino);
        if (palautaKaikkikaaret().sisaltaako(e)) {
            return true;
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
    
    public String laskuri(int luku, Kaari kaari) {
        if (luku > 1) {
            return "  |||N:  " + kaari.solmu2 + "  |P: " + kaari.paino + " ";
        }
        else if (luku == 1) {
            return "  |||N:  " + kaari.solmu2 + "  |P: " + kaari.paino + "   |||  \n";
        }
        else return "";
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
            int laskin = tama.kaaret.size();
            System.out.print("\n|S: " + tama.numero);
            for (int j = 0; j < tama.kaaret.size(); j++) {
                System.out.print(laskuri(laskin, tama.etsiUusipieninkaari()));
                laskin--;
            }
        }
        return "";
    }
    
    public static void main(String[] args) {
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
        
        System.out.println(verkko);
        System.out.println(verkko.palautaKaikkikaaret());
        
        boolean taulu[][] = verkko.palautaVieruslista(); 
        
        for (int i = 0; i < verkko.palautaVerkko().size(); i++) {
            for (int j = 0; j < verkko.palautaVerkko().size(); j++) {
                System.out.println(taulu[i][j]);
            } 
        }
    }
}
