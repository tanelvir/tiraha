
import java.util.ArrayList;


class Solmu implements Comparable<Solmu>
{
    ArrayList<Kaari> kaaret;
    int numero;
    
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
    
    public void lisaaKaari(Solmu naapuri, int paino) {
        Kaari uusi;
        uusi = new Kaari(this ,naapuri, paino);
        kaaret.add(uusi);
    }
    
    @Override
    public String toString() {
        return "Solmun kaaret: " + kaaret + " yhteensä siis " + kaaret.size() + " kaarta";
    }
    
    public int compareTo(Solmu solmu) {
        return (this.numero < solmu.numero) ? -1 : 1;
    }
}