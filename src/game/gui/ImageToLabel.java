package game.gui;

import game.SpielSeite;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

public class ImageToLabel extends JLabel {

    public static int iconSize = 50;

    SpielSeite seite;
    public Icon icon;
    public ImageToLabel(String image, SpielSeite seite) {
        super();

        this.seite = seite;
        // set up the image
        ImageIcon imageIcon = new ImageIcon(image);
        this.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.setIcon(imageIcon);
        Image img = imageIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(img);


    }
}
