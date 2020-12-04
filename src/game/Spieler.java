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
	public ArrayList<BeweglichesSpielstueck> beweglichenSpielst�cke = new ArrayList<BeweglichesSpielstueck>() ;

	public SpielstueckTyp ausgew�hlterSpielstueckTyp;
	public Feld best�tigtesFeld;
	public Feld ausgew�hltesFeld;

	public String name;

	public Spieler(Armee armee, Spiel leitung, String name) {
		this.armee = armee;
		this.spielLeitung = leitung;
		this.name = name;
	}



	
	public void erstelleSpielst�ck(SpielstueckTyp typ, BegehbaresFeld feld, Color farbe) {


		switch(typ) {
			case Spion:
				armee.new Spion(feld, farbe, this, typ);
				break;
			case Aufkl�rer:
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

	public SpielstueckTyp spielst�ckTypAusw�hlen() {
		// TODO - implement Spieler.spielst�ckAufstellen
		throw new UnsupportedOperationException();
	}

	public BegehbaresFeld feldAusw�hlen() {
		// TODO - implement Spieler.spielst�ckAufstellen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param feld
	 */
	public boolean feldBelegt(BegehbaresFeld feld) {
		return feld.istBelegt();
	}

	public void zugDurchf�hren() {
		// TODO - implement Spieler.zugDurchf�hren
		throw new UnsupportedOperationException();
	}



	public BeweglichesSpielstueck beweglichesSpielst�ckAusw�hlen() {
		// TODO - implement Spieler.beweglichesSpielst�ckAusw�hlen
		throw new UnsupportedOperationException();
	}

	public Feld zielFeldAusw�hlen() {
		// TODO - implement Spieler.zielFeldAusw�hlen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param spielst�ck
	 */
	public boolean beweglichesSpielst�ckKannFeldErreichen(BeweglichesSpielstueck spielst�ck) {
		// TODO - implement Spieler.beweglichesSpielst�ckKannFeldErreichen
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param feld
	 */
	public boolean feldNichtMitEigenemSpielst�ckBelegt(Feld feld) {
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