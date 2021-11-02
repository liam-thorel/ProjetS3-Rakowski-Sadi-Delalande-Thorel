package logic;

import java.util.ArrayList;

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
            double distanceX = (a.getPositionX() - this.positionX) * Simulation.scaleDistance;
            double distanceY = (a.getPositionY() - this.positionY) * Simulation.scaleDistance;
            double distance = Math.sqrt(Math.pow(Math.abs(distanceX), 2) + (Math.pow(Math.abs(distanceY), 2) + 1.f));

            vSommeForces.incrementXBy(Simulation.g * (distanceX * (a.getMasse() * Simulation.scaleMasse)/ Math.pow(distance, 3)/ Simulation.simuRate));
            vSommeForces.incrementYBy(Simulation.g * (distanceY * (a.getMasse() * Simulation.scaleMasse) / Math.pow(distance,3)/ Simulation.simuRate));
        }
        System.out.println("vSommeForces = " + vSommeForces);
        return vSommeForces;
    }



    @Override
    public void addVitesse(ArrayList<Astre> listeA){
        Vecteur vAcc;
        if(this.isFixed){
            vAcc = new Vecteur(0, 0);
        }else {

            vAcc = calculerSommeForces(listeA);
        }
        this.vitesseX += (vAcc.getX() * Simulation.scaleTemps) /masse *0.0005;
        this.vitesseY += (vAcc.getY() * Simulation.scaleTemps) /masse *0.0005 ;
        System.out.println("vitesseX = " + vitesseX + "    vitesseY = " + vitesseY);
    }

    public void setPositions(){
        positionX += vitesseX *0.0005;
        positionY += vitesseY *0.0005;
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