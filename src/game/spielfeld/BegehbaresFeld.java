package game.spielfeld;


import game.spielfiguren.Spielstueck;
import java.awt.Point;

public class BegehbaresFeld extends Feld {

	private Spielstueck besetzer;
	private boolean belegt;
	public boolean aufstellungsFeld;

	public BegehbaresFeld(Point position, int nr) {
		super(position, nr);
		if(nr >= 60)
			aufstellungsFeld = true;
	}

	public boolean istBelegt() {
		return belegt;
	}

	public void setBelegt(boolean b) {
		this.belegt = b;
	}

	public Spielstueck getBesetzer() {
		return besetzer;
	}

	public void setBesetzer(Spielstueck besetzer) {
		this.besetzer = besetzer;
	}
}