package game.gui;

import game.Spiel;
import game.SpielSeite;
import game.SpielZustand;
import game.Spieler;
import game.spielfeld.BegehbaresFeld;
import game.spielfiguren.Armee.Aufklaerer;
import game.spielfiguren.Armee.Fahne;
import game.spielfiguren.BeweglichesSpielstueck;
import game.spielfiguren.Spielstueck;
import game.spielfiguren.SpielstueckTyp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;

public class GUI {

  public JFrame mainFrame;

  RotSpielfigurLabel aufklaerer = new RotSpielfigurLabel("roteArmee/aufklaerer.png", SpielSeite.ROT, SpielstueckTyp.Aufklärer);
  RotSpielfigurLabel bombe = new RotSpielfigurLabel("roteArmee/bombe.png", SpielSeite.ROT, SpielstueckTyp.Bombe);
  RotSpielfigurLabel fahne = new RotSpielfigurLabel("roteArmee/fahne.png", SpielSeite.ROT, SpielstueckTyp.Fahne);
  RotSpielfigurLabel feldmarschall = new RotSpielfigurLabel("roteArmee/feldmarschall.png", SpielSeite.ROT, SpielstueckTyp.Feldmarschall);
  RotSpielfigurLabel general = new RotSpielfigurLabel("roteArmee/general.png", SpielSeite.ROT, SpielstueckTyp.General);
  RotSpielfigurLabel hauptmann = new RotSpielfigurLabel("roteArmee/hauptmann.png", SpielSeite.ROT, SpielstueckTyp.Hauptmann);
  RotSpielfigurLabel leutnant = new RotSpielfigurLabel("roteArmee/leutnant.png", SpielSeite.ROT, SpielstueckTyp.Leutnant);
  RotSpielfigurLabel major = new RotSpielfigurLabel("roteArmee/major.png", SpielSeite.ROT, SpielstueckTyp.Major);
  RotSpielfigurLabel mineur = new RotSpielfigurLabel("roteArmee/mineur.png", SpielSeite.ROT, SpielstueckTyp.Mineur);
  RotSpielfigurLabel oberst = new RotSpielfigurLabel("roteArmee/oberst.png", SpielSeite.ROT, SpielstueckTyp.Oberst);
  RotSpielfigurLabel spion = new RotSpielfigurLabel("roteArmee/spion.png", SpielSeite.ROT, SpielstueckTyp.Spion);
  RotSpielfigurLabel unteroffizer = new RotSpielfigurLabel("roteArmee/unteroffizier.png", SpielSeite.ROT, SpielstueckTyp.Unteroffizier);

  BlauSpielfigurLabel blauaufklaerer = new BlauSpielfigurLabel("blaueArmee/aufklaerer.png", SpielSeite.BLAU, SpielstueckTyp.Aufklärer);
  BlauSpielfigurLabel blaubombe = new BlauSpielfigurLabel("blaueArmee/bombe.png", SpielSeite.BLAU, SpielstueckTyp.Bombe);
  BlauSpielfigurLabel blaufahne = new BlauSpielfigurLabel("blaueArmee/fahne.png", SpielSeite.BLAU, SpielstueckTyp.Fahne);
  BlauSpielfigurLabel blaufeldmarschall = new BlauSpielfigurLabel("blaueArmee/feldmarschall.png", SpielSeite.BLAU, SpielstueckTyp.Feldmarschall);
  BlauSpielfigurLabel blaugeneral = new BlauSpielfigurLabel("blaueArmee/general.png", SpielSeite.BLAU, SpielstueckTyp.General);
  BlauSpielfigurLabel blauhauptmann = new BlauSpielfigurLabel("blaueArmee/hauptmann.png", SpielSeite.BLAU, SpielstueckTyp.Hauptmann);
  BlauSpielfigurLabel blauleutnant = new BlauSpielfigurLabel("blaueArmee/leutnant.png", SpielSeite.BLAU, SpielstueckTyp.Leutnant);
  BlauSpielfigurLabel blaumajor = new BlauSpielfigurLabel("blaueArmee/major.png", SpielSeite.BLAU, SpielstueckTyp.Major);
  BlauSpielfigurLabel blaumineur = new BlauSpielfigurLabel("blaueArmee/mineur.png", SpielSeite.BLAU, SpielstueckTyp.Mineur);
  BlauSpielfigurLabel blauoberst = new BlauSpielfigurLabel("blaueArmee/oberst.png", SpielSeite.BLAU, SpielstueckTyp.Oberst);
  BlauSpielfigurLabel blauspion = new BlauSpielfigurLabel("blaueArmee/spion.png", SpielSeite.BLAU, SpielstueckTyp.Spion);
  BlauSpielfigurLabel blauunteroffizer = new BlauSpielfigurLabel("blaueArmee/unteroffizier.png", SpielSeite.BLAU, SpielstueckTyp.Unteroffizier);


  //Buttons
  JButton startButton = new JButton("start");
  JButton exitButton = new JButton("exit");
  JButton randomButton = new JButton("zufällig");

  public JTextArea informationsFeld = new JTextArea("");
  JScrollPane scrollPane = new JScrollPane(informationsFeld);
//	JButton startButton = new JButton("start");
//	JButton startButton = new JButton("start");

  // JPanel mainpanels
  JPanel westPanel = new JPanel();
  JPanel northPanel = new JPanel();
  JPanel eastPanel = new JPanel();
  JPanel southPanel = new JPanel();
  CenterJPanel centerPanel = new CenterJPanel(this);

  // Layouts
  BorderLayout mainFrameLayout = new BorderLayout();
  GridBagLayout southLayout = new GridBagLayout();
  //	GridLayout centerLayout = new GridLayout(10, 10);
  GridBagLayout centerLayout = new GridBagLayout();
  GridBagConstraints centerCon = new GridBagConstraints();
  GridBagLayout westLayout = new GridBagLayout();
  GridBagConstraints c = new GridBagConstraints();
  GridBagLayout eastLayout = new GridBagLayout();
  GridBagConstraints d = new GridBagConstraints();
  GridBagConstraints southConstraints = new GridBagConstraints();

  //Das Feld
  public ZellenLabel[][] zellen = new ZellenLabel[10][10];
  public Spiel spiel;
  private Spieler p1, p2;
  private JComponent ziel;
  public ZellenLabel quelle;
  public BeweglichesSpielstueck spielstueckAufFeld;


  public void zugDurchfuehren(BeweglichesSpielstueck angreifer, Point zielPos) {
    ZellenLabel zielLabel = getZellenLabel(zielPos);


    if (spiel.feldMitFeindlichemSpielstueckBelegt(spielstueckAufFeld.besitzer, zielLabel.feld)) {
      Spielstueck verteidiger = spiel.spielTafel.getBegehbaresFeld(zielLabel.feld.position).getBesetzer();
      Spielstueck gewinner = spiel.ermittelGewinner(angreifer, verteidiger);

      spielstueckAufdecken(verteidiger);
      centerPanel.paintImmediately(centerPanel.getBounds());
      mainFrame.repaint();
      update();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      spielstueckAufdecken(angreifer);
      centerPanel.paintImmediately(centerPanel.getBounds());
      mainFrame.repaint();
      update();


      if (angreifer == gewinner) {
        saeubereZelle(zielLabel);
        verteidiger.besitzer.getArmee().entferneSpielstück(verteidiger);
        versetzeSpielstueckAufFreieZelle(angreifer, zielLabel);
        spielstueckAufdecken(angreifer);
        informationsFeld.append(angreifer.getTyp() + " " + angreifer.besitzer.name + " hat " + verteidiger.getTyp() + " " + verteidiger.besitzer.name + " geschlagen \n");
//        informationsFeld.setCaretPosition(informationsFeld.getDocument().getLength());
        System.out.println(angreifer.getTyp() + " hat " + verteidiger.getTyp() + " geschlagen");

      } else if (verteidiger == gewinner) {
        spielstueckAufdecken(verteidiger);
        angreifer.besitzer.getArmee().entferneSpielstück(angreifer);
        saeubereZelle(quelle);
        informationsFeld.append(angreifer.getTyp() + " " + angreifer.besitzer.name + " hat gegen " + verteidiger.getTyp() + " " + verteidiger.besitzer.name + " verloren\n");
        System.out.println(angreifer.getTyp() + " hat gegen " + verteidiger.getTyp() + " verloren");
      } else if (gewinner == null) {
        saeubereZelle(zielLabel);
        saeubereZelle(quelle);
        angreifer.besitzer.getArmee().entferneSpielstück(angreifer);
        verteidiger.besitzer.getArmee().entferneSpielstück(verteidiger);
        informationsFeld.append(angreifer.getTyp() + " " + angreifer.besitzer.name + " und " + verteidiger.getTyp() + " " + verteidiger.besitzer.name + " haben sich gegenseitig ausgeschaltet\n");
        System.out.println(angreifer.getTyp() + " und " + verteidiger.getTyp() + " haben sich gegenseitig ausgeschaltet");
      }
      if (verteidiger instanceof Fahne) {
        informationsFeld.append("Spieler " + angreifer.getFarbe() + " " + angreifer.besitzer.name + " hat die Fahne von Spieler " + verteidiger.getFarbe() + " " + verteidiger.besitzer.name + " erobert! \n Die Partie ist vorbei!\n");
        spiel.spielZustand = SpielZustand.spielBeendet;
      } else if (!spiel.hatSpielerNochBeweglicheSpielstuecke(verteidiger.besitzer)) {
        informationsFeld.append("Spieler " + verteidiger.getFarbe() + " hat keine Beweglichen Spielstücke mehr und hat deshalb verloren!\n");
        spiel.spielZustand = SpielZustand.spielBeendet;
      }
    } else {
      if (angreifer instanceof Aufklaerer && !angreifer.getAufgedeckt() && (Math.abs(quelle.pos.x - zielLabel.pos.x) > 1 || Math.abs(quelle.pos.y - zielLabel.pos.y) > 1)) {
        spielstueckAufdecken(angreifer);
        informationsFeld.append(angreifer.getTyp() + " " + angreifer.besitzer.name + " wurde aufgedeckt\n");
        System.out.println("Aufklärer aufgedeckt");
      }
      versetzeSpielstueckAufFreieZelle(angreifer, zielLabel);
    }

    informationsFeld.setCaretPosition(informationsFeld.getDocument().getLength());
    spielstueckAufFeld = null;
    centerPanel.paintImmediately(centerPanel.getBounds());
    mainFrame.repaint();
    update();
  }

  public void spielstueckAufdecken(Spielstueck s) {
    s.setAufgedeckt(true);
    getZellenLabel(s.position.position).setIcon(SpielfigurLabel.getIconFromType(s.getTyp(), s.getFarbe()), true, s.getFarbe());

  }

  public void versetzeSpielstueckAufFreieZelle(Spielstueck s, ZellenLabel nach) {

    zellen[nach.pos.x][nach.pos.y].setIcon(quelle.getIcon(), s.getAufgedeckt(), s.besitzer.getArmee().getFarbe());
    zellen[quelle.pos.x][quelle.pos.y].setIcon(null);
    spiel.spielstueckAufFreiesFeldBewegen(s, (BegehbaresFeld) nach.feld);
    quelle.setIcon(null);
    spielstueckAufFeld = null;
  }

  public void vertauscheSpielstuecke(ZellenLabel l1, ZellenLabel l2, Spielstueck s1, Spielstueck s2) {
    Icon tmpIcon = l1.getIcon();
    spiel.spielstueckeVetauschen(s1, s2);
    l1.setIcon(l2.getIcon(), s2.getAufgedeckt(), s2.getFarbe());
    l2.setIcon(tmpIcon, s1.getAufgedeckt(), s1.getFarbe());
    informationsFeld.append(s1.getTyp() + " hat den Platz mit " + s2.getTyp() + " getauscht.\n");

  }

  public GUI(Spiel spiel) {
    this.spiel = spiel;
    mainFrame = new JFrame();
    mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);


    p1 = spiel.mitspieler[0];
    p2 = spiel.mitspieler[1];


    centerPanel.setBorder(LineBorder.createBlackLineBorder());
//		centerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
//		westPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
//		westPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

    MouseListener mouseListener = new DragMouseAdapter(this);

    southPanel.setLayout(southLayout);

    //Start Button
    startButton.setEnabled(false);
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        spiel.starteSpiel();
        randomButton.setEnabled(false);
        startButton.setEnabled(false);
        update();
      }
    });

    //Exit Button
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });


    //Zufall Button
    randomButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        for (Spielstueck s : p1.getArmee().getSpielstuecke()) {
          saeubereZelle(zellen[s.getPosition().position.x][s.getPosition().position.y]);
        }

        spiel.erstelleZufaelligeAufstellung();
        checkStartButton();
        update();
      }
    });

    informationsFeld.setEditable(false);
    informationsFeld.setLineWrap(true);
    scrollPane.setPreferredSize(new Dimension(450, 70));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


    southPanel.add(exitButton);
    southPanel.add(randomButton);
    southPanel.add(startButton);
    southPanel.add(scrollPane);
    //startButton.setPreferredSize(new Dimension(5,5));


    southConstraints.fill = GridBagConstraints.HORIZONTAL;
    southConstraints.ipady = 0;       //reset to default
    southConstraints.weighty = 2.0;   //request any extra vertical space
    southConstraints.anchor = GridBagConstraints.LINE_START; //bottom of space
    southConstraints.insets = new Insets(10, 0, 0, 0);  //top padding
    southConstraints.gridx = 0;
    southConstraints.gridy = 0;
    southConstraints.gridwidth = 4;   //2 columns wide


    for (SpielfigurLabel label : SpielfigurLabel.alleSpielfigurenLabels) {
      label.setTransferHandler(new DragHelper(this, spiel));
      label.addMouseListener(mouseListener);
    }


    int k = 0;
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        ZellenLabel zelle = new ZellenLabel(new Point(i, j), k, spiel.spielTafel.getFeld(new Point(i, j)));
        //zelle.setTransferHandler(dragHandler);
        zelle.setTransferHandler(new DragHelper(this, spiel));
        zelle.addMouseListener(mouseListener);
        zelle.setBorder(BorderFactory.createRaisedBevelBorder());
        zelle.setName(k + "");
//        zelle.setPreferredSize(new Dimension(80, 80));
        zelle.setPreferredSize(new Dimension(60, 60));

        centerCon.gridx = j;
        centerCon.gridy = i;
        centerCon.weightx = 1;
        centerCon.weighty = 1;
        centerCon.fill = GridBagConstraints.BOTH;
        centerCon.gridheight = 1;
        centerCon.gridwidth = 1;
        centerCon.ipady = 0;
        //centerCon.anchor = GridBagConstraints.CENTER;
        centerLayout.setConstraints(zelle, centerCon);
        centerPanel.add(zelle, centerCon);
        zellen[i][j] = zelle;
        k++;

      }
    }


    /**
     * for (int i = 0; i<(2*12); i++){ JLabel label = new JLabel("Figuras");
     * label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
     * label.setPreferredSize(new Dimension(70,50)); westPanel.add(label); }
     */


    // add Image red site

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(fahne.txtAmount, c);
    westPanel.add(fahne.txtAmount);

    // Textfeld für den Preis
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(fahne, c);
    westPanel.add(fahne);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(feldmarschall.txtAmount, c);
    westPanel.add(feldmarschall.txtAmount);

    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(feldmarschall, c);
    westPanel.add(feldmarschall);

    // Label
    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(general.txtAmount, c);
    westPanel.add(general.txtAmount);

    // Textfeld für den Preis
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(general, c);
    westPanel.add(general);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(oberst.txtAmount, c);
    westPanel.add(oberst.txtAmount);

    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(oberst, c);
    westPanel.add(oberst);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;

    westLayout.setConstraints(major.txtAmount, c);
    westPanel.add(major.txtAmount);

    // Textfeld für den Preis
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(major, c);
    westPanel.add(major);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(hauptmann.txtAmount, c);
    westPanel.add(hauptmann.txtAmount);

    // Textfeld für den Preis
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(hauptmann, c);
    westPanel.add(hauptmann);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(leutnant.txtAmount, c);
    westPanel.add(leutnant.txtAmount);

    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(leutnant, c);
    westPanel.add(leutnant);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(unteroffizer.txtAmount, c);
    westPanel.add(unteroffizer.txtAmount);

    // Textfeld für den Preis
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(unteroffizer, c);
    westPanel.add(unteroffizer);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(mineur.txtAmount, c);
    westPanel.add(mineur.txtAmount);

    // Textfeld für den Preis
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(mineur, c);
    westPanel.add(mineur);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(aufklaerer.txtAmount, c);
    westPanel.add(aufklaerer.txtAmount);

    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(aufklaerer, c);
    westPanel.add(aufklaerer);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(spion.txtAmount, c);
    westPanel.add(spion.txtAmount);

    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(spion, c);
    westPanel.add(spion);

    c.gridwidth = 1;
    c.insets = new Insets(0, 5, 0, 5);
    c.weightx = 1;
    c.weighty = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    westLayout.setConstraints(bombe.txtAmount, c);
    westPanel.add(bombe.txtAmount);

    // Textfeld für den Preis
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 1;
    c.insets = new Insets(5, 0, 5, 0);
    westLayout.setConstraints(bombe, c);
    westPanel.add(bombe);

    // add Image blue site

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blaufahne, d);
    eastPanel.add(blaufahne);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blaufahne.txtAmount, d);
    eastPanel.add(blaufahne.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blaufeldmarschall, d);
    eastPanel.add(blaufeldmarschall);

    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blaufeldmarschall.txtAmount, d);
    eastPanel.add(blaufeldmarschall.txtAmount);

    // Label
    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blaugeneral, d);
    eastPanel.add(blaugeneral);

    // Textfeld für den Preis

    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blaugeneral.txtAmount, d);
    eastPanel.add(blaugeneral.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blauoberst, d);
    eastPanel.add(blauoberst);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blauoberst.txtAmount, d);
    eastPanel.add(blauoberst.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blaumajor, d);
    eastPanel.add(blaumajor);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blaumajor.txtAmount, d);
    eastPanel.add(blaumajor.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blauhauptmann, d);
    eastPanel.add(blauhauptmann);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blauhauptmann.txtAmount, d);
    eastPanel.add(blauhauptmann.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blauleutnant, d);
    eastPanel.add(blauleutnant);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blauleutnant.txtAmount, d);
    eastPanel.add(blauleutnant.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blauunteroffizer, d);
    eastPanel.add(blauunteroffizer);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blauunteroffizer.txtAmount, d);
    eastPanel.add(blauunteroffizer.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blaumineur, d);
    eastPanel.add(blaumineur);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blaumineur.txtAmount, d);
    eastPanel.add(blaumineur.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(0, 0, 0, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blauaufklaerer, d);
    eastPanel.add(blauaufklaerer);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blauaufklaerer.txtAmount, d);
    eastPanel.add(blauaufklaerer.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blauspion, d);
    eastPanel.add(blauspion);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blauspion.txtAmount, d);
    eastPanel.add(blauspion.txtAmount);

    d.gridwidth = 1;
    d.insets = new Insets(5, 0, 5, 0);
    d.weightx = 1;
    d.weighty = 1;
    d.fill = GridBagConstraints.NONE;
    eastLayout.setConstraints(blaubombe, d);
    eastPanel.add(blaubombe);

    // Textfeld für den Preis
    d.gridwidth = GridBagConstraints.REMAINDER;
    d.fill = GridBagConstraints.HORIZONTAL;
    d.weightx = 1;
    d.insets = new Insets(0, 5, 0, 5);
    eastLayout.setConstraints(blaubombe.txtAmount, d);
    eastPanel.add(blaubombe.txtAmount);
    // add layouts
    mainFrame.setLayout(mainFrameLayout);
    centerPanel.setLayout(centerLayout);
    westPanel.setLayout(westLayout);
    eastPanel.setLayout(eastLayout);

    mainFrame.getContentPane().add(westPanel, BorderLayout.WEST);
    mainFrame.getContentPane().add(eastPanel, BorderLayout.EAST);
    //mainFrame.getContentPane().add(northPanel, BorderLayout.NORTH);
    mainFrame.getContentPane().add(southPanel, BorderLayout.SOUTH);
    mainFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);

    initPanelColor();

    westPanel.setPreferredSize(new Dimension(120, 600));
    eastPanel.setPreferredSize(new Dimension(120, 600));
    southPanel.setPreferredSize(southPanel.getPreferredSize());
    //centerPanel.setPreferredSize(new Dimension(684, 660));
    //centerPanel.setPreferredSize(centerPanel.getPreferredSize());


/*    westPanel.setPreferredSize(westPanel.getPreferredSize());
    eastPanel.setPreferredSize(eastPanel.getPreferredSize());
    northPanel.setPreferredSize(northPanel.getPreferredSize());
    southPanel.setPreferredSize(southPanel.getPreferredSize());
    centerPanel.setPreferredSize(centerPanel.getPreferredSize());
    centerPanel.setPreferredSize(new Dimension(830, 830));
*/

    mainFrame.setResizable(false);
    centerPanel.validate();
    mainFrame.setTitle("Stratego GSE");
    mainFrame.setSize(mainFrame.getPreferredSize());
    mainFrame.pack();
    mainFrame.validate();
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);

    System.out.println(centerPanel.getSize());
    System.out.println(centerPanel.getComponent(0).getSize());
    System.out.println(eastPanel.getSize());
    System.out.println(fahne.txtAmount.getSize());

  }


  public void update() {

    for (SpielfigurLabel label : SpielfigurLabel.alleSpielfigurenLabels) {
      if (label instanceof RotSpielfigurLabel) {
        label.txtAmount.setText(
          p1.getArmee().getAnzahlSpielstückInArmee(label.typ) + "/" + label.typ
            .getMaxAmount());
      } else if (label instanceof BlauSpielfigurLabel) {
        label.txtAmount.setText(
          p2.getArmee().getAnzahlSpielstückInArmee(label.typ) + "/" + label.typ
            .getMaxAmount());
      }
    }

    for (ZellenLabel zelle : ZellenLabel.alleZellen) {
      zelle.setHorizontalAlignment(SwingConstants.CENTER);
    }
    centerPanel.paintImmediately(centerPanel.getBounds());
    mainFrame.repaint();


  }

  public void checkStartButton() {
    if (p1.getArmee().getSpielstuecke().size() == 40) {
      startButton.setEnabled(true);
    }
  }

  public void saeubereZelle(ZellenLabel zelle) {
    zelle.setIcon(null);
    spiel.feldAufraeumen(zelle.feld);
  }

  public ZellenLabel getZellenLabel(Point pos) {
    return zellen[pos.x][pos.y];
  }

  public void initPanelColor() {
    westPanel.setBackground(Color.red);
    eastPanel.setBackground(Color.blue);
    //northPanel.setBackground(Color.yellow);
    southPanel.setBackground(Color.gray);
    centerPanel.setBackground(Color.BLACK);
  }

  static class DragMouseAdapter extends MouseAdapter {

    private final GUI gui;

    public DragMouseAdapter(GUI gui) {
      this.gui = gui;
    }

    @Override
    public void mousePressed(MouseEvent e) {
      JComponent c = (JComponent) e.getSource();
      TransferHandler handler = c.getTransferHandler();

      gui.quelle = null;
      if (c instanceof RotSpielfigurLabel) {
        if (gui.spiel.spielZustand == SpielZustand.armeeAufbauen) {
          RotSpielfigurLabel r = (RotSpielfigurLabel) c;
          System.out.println("rot ryp: " + r.typ);
          if (gui.p1.getArmee().spielstückNochAufzustellen(r.typ)) {
            gui.p1.ausgewählterSpielstueckTyp = r.typ;
            handler.exportAsDrag(c, e, TransferHandler.COPY);
          } else {
            gui.informationsFeld.append(r.typ + " muss nicht mehr aufgestellt werden \n");
            System.out.println("Spielstück muss nicht mehr aufgestellt werden");
          }
        }
      } else if (c instanceof BlauSpielfigurLabel) {

      } else if (c instanceof ZellenLabel) {

        ZellenLabel zelle = (ZellenLabel) c;
  //				System.out.println(zelle.feld instanceof BegehbaresFeld ? ((BegehbaresFeld) zelle.feld).istBelegt() : "Leer");

        if (zelle.feld instanceof BegehbaresFeld) {
          BegehbaresFeld feld = (BegehbaresFeld) zelle.feld;

          if (gui.spiel.spielZustand == SpielZustand.armeeAufbauen) {
            if (feld.istBelegt() && zelle.nr >= 60) {
              handler.exportAsDrag(c, e, TransferHandler.COPY);
              gui.quelle = gui.getZellenLabel(zelle.pos);
            }
          } else if (gui.spiel.spielZustand == SpielZustand.spielerAmZug) {

            BeweglichesSpielstueck figur = null;
            if (feld.getBesetzer() instanceof BeweglichesSpielstueck) {
              figur = (BeweglichesSpielstueck) feld.getBesetzer();
              gui.spielstueckAufFeld = figur;
              boolean eigenesBeweglichesSpielstueckAufFeld =
                  figur.getFarbe() == gui.p1.getArmee().getFarbe();
              if (eigenesBeweglichesSpielstueckAufFeld) {
                handler.exportAsDrag(c, e, TransferHandler.COPY);
                gui.quelle = (ZellenLabel) c;
                figur.bewegungsRaum = gui.spiel.ermittleBewegungsBereich(figur);
              }
            }
          }
        }
      }
    }

  }
}
