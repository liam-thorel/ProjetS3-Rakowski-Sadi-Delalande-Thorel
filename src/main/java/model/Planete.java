package model;


import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Planete extends Astre{
    private float taille;
    private float masse;
    private String nom;
    private double positionX;
    private double positionY;
    private double vitesseX;
    private double vitesseY;
    private boolean isFixed;
    private Color color;


    public Planete(String nom, float taille, float masse,  double positionX, double positionY, double vitesseX, double vitesseY, boolean isFixed) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.isFixed = isFixed;
    }


    public Vecteur getVitesse() {
        return new Vecteur(vitesseX,vitesseY);
    }


    public boolean isFixed() {
        return isFixed;
    }


    @Override
    public String getArgString(){
        return nom + " "+ taille+ " " + masse+ " " + (int) positionX+ " " + (int) positionY + " "  + (int) vitesseX+ " " + (int) vitesseY+ " " + isFixed+"\n";
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

    public double getVitesseX() {
        return vitesseX;
    }

    public double getVitesseY() {
        return vitesseY;
    }

    public void setAll(float taille, float masse, String nom, double positionX, double positionY, double vitesseX, double vitesseY){
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX = positionX;
        this.positionY = positionY;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return  this.color;
    }
}