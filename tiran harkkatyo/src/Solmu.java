/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.util.PriorityQueue;

/**
 * Solmulla voi olla E määrä kaaria. Solmu ei tunne naapuriaan, mutta sen kaari tuntee naapurinsa.
 *
 */
class Solmu implements Comparable<Solmu> {

    PriorityQueue<Kaari> kaaret;
    PriorityQueue<Kaari> kaymattomatKaaret;
    int numero; //Tämä tarkoittaa ikäänkuin solmun nimeä. Numerojärjestystä voidaan käyttää algoritmissa.
    boolean lapikayty; //Onko solmu jo virittävässä puussa.
    
    /**
     * Lisätään vain jokin solmu ilman numeroa.
     */

    public Solmu() {
        kaaret = new PriorityQueue<Kaari>();
        kaymattomatKaaret = new PriorityQueue<Kaari>();
        lapikayty = false;
    }
    
    /**
     * Lisätään solmu numeron kanssa.
     * @param numero 
     */

    public Solmu(int numero) {
        this.numero = numero;
        kaaret = new PriorityQueue<Kaari>();
        kaymattomatKaaret = new PriorityQueue<Kaari>();
        lapikayty = false;
    }
    
    /**
     * Lisätään solmu numeron, naapurin ja painon kanssa
     * 
     * @param tama
     * @param naapuri
     * @param paino 
     */
    
    public Solmu(int tama, int naapuri, int paino) {
        this.numero = tama;
        kaaret = new PriorityQueue<Kaari>();
        kaymattomatKaaret = new PriorityQueue<Kaari>();
        lapikayty = false;
        lisaaKaari(naapuri, paino);
    }
    
    /**
     * Numeroa voidaan vaihtaa
     * @param numero 
     */

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
     * Solmu lisätään virittävään puuhun
     * 
     */

    public void kaytyLapi() {
        this.lapikayty = true;
    }

    public int returnNumero() {
        return numero;
    }
    
    /**
     * Onkos solmu lisätty jo virittävään puuhun.
     * 
     */

    public boolean onkoKayty() {
        return lapikayty;
    }

    public PriorityQueue<Kaari> palautaKaaret() {
        return kaaret;
    }
    
    /**
     * Lisätään kaari, jolla on paino ja päätepisteenä on naapuri.
     * 
     * @param naapuri
     * @param paino 
     */

    public void lisaaKaari(int naapuri, int paino) {
        Kaari uusi;
        Solmu solmu;
        solmu = new Solmu(naapuri);
        uusi = new Kaari(this, solmu, paino);
        kaaret.add(uusi);
        kaymattomatKaaret.add(uusi);
    }
    
    /**
     * Kaari, jota ei olla viellä käyty läpi.
     * 
     * @param naapuri
     * @param paino 
     */
    
    public void lisaaKaymatonkaari(int naapuri, int paino) {
        Kaari uusi;
        Solmu solmu;
        solmu = new Solmu(naapuri);
        uusi = new Kaari(this, solmu, paino);
        kaymattomatKaaret.add(uusi);
    }
    
    /**
     * Napataan keosta uusi pienin kaari.
     * 
     * @return 
     */
    
    public Kaari etsiUusipieninkaari() {
        Kaari pieninKaari = kaymattomatKaaret.poll();
        return pieninKaari;
    }
    
    /**
     * Asetetaan kaikkien kaarien painoksi ääretön.
     */

    public void nollaaKaaret() {
        for (Kaari e : kaaret) {
            e.setPaino(Integer.MAX_VALUE);
        }
    }

    @Override
    public String toString() {
        return "" + this.numero;
    }

    public int compareTo(Solmu solmu) {
        return (this.kaymattomatKaaret.peek().paino < solmu.kaymattomatKaaret.peek().paino) ? -1 : 1;
    }
}