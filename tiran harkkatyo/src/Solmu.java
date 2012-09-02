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
 * Solmulla voi olla E määrä kaaria. Solmu ei tunne naapuriaan, mutta sen kaari
 * tuntee päätesolmunsa. Solmulla voi olla setti, eli siis metsä johon se kuuluu.
 *
 */
class Solmu {

    PriorityQueue<Kaari> kaaret; //Minimikeko solmun kaarista.
    PriorityQueue<Kaari> uudet; //Tähän kopioidaan kaaret tulostusta varten, jotta keko ei tyhjene.
    int numero; //Tämä tarkoittaa ikäänkuin solmun nimeä. Numerojärjestystä voidaan käyttää algoritmissa.
    boolean lapikayty; //Onko solmu jo virittävässä puussa.
    int setti; //Mihin metsään solmu kuuluu. On oletuksena sama kuin numero.

    /**
     * Lisätään vain jokin solmu ilman numeroa.
     */
    public Solmu() {
        kaaret = new PriorityQueue<Kaari>();
        lapikayty = false;
    }

    /**
     * Lisätään solmu numeron kanssa.
     *
     * @param numero
     */
    public Solmu(int numero) {
        this.numero = numero;
        kaaret = new PriorityQueue<Kaari>();
        lapikayty = false;
        this.setti = numero;
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
        lapikayty = false;
        lisaaKaari(naapuri, paino);
        this.setti = numero;
    }

    /**
     * Numeroa voidaan vaihtaa
     *
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    /**
     * Sijoitetaan solmu johonkin metsään.
     * 
     * @param numero 
     */
    
    public void setSetti(int numero ){
        this.setti = numero;
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
    
    /**
     * Kaaret voidaan kokonaan vaihtaa myös.
     * 
     * @param uudet 
     */
    
    public void setKaaret(PriorityQueue<Kaari> uudet) {
        this.kaaret = uudet;
    }
    
    public void setKaaret(ArrayList<Kaari> uudet) {
        this.kaaret = new PriorityQueue<Kaari>();
        for (Kaari e : uudet) {
            kaaret.add(e);
        }
    }

    public PriorityQueue<Kaari> palautaKaaret() {
        return kaaret;
    }
    
    /**
     * Palauttaa kaaret ArrayListin muodossa poistamatta alkuperäisiä kaaria.
     * 
     * @return ArraList<Kaari>
     */
    
    public ArrayList<Kaari> palautaKaaret2() {
        ArrayList<Kaari> kaaret = new ArrayList<Kaari>(); 
        kopioiKaaret();
        while (!(uudet.isEmpty())) {
            Kaari uusi = uudet.poll();
            kaaret.add(uusi);
        }
        return kaaret;
    }

    /**
     * Lisätään kaari, jolla on paino ja päätepisteenä on naapuri. Solmulla voi olla max. 4 kaarta.
     *
     * @param naapuri
     * @param paino
     */
    public void lisaaKaari(int naapuri, int paino) {
        if (kaaret.size() < 4 && sisaltaakoKaaren(naapuri)==false) {
            Kaari uusi;
            Solmu solmu;
            solmu = new Solmu(naapuri);
            uusi = new Kaari(this, solmu, paino);
            kaaret.add(uusi);
        }
        else {
            System.out.println("Kaaria voi olla max. 4");
        }
    }
    
    /**
     * Katsoo onko kahden solmun välillä kaarta. Jos on palautus on true.
     * 
     * @param naapuri
     * @return true/false
     */
    
    public boolean sisaltaakoKaaren(int naapuri) {
        ArrayList<Kaari> kaikki = new ArrayList<Kaari>();
        kaikki = palautaKaaret2();
        for (int i = 0; i < kaikki.size(); i++) {
            Kaari e = kaikki.get(i);
            if (this.numero==e.Solmu1().numero && naapuri==e.Solmu2().numero) {
                return true;
            }
        }
        return false;
    }

    /**
     * Napataan keosta uusi pienin kaari. Kyseinen kaari siis poistuu, mutta ei kaaret-keosta.
     *
     * @return
     */
    public Kaari etsiUusipieninkaari() {
        Kaari pieninKaari = this.uudet.poll();
        return pieninKaari;
    }
    
    public void kopioiKaaret() {
        this.uudet = new PriorityQueue<Kaari>();
        this.uudet = new PriorityQueue<Kaari>(kaaret);
    }

    /**
     * Tyhjennetään solmu kaarista (ääretön).
     */
    public void nollaaKaaret() {
        kaaret = new PriorityQueue();
    }

    @Override
    public String toString() {
        return "" + this.numero;
    }
}