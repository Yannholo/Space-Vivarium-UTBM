package test.yannholo;

public class Test {

    public static void main(String[] args) {
        Fenetre fenetre = new Fenetre();

        while(true) {
            fenetre.repaint();
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
