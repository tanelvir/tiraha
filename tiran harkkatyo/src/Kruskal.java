
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Kruskalin algoritmi. Algoritmi tuntee listan kaikista verkon kaarista.
 * Virittävä puu kootaan pikkuhiljaa lisäämällä pienimpiä kaaria, kunnes kaikki
 * solmut on käyty läpi ja puu on yhtenäinen eli joka solmusta pääsee jotakin
 * reittiä toiseen solmuun. Yhdistely tapahtuu antamalla solmulle metsän numero (setti).
 * Jos setit ovat erilaiset, metsät yhdistetään
 *
 *
 * @author tanelvir
 */
public class Kruskal {

    PriorityQueue<Kaari> kaikkikaaret;
    Painotettuverkko T; //Virittävä puu
    PriorityQueue<Kaari> lapikaymattomatkaaret;
    Tarkastaja tarkastaja;

    /**
     * Annetaan verkko, aletaan rakentamaan virittävää puuta.
     *
     * @param G
     */
    public Kruskal(Painotettuverkko G) {

        kaikkikaaret = G.palautaKaikkikaaret();
        T = new Painotettuverkko();
        T = new Painotettuverkko();
        T.setVerkko(G.palautaVerkko());
        lapikaymattomatkaaret = new PriorityQueue<Kaari>();

        while (!(kaikkikaaret.isEmpty())) {
            etsiKaari();
        }

        while (!(lapikaymattomatkaaret.isEmpty())) {
            yhdistaSetit();
        }
    }
    
    /**
     * Käydään läpi jokainen solmu. Naapurin setti muuttuu lähtösolmun setin mukaan.
     * Läpikäymättömiin kaariin tulee kaikistakaarista ylimääräiset kaaret. Pienin kaari liitetään
     * niin että se vie käymättömään solmuun.
     */

    public void etsiKaari() {
        Kaari pienin = kaikkikaaret.poll();
        if (T.palautaSolmu(pienin.Solmu2().numero).lapikayty == false) {
            T.palautaSolmu(pienin.Solmu2().numero).kaytyLapi();
            T.palautaSolmu(pienin.Solmu2().numero).setSetti(T.palautaSolmu(pienin.Solmu1().numero).setti);
            T.palautaSolmu(pienin.Solmu1().numero).kaytyLapi();
            T.lisaaKaari(pienin.Solmu1().numero, pienin.Solmu2().numero, pienin.paino);
        } else {
            lapikaymattomatkaaret.add(pienin);
        }
    }
    
    /**
     * Setit yhdistetään sillain että jos havaitaan kaari joka yhdistää kaksi eri settiä. 
     * Toisen setin solmujen setit muuttuvat for-lauseella, joka käy koko verkon läpi.
     */

    public void yhdistaSetit() {
        Kaari pienin = lapikaymattomatkaaret.poll();
        if ((T.palautaSolmu(pienin.Solmu1().numero).setti) != (T.palautaSolmu(pienin.Solmu2().numero).setti)) {
            int setti1 = T.palautaSolmu(pienin.Solmu1().numero).setti;
            int setti2 = T.palautaSolmu(pienin.Solmu2().numero).setti;
            for (int i = 0; i < T.palautaVerkko().size(); i++) {
                if (T.palautaVerkko().get(i).setti == setti2) {
                    T.palautaVerkko().get(i).setSetti(setti1);
                }
            }
            T.lisaaKaari(pienin.Solmu1().numero, pienin.Solmu2().numero, pienin.paino);
        }
    }

    public Painotettuverkko palautaVirittavapuu() {
        return T;
    }

    public static void main(String[] args) {
        //Testiä
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
        System.out.println(kruskal.palautaVirittavapuu());

    }
}