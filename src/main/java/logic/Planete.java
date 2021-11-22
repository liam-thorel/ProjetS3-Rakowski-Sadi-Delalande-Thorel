package logic;


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


    /** calcule la somme des forces que recoit this des autres
     * @param liste les astres qui ont une influence sur this
     * @return le vesteur de l'acceleration de this par rapport aux autres planetes
     * */
    public Vecteur calculerSommeForces(ArrayList<Astre> liste){
        Vecteur vSommeForces = new Vecteur(0,0);

        for(Astre a : liste){
            double distanceX = (a.getPositionX() - this.positionX) * Simulation.scaleDistance;
            double distanceY = (a.getPositionY() - this.positionY) * Simulation.scaleDistance;
            double distance = Math.sqrt(Math.pow(Math.abs(distanceX), 2) + (Math.pow(Math.abs(distanceY), 2) + 1.f));

            vSommeForces.incrementXBy(Simulation.g * (distanceX * (a.getMasse() * Simulation.scaleMasse)/ Math.pow(distance, 3)/ Simulation.simuRate));
            vSommeForces.incrementYBy(Simulation.g * (distanceY * (a.getMasse() * Simulation.scaleMasse) / Math.pow(distance,3)/ Simulation.simuRate));
        }
        //System.out.println("vSommeForces = " + vSommeForces);
        return vSommeForces;
    }


    /** ajoute la vitesse a this et multiplie par le pas de temps
     * @param listeA la liste des astres qui influent this
     * */
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
        //System.out.println("vitesseX = " + vitesseX + "    vitesseY = " + vitesseY);
    }

    /**position * le pas de temps*
     **/

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