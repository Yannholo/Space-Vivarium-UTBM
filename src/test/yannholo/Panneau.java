package test.yannholo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import test.yannholo.core.Action;
import test.yannholo.core.Bestiole;
import test.yannholo.core.Case;

public class Panneau extends JPanel { 

    int counter = 0;
    static final int sizex = 50;
    static final int sizey = 50;
    static final int nbBestioles = 10; 

    Map<Point, Case> cases;
    List<Bestiole> bestioles;


    public Panneau() {
        super();
        cases = new HashMap<Point, Case>(sizex*sizey);
        // on crée toutes les cases
        Point tmp;
        for(int x = 0; x < sizex; x++){
            for(int y = 0; y < sizey; y++){
                tmp = new Point(x, y);
                cases.put(tmp, new Case(tmp, cases));
            }
        }
        bestioles = new ArrayList<>(nbBestioles);
        for(int i = 0 ; i < nbBestioles; i++){
            int x = (int) (Math.random()*sizex);
            int y = (int) (Math.random()*sizey);
            Case unecase  = cases.get(new Point(x, y));
            Bestiole bestiole = new Bestiole(unecase);
            bestioles.add(bestiole);
            unecase.setBestiole(bestiole);
        }
    }



    public void paintComponent(Graphics g){
        //très très sale
        System.out.println("update " + counter++);
        // on choppe ce que veulent faire les bestioles
        List<Case> changed = new LinkedList<>();
        List<Action> askedActions = new ArrayList<>(bestioles.size());
        for (Bestiole bestiole : bestioles) {
            Case depart = bestiole.getLaCase();
            // on choppe toutes les cases vues par la bestiolle
            int nbCasesVues = bestiole.getVision()*4 + (int)Math.pow(4, bestiole.getVision());
            List<Case> vues = new ArrayList<>(nbCasesVues);
            for(int x = depart.getPoint().x - bestiole.getVision() ;
                    x <= depart.getPoint().x + bestiole.getVision() ;
                    x++) {
                for(int y = depart.getPoint().y - bestiole.getVision() ;
                        y <= depart.getPoint().y + bestiole.getVision() ;
                        y++) {
                    if(x >= 0 && x < sizex && y >=0 && y < sizey ){
                        vues.add(cases.get(new Point(x, y)));
                    }
                }
            }
            askedActions.add(bestiole.update(vues));
        }

        // on fait ce que veulent faire les bestioles + TODO verifier les conflits
        for (Action action : askedActions) {
            action.doit();
        }

        // on affiche les Cases et tout
        Graphics2D g2d = (Graphics2D)g; 
        for (Case unecase : cases.values()) {
            unecase.print(g2d);
        }

    }               
}