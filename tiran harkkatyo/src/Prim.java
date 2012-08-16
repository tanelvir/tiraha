
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

    PriorityQueue<Solmu> lapikaydytsolmut;
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

        lapikaydytsolmut = new PriorityQueue<Solmu>();
        kaikkikaaret = new PriorityQueue<Kaari>();
        T = new Painotettuverkko();
        System.out.println("kaaret: " + r.palautaKaaret());
        T = nollaaKaaret(G);
        lapikaydytsolmut.add(r);

        System.out.println(T.palautaVerkko().get(0).palautaKaaret());
        System.out.println(T.palautaVerkko().get(1).palautaKaaret());
        System.out.println(lapikaydytsolmut);


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
        verkko.lisaaSolmu(1, 2, 1);
        verkko.palautaVerkko().get(0).lisaaKaari(3, 3);
        verkko.lisaaSolmu(2, 5, 4);
        verkko.palautaVerkko().get(1).lisaaKaari(6, 5);
        verkko.palautaVerkko().get(1).lisaaKaari(1, 1);
        verkko.lisaaSolmu(3, 1, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(6, 1);
        verkko.palautaVerkko().get(2).lisaaKaari(7, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(4, 2);
        verkko.lisaaSolmu(4, 3, 2);
        verkko.palautaVerkko().get(3).lisaaKaari(7, 5);
        verkko.lisaaSolmu(5, 2, 4);
        verkko.palautaVerkko().get(4).lisaaKaari(6, 1);
        verkko.lisaaSolmu(6, 5, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(2, 5);
        verkko.palautaVerkko().get(5).lisaaKaari(3, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(7, 4);
        verkko.lisaaSolmu(7, 6, 4);
        verkko.palautaVerkko().get(6).lisaaKaari(3, 3);
        verkko.palautaVerkko().get(6).lisaaKaari(4, 5);

        prim = new Prim(verkko, verkko.palautaVerkko().get(0));
        System.out.println(verkko.palautaKaikkikaaret());
    }
}