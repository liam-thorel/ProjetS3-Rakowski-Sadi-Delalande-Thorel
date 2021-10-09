package logic;

public class Planete extends Astre{
    private float taille;
    private float masse;
    private String nom;
    private int positionX;
    private int positionY;


    public Planete(String nom, float taille, float masse, int pX, int pY ) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
        this.positionX = pX;
        this.positionY = pY;
    }

    @Override
    public String getArgString(){
        return nom + " "+ taille+ " " + masse+ " " + positionX+ " " + positionY + "\n";
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
