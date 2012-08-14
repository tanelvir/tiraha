
import java.util.HashSet;
import java.util.TreeSet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tanelvir
 */
public class Kaartenlisaaja {

    HashSet<Solmu> solmut = new HashSet<Solmu>();
    TreeSet<Kaari> kaaret = new TreeSet<Kaari>();

    public TreeSet<Kaari> getEdges()
    {
        return kaaret;
    }
    HashSet<Solmu> otaSolmut(Solmu solmu)
    {
        for (Solmu tunnettuSolmu : solmut) {
            if (tunnettuSolmu.contains(solmu)) {
                return tunnettuSolmu;
            }
        }
        return null;
    }
    public void kaarenLisays(Kaari kaari)
    {
        Solmu solmu1 = kaari.solmu1;
        Solmu solmu2 = kaari.solmu2;

        HashSet<Solmu> ykkössolmmut = otaSolmut(solmu1);
        HashSet<Solmu> kakkossolmut = otaSolmut(solmu2);

        if (ykkössolmmut == null) {
            kaaret.add(kaari);
            if (kakkossolmut == null) {
                HashSet<Solmu> uudetSolmut = new HashSet<Solmu>();
                uudetSolmut.add(solmu1);
                uudetSolmut.add(solmu2);
                solmut.add(uudetSolmut);
            }
            else {
                kakkossolmut.add(solmu1);           
            }
        }
        else {
            if (kakkossolmut == null) {
                ykkössolmmut.add(solmu2);
                kaaret.add(kaari);
            }
            else if (ykkössolmmut != kakkossolmut) {
                ykkössolmmut.addAll(kakkossolmut);
                solmut.remove(kakkossolmut);
                kaaret.add(kaari);
            }
        }
    }
}
