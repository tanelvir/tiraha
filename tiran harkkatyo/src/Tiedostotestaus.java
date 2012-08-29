
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Tämän luokan tarkoituksena on testata tiedoston lukemista Scannerin avulla. Tiedosto on muotoa .txt ja 
 * se muuntaa kirjaimet ASCII-arvoiksi.
 *
 * @author Taneli
 */
public class Tiedostotestaus {

    Painotettuverkko verkko;

    /**
     * Syötetään tiedoston nimi.
     *
     * @param nimi
     * @throws FileNotFoundException
     */
    public Tiedostotestaus(String nimi) throws FileNotFoundException {
        lueTiedosto(nimi);
    }
    
    /**
     * 
     * @param tiedosto
     * @throws FileNotFoundException 
     */
    
    public Tiedostotestaus(File tiedosto) throws FileNotFoundException {
        lueTiedosto(tiedosto);
    }

    /**
     * Luetaan tiedosto while file.hasNextLine avulla. Kirjaimet luetaan tiedoston tiedystä kohdasta.
     *
     * @param nimi
     * @throws FileNotFoundException
     */
    public void lueTiedosto(String nimi) throws FileNotFoundException {
        verkko = new Painotettuverkko();
        Scanner file = new Scanner(new File(nimi));
        while (file.hasNextLine()) {
            String actor = file.nextLine();
            if ((actor.charAt(0)-96)==1) {
                verkko.lisaaSolmu(actor.charAt(2) - 96);
            }
            else if ((actor.charAt(0)-96)==2) {
                verkko.lisaaKaari(actor.charAt(2) - 96, actor.charAt(4) - 96, actor.charAt(6) - 96);
            }
            else if ((actor.charAt(0)-96)==3) {
                Solmu v = new Solmu(actor.charAt(2) - 96, actor.charAt(4) - 96, actor.charAt(6) - 96);
                verkko.lisaaSolmu(v);
            }
            
        }
    }
    
    /**
     * Luetaan tiedosto while file.hasNextLine avulla. Eri parametrit vaan.
     * 
     * @param tiedosto
     * @throws FileNotFoundException 
     */
    
    public void lueTiedosto(File tiedosto) throws FileNotFoundException {
        verkko = new Painotettuverkko();
        Scanner file = new Scanner(tiedosto);
        while (file.hasNextLine()) {
            String actor = file.nextLine();
            if ((actor.charAt(0)-96)==1) {
                verkko.lisaaSolmu(actor.charAt(2) - 96);
            }
            else if ((actor.charAt(0)-96)==2) {
                verkko.lisaaKaari(actor.charAt(2) - 96, actor.charAt(4) - 96, actor.charAt(6) - 96);
            }
            else if ((actor.charAt(0)-96)==3) {
                Solmu v = new Solmu(actor.charAt(2) - 96, actor.charAt(4) - 96, actor.charAt(6) - 96);
                verkko.lisaaSolmu(v);
            }
            
        }
    }

    @Override
    public String toString() {
        return "" + verkko.palautaKaikkikaaret();
    }
    
    public Painotettuverkko returnVerkko() {
        return verkko;
    }

    public static void main(String[] args) {
        try {
            Tiedostotestaus testi = new Tiedostotestaus("src/verkot/testi.txt");
            System.out.println(testi.returnVerkko());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
