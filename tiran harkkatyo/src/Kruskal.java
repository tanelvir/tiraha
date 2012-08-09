
import java.util.PriorityQueue;

public class Kruskal {

    PriorityQueue<Kaari> kaikkikaaret;
    Painotettuverkko T;

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
        verkko.lisaaSolmu(1, 2, 2);
        verkko.lisaaSolmu(2, 3, 3);
        verkko.palautaVerkko().get(3).lisaaKaari(1, 1);
        verkko.lisaaSolmu(4, 2, 5);
        verkko.palautaVerkko().get(4).lisaaKaari(3, 4);
    }
}
