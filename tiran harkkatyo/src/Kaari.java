
class Kaari implements Comparable<Kaari>
{
    Solmu solmu1, solmu2;
    int paino;

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

    @Override
    public String toString() {
        return "Solmun" + solmu1 + " ja " + solmu2 + " paino on " + paino;
    }

    public int compareTo(Kaari kaari) {
        return (this.paino < kaari.paino) ? -1 : 1;
    }
}
