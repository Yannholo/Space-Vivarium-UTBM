package spaceVivarium.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import spaceVivarium.core.maps.Board;
import spaceVivarium.core.maps.xml.XmlReader;
import spaceVivarium.exception.XmlFailureException;

//import java.awt.event.*;

public class Fenetre2 extends JFrame {

    private JButton charge, ok, supprimer, simuler;
    private JComboBox species, species1;
    private JTextField number, number1;
    private JLabel typemap;

    private JPanel vide;
    private JTable animalTable;
    private List<AnimalQuantite> animalsList = new ArrayList<AnimalQuantite>();
    private TableModel model;
    private String path;
    private Board terrain;

    JScrollPane scrollPane;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * public Fenetre2() { // D閒init un titre pour notre fen阾re
     * setTitle("créer small world"); // D閒init sa taille : 500 pixels de large
     * et 500 pixels de haut setPreferredSize(new Dimension(520, 520));
     * 
     * // Nous demandons maintenant �notre objet de se positionner au centre
     * setLocationRelativeTo(null); // Termine le processus lorsqu'on clique sur
     * la croix rouge setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
     * Instanciation d'un objet JPanel // Et enfin, la rendre visible
     * 
     * setVisible(true); this.pack(); }
     */
    public Fenetre2() {
        init();
        configParams();
        setLayout();
        addJComboBoxItem();
        addListener();
        suppriListener();
        Mdialog();

    }

    private void addListener() {
        ok.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                addAnimalInfo(species.getSelectedItem().toString(),
                        Integer.parseInt(number.getText()));
                System.out.println(species.getSelectedItem().toString());

            }
        });

    }

    private void suppriListener() {
        supprimer.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                suppriAnimalInfo(species1.getSelectedItem().toString(),
                        Integer.parseInt(number1.getText()));
                System.out.println(species1.getSelectedItem().toString());

            }
        });

    }

    private void configParams() {
        locateToScreen();
        // setAlwaysOnTop(true);
        // setResizable(false);
        setVisible(true);

    }

    private void locateToScreen() {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (screenDim.getWidth() / 2 - 200),
                (int) (screenDim.getHeight() / 2 - 200));
        setElementSize();

    }

    private void setElementSize() {
        this.setSize(700, 400);
    }

    private void init() {
        setTitle("creer small world");
        charge = new JButton("charge");
        ok = new JButton("OK");
        supprimer = new JButton("supprimer");
        simuler = new JButton("simuler");

        // TODO
        vide = new JPanel();
        typemap = new JLabel(path);

        species = new JComboBox();
        number = new JTextField("");
        species1 = new JComboBox();
        number1 = new JTextField("");
        model = new AnimalTableModel(animalsList);
        animalTable = new JTable(model);
        scrollPane = new JScrollPane(animalTable);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setLayout() {
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel panelNorthNorth = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelNorthSouth = new JPanel();
        this.add(northPanel, BorderLayout.NORTH);
        // this.add(animalTable, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        northPanel.setLayout(new BorderLayout());
        northPanel.add(panelNorthNorth, BorderLayout.NORTH);
        northPanel.add(panelCenter, BorderLayout.CENTER);
        northPanel.add(panelNorthSouth, BorderLayout.SOUTH);
        panelNorthNorth.setLayout(new BorderLayout());
        panelNorthNorth.add(vide, BorderLayout.NORTH);
        panelNorthNorth.add(typemap, BorderLayout.CENTER);
        panelNorthNorth.add(charge, BorderLayout.EAST);
        panelCenter.setLayout(new GridLayout(1, 3));
        panelCenter.add(species, BorderLayout.SOUTH);
        panelCenter.add(number, BorderLayout.SOUTH);
        panelCenter.add(ok, BorderLayout.SOUTH);
        panelNorthSouth.setLayout(new GridLayout(1, 3));
        panelNorthSouth.add(species1, BorderLayout.SOUTH);
        panelNorthSouth.add(number1, BorderLayout.SOUTH);
        panelNorthSouth.add(supprimer, BorderLayout.SOUTH);
        animalTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);
        // this.add(animalTable,BorderLayout.CENTER);
        southPanel.setLayout(new BorderLayout());
        southPanel.add(simuler, BorderLayout.EAST);
    }

    private void addAnimalInfo(String name, int num) {
        boolean estPresent = false;
        for (AnimalQuantite animal : animalsList) {
            if (animal.getName().equals(name)) {
                estPresent = true;
                animal.setNum(animal.getNum() + num);
                scrollPane.updateUI();

            }
        }

        if (!estPresent) {

            AnimalQuantite animal = new AnimalQuantite(name, num);
            animalsList.add(animal);
            scrollPane.updateUI();
        }
    }

    private void suppriAnimalInfo(String name, int num) {
        boolean estPresent = false;
        AnimalQuantite animals = null;
        for (AnimalQuantite animal : animalsList) {
            if (animal.getName().equals(name)) {
                estPresent = true;
                animal.setNum(animal.getNum() - num);
                if (animal.getNum() <= 0) {
                    // name = null;
                    // AnimalQuantite animal1 = new AnimalQuantite(name, num);
                    animals = animal;
                }
                scrollPane.updateUI();

            }
        }
        animalsList.remove(animals);

    }

    private void addJComboBoxItem() {
        species.addItem("Cthuli");
        species.addItem("Petite araignee veneneuse");
        species.addItem("Fourmi geantes");
        species.addItem("Humains");

        species1.addItem("Cthuli");
        species1.addItem("Petite araignee veneneuse");
        species1.addItem("Fourmi geantes");
        species1.addItem("Humains");
    }

    private void Mdialog() {

        // fc.addJFileChooser();

        charge.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "XML file", "xml");
                fc.setFileFilter(filter);
                // if (e.getSource() == charge) {
                // fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                // }
                fc.setDialogTitle("choisir un map");
                int result = fc.showOpenDialog(Fenetre2.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    path = fc.getSelectedFile().getAbsolutePath();
                    typemap.setText(path);

                    try {
                        terrain = XmlReader.xmlToBoard(path);

                    } catch (XmlFailureException e1) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(Fenetre2.this,
                                "Ficher Xml non conforme", "Erreur",
                                JOptionPane.WARNING_MESSAGE);

                    }

                }
            }
        });

    }

}
