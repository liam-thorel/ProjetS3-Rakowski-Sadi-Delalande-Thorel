package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
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

    public DoubleProperty vitesseYProperty() {return vitesseY;}

    public void incrementTaille(double increment){this.taille += increment;}

    public void incrementMasse(double increment){this.masse += increment;}

    /** calcule la somme des forces que recoit this des autres
     * @param liste les astres qui ont une influence sur this
     * @return le vesteur de l'acceleration de this par rapport aux autres planetes
     * */
    public static Vecteur calculerSommeForces(ArrayList<Astre> liste, Astre current){
        Vecteur acceleration = new Vecteur(0,0);
        System.out.println("je calcule");
        for(Astre a : liste){
            double distanceX = (a.getPositionX() - current.getPositionX()) * Simulation.scaleDistance;
            double distanceY = (a.getPositionY() - current.getPositionY()) * Simulation.scaleDistance;
            double distance = Math.sqrt(Math.pow(Math.abs(distanceX), 2) + (Math.pow(Math.abs(distanceY), 2) + 1.f));


            acceleration.incrementXBy(Simulation.g * (distanceX * (a.getMasse() * Simulation.scaleMasse)/ Math.pow(distance, 3)));
            acceleration.incrementYBy(Simulation.g * (distanceY * (a.getMasse() * Simulation.scaleMasse) / Math.pow(distance,3)));
        }
        //System.out.println("acceleration = " + acceleration);
        return acceleration;
    }


    /** ajoute la vitesse a this et multiplie par le pas de temps
     * @param listeA la liste des astres qui influent this
     * */

    public static void addVitesse(ArrayList<Astre> listeA, Astre current){
        Vecteur vAcc;
        if(current.isFixed()){
            vAcc = new Vecteur(0, 0);
        }else {

            vAcc = calculerSommeForces(listeA,current);
        }
        //System.out.println("je met a jour la vitesse");
        current.setVitesseX(current.getVitesseX() + (vAcc.getX() * Simulation.scaleTemps));
        current.setVitesseY(current.getVitesseY() +(vAcc.getY() * Simulation.scaleTemps))  ;

    }


    /**position * le pas de temps*
     **/

    public static void setPositions(Astre current){
        //System.out.println("je met a jour la position de " + current.getNom());
        //System.out.println("vitesseX = " + current.getVitesseX() + "    vitesseY = " + current.getVitesseY());
        //remplacer 0.0005 par echelle de temps
        current.setPositionX(current.getPositionX() + (current.getVitesseX() * Simulation.scaleTemps )/Simulation.scaleDistance);
        current.setPositionY(current.getPositionY() + (current.getVitesseY() * Simulation.scaleTemps)/Simulation.scaleDistance);
        /*double x = current.getPositionX() + (current.getVitesseX() *0.0005);
        double y = current.getPositionY() + (current.getVitesseY() *0.0005);
        System.out.println("positionX " +x );
        System.out.println("positionY " + y);*/
    }


    public static void addListenerAll(Astre a, ChangeListener<Number> changeListener){
        a.positionXProperty().addListener(changeListener);
        a.positionYProperty().addListener(changeListener);
        a.vitesseXProperty().addListener(changeListener);
        a.vitesseYProperty().addListener(changeListener);
    }

    public static double distanceCentres(Astre a1, Astre a2){
        double distanceX = (a1.getPositionX() - a2.getPositionX());
        double distanceY = (a1.getPositionY() - a2.getPositionY());
        return Math.sqrt(Math.pow(Math.abs(distanceX), 2) + (Math.pow(Math.abs(distanceY), 2)));

    }
    public static Boolean verifCollision(Astre a, Astre b) {
        return distanceCentres(a, b) <= a.getTaille()/2 + b.getTaille()/2;
    }

    public static void collisionFusion(Astre a, Astre b){
        a.incrementMasse(b.getMasse());
        a.incrementTaille(b.getTaille()/2);
        a.setVitesseX((a.getVitesseX()+b.getVitesseX())*a.getMasse()/(b.getMasse()+a.getMasse()));
        a.setVitesseY((a.getVitesseY()+b.getVitesseY())*a.getMasse()/(b.getMasse()+a.getMasse()));
        a.setColor(Color.hsb((a.getColor().getHue()+b.getColor().getHue())/2, (a.getColor().getSaturation()+b.getColor().getSaturation())/2, (a.getColor().getBrightness()+b.getColor().getBrightness())/2));
    }
}
