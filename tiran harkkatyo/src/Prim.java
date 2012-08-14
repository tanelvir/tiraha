
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


        for (int i = 0; i < T.palautaVerkko().size(); i++) {
            siirraKaaret(etsiUusireitti().solmu2);
        }
    }

    /**
     * Miksei tämä toimi!??????
     *
     * En koskekkaan G:hen ja silti sieltä tulee ääretöntä... :(
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

    public Kaari etsiUusireitti() {
        Kaari pieninKaari;
        Solmu solmu = lapikaydytsolmut.poll();
        pieninKaari = solmu.etsiUusipieninkaari();
        while (lapikaydytsolmut.isEmpty()) {
            if (T.palautaKaikkikaaret().contains(pieninKaari)) {
                solmu = lapikaydytsolmut.poll();
                pieninKaari = solmu.etsiUusipieninkaari();
            } else {
                return pieninKaari;
            }
        }
        return null;

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
        verkko.lisaaSolmu(1, 2, 2);
        verkko.lisaaSolmu(2, 3, 3);
        verkko.lisaaSolmu(3, 1, 1);
        verkko.lisaaSolmu(4, 2, 5);

        prim = new Prim(verkko, verkko.palautaVerkko().get(1));
    }
}