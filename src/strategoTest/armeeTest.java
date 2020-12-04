package strategoTest;


import static org.junit.jupiter.api.Assertions.assertTrue;

import game.Spiel;
import game.Spieler;
import game.spielfeld.BegehbaresFeld;
import game.spielfiguren.Armee;
import game.spielfiguren.SpielstueckTyp;
import java.awt.Color;
import java.awt.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class armeeTest {

    Spieler p1;
    Armee armee;
    Spiel spiel;

    @BeforeEach
    public void buildUp(){
        spiel = new Spiel();
        armee = new Armee(Color.RED);
        p1 = new Spieler(armee, spiel, "Rot");

    }


    @Test
    public void testCountArmee(){

        p1.erstelleSpielstück(SpielstueckTyp.Fahne, new BegehbaresFeld(new Point(0,0), 0), Color.RED);
        p1.erstelleSpielstück(SpielstueckTyp.Fahne, new BegehbaresFeld(new Point(0,0), 0), Color.RED);
        p1.erstelleSpielstück(SpielstueckTyp.Fahne, new BegehbaresFeld(new Point(0,0), 0), Color.RED);
        p1.erstelleSpielstück(SpielstueckTyp.Fahne, new BegehbaresFeld(new Point(0,0), 0), Color.RED);
        p1.erstelleSpielstück(SpielstueckTyp.General, new BegehbaresFeld(new Point(0,0), 0), Color.RED);
        p1.erstelleSpielstück(SpielstueckTyp.Major, new BegehbaresFeld(new Point(0,0), 0), Color.RED);

        int actual = armee.getAnzahlSpielstückInArmee(SpielstueckTyp.Fahne);
        actual +=  armee.getAnzahlSpielstückInArmee(SpielstueckTyp.General);
        actual += armee.getAnzahlSpielstückInArmee(SpielstueckTyp.Major);


        assertTrue(actual == 6);


    }

}
