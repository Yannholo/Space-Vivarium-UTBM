package spaceVivarium.ihm;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Fenetre() {
        // D�finit un titre pour notre fen�tre
        setTitle("Test swing");
        // D�finit sa taille : 500 pixels de large et 500 pixels de haut
        setSize(520, 540);
        // Nous demandons maintenant � notre objet de se positionner au centre
        setLocationRelativeTo(null);
        // Termine le processus lorsqu'on clique sur la croix rouge
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Instanciation d'un objet JPanel
        // Et enfin, la rendre visible
        setVisible(true);
    }

}
