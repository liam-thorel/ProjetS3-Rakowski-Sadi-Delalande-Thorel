package logic;

import java.util.ArrayList;
import java.util.Vector;

public class Planete extends Astre{
    private int taille;
    private int masse;
    private String nom;
    private double positionX;
    private double positionY;
    private double vitesseX;
    private double vitesseY;
    private boolean isFixed;


    public Planete(String nom, int taille, int masse,  double positionX, double positionY, double vitesseX, double vitesseY, boolean isFixed) {
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
  //
    public Vecteur calculerSommeForces(ArrayList<Astre> liste){
        Vecteur vSommeForces = new Vecteur(0,0);

        for(Astre a : liste){
            double distanceX = a.getPositionX() - this.positionX;
            double distanceY = a.getPositionY() - this.positionY;
            double distance = Math.sqrt(Math.pow(Math.abs(distanceX), 2) + (Math.pow(Math.abs(distanceY), 2) + 1.f));

            vSommeForces.setX(vSommeForces.getX() + (Simulation.g * (distanceX * a.getMasse() / Math.pow(distance, 3)/ Simulation.simuRate)));
            vSommeForces.setY(vSommeForces.getY() + (Simulation.g * (distanceY * a.getMasse() / Math.pow(distance,3)/ Simulation.simuRate)));
        }
        System.out.println("vSommeForces = " + vSommeForces);
        return vSommeForces;
    }



    @Override
    public void addVistesse(ArrayList<Astre> listeA){
        Vecteur vAcc;
        if(this.isFixed){
            vAcc = new Vecteur(0, 0);
        }else {

            vAcc = calculerSommeForces(listeA);
        }
        this.vitesseX += vAcc.getX() * 0.001f/masse;
        this.vitesseY += vAcc.getY() * 0.001f/masse;
        System.out.println("vitesseX = " + vitesseX + "    vitesseY = " + vitesseY);
    }

    public void setPositions(){
        positionX += vitesseX * 0.001f;
        positionY += vitesseY * 0.001f;
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

    public int getMasse() {
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