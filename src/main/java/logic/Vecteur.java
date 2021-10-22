package logic;

public class Vecteur {
    private double x;
    private double y;

    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double calculerLongueur(Vecteur v) {
        return v.getX()-v.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }
}
