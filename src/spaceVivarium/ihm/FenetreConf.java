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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

import spaceVivarium.core.entities.Ant;
import spaceVivarium.core.entities.Cthuli;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.entities.Human;
import spaceVivarium.core.entities.Spider;
import spaceVivarium.core.maps.Board;
import spaceVivarium.core.maps.xml.XmlReader;
import spaceVivarium.core.simulation.Simulation;
import spaceVivarium.exception.XmlFailureException;
import spaceVivarium.thread.IHMThread;
import spaceVivarium.thread.SimulationThread;
import spaceVivarium.utils.ThreadUtil;

//import java.awt.event.*;

public class FenetreConf extends JFrame {

    private static final String CTHULI = "Cthuli";
    private static final String SPIDER = "Araignée de l'espace";
    private static final String ANT = "Fourmi géante";
    private static final String HUMAN = "Humain";

    private JButton charge, ok, supprimer, simuler;
    private JComboBox<String> species, species1;
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

    public FenetreConf() {
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
                try {
                    addAnimalInfo(species.getSelectedItem().toString(),
                            Integer.parseInt(number.getText()));
                    // System.out.println(species.getSelectedItem().toString());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(FenetreConf.this,
                            "La quantitée n'est pas valide", "Erreur",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        simuler.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (terrain != null) {
                    Simulation simulation = getSimulation(terrain,
                            getEntitiesConf(animalsList));
                    simulation.init();

                    IHMThread ihmThread = new IHMThread(simulation);
                    SimulationThread simulationThread = new SimulationThread(
                            simulation, ihmThread.getSimulationPanel());
                    // ihmThread.setSimulationThread(simulationThread);

                    SwingUtilities.invokeLater(ihmThread);

                    ThreadUtil.execute(new SimulationThread(simulation,
                            ihmThread.getSimulationPanel()));
                }
            }
        });

    }

    private static Map<Class<? extends Entity>, Integer> getEntitiesConf(
            List<AnimalQuantite> animalsList2) {
        Map<Class<? extends Entity>, Integer> entitiesConf = new HashMap<>();
        for (AnimalQuantite animalQuantite : animalsList2) {
            Class<? extends Entity> entityClass = null;
            switch (animalQuantite.getName()) {
            case CTHULI:
                entityClass = Cthuli.class;
                break;
            case SPIDER:
                entityClass = Spider.class;
                break;
            case ANT:
                entityClass = Ant.class;
                break;
            case HUMAN:
                entityClass = Human.class;
                break;

            }
            entitiesConf.put(entityClass, animalQuantite.getNum());
        }
        return entitiesConf;
    }

    private static Simulation getSimulation(
            Board board, Map<Class<? extends Entity>, Integer> entityConf) {

        return new Simulation(board, entityConf);

    }

    private void suppriListener() {
        supprimer.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    suppriAnimalInfo(species1.getSelectedItem().toString(),
                            Integer.parseInt(number1.getText()));
                    // System.out.println(species1.getSelectedItem().toString());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(FenetreConf.this,
                            "La quantitée n'est pas valide", "Erreur",
                            JOptionPane.WARNING_MESSAGE);
                }

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
        setTitle("configuration Space Vivarium");
        charge = new JButton("Importer XML");
        ok = new JButton("Ajouter");
        supprimer = new JButton("Supprimer");
        simuler = new JButton("Simuler");

        // TODO
        vide = new JPanel();
        typemap = new JLabel(path);

        species = new JComboBox<String>();
        number = new JTextField();
        species1 = new JComboBox<String>();
        number1 = new JTextField();
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

                    animals = animal;
                }
                scrollPane.updateUI();

            }
        }
        animalsList.remove(animals);

    }

    private void addJComboBoxItem() {
        species.addItem(CTHULI);
        species.addItem(SPIDER);
        species.addItem(ANT);
        species.addItem(HUMAN);

        species1.addItem(CTHULI);
        species1.addItem(SPIDER);
        species1.addItem(ANT);
        species1.addItem(HUMAN);
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
                int result = fc.showOpenDialog(FenetreConf.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    path = fc.getSelectedFile().getAbsolutePath();
                    typemap.setText(path);

                    try {
                        terrain = XmlReader.xmlToBoard(path);

                    } catch (XmlFailureException e1) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(FenetreConf.this,
                                "Ficher Xml non conforme", "Erreur",
                                JOptionPane.WARNING_MESSAGE);

                    }

                }
            }
        });

    }

}
