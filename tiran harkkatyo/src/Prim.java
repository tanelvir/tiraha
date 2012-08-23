
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
     * Annetaan verkko sekä aloitussolmu r, josta lähdetään liikkeelle. Luodaan
     * virittävä puu T, johon aletaan lisäämään kaaria. Nollausmetodissa siis
     * pitäisi T:n kaarien muuttua tyhjiksi ja niitä aletaan päivittämään
     * ajan tasalle. Uuden solmun lisättäessä solmun kaaret lisätään minimikekoon kaikkikaaret.
     * Annettu verkko G pienenee ja toimii mittarina millon verkko on täynnä.
     *
     * @param Painotettuverkko G
     * @param Solmu r
     */
    public Prim(Painotettuverkko G, Solmu r) {

        lapikaydytsolmut = new ArrayList<Solmu>();
        kaikkikaaret = new PriorityQueue<Kaari>();
        T = new Painotettuverkko();
        T.setVerkko(G.palautaVerkko());
        lapikaydytsolmut.add(r);
        siirraKaaret(G.poistaSolmu(r.numero));
        Kaari pieninkaari = kaikkikaaret.poll();
        T.lisaaKaari(r.numero, pieninkaari.Solmu2().numero, pieninkaari.paino);
        T.palautaSolmu(r.numero).kaytyLapi();
        siirraKaaret(G.poistaSolmu(pieninkaari.Solmu2().numero));
        T.palautaSolmu(pieninkaari.Solmu2().numero).kaytyLapi();
        lapikaydytsolmut.add(pieninkaari.Solmu2());


        while ((G.palautaVerkko().isEmpty()) == false && (kaikkikaaret.isEmpty()) == false) {
            Kaari pienin = kaikkikaaret.poll();
            Solmu uusi = pienin.Solmu2();
            if (T.palautaSolmu(uusi.numero).onkoKayty()==false) {
                lapikaydytsolmut.add(uusi);
                siirraKaaret(G.poistaSolmu(uusi.numero));
                T.lisaaKaari(pienin.Solmu1().numero, uusi.numero, pienin.paino);
                T.palautaSolmu(uusi.numero).kaytyLapi();
                G.poistaSolmu(uusi.numero);
            }

        }
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
    
    public Painotettuverkko palautaVirittavapuu() {
        return T;
    }

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
        System.out.println(prim.palautaVirittavapuu());
    }
}