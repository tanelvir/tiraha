/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.util.ArrayList;

/**
 * Solmulla voi olla E määrä kaaria.
 *
 */
class Solmu implements Comparable<Solmu> {

    ArrayList<Kaari> kaaret;
    int numero; //Tämä tarkoittaa ikäänkuin solmun nimeä. Numerojärjestystä voidaan käyttää algoritmissa.

    public Solmu(int numero) {
        this.numero = numero;
        kaaret = new ArrayList<Kaari>();
    }

    public int numero() {
        return numero;
    }

    public ArrayList<Kaari> palautaKaaret() {
        return kaaret;
    }

    public void lisaaKaari(int naapuri, int paino) {
        Kaari uusi;
        Solmu solmu;
        solmu = new Solmu(naapuri);
        uusi = new Kaari(this, solmu, paino);
        kaaret.add(uusi);
    }

    @Override
    public String toString() {
        return ""+this.numero;
    }

    public int compareTo(Solmu solmu) {
        return (this.numero < solmu.numero) ? -1 : 1;
    }
}