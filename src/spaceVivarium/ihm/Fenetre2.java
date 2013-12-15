package spaceVivarium.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;

public class Fenetre2 extends JFrame {

    JButton btn1, btn2, btn3;
    JFormattedTextField text1, text2;
    JList list;
    int quantite;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Fenetre2() {
        // D閒init un titre pour notre fen阾re
        setTitle("creer small world");
        // D閒init sa taille : 500 pixels de large et 500 pixels de haut
        setPreferredSize(new Dimension(520, 520));

        // Nous demandons maintenant �notre objet de se positionner au centre
        setLocationRelativeTo(null);
        // Termine le processus lorsqu'on clique sur la croix rouge
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Instanciation d'un objet JPanel
        // Et enfin, la rendre visible
        setVisible(true);

        setLayout(new FlowLayout());
        btn1 = new JButton("Charge");
        this.add(btn1);

        String[] animalStrings = { "Cthuli", "Petite araignee veneneuse",
                "Fourmi geantes", "Humains" };
        // Create the combo box, select item at index 4.
        JComboBox animalList = new JComboBox(animalStrings);
        this.add(animalList);
        animalList.setSelectedIndex(3);
        // animalList.addActionListener(this);

        // entrer la quantité
        text1 = new JFormattedTextField();
        this.add(text1);
        text1.setValue(quantite);
        text1.setColumns(6);
        // text1.setPreferredSize(new Dimension(10, 10));

        btn2 = new JButton("OK");
        this.add(btn2);

        btn3 = new JButton("Simuler");
        this.add(btn3);

        this.pack();
    }

}
