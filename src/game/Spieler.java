package game;

import game.spielfeld.BegehbaresFeld;
import game.spielfeld.Feld;
import game.spielfiguren.Armee;
import game.spielfiguren.BeweglichesSpielstueck;
import game.spielfiguren.Spielstueck;
import game.spielfiguren.SpielstueckTyp;
import java.awt.Color;
import java.util.ArrayList;

public class Spieler{

	public Spiel spielLeitung;
	Armee armee;
	public ArrayList<Spielstueck> aufgestellteSpielstuecke = new ArrayList<Spielstueck>();
	public ArrayList<BeweglichesSpielstueck> beweglichenSpielstücke = new ArrayList<BeweglichesSpielstueck>() ;

	public SpielstueckTyp ausgewählterSpielstueckTyp;
	public Feld bestätigtesFeld;
	public Feld ausgewähltesFeld;

	public String name;

	public Spieler(Armee armee, Spiel leitung, String name) {
		this.armee = armee;
		this.spielLeitung = leitung;
		this.name = name;
	}



	
	public void erstelleSpielstück(SpielstueckTyp typ, BegehbaresFeld feld, Color farbe) {


		switch(typ) {
			case Spion:
				armee.new Spion(feld, farbe, this, typ);
				break;
			case Aufklärer:
				armee.new Aufklaerer(feld, farbe, this, typ);
				break;
			case Mineur:
				armee.new Mineur(feld, farbe, this, typ);
				break;
			case Unteroffizier:
				armee.new Unteroffizier(feld, farbe, this, typ);
				break;
			case Leutnant:
				armee.new Leutnant(feld, farbe, this, typ);
				break;
			case Hauptmann:
				armee.new Hauptmann(feld, farbe, this, typ);
				break;
			case Major:
				armee.new Major(feld, farbe, this, typ);
				break;
			case Oberst:
				armee.new Oberst(feld, farbe, this, typ);
				break;
			case General:
				armee.new General(feld, farbe, this, typ);
				break;
			case Feldmarschall:
				armee.new Feldmarschall(feld, farbe, this, typ);
				break;
			case Fahne:
				armee.new Fahne(feld, farbe, this, typ);
				break;
			case Bombe:
				armee.new Bombe(feld, farbe, this, typ);
				break;
		
		
		}


	}

	public SpielstueckTyp spielstückTypAuswählen() {
		// TODO - implement Spieler.spielstückAufstellen
		throw new UnsupportedOperationException();
	}

	public BegehbaresFeld feldAuswählen() {
		// TODO - implement Spieler.spielstückAufstellen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param feld
	 */
	public boolean feldBelegt(BegehbaresFeld feld) {
		return feld.istBelegt();
	}

	public void zugDurchführen() {
		// TODO - implement Spieler.zugDurchführen
		throw new UnsupportedOperationException();
	}



	public BeweglichesSpielstueck beweglichesSpielstückAuswählen() {
		// TODO - implement Spieler.beweglichesSpielstückAuswählen
		throw new UnsupportedOperationException();
	}

	public Feld zielFeldAuswählen() {
		// TODO - implement Spieler.zielFeldAuswählen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param spielstück
	 */
	public boolean beweglichesSpielstückKannFeldErreichen(BeweglichesSpielstueck spielstück) {
		// TODO - implement Spieler.beweglichesSpielstückKannFeldErreichen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param feld
	 */
	public boolean feldNichtMitEigenemSpielstückBelegt(Feld feld) {
		for(Spielstueck s : armee.getSpielstuecke()) {
			if(s.position.equals(feld))
				return false;
		}
		return true;
	}



	public Armee getArmee() {
		return this.armee;
	}

}