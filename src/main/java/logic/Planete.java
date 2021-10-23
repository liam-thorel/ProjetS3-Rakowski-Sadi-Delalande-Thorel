package logic;

import java.util.ArrayList;

public class Planete extends Astre{
    private int taille;
    private int masse;
    private String nom;
    private double positionX;
    private double positionY;
    //private int vecteurX = 1;
    //private int vecteurY = 1 ;
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

    public Vecteur calculerSommeForces(ArrayList<Astre> liste){

        double force;
        Vecteur vSommeForces = new Vecteur(0,0);

        for(Astre a : liste){

                force = calculerForce(a);
                System.out.println("f = " + force);
                Vecteur vX = new Vecteur(a.getPositionX(), this.positionX);

                Vecteur vY = new Vecteur(a.getPositionY(), this.positionY);
                System.out.println("ancien vX = " + vX + " , ancien vY = " + vY);
                double distanceX = vX.getX() - vX.getY();
                System.out.println("distanceX = " + distanceX);
                double distanceY = vY.getX() - vY.getY();
                System.out.println("distanceY = " + distanceY);
                vX.setX(vX.getX() / (distanceX) * force);
                vX.setY(vX.getY() / (distanceX) * force);
                vY.setX(vY.getX() / (distanceY) * force);
                vY.setY(vY.getY() / (distanceY) * force);

                System.out.println("new vX = " + vX + " , new vY = " + vY);
                vSommeForces.setX(vSommeForces.getX() + Vecteur.calculerNorme(vX));
                vSommeForces.setY(vSommeForces.getY() + Vecteur.calculerNorme(vY));
                System.out.println("vSommeForce = " + vSommeForces);
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
        System.out.println("vecteur acceleration = " + vecteurAcc);
        vecteurAcc.setX((int) (vecteurAcc.getX()/60));
        vecteurAcc.setY((int) (vecteurAcc.getY()/60));

        return vecteurAcc;
    }

    @Override
    public void setVistesse(ArrayList<Astre> listeA){

        this.vitesseX += this.calculerAcc(listeA).getX();
        System.out.println("vitesse X " + vitesseX);
        this.vitesseY += this.calculerAcc(listeA).getY();
        System.out.println("vitesse Y " + vitesseY);
    }

    public void setPositions(){
        positionX += vitesseX * Math.pow(10, -8);
        positionY += vitesseY * Math.pow(10,-8);
        System.out.println("pX = " + positionX + " pY = " + positionY);
    }



    @Override

    //FAUT LE REFAIRE
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