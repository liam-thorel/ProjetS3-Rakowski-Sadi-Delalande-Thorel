package logic;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Planete extends Astre{
    private int taille;
    private int masse;
    private String nom;
    private int positionX;
    private int positionY;
    //private int vecteurX = 1;
    //private int vecteurY = 1 ;
    private int vitesse;



    public Planete(String nom, int taille, int masse, int pX, int pY, int vInit ) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX = pX;
        this.positionY = pY;
        this.vitesse = vInit;
        //vecteurX *= vInit;
        //vecteurY *= vInit;

    }

    public int getVitesse() {
        return vitesse;
    }

    @Override
    public int calculerForce(Astre p1){
        return (int) ((int) (p1.getMasse() * this.masse * Simulation.g)/ Math.pow(calculerDistance(p1), 2));
    }

    @Override
    public int calculerDistance(Astre p1){
        return (int) Math.sqrt(Math.pow(p1.getPositionX() - this.positionX, 2) + Math.pow(p1.getPositionY() - this.positionY, 2));
    }

    @Override
    public int calculerAcc(ArrayList<Astre> listeA){
        int r = 0;
        for(Astre a : listeA){
            r +=calculerForce(a);
        }
        return r/this.masse;
    }

    @Override
    public void setVistesse(ArrayList<Astre> listeA){
        vitesse += calculerAcc(listeA);
    }

    public void setPositions(){
        positionX += vitesse;
        positionY += vitesse;
    }



    @Override
    public String getArgString(){
        return nom + " "+ taille+ " " + masse+ " " + positionX+ " " + positionY + " " + vitesse + "\n";
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

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
