package test.yannholo;

import test.yannholo.ihm.Fenetre;

public class Test {

    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre();

        while(true) {
            fenetre.repaint();
            try {
                Thread.sleep(1*100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
