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
 * Solmulla voi olla E määrä kaaria.
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
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void kaytyLapi() {
        this.lapikayty = true;
    }

    public int returnNumero() {
        return numero;
    }

    public boolean onkoKayty() {
        return lapikayty;
    }

    public PriorityQueue<Kaari> palautaKaaret() {
        return kaaret;
    }

    public void lisaaKaari(int naapuri, int paino) {
        Kaari uusi;
        Solmu solmu;
        solmu = new Solmu(naapuri);
        uusi = new Kaari(this, solmu, paino);
        kaaret.add(uusi);
    }

    public void paivitaKaari(int naapuri, int paino) {
        Kaari uusi;
        Solmu solmu;
        solmu = new Solmu(naapuri);
        uusi = new Kaari(this, solmu, paino);
        kaaret.add(uusi);
    }

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