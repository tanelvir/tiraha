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
                taulu[i][(kaari.Solmu2().numero-1)] = true;
                kaari = v.palautaKaaret().poll();
                System.out.println(i + " " + (kaari.Solmu2().numero-1));
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
        uusi = new Solmu(solmu);
        if (!solmut.contains(uusi)) {
            uusi.lisaaKaari(naapuri, paino);
            System.out.println("uusi: " + uusi);
            solmut.add(uusi);
            solmujennumerot.add(uusi.numero);
        }
    }
    
    /**
     * Lisätään vain solmu indeksillä.
     * @param solmu 
     */

    public void lisaaSolmu(int solmu) {
        Solmu uusi;
        uusi = new Solmu(solmu);
        if (!solmut.contains(uusi)) {
            solmut.add(uusi);
            solmujennumerot.add(uusi.numero);
        }
    }
    
    /**
     * Lisätään solmu oliona.
     * @param solmu 
     */
    
    public void lisaaSolmu(Solmu solmu) {
        if (!solmut.contains(solmu)) {
            solmut.add(solmu);
            solmujennumerot.add(solmu.numero);
        }
    }
    
    /**
     * Sisältääkö verkko solmun.
     * @param numero
     * @return 
     */

    public boolean sisaltaakoSolmun(int numero) {
        if (solmut.get(numero) != null) {
            return true;
        }
        return false;
    }

    /**
     * Tulostetaan itse verkko jossakin muodossa.
     *
     * @return solmut
     */
    @Override
    public String toString() {
        return "Verkko : " + solmut;
    }
}
