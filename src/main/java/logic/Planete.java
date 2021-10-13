package logic;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import view.SimulationView;

import static javafx.scene.paint.Color.WHITE;

public class Planete extends Astre{
    private float taille;
    private float masse;
    private String nom;
    private double positionX;
    private double positionY;


    public Planete(String nom, float taille, float masse, int pX, int pY ) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX = pX;
        this.positionY = pY;
    }

    @Override
    public String getArgString(){
        return nom + " "+ taille+ " " + masse+ " " + positionX+ " " + positionY + "\n";
    }

    @Override
    public String toString() {
        return nom + " {" +
                " taille = " + taille +
                ", masse = " + masse +
                ", coordonnées = X " + positionX +
                ", Y : " + positionY +
                '}'+'\n';
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
