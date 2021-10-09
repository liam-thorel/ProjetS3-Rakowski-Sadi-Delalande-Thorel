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
    public String toString() {
        return nom + " {" +
                " taille= " + taille +
                ", masse= " + masse +
                '}'+'\n';
    }
}
