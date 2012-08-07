
import java.util.PriorityQueue;

public class Kruskal {

    PriorityQueue<Kaari> kaikkikaaret;
    Painotettuverkko T;

    public Kruskal(Painotettuverkko G) {

        kaikkikaaret = new PriorityQueue<Kaari>();
        T = new Painotettuverkko();

        for (Solmu v : G.palautaVerkko()) {
            for (Kaari e : v.palautaKaaret()) {
                kaikkikaaret.add(e);
            }
        }

        T = kokoaVerkko(kaikkikaaret, G);
    }

    public Painotettuverkko kokoaVerkko(PriorityQueue<Kaari> kaaret, Painotettuverkko X) {
        Kaari kaari = kaaret.poll();
        Solmu tama = kaari.Solmu1();
        Solmu naapuri = kaari.Solmu2();
        while (kaaret.isEmpty()) {
            if (X.palautaVerkko().contains(tama) && X.palautaVerkko().contains(naapuri)) {
                kaari = kaaret.poll();
                tama = kaari.Solmu1();
                naapuri = kaari.Solmu2();
            } else if (!X.palautaVerkko().contains(tama) && X.palautaVerkko().contains(naapuri)) {
                tama.kaytyLapi();
                X.lisaaSolmu(tama.numero, naapuri.numero, kaari.paino);
            } else if (X.palautaVerkko().contains(tama) && !X.palautaVerkko().contains(naapuri)) {
                naapuri.kaytyLapi();
                X.lisaaSolmu(naapuri.numero, tama.numero, kaari.paino);
            } else {
                tama.kaytyLapi();
                naapuri.kaytyLapi();
                X.lisaaSolmu(naapuri.numero, tama.numero, kaari.paino);
            }
        }
        return X;
    }
    public Painotettuverkko palautaT() {
        return T;
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
