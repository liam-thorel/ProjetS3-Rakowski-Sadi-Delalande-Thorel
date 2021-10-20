package logic;

import java.util.ArrayList;

public class Planete extends Astre{
    private int taille;
    private int masse;
    private String nom;
    private int positionX;
    private int positionY;
    //private int vecteurX = 1;
    //private int vecteurY = 1 ;
    private int vitesseX;
    private int vitesseY;



    public Planete(String nom, int taille, int masse, int pX, int pY, int vInit ) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX = pX;
        this.positionY = pY;
        //vecteurX *= vInit;
        //vecteurY *= vInit;

    }

    public Vecteur getVitesse() {
        return new Vecteur(vitesseX,vitesseY);
    }

    @Override
    public int calculerForce(Astre p1){
        return (int) ((int) (p1.getMasse() * this.masse * Simulation.g)/ Math.pow(calculerDistance(p1), 2));
    }

    //Maelis tu penses pas qu'il faut faire un truc comme ca
    public Vecteur calculerForce2(ArrayList<Astre> liste){
        int force;
        Vecteur vTotal = new Vecteur(0,0);
        for(Astre a : liste){
            force = calculerForce(a);
            Vecteur vX = new Vecteur(a.getPositionX(), this.positionX);
            Vecteur vY = new Vecteur(a.getPositionY(), this.positionY);
            int distanceX = Vecteur.calculerLongueur(vX);
            int distanceY = Vecteur.calculerLongueur(vY);
            vX.setX((vX.getX()/distanceX)*force);
            vX.setY((vX.getY()/distanceY)*force);
            vY.setX((vY.getX()/distanceX)*force);
            vY.setY((vY.getY()/distanceY)*force);

            vTotal.setX(vTotal.getX()+Vecteur.calculerLongueur(vX));
            vTotal.setY(vTotal.getY()+Vecteur.calculerLongueur(vY));

            //trouver le rapport pour obtenir
        }
        return null;
    }

    @Override
    public int calculerDistance(Astre p1){
        return (int) Math.sqrt(Math.pow(p1.getPositionX() - this.positionX, 2) + Math.pow(p1.getPositionY() - this.positionY, 2));
    }

    @Override
    public Vecteur calculerAcc(ArrayList<Astre> listeA){
        Vecteur r = calculerForce2(listeA);
        r.setX(r.getX()/this.masse);
        r.setY(r.getY()/this.masse);
        return r;
    }

    @Override
    public void setVistesse(ArrayList<Astre> listeA){
        this.vitesseX += this.calculerAcc(listeA).getX();
        this.vitesseY += this.calculerAcc(listeA).getY();
    }

    public void setPositions(){
        positionX += vitesseX;
        positionY += vitesseY;
    }



    @Override

    //FAUT LE REFAIRE
    public String getArgString(){
        return nom + " "+ taille+ " " + masse+ " " + positionX+ " " + positionY + " "  + "\n";
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