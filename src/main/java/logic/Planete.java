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

    @Override
    public double calculerForce(Astre p1){
        return  (p1.getMasse() * this.masse * Simulation.g)/ Math.pow(calculerDistance(p1), 2) * Math.pow(10, 14);
    }

    public boolean isFixed() {
        return isFixed;
    }
  //
    public Vecteur calculerSommeForces(ArrayList<Astre> liste){
        Vecteur vSommeForces = new Vecteur(0,0);

        for(Astre a : liste){
            Vecteur vX = new Vecteur(a.getPositionX(), this.positionX);
            Vecteur vY = new Vecteur(a.getPositionY(), this.positionY);

            vSommeForces.setX(vSommeForces.getX() + (Simulation.g * (Vecteur.normalize(vX) * a.getMasse() / (Math.pow(Vecteur.calculerNorme(vX), 2) + 1.f))/ Simulation.simuRate));
            vSommeForces.setY(vSommeForces.getY() + ( Simulation.g * (Vecteur.normalize(vY) *a.getMasse() / (Math.pow(Vecteur.calculerNorme(vY), 2) + 1.f))/ Simulation.simuRate));
        }
        System.out.println("vSommeForces = " + vSommeForces);
        return vSommeForces;
    }

    @Override
    public int calculerDistance(Astre p1){
        return (int) Math.sqrt(Math.pow(p1.getPositionX() - this.positionX, 2) + Math.pow(p1.getPositionY() - this.positionY, 2));
    }

    @Override
    public Vecteur calculerAcc(ArrayList<Astre> listeA){
        Vecteur vecteurAcc;
        if(this.isFixed){
            vecteurAcc = new Vecteur(0, 0);
        }else {

            vecteurAcc = calculerSommeForces(listeA);
        }
        return vecteurAcc;
    }

    @Override
    public void setVistesse(ArrayList<Astre> listeA){

        this.vitesseX += this.calculerAcc(listeA).getX() * 0.001f;
        this.vitesseY += this.calculerAcc(listeA).getY() * 0.001f;
        System.out.println("vitesseX = " + vitesseX + "    vitesseY = " + vitesseY);
    }

    public void setPositions(){
        positionX += vitesseX;
        positionY += vitesseY;
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