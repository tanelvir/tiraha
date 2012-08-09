
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
    Painotettuverkko T;
    Kaari pieninkaari;
    PriorityQueue<Kaari> kaikkikaaret;

    /**
     * Annetaan verkko sekä aloitussolmu r, josta lähdetään liikkeelle.
     * Luodaan virittävä puu T, johon aletaan lisäämään kaaria.
     * Nollausmetodissa siis pitäisi T:n kaarien muuttua äärettömiksi ja niitä
     * aletaan päivittämään ajan tasalle.
     *
     * @param G
     * @param r
     */
    public Prim(Painotettuverkko G, Solmu r) {

        lapikaydytsolmut = new ArrayList<Solmu>();
        kaikkikaaret = new PriorityQueue<Kaari>();
        T = new Painotettuverkko();
        System.out.println("kaaret: " + r.palautaKaaret());
        T = nollaaKaaret(G);
        int index = G.palautaVerkko().indexOf(r);
        pieninkaari = r.palautaKaaret().poll();
        System.out.println("pieninkaari: " + pieninkaari);
        T.palautaVerkko().get(index).lisaaKaari(pieninkaari.Solmu2().numero, pieninkaari.paino);
        T.palautaVerkko().get(index).kaytyLapi();
        lapikaydytsolmut.add(pieninkaari.solmu1);
        //siirraKaaret(pieninkaari.solmu1);
        /*index = G.palautaVerkko().indexOf(pieninkaari.solmu2);
         T.palautaVerkko().get(index).lisaaKaari(pieninkaari.paino, pieninkaari.Solmu1().numero);
         T.palautaVerkko().get(index).kaytyLapi();
         lapikaydytsolmut.add(pieninkaari.solmu2);
         siirraKaaret(pieninkaari.solmu2);*/

        System.out.println(T.palautaVerkko().get(0).palautaKaaret());
        //System.out.println(T.palautaVerkko().get(1).palautaKaaret());
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
        Solmu v;
        X = new Painotettuverkko();
        X = G;
        System.out.println("1: " + G.palautaVerkko().get(1).palautaKaaret());
        for (int i = 0; i < X.palautaVerkko().size(); i++) {
            v = X.palautaVerkko().get(i);
            v.nollaaKaaret();
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
        Kaari kaari;
        while (!solmu.palautaKaaret().isEmpty()) {
            kaari = solmu.palautaKaaret().poll();
            kaikkikaaret.add(kaari);
        }

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