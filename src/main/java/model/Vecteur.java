package model;


/***
 * Cette classe sert dans calculerSommeForces et elle joue principalement le rôle d'une structure
 */
public class Vecteur {
    private double x;
    private double y;


    public Vecteur(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public void incrementXBy(double px){
        x += px;
    }

    public void incrementYBy(double py){
        y += py;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }
}
