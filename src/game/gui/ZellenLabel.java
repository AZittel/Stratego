package game.gui;

import game.spielfeld.Feld;
import game.spielfiguren.SpielstueckTyp;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class ZellenLabel extends JLabel {

  public Point pos;
  public int nr;
  public Feld feld;
  public SpielstueckTyp typ;
  //   public static ImageIcon verdecktIconRot = new ImageIcon("roteArmee/verdeckt.png");
  public static ImageIcon verdecktIconRot;
  //   public static ImageIcon verdecktIconBlau = new ImageIcon("blaueArmee/verdeckt.png");
  public static ImageIcon verdecktIconBlau;
  public static ArrayList<ZellenLabel> alleZellen = new ArrayList<>();


  public ZellenLabel(Point pos, int nr, Feld feld) {


    this.pos = pos;
    this.nr = nr;
    this.feld = feld;
    this.setTransferHandler(new TransferHandler("icon"));
    alleZellen.add(this);

    ImageIcon imageIcon = new ImageIcon("roteArmee/verdeckt.png");
    Image img = imageIcon.getImage().getScaledInstance(ImageToLabel.iconSize, ImageToLabel.iconSize, Image.SCALE_SMOOTH);
    verdecktIconRot = new ImageIcon(img);

    imageIcon = new ImageIcon("blaueArmee/verdeckt.png");
    img = imageIcon.getImage().getScaledInstance(ImageToLabel.iconSize, ImageToLabel.iconSize, Image.SCALE_SMOOTH);
    verdecktIconBlau = new ImageIcon(img);


  }


  public void setIcon(Icon i, boolean aufgedeckt, Color farbe) {
    if (!aufgedeckt && farbe == Color.RED) {
      super.setIcon(verdecktIconRot);
    } else if (!aufgedeckt && farbe == Color.BLUE) {
      super.setIcon(verdecktIconBlau);
    } else
      setIcon(i);
  }
}
