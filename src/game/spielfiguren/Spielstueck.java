package game.spielfiguren;

import game.Spieler;
import game.spielfeld.BegehbaresFeld;
import java.awt.Color;

public abstract class Spielstueck {

	public Spieler besitzer;

	private SpielstueckTyp typ;
	private  Color farbe;

	public BegehbaresFeld position;
	private boolean aufgedeckt;

	protected Spielstueck(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
		this.aufgedeckt = true;
		this.position = position;
		this.farbe = farbe;
		this.besitzer = besitzer;
		this.typ = typ;
		besitzer.getArmee().spielstuecke.add(this);
		besitzer.aufgestellteSpielstuecke.add(this);

		position.setBesetzer(this);
		position.setBelegt(true);
	}
	
	/**
	 * 
	 * @param aufgedeckt
	 */
	public void setAufgedeckt(boolean aufgedeckt) {
		this.aufgedeckt = aufgedeckt;
	}

	
	public SpielstueckTyp getTyp() {
		return typ;
	}

	public BegehbaresFeld getPosition() {
		return position;
	}

	public void setPosition(BegehbaresFeld position) {
		this.position = position;
	}

	public boolean getAufgedeckt() {
		return this.aufgedeckt;
	}


	public Color getFarbe() {
		return farbe;
	}
}