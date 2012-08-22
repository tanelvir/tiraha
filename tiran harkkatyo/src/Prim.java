
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 *
 * Primin algoritmi. Tekee virittävän puun etsimällä tunnettujen solmujen
 * joukosta pienimmän kaaren uuteen solmuun. Puu kasvaa solmu solmulta ja
 * algoritmi on suoritettu kun kaikki solmut ollaan käyty läpi.
 *
 *
 * @author tanelvir
 */
public class Prim {

    ArrayList<Solmu> lapikaydytsolmut;
    ArrayList<Solmu> käymättömätsolmut;
    Painotettuverkko T;
    Kaari pieninkaari;
    PriorityQueue<Kaari> kaikkikaaret;

    /**
     * Annetaan verkko sekä aloitussolmu r, josta lähdetään liikkeelle. Luodaan
     * virittävä puu T, johon aletaan lisäämään kaaria. Nollausmetodissa siis
     * pitäisi T:n kaarien muuttua äärettömiksi ja niitä aletaan päivittämään
     * ajan tasalle.
     *
     * @param G
     * @param r
     */
    public Prim(Painotettuverkko G, Solmu r) {

        lapikaydytsolmut = new ArrayList<Solmu>();
        kaikkikaaret = new PriorityQueue<Kaari>();
        käymättömätsolmut = G.palautaVerkko();
        T = new Painotettuverkko();
        System.out.println("kaaret: " + r.palautaKaaret());
        T = nollaaKaaret(G);
        lapikaydytsolmut.add(r);
        G.poistaSolmu(r.numero);
        Kaari pieninkaari = r.etsiUusipieninkaari();
        T.palautaSolmu(r.numero).lisaaKaari(pieninkaari.Solmu2().numero, pieninkaari.paino);
        G.poistaSolmu(pieninkaari.Solmu2().numero);
        lapikaydytsolmut.add(pieninkaari.Solmu2());
        
        while (!G.palautaVerkko().isEmpty()) {
            Kaari pienin = etsiPieninkaari(lapikaydytsolmut);
            
        }


       /* for (int i = 0; i < T.palautaVerkko().size(); i++) {
            etsiUusireitti().Solmu2();
        }*/
        siirraKaaret(G.palautaVerkko().get(0));

        System.out.println(T);
    }

    /**
     * Tämä toimii jotenkin
     *
     * Luodaan nyt tässä vaiheessa tyhjä lista.
     *
     * @param G
     * @return
     */
    public Painotettuverkko nollaaKaaret(Painotettuverkko G) {
        Painotettuverkko X;
        X = new Painotettuverkko();
        System.out.println("1: " + G.palautaVerkko().get(1).palautaKaaret());
        for (int i = 0; i < G.palautaVerkko().size(); i++) {
            Solmu v = new Solmu(G.palautaVerkko().get(i).numero);
            X.lisaaSolmu(v.numero);
        }
        System.out.println("2: " + G.palautaVerkko().get(1).palautaKaaret());
        return X;
    }

    /**
     * Siirretään kaikki solmun kaaret listaan, jotta ollaan ajantasalla
     * kaikista saatavista kaarista.
     *
     * @param solmu
     */
    public void siirraKaaret(Solmu solmu) {
        while (!solmu.palautaKaaret().isEmpty()) {
            Kaari kaari = solmu.palautaKaaret().poll();
            kaikkikaaret.add(kaari);
        }

    }
    
    public Kaari etsiPieninkaari(ArrayList<Solmu> solmut) {
        Minimikeko kaaret = Minimikeko(new ArrayList<Kaari>());
        for (Solmu v : solmut) {
            kaaret.lisaa(v.etsiUusipieninkaari());
        }
        return kaaret.poista();
    }
    
    /**
     * Etsitään reitti levessuuntaisen läpikäynnin avulla.
     * 
     * @return pieninkaari
     */

    public Kaari etsiUusireitti() {
        Kaari pieninKaari;
        Solmu solmu = lapikaydytsolmut.poll();
        pieninKaari = solmu.kaaret.poll();
        System.out.println("höh" + solmu.kaaret.poll());
        while (!lapikaydytsolmut.isEmpty()) {
            if (T.palautaKaikkikaaret().sisaltaako(pieninKaari)) {
                solmu = lapikaydytsolmut.poll();
                pieninKaari = solmu.kaaret.poll();
            } else {
                return pieninKaari;
            }
        }
        return pieninKaari;

    }

    /*public Solmu etsiPieninkaari() {
     while (kaikkikaaret.isEmpty()) {
     pieninkaari = kaikkikaaret.poll();
     if (lapikaydytsolmut.contains(pieninkaari.solmu1) && lapikaydytsolmut.contains(pieninkaari.solmu2)) {
     pieninkaari = kaikkikaaret.poll();
     }
     }
     }*/
    public static void main(String[] args) {
        //Testiä
        Prim prim;
        Painotettuverkko verkko = new Painotettuverkko();
        verkko.lisaaSolmu(1);
        verkko.lisaaSolmu(2);
        verkko.lisaaSolmu(3);
        verkko.lisaaSolmu(4);
        verkko.lisaaSolmu(5);
        verkko.lisaaSolmu(6);
        verkko.lisaaSolmu(7);
        verkko.lisaaKaari(1, 2, 1);
        verkko.lisaaKaari(1, 3, 3);
        verkko.lisaaKaari(2, 5, 4);
        verkko.lisaaKaari(2, 6, 5);
        verkko.lisaaKaari(3, 6, 1);
        verkko.lisaaKaari(3, 7, 3);
        verkko.lisaaKaari(3, 4, 2);
        verkko.lisaaKaari(4, 7, 5);
        verkko.lisaaKaari(5, 6, 1);
        verkko.lisaaKaari(6, 7, 4);

        prim = new Prim(verkko, verkko.palautaVerkko().get(0));
        System.out.println(verkko.palautaKaikkikaaret());
    }

    private Minimikeko Minimikeko(ArrayList<Kaari> arrayList) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}