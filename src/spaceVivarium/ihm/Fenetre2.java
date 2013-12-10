package spaceVivarium.ihm;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;
//import java.awt.event.*;

public class Fenetre2 extends JFrame {

    JButton btn1, btn2, btn3;
    JFormattedTextField text1, text2;
    JList list;
    int quantité;

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Fenetre2() {
        // D閒init un titre pour notre fen阾re
        setTitle("créer small world");
        // D閒init sa taille : 500 pixels de large et 500 pixels de haut
        setPreferredSize(new Dimension(520, 520));

        // Nous demandons maintenant �notre objet de se positionner au centre
        setLocationRelativeTo(null);
        // Termine le processus lorsqu'on clique sur la croix rouge
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Instanciation d'un objet JPanel
        // Et enfin, la rendre visible

        setVisible(true);

        if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            // natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        btn1 = new JButton("Charge");
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        add(btn1, c);

        String[] animalStrings = { "Cthuli", "Petite araignée vénéneuse",
                "Fourmi géantes", "Humains" };
        JComboBox animalList = new JComboBox(animalStrings);
        c.fill = GridBagConstraints.HORIZONTAL;
        // c.ipady = 20; //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(animalList, c);
        animalList.setSelectedIndex(3);

        text1 = new JFormattedTextField();
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        this.add(text1, c);
        text1.setValue(quantité);
        text1.setColumns(6);

        btn2 = new JButton("OK");
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        this.add(btn2, c);

        btn3 = new JButton("Simuler");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; // reset to default
        c.weighty = 1.0; // request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; // bottom of space
        c.insets = new Insets(10, 0, 0, 0); // top padding
        c.gridx = 1; // aligned with button 2
        c.gridwidth = 2; // 2 columns wide
        c.gridy = 2; // third row
        this.add(btn3, c);

        /*
         * setLayout(new FlowLayout()); btn1 = new JButton("Charge");
         * this.add(btn1);
         * 
         * String[] animalStrings = { "Cthuli", "Petite araignée vénéneuse",
         * "Fourmi géantes", "Humains" }; // Create the combo box, select item
         * at index 4. JComboBox animalList = new JComboBox(animalStrings);
         * this.add(animalList); animalList.setSelectedIndex(3); //
         * animalList.addActionListener(this);
         * 
         * // entrer la quantité text1 = new JFormattedTextField();
         * this.add(text1); text1.setValue(quantité); text1.setColumns(6); //
         * text1.setPreferredSize(new Dimension(10, 10));
         * 
         * btn2 = new JButton("OK"); this.add(btn2);
         * 
         * btn3 = new JButton("Simuler"); this.add(btn3);
         */

        /*
         * public void TableDemo() { //String Super("small world"); String[]
         * columnNames = { "Animal", "quantité" }; Object[][] data = { { "", ""
         * }, { "", "" }, { "", "" }, { "", "" } }; JTable table = new
         * JTable(data, columnNames); table.setPreferredSize(new Dimension(50,
         * 50));
         * 
         * }
         */

        this.pack();
    }
}
