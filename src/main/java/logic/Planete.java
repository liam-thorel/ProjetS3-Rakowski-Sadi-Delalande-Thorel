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



    public Planete(String nom, int taille, int masse, double pX, double pY ) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX = pX;
        this.positionY = pY;
        //vecteurX *= vInit;
        //vecteurY *= vInit;

    }

    public static double echelleMasse(double m){
        return Math.pow(m, 20);
    }

    public static double echelleDistance(double d){
        return Math.pow(d,8);
    }

    public Vecteur getVitesse() {
        return new Vecteur(vitesseX,vitesseY);
    }

    @Override
    public double calculerForce(Astre p1){
        return  (echelleMasse(p1.getMasse()) * echelleMasse(this.masse) * Simulation.g)/ Math.pow(echelleDistance(calculerDistance(p1)), 2);
    }

    //Maelis tu penses pas qu'il faut faire un truc comme ca
    public Vecteur calculerForce2(ArrayList<Astre> liste){
        double force;
        Vecteur vTotal = new Vecteur(0,0);
        for(Astre a : liste){
            force = calculerForce(a);
            System.out.println("f = " + force);
            Vecteur vX = new Vecteur(a.getPositionX(), this.positionX);
            Vecteur vY = new Vecteur(a.getPositionY(), this.positionY);
            double distanceX = echelleDistance(Vecteur.calculerLongueur(vX));
            System.out.println("distanceX = " + distanceX);
            double distanceY = echelleDistance(Vecteur.calculerLongueur(vY));
            System.out.println("distanceY = " + distanceY );
            vX.setX(((vX.getX()/distanceX)*force));
            vX.setY((vX.getY()/distanceY)*force);
            vY.setX((vY.getX()/distanceX)*force);
            vY.setY((vY.getY()/distanceY)*force);

            vTotal.setX(vTotal.getX()+echelleDistance(Vecteur.calculerLongueur(vX)));
            vTotal.setY(vTotal.getY()+echelleDistance(Vecteur.calculerLongueur(vY)));

            //trouver le rapport pour obtenir
        }
        System.out.println(vTotal);
        return vTotal;
    }

    @Override
    public int calculerDistance(Astre p1){
        return (int) Math.sqrt(Math.pow(p1.getPositionX() - this.positionX, 2) + Math.pow(p1.getPositionY() - this.positionY, 2));
    }

    @Override
    public Vecteur calculerAcc(ArrayList<Astre> listeA){
        Vecteur r = calculerForce2(listeA);
        r.setX((int) (r.getX()/echelleMasse(this.masse)));
        r.setY((int) (r.getY()/echelleMasse(this.masse)));
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
        return nom + " "+ taille+ " " + masse+ " " + (int) positionX+ " " + (int) positionY + " "  + "\n";
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