package view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.Planete;

public class Espace {
    private StackPane zone;

    private Circle creerPlaneteCercle(Planete p){
        Circle planete = new Circle();
        planete.setCenterX(p.getPositionX());
        planete.setCenterY(p.getPositionX());
        planete.setRadius(p.getTaille()/2);
        return planete;
    }
}
