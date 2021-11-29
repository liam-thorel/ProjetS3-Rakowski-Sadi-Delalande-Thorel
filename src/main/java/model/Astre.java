package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Astre implements Serializable {
    private float taille;
    private float masse;
    private String nom;
    private DoubleProperty positionX = new SimpleDoubleProperty();
    private DoubleProperty positionY = new SimpleDoubleProperty();
    private DoubleProperty vitesseX= new SimpleDoubleProperty();
    private DoubleProperty vitesseY = new SimpleDoubleProperty();
    private boolean isFixed;
    private Color color;


    public abstract String getArgString();
    public abstract String toString();
    private Node n;

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
        return positionX.get();
    }

    public double getPositionY() {
        return positionY.get();
    }

    public double getVitesseX() {
        return vitesseX.get();
    }

    public double getVitesseY() {
        return vitesseY.get();
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


    public void setPositionX(double positionX) {
        this.positionX.set(positionX);
    }

    public void setPositionY(double positionY) {
        this.positionY.set(positionY);
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX.set(vitesseX);
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY.set(vitesseY);
    }

    public abstract  void setAll(float taille, float masse, String nom, double positionX, double positionY, double vitesseX, double vitesseY);

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return  this.color;
    }

    public DoubleProperty positionXProperty() {
        return positionX;
    }

    public DoubleProperty positionYProperty() {
        return positionY;
    }

    public DoubleProperty vitesseXProperty() {
        return vitesseX;
    }

    public DoubleProperty vitesseYProperty() {
        return vitesseY;
    }
}
