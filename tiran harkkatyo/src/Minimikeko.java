
import java.util.*;

/*
 * Minimikeko on tietorakenne, jossa solmut ovat aina suuruusjärjestyksessä pienimmästä suurempaan.
 * Lisäksi siitä etsiminen on nopeaa (log n) sillä kaikilla paitsi pääsolmulla on vanhempi solmu.
 * Lisäksi kaikilla paitsi viimeisillä solmuilla on lapsia.
 */
public class Minimikeko {

    List<Kaari> keko = new ArrayList<Kaari>();

    /**
     * Luodaan keko tekemällä heapifyita.
     *
     * @param taulukko
     */
    public Minimikeko(ArrayList<Kaari> kaaret) {
        keko = kaaret;
        for (int k = keko.size() / 2 - 1; k >= 0; k--) {
            heapify(k, keko.get(k));
        }
    }

    /**
     *
     * Lisätään solmu kekoon katsomalla sille oikea sijainti keon
     * järjestyksessä.
     *
     * @param solmu
     */
    public void lisaa(Kaari solmu) {
        keko.add(null);
        int k = keko.size() - 1;
        while (k > 0) {
            int vanhempi = (k - 1) / 2;
            Kaari p = keko.get(vanhempi);
            if (solmu.compareTo(p) >= 0) {
                break;
            }
            keko.set(k, p);
            k = vanhempi;
        }
        keko.set(k, solmu);
    }

    /**
     * Poistetaan solmu ja pidetään oikea järjestys tekemällä heapify
     * viimeiselle solmulle.
     *
     * @return poistettuSolmu
     */
    public Kaari poista() {
        Kaari poistettuSolmu = keko.get(0);
        Kaari viimeinenSolmu = keko.remove(keko.size() - 1);
        heapify(0, viimeinenSolmu);
        return poistettuSolmu;
    }
    
    /**
     * Otetaan viimeinen solmu keon lopusta, järjestys säilyy joten heapifyita ei tarvita.
     * 
     * @return poistettuSolmu
     */
    
    public Kaari poistaSuurin() {
        Kaari poistettuSolmu = keko.remove(keko.size() - 1);
        return poistettuSolmu;
    }

    /**
     * Palauttaa pienimmän solmun
     *
     * @return
     */
    public Kaari pienin() {
        return keko.get(0);
    }

    /**
     * Palauttaa true jos keko on tyhjä, muuten false.
     *
     * @return
     */
    public boolean onkoTyhja() {
        return keko.isEmpty();
    }
    
    /**
     * Sisältääkö keko kyseisen kaaren.
     * 
     * @param kaari
     * @return 
     */
    
    public boolean sisaltaako(Kaari kaari) {
        if (keko.contains(kaari)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Palauttaa keon koon.
     * 
     * @return 
     */
    
    public int palautaKoko() {
        return keko.size();
    }

    /**
     * Katsotaan onko lapsi pienempi kuin vanhempansa ja onko lapsen viereinen
     * solmu suurempi kuin se itse. Jos ei ole niin siirretään se oikealle
     * paikalleen.
     *
     * @param k
     * @param solmu
     */
    void heapify(int k, Kaari kaari) {
        if (keko.isEmpty()) {
            return;
        }
        while (k < keko.size() / 2) {
            int lapsi = 2 * k + 1;
            if (lapsi < keko.size() - 1 && keko.get(lapsi).compareTo(keko.get(lapsi + 1)) > 0) {
                lapsi++;
            }
            if (kaari.compareTo(keko.get(lapsi)) <= 0) {
                break;
            }
            keko.set(k, keko.get(lapsi));
            k = lapsi;
        }
        keko.set(k, kaari);
    }

    @Override
    public String toString() {
        return "" + keko;
    }

    // Testausta
    public static void main(String[] args) {
        Kaari[] taulu = {new Kaari(2,3,4), new Kaari(4,5,2), new Kaari(1,4,3), new Kaari(3,1,5)};
        Minimikeko heap;
        //heap = new Minimikeko(taulu);
        // tulostetaan solmut järjestyksessä
        //while (!heap.onkoTyhja()) {
        //    System.out.println(heap.poista());
        //}
    }
}
