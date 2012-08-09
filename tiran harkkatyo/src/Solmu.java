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
    int numero; //Tämä tarkoittaa ikäänkuin solmun nimeä. Numerojärjestystä voidaan käyttää algoritmissa.
    boolean lapikayty; //Onko solmu jo virittävässä puussa.

    public Solmu() {
        kaaret = new PriorityQueue<Kaari>();
        lapikayty = false;
    }

    public Solmu(int numero) {
        this.numero = numero;
        kaaret = new PriorityQueue<Kaari>();
        lapikayty = false;
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
        return (this.numero < solmu.numero) ? -1 : 1;
    }
}