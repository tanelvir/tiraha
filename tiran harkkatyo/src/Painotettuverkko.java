/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.util.ArrayList;

public class Painotettuverkko {
    
    ArrayList<Solmu> solmut;
    
    public Painotettuverkko() {
        solmut = new ArrayList<Solmu>();
    }
    
    public ArrayList<Solmu> palautaVerkko() {
        return solmut;
    }
    
    public void lisaaSolmu(int solmu, int naapuri, int paino) {
        Solmu uusi;
        uusi = new Solmu(solmu);
        Solmu vierus;
        vierus = new Solmu(naapuri);
        uusi.lisaaKaari(vierus, paino);
        solmut.add(uusi);
    }
    
    @Override
    public String toString() {
        return "Verkko : " + solmut;
    }
}
