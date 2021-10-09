package logic;

public class CreateurPlanete extends CreateurAstre{

    @Override
    public Planete factory(String nom, float taille, float masse, int pX, int pY){
        return new Planete(nom, taille, masse, pX, pY);
    }
}
