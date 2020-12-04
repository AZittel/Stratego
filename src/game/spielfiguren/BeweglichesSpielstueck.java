package game.spielfiguren;

import game.Spieler;
import game.spielfeld.BegehbaresFeld;
import java.awt.Color;
import java.util.ArrayList;

public class BeweglichesSpielstueck extends Spielstueck {

	public ArrayList<BegehbaresFeld> bewegungsRaum = new ArrayList<>();

	protected BeweglichesSpielstueck(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
		super(position, farbe, besitzer, typ);
		besitzer.beweglichenSpielstücke.add(this);
	}



}