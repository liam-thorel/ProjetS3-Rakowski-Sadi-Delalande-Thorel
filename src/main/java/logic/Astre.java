package logic;

import java.io.Serializable;

public abstract class Astre implements Serializable {
    private float taille;
    private float masse;
    private String nom;

    public abstract String toString();
}
