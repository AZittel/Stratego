package game.gui;


import game.SpielSeite;
import game.spielfiguren.SpielstueckTyp;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JTextField;

public class SpielfigurLabel extends ImageToLabel {

  public static List<SpielfigurLabel> alleSpielfigurenLabels = new ArrayList<>();
  public SpielstueckTyp typ;
  public JTextField txtAmount = new JTextField("");


  public SpielfigurLabel(String image, SpielSeite seite, SpielstueckTyp typ) {
    super(image, seite);
    this.typ = typ;
    txtAmount.setEditable(false);
    alleSpielfigurenLabels.add(this);
    this.setToolTipText(String.valueOf(typ));
    txtAmount.setPreferredSize(new Dimension(40, 20));


  }

  public static Icon getIconFromType(SpielstueckTyp typ, Color color) {
    for (SpielfigurLabel label : alleSpielfigurenLabels) {
      if (label.typ.equals(typ) && label instanceof RotSpielfigurLabel && color.equals(Color.RED)) {
        return label.icon;
      } else if (label.typ.equals(typ) && label instanceof BlauSpielfigurLabel&& color.equals(Color.BLUE)) {
        return label.icon;
      }
    }
    return null;
  }
}
