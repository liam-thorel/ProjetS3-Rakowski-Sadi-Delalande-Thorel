package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Planete extends Astre{
    private float taille;
    private float masse;
    private String nom;
    private DoubleProperty positionX = new SimpleDoubleProperty();
    private DoubleProperty positionY = new SimpleDoubleProperty();
    private DoubleProperty vitesseX= new SimpleDoubleProperty();
    private DoubleProperty vitesseY = new SimpleDoubleProperty();
    private boolean isFixed;
    private Color color;


    public Planete(String nom, float taille, float masse,  double positionX, double positionY, double vitesseX, double vitesseY, boolean isFixed) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX.set(positionX);
        this.positionY.set(positionY);
        this.vitesseX.set(vitesseX);
        this.vitesseY.set(vitesseY);
        this.isFixed = isFixed;
    }


    public Vecteur getVitesse() {
        return new Vecteur(vitesseX.getValue(),vitesseY.getValue());
    }


    public boolean isFixed() {
        return isFixed;
    }


    @Override
    public String getArgString(){
        return nom + " "+ taille+ " " + masse+ " " + (int) positionX.get()+ " " + (int) positionY.get() + " "  + (int) vitesseX.get()+ " " + (int) vitesseY.get()+ " " + isFixed+"\n";
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

    public void setAll(float taille, float masse, String nom, double positionX, double positionY, double vitesseX, double vitesseY){
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX.set(positionX);
        this.positionY.set(positionY);
        this.vitesseX.set(vitesseX);
        this.vitesseY.set(vitesseY);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return  this.color;
    }

    @Override
    public void setTaille(float taille) {
        this.taille = taille;
    }

    public void setMasse(float masse) {
        this.masse = masse;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public DoubleProperty positionXProperty() {
        return positionX;
    }

    @Override
    public DoubleProperty positionYProperty() {
        return positionY;
    }

    @Override
    public DoubleProperty vitesseXProperty() {
        return vitesseX;
    }

    @Override
    public DoubleProperty vitesseYProperty() {
        return vitesseY;
    }

    @Override
    public void setPositionX(double positionX) {
        this.positionX.set(positionX);
    }

    @Override
    public void setPositionY(double positionY) {
        this.positionY.set(positionY);
    }

    @Override
    public void setVitesseX(double vitesseX) {
        this.vitesseX.set(vitesseX);
    }

    @Override
    public void setVitesseY(double vitesseY) {
        this.vitesseY.set(vitesseY);
    }


    public static void addVitesse(ArrayList<Astre> listeA, Astre current){
        Astre.addVitesse(listeA, current);
    }

    public static void setPositions(Astre current){
        Astre.setPositions(current);
        System.out.println("je passe par planete");
    }

    public static void addListenerAll(Astre a, ChangeListener<Number> changeListener){
        Astre.addListenerAll(a, changeListener);
    }

}