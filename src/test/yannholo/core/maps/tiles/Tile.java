package test.yannholo.core.maps.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.util.Map;

import test.yannholo.core.entities.Entity;

public class Tile {
    private Point point;
    private Map<Point, Tile>cases;
    private Entity bestiole;
    
    public Tile(Point point, Map<Point, Tile> cases) {
        this.point = point;
        this.cases = cases;
    }
    
    @Override
    public boolean equals(Object arg0) {
        if(arg0.getClass() != this.getClass()) {
            return false;
        }
        Tile autre = (Tile) arg0;
        return point.x == autre.getPoint().x && point.y == autre.getPoint().y;
    }

    public Point getPoint() {
        return point;
    }

    public Entity getBestiole() {
        return bestiole;
    }

    public void setBestiole(Entity bestiole) {
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
