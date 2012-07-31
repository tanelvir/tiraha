
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;


public class Kruskal {
    
    ArrayList<Solmu> lapikaydyt;
    TreeSet<Kaari> virittavapuu;
    PriorityQueue<Kaari> kaaret;
    
    public Kruskal(Painotettuverkko G) {
        ArrayList<Solmu> lapikaydyt = new ArrayList<Solmu>();
        TreeSet<Kaari> virittavapuu = new TreeSet<Kaari>();
        PriorityQueue<Kaari> kaaret = new PriorityQueue<Kaari>();
        
        kaaret = taytaKaaret(G);
        
        while (lapikaydyt.size() != G.palautaVerkko().size()) {
            etsiPieninkaari(kaaret);
            this.kaaret = kaaret;
        }
        
    }
    
    public PriorityQueue taytaKaaret(Painotettuverkko G) {
        int koko = 0;
        ArrayList<Kaari> kaarilista = new ArrayList<Kaari>();
        koko = G.palautaVerkko().size();
        PriorityQueue<Kaari> listakaarista = new PriorityQueue<Kaari>();
        for (int i = 0; i < koko; i++) {
            kaarilista = G.palautaVerkko().get(i).palautaKaaret();
            for (int j = 0; j < kaarilista.size(); j++) {
                listakaarista.add(kaarilista.get(j));
            }
        }
        return listakaarista;
    }
    
    public PriorityQueue etsiPieninkaari(PriorityQueue<Kaari> lista) {
         Kaari pieninkaari; 
         pieninkaari = lista.poll();
         while (lapikaydyt.contains(pieninkaari.solmu1) && lapikaydyt.contains(pieninkaari.solmu2)) {
             pieninkaari = lista.poll();
         }
         virittavapuu.add(pieninkaari);
         lapikaydyt.add(pieninkaari.solmu1);
         lapikaydyt.add(pieninkaari.solmu2);
         return lista;
    }
    
    public TreeSet<Kaari> palautaVirittavapuu() {
        return this.virittavapuu;
    }
}
