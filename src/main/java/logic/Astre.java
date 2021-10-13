package logic;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

import java.io.Serializable;

import static javafx.scene.paint.Color.WHITE;

public abstract class Astre implements Serializable {
    private float taille;
    private float masse;
    private String nom;
    private double positionX;
    private double positionY;
    public abstract String getArgString();
    public abstract String toString();
    private static Node n;

    public static void setN(Node n) {
        Astre.n = n;
    }

    public Node getNode() {
        return n;
    }

    public float getTaille() {
        return taille;
    }

    public float getMasse() {
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

}
