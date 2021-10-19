package logic;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Astre implements Serializable {
    private float taille;
    private float masse;
    private String nom;
    private int positionX;
    private int positionY;
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

    public float getMasse() {
        return masse;
    }

    public String getNom() {
        return nom;
    }

    public int getPositionX() {
        return positionX;
    }


    public int getVitesse() {
        return vitesse;
    }

    public int getPositionY() {
        return positionY;
    }

    public abstract int calculerForce(Astre a1);

    public abstract int calculerDistance(Astre a1);

    public abstract int calculerAcc(ArrayList<Astre> listeA);

    public abstract void setVistesse(ArrayList<Astre> listeA);

    public abstract void setPositions();

}
