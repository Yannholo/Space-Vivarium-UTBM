package test.yannholo;

import java.awt.Color;

import javax.swing.JFrame;

public class Fenetre extends JFrame{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Fenetre() {
        //Définit un titre pour notre fenêtre
        setTitle("Test swing");
        //Définit sa taille : 500 pixels de large et 500 pixels de haut
        setSize(520, 540);
        //Nous demandons maintenant à notre objet de se positionner au centre
        setLocationRelativeTo(null);
        //Termine le processus lorsqu'on clique sur la croix rouge
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Instanciation d'un objet JPanel
        Panneau pan = new Panneau();
        //Définition de sa couleur de fond
        pan.setBackground(Color.BLUE);        
        //On prévient notre JFrame que notre JPanel sera son content pane
        setContentPane(pan); 
        //Et enfin, la rendre visible        
        setVisible(true);
    }

}
