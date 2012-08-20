
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Tämän luokan tarkoituksena on testata tiedoston lukemista Scannerin avulla.
 *
 * @author Taneli
 */
public class Tiedostotestaus {

    Painotettuverkko verkko;

    /**
     * Syötetään tiedoston nimi.
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public Tiedostotestaus(String fileName) throws FileNotFoundException {
        lueTiedosto(fileName);
    }

    /**
     * Luetaan tiedosto while file.hasNextLine avulla.
     *
     * @param nimi
     * @throws FileNotFoundException
     */
    public void lueTiedosto(String nimi) throws FileNotFoundException {
        verkko = new Painotettuverkko();
        Scanner file = new Scanner(new File(nimi));
        while (file.hasNextLine()) {
            String actor = file.nextLine();
            Solmu v1 = new Solmu(actor.charAt(0) - 96, actor.charAt(2) - 96, actor.charAt(4) - 96);
            verkko.lisaaSolmu(v1);
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
            Tiedostotestaus testi = new Tiedostotestaus("src/testi.txt");
            System.out.println(testi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
