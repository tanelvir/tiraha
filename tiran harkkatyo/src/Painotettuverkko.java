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

    public Painotettuverkko() {
        solmut = new ArrayList<Solmu>();
    }
    
    public Painotettuverkko(ArrayList<Solmu> verkko) {
        solmut = verkko;
    }
    
    public void setVerkko (ArrayList<Solmu> verkko) {
        this.solmut = verkko;
    }

    public ArrayList<Solmu> palautaVerkko() {
        return solmut;
    }
    
    public PriorityQueue<Kaari> palautaKaikkikaaret() {
        PriorityQueue<Kaari> kaaret = new PriorityQueue<Kaari>();
        for (int i = 0; i < solmut.size(); i++) {
            Solmu v = solmut.get(i);
            while (!v.palautaKaaret().isEmpty()) {
                kaaret.add(v.palautaKaaret().poll());
            }
        }
        return kaaret;
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
        uusi.lisaaKaari(naapuri, paino);
        solmut.add(uusi);
    }
    
    public void lisaaSolmu(int solmu) {
        Solmu uusi;
        uusi = new Solmu(solmu);
        solmut.add(uusi);
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
