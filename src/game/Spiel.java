package game;


import game.gui.GUI;
import game.gui.SpielfigurLabel;
import game.spielfeld.BegehbaresFeld;
import game.spielfeld.Feld;
import game.spielfeld.Spieltafel;
import game.spielfiguren.Armee;
import game.spielfiguren.Armee.Aufklaerer;
import game.spielfiguren.Armee.Bombe;
import game.spielfiguren.Armee.Feldmarschall;
import game.spielfiguren.BeweglichesSpielstueck;
import game.spielfiguren.Spielstueck;
import game.spielfiguren.Armee.Mineur;
import game.spielfiguren.Armee.Spion;

import game.spielfiguren.SpielstueckTyp;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class Spiel {

  public Spieler[] mitspieler = new Spieler[2];
  public Spieler computer;
  public Spieltafel spielTafel;
  public static GUI gui;
  public SpielZustand spielZustand = SpielZustand.armeeAufbauen;
  public int zugZaehler = 1;

  public static void main(String[] args) {

    Spiel spiel = new Spiel();
    Armee a1 = new Armee(Color.RED);
    Armee a2 = new Armee(Color.BLUE);
    Spieler p1 = new Spieler(a1, spiel, "Rot");
    Spieler p2 = new Spieler(a2, spiel, "Blau");

    a1.setKontrolleur(p1);
    a2.setKontrolleur(p2);

    spiel.mitspieler[0] = p1;
    spiel.mitspieler[1] = p2;
    spiel.computer = spiel.mitspieler[1];
    spiel.spielTafel = new Spieltafel();
    gui = new GUI(spiel);

    spiel.erstelleComputerArmeeAufstellung(p2, spiel, a2, gui);

    gui.update();


  }

  /**
   * Platziert ein Spielstück auf ein freies feld
   */
  public void spielstueckAufFreiesFeldBewegen(Spielstueck s, BegehbaresFeld zielFeld) {
    if (!zielFeld.istBelegt()) {
      BegehbaresFeld vonFeld = s.getPosition();

      vonFeld.setBesetzer(null);
      vonFeld.setBelegt(false);

      s.setPosition(zielFeld);
      zielFeld.setBelegt(true);
      zielFeld.setBesetzer(s);
    }
  }

  public void computerSpielzugDurchfuehren() {


    try {
      Thread.sleep(750);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    ArrayList<Integer> numb = erstelleZufaelligeListe(0, computer.armee.spielstuecke.size());

    for (int i = 0; i < numb.size(); i++) {
      Spielstueck angreifer = computer.getArmee().spielstuecke.get(numb.get(0));
      numb.remove(0);

      if (angreifer instanceof BeweglichesSpielstueck) {
        gui.quelle = gui.getZellenLabel(angreifer.position.position);
        gui.spielstueckAufFeld = (BeweglichesSpielstueck) angreifer;
        ArrayList<BegehbaresFeld> bewegungsBereich = ermittleBewegungsBereich((BeweglichesSpielstueck) angreifer);
        if (bewegungsBereich.size() > 0) {
          int rndNr = (int) Math.abs((Math.random() * (bewegungsBereich.size()-1)));


          BegehbaresFeld zielFeld = bewegungsBereich.get(rndNr);

          gui.zugDurchfuehren((BeweglichesSpielstueck) angreifer, zielFeld.position);
          spielZustand = SpielZustand.spielerAmZug;
          gui.informationsFeld.append("Zug: " + ++zugZaehler + " beginnt!\n");
          break;
        }


      }


    }


  }


  public ArrayList<BegehbaresFeld> ermittleBewegungsBereich(BeweglichesSpielstueck spielstueck) {
    ArrayList<BegehbaresFeld> bewegungsFelder = new ArrayList<>();

    if (!(spielstueck instanceof Aufklaerer)) {
      // Kein Aufklärer
      if (pruefeEinfacheDirektionaleBewegung((int) spielstueck.getPosition().position.getX() - 1, (int) spielstueck.getPosition().position.getY(), spielstueck))
        bewegungsFelder.add((BegehbaresFeld) spielTafel.getFeld(new Point((int) spielstueck.getPosition().position.getX() - 1, (int) spielstueck.getPosition().position.getY())));
      if (pruefeEinfacheDirektionaleBewegung((int) spielstueck.getPosition().position.getX(), (int) spielstueck.getPosition().position.getY() - 1, spielstueck))
        bewegungsFelder.add((BegehbaresFeld) spielTafel.getFeld(new Point((int) spielstueck.getPosition().position.getX(), (int) spielstueck.getPosition().position.getY() - 1)));
      if (pruefeEinfacheDirektionaleBewegung((int) spielstueck.getPosition().position.getX(), (int) spielstueck.getPosition().position.getY() + 1, spielstueck))
        bewegungsFelder.add((BegehbaresFeld) spielTafel.getFeld(new Point((int) spielstueck.getPosition().position.getX(), (int) spielstueck.getPosition().position.getY() + 1)));
      if (pruefeEinfacheDirektionaleBewegung((int) spielstueck.getPosition().position.getX() + 1, (int) spielstueck.getPosition().position.getY(), spielstueck))
        bewegungsFelder.add((BegehbaresFeld) spielTafel.getFeld(new Point((int) spielstueck.getPosition().position.getX() + 1, (int) spielstueck.getPosition().position.getY())));

    } else {
      //Aufklärer
      int x = (int) spielstueck.getPosition().position.getX();
      int y = (int) spielstueck.getPosition().position.getY();
      Point pos = new Point(x, y - 1);
      //Norden
      while (true) {
        if (spielTafel.getBegehbaresFeld(pos) == null || (pos.y < y - 1 && spielTafel.getBegehbaresFeld(pos).istBelegt())
          || istSpielstueckVonArmeeAufFeld(spielstueck.besitzer.getArmee(), spielTafel.getFeld(pos))) {
          break;
        }
        bewegungsFelder.add(spielTafel.getBegehbaresFeld(pos));
        pos.y--;
      }

      //Osten
      pos.x = x + 1;
      pos.y = y;
      while (true) {
        if (spielTafel.getBegehbaresFeld(pos) == null || (pos.x > x + 1 && spielTafel.getBegehbaresFeld(pos).istBelegt())
          || istSpielstueckVonArmeeAufFeld(spielstueck.besitzer.getArmee(), spielTafel.getFeld(pos))) {
          break;
        }
        bewegungsFelder.add(spielTafel.getBegehbaresFeld(pos));
        pos.x++;
      }
      //Süden
      pos.x = x;
      pos.y = y + 1;
      while (true) {
        if (spielTafel.getBegehbaresFeld(pos) == null || (pos.y > y + 1 && spielTafel.getBegehbaresFeld(pos).istBelegt())
          || istSpielstueckVonArmeeAufFeld(spielstueck.besitzer.getArmee(), spielTafel.getFeld(pos))) {
          break;
        }
        bewegungsFelder.add(spielTafel.getBegehbaresFeld(pos));
        pos.y++;
      }
      //Westen
      pos.x = x - 1;
      pos.y = y;
      while (true) {
        if (spielTafel.getBegehbaresFeld(pos) == null || (pos.x < x - 1 && spielTafel.getBegehbaresFeld(pos).istBelegt())
          || istSpielstueckVonArmeeAufFeld(spielstueck.besitzer.getArmee(), spielTafel.getFeld(pos))) {
          break;
        }
        bewegungsFelder.add(spielTafel.getBegehbaresFeld(pos));
        pos.x--;
      }


    }


    return bewegungsFelder;
  }

  public boolean feldImBewegungsRaum(BegehbaresFeld f, BeweglichesSpielstueck s) {
    for (BegehbaresFeld bFeld : s.bewegungsRaum) {
      if (bFeld == f)
        return true;
    }
    return false;
  }


  public boolean pruefeEinfacheDirektionaleBewegung(int x, int y, BeweglichesSpielstueck s) {

    Feld feld = spielTafel.getFeld(new Point(x, y));
    if (feld instanceof BegehbaresFeld &&
      (!((BegehbaresFeld) feld).istBelegt() ||
        feldMitFeindlichemSpielstueckBelegt(s.besitzer, feld))) {
      return true;
    }
    return false;
  }

  private void erstelleZufallSpielstueck(SpielstueckTyp typ, ArrayList<Integer> numbers, Spieler p, GUI gui) {
    Color color = p.getArmee().getFarbe();
    for (int i = 0; i < typ.getMaxAmount(); i++) {
      BegehbaresFeld feld = null;
      int startK = color != Color.RED ? 0 : 5;
      sucheFeld:
      for (int k = startK; k < spielTafel.getSpielTafel().length; k++) {
        for (int j = 0; j < spielTafel.getSpielTafel()[k].length; j++) {
          if (spielTafel.getSpielTafel()[k][j].number == numbers.get(0)) {
            feld = (BegehbaresFeld) spielTafel.getFeld(new Point(k, j));
            numbers.remove(0);
            for (SpielfigurLabel label : SpielfigurLabel.alleSpielfigurenLabels) {
              if (color == Color.BLUE && label.typ == typ) {
                gui.zellen[k][j].setIcon(SpielfigurLabel.getIconFromType(typ,color), false, color);
                break;
              } else if (color == Color.RED && label.typ == typ) {
                gui.zellen[k][j].setIcon(SpielfigurLabel.getIconFromType(typ,color), true, color);
                break;
              }

            }
            break sucheFeld;
          }

        }
      }
      p.erstelleSpielstück(typ, feld, color);
    }

  }

  public boolean hatSpielerNochBeweglicheSpielstuecke(Spieler p){
    for(Spielstueck s : p.getArmee().spielstuecke){
      if(s instanceof BeweglichesSpielstueck)
        return true;
    }
    return false;
  }

  private void erstelleComputerArmeeAufstellung(Spieler p2, Spiel spiel, Armee a2, GUI gui) {
    ArrayList<Integer> numbers = erstelleZufaelligeListe(0, 40);
    for (SpielstueckTyp t : SpielstueckTyp.values()) {
      erstelleZufallSpielstueck(t, numbers, p2, gui);
    }

    for (Spielstueck stück : p2.getArmee().getSpielstuecke()) {
      //stück.setAufgedeckt(false);
    }


  }


  public void erstelleZufaelligeAufstellung() {
    ArrayList<Integer> numbers = erstelleZufaelligeListe(60, 100);
    Spieler p1 = mitspieler[0];

    for (int i = 0; i < p1.getArmee().getSpielstuecke().size(); i++) {
      p1.getArmee().löscheSpielstück(p1.getArmee().getSpielstuecke().remove(i));
      i--;
    }

    for (SpielstueckTyp t : SpielstueckTyp.values()) {
      erstelleZufallSpielstueck(t, numbers, p1, gui);
    }

  }


  public void starteSpiel() {
    gui.informationsFeld.append("Zug: " + zugZaehler + " beginnt!\n");
    spielZustand = SpielZustand.spielerAmZug;

  }


  private ArrayList<Integer> erstelleZufaelligeListe(int von, int bis) {
    ArrayList<Integer> numbers = new ArrayList<>();
    for (int i = von; i < bis; i++) {
      numbers.add(i);
    }
    Collections.shuffle(numbers);
    return numbers;
  }

  public Feld[][] getAufstellbarenBereich() {
    return spielTafel.getSpielTafel();
  }

  public Feld[][] hohleSpielfeld() {
    return spielTafel.getSpielTafel();
  }


  /**
   * Vertauscht die Position zweier Spielstücke
   */
  public void spielstueckeVetauschen(Spielstueck a, Spielstueck b) {
    BegehbaresFeld tmp = a.getPosition();
    a.position.setBesetzer(b);
    a.setPosition(b.position);
    b.position.setBesetzer(a);
    b.setPosition(tmp);
  }

  /**
   *
   */
  public boolean feldMitFeindlichemSpielstueckBelegt(Spieler aktuell, Feld feld) {

    for (Spieler p : mitspieler) {
      if (p != aktuell) {
        for (Spielstueck s : p.getArmee().getSpielstuecke()) {
          if (s.position.equals(feld))
            return true;
        }
      }
    }

    return false;
  }

  /**
   *
   */
  public boolean istSpielstueckVonArmeeAufFeld(Armee armee, Feld feld) {
    for (Spielstueck s : armee.getSpielstuecke()) {
      if (s.position.equals(feld))
        return true;
    }
    return false;
  }

  /**
   *
   */
  public Spielstueck findeSpielstueckAufFeld(Armee armee, Feld feld) {
    for (Spielstueck s : armee.getSpielstuecke()) {
      if (s.position.equals(feld))
        return s;
    }
    return null;
  }

  /**
   * Prüft welches Spielstück den Schlagabtausch gewinnt
   *
   * @return Gibt das Sieger Spielstück zurück, falls es keinen Sieger gibt wird null zurückgegeben
   */
  public Spielstueck ermittelGewinner(BeweglichesSpielstueck angreifer, Spielstueck verteidiger) {

    if (angreifer instanceof Spion && verteidiger instanceof Feldmarschall) {
      return angreifer;
    } else if (angreifer instanceof Mineur && verteidiger instanceof Bombe) {
      return angreifer;
    }
    if (verteidiger instanceof Bombe) {
      return null;
    }

    if (angreifer.getTyp().getWertigkeit() > verteidiger.getTyp().getWertigkeit()) {
      return angreifer;
    } else if (angreifer.getTyp().getWertigkeit() < verteidiger.getTyp().getWertigkeit()) {
      return verteidiger;
    } else
      return null;

  }

  public void feldAufraeumen(Feld f) {
    if (f instanceof BegehbaresFeld) {
      ((BegehbaresFeld) f).setBesetzer(null);
      ((BegehbaresFeld) f).setBelegt(false);
    }
  }
}