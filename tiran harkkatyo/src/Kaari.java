/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
/**
 * Kaari on kahden solmun välillä ja sillä voi olla oma paino. Toimii siis vain linkkinä kahden solmun välillä.
 *
 */
class Kaari implements Comparable<Kaari> {

    Solmu solmu1, solmu2;
    int paino;

    /**
     * Kaari tuntee lähtösolmun ja sen naapurisolmun sekä painonsa.
     *
     */
    public Kaari(Solmu solmu1, Solmu solmu2, int paino) {
        this.solmu1 = solmu1;
        this.solmu2 = solmu2;
        this.paino = paino;
    }

    /**
     * Sama luonti numeroiden avulla.
     *
     * @param solmu1
     * @param solmu2
     * @param paino
     */
    public Kaari(int solmu1, int solmu2, int paino) {
        Solmu uusi1 = new Solmu(solmu1);
        Solmu uusi2 = new Solmu(solmu2);
        this.solmu1 = uusi1;
        this.solmu2 = uusi2;
        this.paino = paino;
    }

    /**
     * Painoa voidaan vaihtaa.
     *
     * @param paino
     */
    public void setPaino(int paino) {
        this.paino = paino;
    }

    public Solmu Solmu1() {
        return solmu1;
    }

    public Solmu Solmu2() {
        return solmu2;
    }

    public int kaarenPaino() {
        return paino;
    }

    /**
     * Voidaan katsoa onko naapuri jo virittävässä puussa.
     *
     * @return
     */
    public boolean onkoNaapurilapikayty() {
        return solmu2.lapikayty;
    }

    /**
     * Tulostetaan tiedot solmuista ja niiden kaarista.
     *
     */
    @Override
    public String toString() {
        return "Solmun " + solmu1 + " ja " + solmu2 + " välinen paino " + paino;
    }

    /**
     * Verrataan kaaria painon mukaan. Tämä metodi mahdollistaa minimikeon käytön tietorakenteena.
     *
     */
    public int compareTo(Kaari kaari) {
       return (this.paino < kaari.paino) ? -1 : 1;
    }
}
