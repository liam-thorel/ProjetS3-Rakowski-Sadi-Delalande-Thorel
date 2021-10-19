package logic;

import javafx.scene.paint.Color;

public class CreateurPlanete extends CreateurAstre{

    @Override
    public Planete factory(String nom, int taille, int masse, int pX, int pY, int vInit){
        return new Planete(nom, taille, masse, pX, pY, vInit);
    }
}
