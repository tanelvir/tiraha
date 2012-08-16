
import java.util.ArrayList;
import java.util.Stack;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Tarkastaa onko verkko yhtenäinen. Otettu tietorakenteet 2012-kurssilta pajan mallivastauksista. 
 * Ei minun tekemä.
 * 
 * Tarkistetaan leveyssuuntaisella läpikäynnillä onko  verkko yhtenäinen.
 *
 *
 * 
 */
public class Tarkastaja {
 
    public static boolean Tarkastaja(boolean[][] verkko) {
        // Toteuta minut
        boolean[] kayty = new boolean[verkko.length];
        dfs(verkko, kayty, 0);
 
        // Verkko on yhtenäinen joss dfs kävi kaikissa solmuissa. Siispä palautetaan false jos jossain solmussa ei käyty.
        for (boolean b : kayty) {
            if (!b)
                return false;
        }
        return true;
    }
 
    static void dfs(boolean[][] verkko, boolean[] kayty, int solmu) {
        boolean[] rivi = verkko[solmu];
        kayty[solmu] = true;
 
        for (int i = 0; i < rivi.length; i++) {
            if (rivi[i] && !kayty[i]) {
                dfs(verkko, kayty, i);
            }
        }
    }
 
    static boolean[][] esim1 = new boolean[][]
    {
        {false, true,  true,  true,  false},
        {true,  false, true,  true,  true},
        {true,  true,  false, false, false},
        {true,  true,  false, false, true},
        {false, true,  false, true,  false}
    };
 
    static boolean[][] esim2 = new boolean[][]
    {
        {false, false, true,  false, false},
        {false, false, false, true,  true},
        {true,  false, false, false, false},
        {false, true,  false, false, true},
        {false, true,  false, true,  false}
    };
 
    public static void main(String[] args) {
        System.out.println(Tarkastaja(esim1));
        System.out.println(Tarkastaja(esim2));
    }
    
    
    /*public static void main(String[] args) {
        Painotettuverkko verkko = new Painotettuverkko();
        verkko.lisaaSolmu(1, 2, 1);
        verkko.palautaVerkko().get(0).lisaaKaari(3, 3);
        verkko.lisaaSolmu(2, 5, 4);
        verkko.palautaVerkko().get(1).lisaaKaari(6, 5);
        verkko.palautaVerkko().get(1).lisaaKaari(1, 1);
        verkko.lisaaSolmu(3, 1, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(6, 1);
        verkko.palautaVerkko().get(2).lisaaKaari(7, 3);
        verkko.palautaVerkko().get(2).lisaaKaari(4, 2);
        verkko.lisaaSolmu(4, 3, 2);
        verkko.palautaVerkko().get(3).lisaaKaari(7, 5);
        verkko.lisaaSolmu(5, 2, 4);
        verkko.palautaVerkko().get(4).lisaaKaari(6, 1);
        verkko.lisaaSolmu(6, 5, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(2, 5);
        verkko.palautaVerkko().get(5).lisaaKaari(3, 1);
        verkko.palautaVerkko().get(5).lisaaKaari(7, 4);
        verkko.lisaaSolmu(7, 6, 4);
        verkko.palautaVerkko().get(6).lisaaKaari(3, 3);
        verkko.palautaVerkko().get(6).lisaaKaari(4, 5);
        //Tarkastaja tarkastaja = new Tarkastaja(verkko);
        
    }*/
}
