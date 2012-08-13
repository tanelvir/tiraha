/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Taneli
 */
import java.util.ArrayList;

/**
 * Painotettu verkko koostuu V määrästä solmuja ja solmut tuntevat kaarensa.
 * Yhdessä ne muodostavat verkon jossa kaarilla on painot ja kahden solmun
 * välillä on vain 1 kaari.
 *
 */
public class Painotettuverkko implements Cloneable {

    ArrayList<Solmu> solmut; //Solmut puu-rakenteen muodossa.

    public Painotettuverkko() {
        solmut = new ArrayList<Solmu>();
    }

    public ArrayList<Solmu> palautaVerkko() {
        return solmut;
    }
    
    public Object clone(Painotettuverkko G)
      {
          try
      {
              this.solmut = G.palautaVerkko().clone();
          }
      catch( CloneNotSupportedException e )
      {
              return null;
          }
      } 

    /**
     *
     * Lisätään solmu, jolla on oltava naapuri ja niiden välillä kaaren paino.
     * Vain itse solmu lisätään.
     *
     *
     * @param solmu
     * @param naapuri
     * @param paino
     */
    public void lisaaSolmu(int solmu, int naapuri, int paino) {
        Solmu uusi;
        Solmu vierus;
        uusi = new Solmu(solmu);
        uusi.lisaaKaari(naapuri, paino);
        solmut.add(uusi);
    }

    /**
     * Tulostetaan itse verkko jossakin muodossa.
     *
     * @return solmut
     */
    @Override
    public String toString() {
        return "Verkko : " + solmut;
    }
}
