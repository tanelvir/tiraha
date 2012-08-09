
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

    PriorityQueue<Kaari> kaikkikaaret;
    Painotettuverkko T; //Virittävä puu
    
    /**
     * Annetaan verkko, aletaan rakentamaan virittävää puuta.
     * 
     * @param G 
     */

    public Kruskal(Painotettuverkko G) {

        kaikkikaaret = new PriorityQueue<Kaari>();
        T = G;

        for (Solmu v : G.palautaVerkko()) {
            for (Kaari e : v.palautaKaaret()) {
                kaikkikaaret.add(e);
            }
        }
        System.out.println(kaikkikaaret);
    }
    
    /**
     * Tämä metodi ei toimi jostain kumman syystä. Heittää erroria. Missäköhän on vika...
     * 
     * @param X
     * @return 
     */

    public Painotettuverkko kokoaVerkko(Painotettuverkko X) {
        Kaari kaari = kaikkikaaret.poll();
        Solmu tama = kaari.Solmu1();
        Solmu naapuri = kaari.Solmu2();
        while (T.palautaVerkko().size() == X.palautaVerkko().size()) {
            if (X.palautaVerkko().contains(tama) && X.palautaVerkko().contains(naapuri)) {
                kaari = kaikkikaaret.poll();
                System.out.println(kaari);
                tama = kaari.Solmu1();
                naapuri = kaari.Solmu2();
            } else if (!(X.palautaVerkko().contains(tama)) && X.palautaVerkko().contains(naapuri)) {
                tama.kaytyLapi();
                System.out.println("tama: " + tama);
                X.lisaaSolmu(tama.numero, naapuri.numero, kaari.paino);
                kaari = kaikkikaaret.poll();
                System.out.println(kaari);
                tama = kaari.Solmu1();
                naapuri = kaari.Solmu2();
            } else if (X.palautaVerkko().contains(tama) && !(X.palautaVerkko().contains(naapuri))) {
                naapuri.kaytyLapi();
                System.out.println("naapuri: " + naapuri);
                X.lisaaSolmu(naapuri.numero, tama.numero, kaari.paino);
                System.out.println(kaari);
                kaari = kaikkikaaret.poll();
                tama = kaari.Solmu1();
                naapuri = kaari.Solmu2();
            } else {
                tama.kaytyLapi();
                naapuri.kaytyLapi();
                System.out.println("molemmat: " + tama + ", " + naapuri);
                X.lisaaSolmu(naapuri.numero, tama.numero, kaari.paino);
                X.lisaaSolmu(tama.numero, naapuri.numero, kaari.paino);
                kaari = kaikkikaaret.poll();
                System.out.println(kaari);
                tama = kaari.Solmu1();
                naapuri = kaari.Solmu2();
            }
        }
        return X;
    }

    public static void main(String[] args) {
        Painotettuverkko verkko = new Painotettuverkko();
        Painotettuverkko puu = new Painotettuverkko();
        verkko.lisaaSolmu(1, 2, 2);
        verkko.lisaaSolmu(2, 3, 3);
        verkko.lisaaSolmu(3, 1, 1);
        verkko.lisaaSolmu(4, 2, 5);
        verkko.palautaVerkko().get(3).lisaaKaari(3, 4);
        Kruskal kruskal;
        kruskal = new Kruskal(verkko);
        puu = kruskal.kokoaVerkko(verkko);
        
        /*System.out.println(puu.palautaVerkko().get(0).palautaKaaret());
        System.out.println(puu.palautaVerkko().get(1).palautaKaaret());
        System.out.println(puu.palautaVerkko().get(2).palautaKaaret());
        System.out.println(puu.palautaVerkko().get(3).palautaKaaret());*/
        
    }
}