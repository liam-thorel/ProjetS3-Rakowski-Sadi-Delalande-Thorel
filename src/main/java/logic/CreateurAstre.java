package logic;

import javafx.scene.paint.Color;

public abstract  class CreateurAstre {
    public abstract Astre factory(String nom, float taille, float masse, int pX, int pY, double vitesseX, double vitesseY, boolean isFixed);
}
