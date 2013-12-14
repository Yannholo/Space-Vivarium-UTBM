package spaceVivarium.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

//import java.awt.event.*;

public class Fenetre2 extends JFrame {

    private JButton charge, ok, simulater;
    private JComboBox species;
    private JTextField number;

    private JPanel map;
    private JTable animalTable;
    private List<AnimalQuantite> animalsList = new ArrayList<AnimalQuantite>();
    private TableModel model;
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

    }

    private void addListener() {
        ok.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                addAnimalInfo(species.getSelectedItem().toString(),
                        number.getText());
                System.out.println(species.getSelectedItem().toString());

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
        this.setSize(500, 400);
    }

    private void init() {
        setTitle("créer small world");
        charge = new JButton("charge");
        ok = new JButton("OK");
        simulater = new JButton("simulater");

        // TODO
        map = new JPanel();
        species = new JComboBox();
        number = new JTextField("");
        model = new AnimalTableModel(animalsList);
        animalTable = new JTable(model);
        scrollPane = new JScrollPane(animalTable);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setLayout() {
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel panelNorthNorth = new JPanel();
        JPanel panelNorthSouth = new JPanel();
        this.add(northPanel, BorderLayout.NORTH);
        // this.add(animalTable, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
        northPanel.setLayout(new BorderLayout());
        northPanel.add(panelNorthNorth, BorderLayout.CENTER);
        northPanel.add(panelNorthSouth, BorderLayout.SOUTH);
        panelNorthNorth.setLayout(new BorderLayout());
        panelNorthNorth.add(map, BorderLayout.CENTER);
        panelNorthNorth.add(charge, BorderLayout.EAST);
        panelNorthSouth.setLayout(new GridLayout(1, 3));
        panelNorthSouth.add(species, BorderLayout.SOUTH);
        panelNorthSouth.add(number, BorderLayout.SOUTH);
        panelNorthSouth.add(ok, BorderLayout.SOUTH);
        animalTable.setFillsViewportHeight(true);
        this.add(scrollPane, BorderLayout.CENTER);
        // this.add(animalTable,BorderLayout.CENTER);
        southPanel.setLayout(new BorderLayout());
        southPanel.add(simulater, BorderLayout.EAST);
    }

    private void addAnimalInfo(String name, String string) {

        AnimalQuantite animal = new AnimalQuantite(name, string);
        animalsList.add(animal);
        scrollPane.updateUI();
    }

    private void addJComboBoxItem() {
        species.addItem("Cthuli");
        species.addItem("Petite araignée vénéneuse");
        species.addItem("Fourmi géantes");
        species.addItem("Humains");
    }
}
