package logic;

import javafx.scene.Node;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Astre implements Serializable {
    private float taille;
    private int masse;
    private String nom;
    private double positionX;
    private double positionY;
    private int vitesseInit;
    //private int vecteurX;
    //private int vecteurY;
    private int vitesse;
    public abstract String getArgString();
    public abstract String toString();
    private Node n;


    public Node getN() {return n;}

    public void setN(Node n) {this.n = n;}

    public int getVitesseInit() {return vitesseInit;}

    public float getTaille() {
        return taille;
    }

    public int getMasse() {
        return masse;
    }

    public String getNom() {
        return nom;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public abstract double calculerForce(Astre a1);

    public abstract int calculerDistance(Astre a1);

    public abstract Vecteur calculerAcc(ArrayList<Astre> listeA);

    public abstract void setVistesse(ArrayList<Astre> listeA);

    public abstract void setPositions();

}
