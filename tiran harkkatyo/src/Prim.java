
import java.util.PriorityQueue;

public class Prim {

    public Prim(Painotettuverkko G, Solmu r) {

        Painotettuverkko T;
        PriorityQueue<Kaari> kaaret;
        T = nollaaKaaret(G);
        int index = G.palautaVerkko().indexOf(r);
        Kaari pieninkaari = r.palautaKaaret().poll();
        T.palautaVerkko().get(index).lisaaKaari(pieninkaari.paino, pieninkaari.Solmu2());
        T.palautaVerkko().get(index).kaytyLapi();

    }

    public Painotettuverkko nollaaKaaret(Painotettuverkko G) {
        Painotettuverkko T;
        T = G;
        for (Solmu v : T.palautaVerkko()) {
            v.nollaaKaaret();
        }
        return T;
    }
}