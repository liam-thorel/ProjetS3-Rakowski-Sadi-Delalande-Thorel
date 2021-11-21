package logic;

import javafx.scene.paint.Color;

public class CreateurPlanete extends CreateurAstre{

    @Override
    public Planete factory(String nom, float taille, float masse, int pX, int pY, double vitesseX, double vitesseY, boolean isFixed){
        return new Planete(nom, taille, masse, pX, pY, vitesseX, vitesseY, isFixed);
    }
}
