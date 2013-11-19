package test.yannholo.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.util.Map;

public class Case {
    private Point point;
    private Map<Point, Case>cases;
    private Bestiole bestiole;
    
    public Case(Point point, Map<Point, Case> cases) {
        this.point = point;
        this.cases = cases;
    }
    
    @Override
    public boolean equals(Object arg0) {
        if(arg0.getClass() != this.getClass()) {
            return false;
        }
        Case autre = (Case) arg0;
        return point.x == autre.getPoint().x && point.y == autre.getPoint().y;
    }

    public Point getPoint() {
        return point;
    }

    public Bestiole getBestiole() {
        return bestiole;
    }

    public void setBestiole(Bestiole bestiole) {
        this.bestiole = bestiole;
    }

    public void print(Graphics2D g2d) {
        if(bestiole != null) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
        }
        g2d.fillRect(point.x*10, point.y*10, 10, 10);
        g2d.setColor(Color.GRAY);
        g2d.drawRect(point.x*10, point.y*10, 10, 10);
        
        
    }
    
    
}
