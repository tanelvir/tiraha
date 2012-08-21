
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Kruskalin algoritmi. Algoritmi tuntee listan kaikista verkon kaarista.
 * Virittävä puu kootaan pikkuhiljaa lisäämällä pienimpiä kaaria, kunnes kaikki
 * solmut on käyty läpi ja puu on yhtenäinen eli joka solmusta pääsee jotakin
 * reittiä toiseen solmuun.
 *
 *
 * @author tanelvir
 */
public class Kruskal {

    Minimikeko kaikkikaaret;
    Painotettuverkko T; //Virittävä puu
    PriorityQueue<Kaari> lapikaydytkaaret;
    Tarkastaja tarkastaja;

    /**
     * Annetaan verkko, aletaan rakentamaan virittävää puuta.
     *
     * @param G
     */
    public Kruskal(Painotettuverkko G) {

        kaikkikaaret = G.palautaKaikkikaaret();
        T = new Painotettuverkko();
        lapikaydytkaaret = new PriorityQueue<Kaari>();
        System.out.println(G.palautaVerkko().size());

        while (T.palautaVerkko().size() < G.palautaVerkko().size()) {
            System.out.println("verkon koko: "+T.palautaVerkko().size());
            etsiKaari();
        }
        
        tarkastaja = new Tarkastaja();
        
        System.out.println(kaikkikaaret);
        
        while (kaikkikaaret.onkoTyhja()==false) {
            Kaari pienin = kaikkikaaret.poista();
            T.lisaaKaari(pienin.Solmu1(), pienin.Solmu2(), pienin.paino);
            if (tarkastaja.syotaVerkko(T.palautaVieruslista())) {
                break;
            }
            else {
                T.palautaKaikkikaaret().poistaSuurin();
            }
        }
        
        System.out.println(T.palautaKaikkikaaret());
        System.out.println(T.palautaVerkko());
    }
    
    

    /**
     * Lisää niitä kaaria ja solmuja joita virittävässä puussa ei vielä ole.
     * 
     *
     * @param X
     * @return
     */
    public void etsiKaari() {
        Kaari pienin = kaikkikaaret.poista();
        System.out.println("numba: " + T.solmujennumerot.contains(pienin.solmu1.numero) + " " + T.solmujennumerot.contains(pienin.solmu2.numero));
        System.out.println(pienin);
        if (T.solmujennumerot.contains(pienin.solmu1.numero)==true && T.solmujennumerot.contains(pienin.solmu2.numero)==true) {
            pienin = kaikkikaaret.poista();
        } else {
            if (T.solmujennumerot.contains(pienin.solmu1.numero)==true) {
                T.lisaaSolmu(pienin.solmu2.numero, pienin.solmu1.numero, pienin.paino);
            }
            else if (T.solmujennumerot.contains(pienin.solmu2.numero)==true) {
                T.lisaaSolmu(pienin.solmu1.numero, pienin.solmu2.numero, pienin.paino);
            }
            else {
                T.lisaaSolmu(pienin.solmu1.numero, pienin.solmu2.numero, pienin.paino);
                T.lisaaSolmu(pienin.solmu2.numero, pienin.solmu1.numero, pienin.paino);
            }
        }
    }

    public static void main(String[] args) {
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
        
        Kruskal kruskal = new Kruskal(verkko);
        
        

        /*System.out.println(puu.palautaVerkko().get(0).palautaKaaret());
         System.out.println(puu.palautaVerkko().get(1).palautaKaaret());
         System.out.println(puu.palautaVerkko().get(2).palautaKaaret());
         System.out.println(puu.palautaVerkko().get(3).palautaKaaret());*/

    }
}