package game.gui;

import game.SpielZustand;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class CenterJPanel extends JPanel {

  Image background = Toolkit.getDefaultToolkit().createImage("strategoKarte.jpg");

  GUI gui;

  public CenterJPanel(GUI gui) {
    this.gui = gui;
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    g.drawImage(background, getComponent(0).getX(), getComponent(0).getY(), null);

    if (gui.spiel.spielZustand == SpielZustand.armeeAufbauen) {
      g.setColor(new Color(128, 128, 128, 200));
      for (Component c : this.getComponents()) {
        if (c instanceof ZellenLabel && ((ZellenLabel) c).nr < 60) {
          g.fillRect(c.getX(), c.getY(), c.getWidth(), c.getHeight());
        }

      }
    } else if (gui.spiel.spielZustand == SpielZustand.spielerAmZug) {

    }

  }
}