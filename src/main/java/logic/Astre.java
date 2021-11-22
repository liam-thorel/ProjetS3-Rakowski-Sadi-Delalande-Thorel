package logic;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Astre implements Serializable {
    private float taille;
    private float masse;
    private String nom;
    private double positionX;
    private double positionY;
    private Color color;
    private double vitesseX;
    private double vitesseY;


    public abstract String getArgString();
    public abstract String toString();
    private Node n;
    private boolean isFixed;

    public boolean isFixed() {
        return isFixed;
    }

    public Node getN() {return n;}

    public void setN(Node n) {this.n = n;}


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

    public abstract void addVitesse(ArrayList<Astre> listeA);

    public abstract void setPositions();

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public double getVitesseY() {
        return vitesseY;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public void setMasse(int masse) {
        this.masse = masse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public abstract  void setAll(float taille, float masse, String nom, double positionX, double positionY, double vitesseX, double vitesseY);

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return  this.color;
    }
}
