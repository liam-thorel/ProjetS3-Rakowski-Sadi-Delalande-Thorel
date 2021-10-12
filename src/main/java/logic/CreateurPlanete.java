package logic;

public class CreateurPlanete extends CreateurAstre{

    @Override
    public Planete factory(String nom, int taille, int masse, int pX, int pY){
        return new Planete(nom, taille, masse, pX, pY);
    }
}
