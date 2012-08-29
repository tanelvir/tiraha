
import java.util.ArrayList;
import java.util.Stack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Tarkastaa onko verkko yhtenäinen. Otettu tietorakenteet 2012-kurssilta pajan mallivastauksista. 
 * EI OLE MINUN TEKEMÄ!
 * 
 * Tarkistetaan leveyssuuntaisella läpikäynnillä onko  verkko yhtenäinen.
 *
 *
 * 
 */
public class Tarkastaja {
    
    public Tarkastaja() {
    }
 
    public boolean syotaVerkko(boolean[][] verkko) {
        boolean[] kayty = new boolean[verkko.length];
        dfs(verkko, kayty, 0);
 
        // Verkko on yhtenäinen joss dfs kävi kaikissa solmuissa. Siispä palautetaan false jos jossain solmussa ei käyty.
        for (boolean b : kayty) {
            if (!b)
                return false;
        }
        return true;
    }
 
    void dfs(boolean[][] verkko, boolean[] kayty, int solmu) {
        boolean[] rivi = verkko[solmu];
        kayty[solmu] = true;
 
        for (int i = 0; i < rivi.length; i++) {
            if (rivi[i] && !kayty[i]) {
                dfs(verkko, kayty, i);
            }
        }
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
        Tarkastaja tarkastaja = new Tarkastaja();
        System.out.println(tarkastaja.syotaVerkko(verkko.palautaVieruslista()));
        System.out.println(tarkastaja.syotaVerkko(verkko.palautaVieruslista()));
        System.out.println(tarkastaja.syotaVerkko(verkko.palautaVieruslista()));
        System.out.println(tarkastaja.syotaVerkko(verkko.palautaVieruslista()));
        
        
    }
}
