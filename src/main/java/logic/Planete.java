package logic;

public class Planete extends Astre{
    private float taille;
    private float masse;
    private String nom;

    public Planete(String nom, float taille, float masse ) {
        this.taille = taille;
        this.masse = masse;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom + " {" +
                " taille= " + taille +
                ", masse= " + masse +
                '}'+'\n';
    }
}
