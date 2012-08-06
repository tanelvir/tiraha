
import java.util.*;

/*
 * Minimikeko on tietorakenne, jossa solmut ovat aina suuruusjärjestyksessä pienimmästä suurempaan.
 * Lisäksi siitä etsiminen on nopeaa (log n) sillä kaikilla paitsi pääsolmulla on vanhempi solmu.
 * Lisäksi kaikilla paitsi viimeisillä solmuilla on lapsia.
 */
public class Minimikeko {

    List<Solmu> h = new ArrayList<Solmu>();

    /**
     * Luodaan keko tekemällä heapifyita.
     *
     * @param taulukko
     */
    public Minimikeko(Solmu[] taulukko) {
        for (Solmu key : taulukko) {
            h.add(key);
        }
        for (int k = h.size() / 2 - 1; k >= 0; k--) {
            heapify(k, h.get(k));
        }
    }

    /**
     *
     * Lisätään solmu kekoon katsomalla sille oikea sijainti keon
     * järjestyksessä.
     *
     * @param solmu
     */
    public void lisaa(Solmu solmu) {
        h.add(null);
        int k = h.size() - 1;
        while (k > 0) {
            int vanhempi = (k - 1) / 2;
            Solmu p = h.get(vanhempi);
            if (solmu.compareTo(p) >= 0) {
                break;
            }
            h.set(k, p);
            k = vanhempi;
        }
        h.set(k, solmu);
    }

    /**
     * Poistetaan solmu ja pidetään oikea järjestys tekemällä heapify
     * viimeiselle solmulle.
     *
     * @return
     */
    public Solmu poista() {
        Solmu poistettuSolmu = h.get(0);
        Solmu viimeinenSolmu = h.remove(h.size() - 1);
        heapify(0, viimeinenSolmu);
        return poistettuSolmu;
    }

    /**
     * Palauttaa pienimmän solmun
     *
     * @return
     */
    public Solmu pienin() {
        return h.get(0);
    }

    /**
     * Palauttaa true jos keko on tyhjä, muuten false.
     *
     * @return
     */
    public boolean onkoTyhja() {
        return h.isEmpty();
    }

    /**
     * Katsotaan onko lapsi pienempi kuin vanhempansa ja onko lapsen viereinen
     * solmu suurempi kuin se itse.
     *
     * @param k
     * @param solmu
     */
    void heapify(int k, Solmu solmu) {
        if (h.isEmpty()) {
            return;
        }
        while (k < h.size() / 2) {
            int lapsi = 2 * k + 1;
            if (lapsi < h.size() - 1 && h.get(lapsi).compareTo(h.get(lapsi + 1)) > 0) {
                lapsi++;
            }
            if (solmu.compareTo(h.get(lapsi)) <= 0) {
                break;
            }
            h.set(k, h.get(lapsi));
            k = lapsi;
        }
        h.set(k, solmu);
    }

    // Testausta
    public static void main(String[] args) {
        Solmu[] taulu = {new Solmu(2), new Solmu(4), new Solmu(1), new Solmu(3)};
        Minimikeko heap;
        heap = new Minimikeko(taulu);
        // tulostetaan solmut järjestyksessä
        while (!heap.onkoTyhja()) {
            System.out.println(heap.poista());
        }
    }
}
