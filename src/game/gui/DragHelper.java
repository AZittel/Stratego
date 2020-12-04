package game.gui;

import game.Spiel;
import game.SpielZustand;
import game.Spieler;
import game.spielfeld.BegehbaresFeld;
import game.spielfeld.NichtBegehbaresFeld;
import game.spielfiguren.BeweglichesSpielstueck;
import game.spielfiguren.Spielstueck;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class DragHelper extends TransferHandler {


  Spiel spiel;
  GUI gui;
  Spieler p1, p2;


  public DragHelper(GUI gui, Spiel spiel) {
    super("icon");
    this.gui = gui;
    this.spiel = spiel;
    p1 = spiel.mitspieler[0];
    p2 = spiel.mitspieler[1];
  }

  public boolean ausPaletteGezogen() {
    return gui.quelle == null;
  }

  /*
   Hier wird der tats�chliche Drop vorbereitet
      */
  @Override
  public boolean importData(TransferHandler.TransferSupport support) {


    if (support.getComponent() instanceof ZellenLabel) {
      ZellenLabel zielLabel = (ZellenLabel) support.getComponent();
      p1.best�tigtesFeld = spiel.spielTafel
        .getFeld(zielLabel.pos);
      if (spiel.spielZustand == SpielZustand.armeeAufbauen) {

//						if(p1.best�tigtesFeld instanceof BegehbaresFeld && !((BegehbaresFeld) p1.best�tigtesFeld).istBelegt() && quelle == null){


        if (ausPaletteGezogen()) {
          gui.informationsFeld.append(p1.ausgew�hlterSpielstueckTyp + " wurde erstellt!\n");
          System.out.println("erstelle spielst�ck aus : " + p1.ausgew�hlterSpielstueckTyp);
          p1.erstelleSpielst�ck(p1.ausgew�hlterSpielstueckTyp,
            (BegehbaresFeld) p1.best�tigtesFeld, p1.getArmee().getFarbe());
          gui.quelle = null;
          gui.checkStartButton();
          gui.update();
          return super.importData(support);
        } else {

          //Spielst�ck auf Spieltafel versetzen


//          System.out.println("Ziel Icon: " + gui.getZellenLabel(zielLabel.pos).getIcon());
//          System.out.println("Quelle Icon: " + gui.getZellenLabel(gui.quelle.pos).getIcon());
          Spielstueck bewegtesSpielstueck = p1.getArmee().getSpielst�ckVonSpieltafel(gui.quelle.pos);
          boolean zielFeldIstBelegt = (p1.best�tigtesFeld instanceof BegehbaresFeld && ((BegehbaresFeld) p1.best�tigtesFeld).istBelegt());

          //Zielfeld nicht belegt
          if (!zielFeldIstBelegt) {
            gui.versetzeSpielstueckAufFreieZelle(bewegtesSpielstueck, zielLabel);
          } else
          // Zielfeld mit eigenem Spielst�ck belegt -> vertauschen
          {
            Spielstueck zweitesSpielstueck = p1.getArmee().getSpielst�ckVonSpieltafel(zielLabel.pos);
            gui.vertauscheSpielstuecke(gui.quelle, zielLabel, bewegtesSpielstueck,
                zweitesSpielstueck);

          }
          gui.quelle = null;
          gui.update();
          return true;
        }

      } else if (spiel.spielZustand == SpielZustand.spielerAmZug) {
        BeweglichesSpielstueck angreifer = gui.spielstueckAufFeld;
        gui.zugDurchfuehren(angreifer, zielLabel.pos);
        spiel.spielZustand = SpielZustand.computerAmZug;
        spiel.computerSpielzugDurchfuehren();
      }
    }
    return true;
  }

  /*
  Hier wird �berpr�ft ob Drag & Drop auf dem Ziel Component erlaubt ist
   */
  @Override
  public boolean canImport(TransferHandler.TransferSupport support) {

    JComponent targetComp = (JComponent) support.getComponent();

    if (targetComp instanceof BlauSpielfigurLabel) {
      return false;
    } else if (targetComp instanceof RotSpielfigurLabel) {
      return false;
    }

    if (targetComp instanceof ZellenLabel) {
      ZellenLabel zielLabel = (ZellenLabel) targetComp;
      p1.best�tigtesFeld = spiel.spielTafel
        .getFeld(zielLabel.pos);

      boolean zielFeldNichtBegehbar = p1.best�tigtesFeld instanceof NichtBegehbaresFeld;
      boolean zielFeldIstBelegt = (p1.best�tigtesFeld instanceof BegehbaresFeld && ((BegehbaresFeld) p1.best�tigtesFeld).istBelegt());

      if (spiel.spielZustand == SpielZustand.armeeAufbauen) {
        boolean zielFeldNichtImAufstellungsBereich = p1.best�tigtesFeld.number < 60;
        if (zielFeldNichtBegehbar || zielFeldNichtImAufstellungsBereich || zielLabel == gui.quelle || (zielFeldIstBelegt && ausPaletteGezogen())) {
          return false;
        }

      } else if (spiel.spielZustand == SpielZustand.spielerAmZug) {

        return zielLabel.feld instanceof BegehbaresFeld && spiel.feldImBewegungsRaum((BegehbaresFeld) zielLabel.feld, gui.spielstueckAufFeld);
      }

      return true;
    }


    return false;
  }


}
