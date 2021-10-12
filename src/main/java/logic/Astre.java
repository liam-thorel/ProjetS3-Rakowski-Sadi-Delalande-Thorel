package logic;

import java.io.Serializable;

public abstract class Astre implements Serializable {
    private float taille;
    private float masse;
    private String nom;
    private int positionX;
    private int positionY;
    public abstract String getArgString();
    public abstract String toString();

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

    public int getPositionY() {
        return positionY;
    }
}
