package game.spielfeld;

import game.Spiel;
import java.awt.Point;
import java.util.ArrayList;

public class Spieltafel {

  private Feld[][] spielTafel;
  public ArrayList<BegehbaresFeld> begehbareFelder = new ArrayList<>();
  Spiel spielLeitung;

  public Spieltafel() {

    //l  für land
    BegehbaresFeld l = new BegehbaresFeld(null, 0);

    //w für wasser
    NichtBegehbaresFeld w = new NichtBegehbaresFeld(null, 0);
    Feld[][] felder = {
      {l, l, l, l, l, l, l, l, l, l},
      {l, l, l, l, l, l, l, l, l, l},
      {l, l, l, l, l, l, l, l, l, l},
      {l, l, l, l, l, l, l, l, l, l},
      {l, l, w, w, l, l, w, w, l, l},
      {l, l, w, w, l, l, w, w, l, l},
      {l, l, l, l, l, l, l, l, l, l},
      {l, l, l, l, l, l, l, l, l, l},
      {l, l, l, l, l, l, l, l, l, l},
      {l, l, l, l, l, l, l, l, l, l}};

    this.spielTafel = felder;

    int k = 0;
    for (int i = 0; i < felder.length; i++) {
      for (int j = 0; j < felder[i].length; j++) {
        Feld f = felder[i][j];
        if (f instanceof BegehbaresFeld) {
          begehbareFelder.add((BegehbaresFeld) f);
          spielTafel[i][j] = new BegehbaresFeld(new Point(i, j), k);
        } else if (f instanceof NichtBegehbaresFeld) {
          spielTafel[i][j] = new NichtBegehbaresFeld(new Point(i, j), k);
        }
        k++;
      }

    }

  }


  public Feld[][] getSpielTafel() {
    return spielTafel;
  }


  public BegehbaresFeld getBegehbaresFeld(Point position) {
		if(getFeld(position) instanceof BegehbaresFeld)
		  return (BegehbaresFeld) getFeld(position);
		return null;
  }

  /**
   *
   */
  public Feld getFeld(Point position) throws IndexOutOfBoundsException {
    try {
      return spielTafel[position.x][position.y];
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }


}