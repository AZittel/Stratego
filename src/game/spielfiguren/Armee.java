package game.spielfiguren;

import game.Spieler;
import game.spielfeld.BegehbaresFeld;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Armee {



	Spieler kontrolleur;
	private Color farbe;
	public ArrayList<Spielstueck> spielstuecke = new ArrayList<>();
	BeweglichesSpielstueck[] beweglicheSpielstücke;
	UnbeweglichesSpielstueck[] unbeweglicheSpielstücke;


	public Armee(Color c){
		this.farbe = c;
	}

	public void löscheSpielstück(Spielstueck s){

		s.position.setBelegt(false);
		s.position.setBesetzer(null);
		spielstuecke.remove(s);
	}


	/**
	 *
	 */
	public boolean spielstückNochAufzustellen(SpielstueckTyp typ) {
		return anzahlVonSpielstückTypMaximumErreicht(typ);
	}

	/**
	 *
	 */
	public boolean anzahlVonSpielstückTypMaximumErreicht(SpielstueckTyp typ) {
		 if(getAnzahlSpielstückInArmee(typ) < typ.getMaxAmount())
			return true;
		else
			return false;

	}

	public int getAnzahlSpielstückInArmee(SpielstueckTyp typ){
		int count = 0;
		for (Spielstueck s : spielstuecke) {
			if (s.getTyp().toString() == typ.toString())
				count++;
		}

		return count;
	}

	public Spielstueck getSpielstückVonSpieltafel(Point position){
		BegehbaresFeld feld = (BegehbaresFeld)kontrolleur.spielLeitung.spielTafel.getFeld(position);
		if(feld.istBelegt()){
			for(Spielstueck figur : spielstuecke){
				if(figur.position.position.equals(position)){
					return figur;
				}
			}
		}

		return null;


	}

	public void entferneSpielstück(Spielstueck s){
		spielstuecke.remove(s);
	}

	public ArrayList<Spielstueck> getSpielstuecke() {
		return spielstuecke;
	}

	public Color getFarbe() {
		return farbe;
	}



	

	public class Spion extends BeweglichesSpielstueck {
		public Spion(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer, typ);
		}
	}
	
	

	public class Leutnant extends BeweglichesSpielstueck {

		public Leutnant(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer, typ);
			// TODO Auto-generated constructor stub
		}
		
	}


	public class Unteroffizier extends BeweglichesSpielstueck {

		public Unteroffizier(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer, typ);
			// TODO Auto-generated constructor stub
		}
		
	}


	public class Major extends BeweglichesSpielstueck {

		public Major(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}

	}


	public class General extends BeweglichesSpielstueck {

		public General(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
		
	}


	public class Oberst extends BeweglichesSpielstueck {

		public Oberst(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
	}


	public class Bombe extends UnbeweglichesSpielstueck {

		public Bombe(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
	}


	public class Aufklaerer extends BeweglichesSpielstueck {

		public Aufklaerer(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
	}


	public class Mineur extends BeweglichesSpielstueck {

		public Mineur(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
	}


	public class Fahne extends UnbeweglichesSpielstueck {

		public Fahne(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
	}


	public class Feldmarschall extends BeweglichesSpielstueck {

		public Feldmarschall(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
	}


	public class Hauptmann extends BeweglichesSpielstueck {

		public Hauptmann(BegehbaresFeld position, Color farbe, Spieler besitzer, SpielstueckTyp typ) {
			super(position, farbe, besitzer,typ);
			// TODO Auto-generated constructor stub
		}
	}
	public Spieler getKontrolleur() {
		return kontrolleur;
	}

	public void setKontrolleur(Spieler kontrolleur) {
		this.kontrolleur = kontrolleur;
	}
}