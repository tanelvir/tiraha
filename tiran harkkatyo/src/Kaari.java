/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
/**
 * Kaari on kahden solmun välillä ja sillä voi olla oma paino.
 *
 */
class Kaari implements Comparable<Kaari> {

    Solmu solmu1, solmu2;
    int paino;

    /**
     * Kaari tuntee lähtösolmun ja sen naapurisolmun.
     *
     */
    public Kaari(Solmu solmu1, Solmu solmu2, int paino) {
        this.solmu1 = solmu1;
        this.solmu2 = solmu2;
        this.paino = paino;
    }

    public int Solmu1() {
        return solmu1.numero;
    }

    public int Solmu2() {
        return solmu2.numero;
    }

    public int kaarenPaino() {
        return paino;
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
     * Verrataan kaaria painon mukaan.
     *
     */
    public int compareTo(Kaari kaari) {
        return (this.paino < kaari.paino) ? -1 : 1;
    }
}
