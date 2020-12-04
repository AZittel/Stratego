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
   Hier wird der tatsächliche Drop vorbereitet
      */
  @Override
  public boolean importData(TransferHandler.TransferSupport support) {


    if (support.getComponent() instanceof ZellenLabel) {
      ZellenLabel zielLabel = (ZellenLabel) support.getComponent();
      p1.bestätigtesFeld = spiel.spielTafel
        .getFeld(zielLabel.pos);
      if (spiel.spielZustand == SpielZustand.armeeAufbauen) {

//						if(p1.bestätigtesFeld instanceof BegehbaresFeld && !((BegehbaresFeld) p1.bestätigtesFeld).istBelegt() && quelle == null){


        if (ausPaletteGezogen()) {
          gui.informationsFeld.append(p1.ausgewählterSpielstueckTyp + " wurde erstellt!\n");
          System.out.println("erstelle spielstück aus : " + p1.ausgewählterSpielstueckTyp);
          p1.erstelleSpielstück(p1.ausgewählterSpielstueckTyp,
            (BegehbaresFeld) p1.bestätigtesFeld, p1.getArmee().getFarbe());
          gui.quelle = null;
          gui.checkStartButton();
          gui.update();
          return super.importData(support);
        } else {

          //Spielstück auf Spieltafel versetzen


//          System.out.println("Ziel Icon: " + gui.getZellenLabel(zielLabel.pos).getIcon());
//          System.out.println("Quelle Icon: " + gui.getZellenLabel(gui.quelle.pos).getIcon());
          Spielstueck bewegtesSpielstueck = p1.getArmee().getSpielstückVonSpieltafel(gui.quelle.pos);
          boolean zielFeldIstBelegt = (p1.bestätigtesFeld instanceof BegehbaresFeld && ((BegehbaresFeld) p1.bestätigtesFeld).istBelegt());

          //Zielfeld nicht belegt
          if (!zielFeldIstBelegt) {
            gui.versetzeSpielstueckAufFreieZelle(bewegtesSpielstueck, zielLabel);
          } else
          // Zielfeld mit eigenem Spielstück belegt -> vertauschen
          {
            Spielstueck zweitesSpielstueck = p1.getArmee().getSpielstückVonSpieltafel(zielLabel.pos);
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
  Hier wird überprüft ob Drag & Drop auf dem Ziel Component erlaubt ist
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
      p1.bestätigtesFeld = spiel.spielTafel
        .getFeld(zielLabel.pos);

      boolean zielFeldNichtBegehbar = p1.bestätigtesFeld instanceof NichtBegehbaresFeld;
      boolean zielFeldIstBelegt = (p1.bestätigtesFeld instanceof BegehbaresFeld && ((BegehbaresFeld) p1.bestätigtesFeld).istBelegt());

      if (spiel.spielZustand == SpielZustand.armeeAufbauen) {
        boolean zielFeldNichtImAufstellungsBereich = p1.bestätigtesFeld.number < 60;
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
