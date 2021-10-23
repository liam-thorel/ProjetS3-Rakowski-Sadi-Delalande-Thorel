package logic;

public class Vecteur {
    private double x;
    private double y;
    private double norme;

    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;
        norme = calculerNorme(this);
    }

    public static double calculerNorme(Vecteur v) {
        return Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
    }

    public void setNorme(double norme) {
        this.norme = norme;
    }

    public double getNorme() {
        return norme;
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
