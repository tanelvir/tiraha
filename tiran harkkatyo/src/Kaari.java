/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
class Kaari implements Comparable<Kaari>
{
    String solmu1, solmu2;
    int paino;

    public Kaari(String solmu1, String solmu2, int paino) {
        this.solmu1 = solmu1;
        this.solmu2 = solmu2;
        this.paino = paino;
    }

    public String Solmu1() {
        return solmu1;
    }

    public String Solmu2() {
        return solmu2;
    }

    public int kaarenPaino() {
        return paino;
    }

    @Override
    public String toString() {
        return "(" + solmu1 + ", " + solmu2 + ") : Paino = " + paino;
    }

    public int compareTo(Kaari kaari) {
        //== is not compared so that duplicate values are not eliminated.
        return (this.paino < kaari.paino) ? -1 : 1;
    }
}
