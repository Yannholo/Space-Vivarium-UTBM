package spaceVivarium.utils;

import java.awt.Point;

public class Vector2D {

    private double x, y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D translate(Vector2D translation) {
        return new Vector2D(this.x + translation.x, this.y + translation.y);
    }

    /**
     * Normalize a Vector2D
     * 
     * @return the normalized vector
     */
    public Vector2D normalize() {
        Vector2D res = new Vector2D();
        double length = Math.sqrt(this.x * this.x + this.y * this.y);
        if (length != 0) {
            res.x = this.x / length;
            res.y = this.y / length;
        }
        return res;
    }

    public double getDistance(Vector2D vect) {
        return Math.sqrt((vect.x - this.x) * (vect.x - this.x)
                + (vect.y - this.y) * (vect.y - this.y));
    }

    public Point getIntPoint() {
        return new Point((int) Math.round(this.x), (int) Math.round(this.y));
    }
}
