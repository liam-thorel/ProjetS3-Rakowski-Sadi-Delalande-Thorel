package view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.Astre;
import logic.Planete;
import logic.Simulation;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.ArrayList;

public class EspaceView extends Pane {

    ArrayList<Astre> listeA;

    public EspaceView(SimulationView s){
        listeA = s.getSimulation().getListeAstre();

        for (Astre a: listeA) {
            Circle p = creerPlaneteCercle(a);
            getChildren().add(p);
            p.relocate(a.getPositionX(), a.getPositionY());
        }
    }
    public Circle creerPlaneteCercle(Astre p){
        Circle planete = new Circle();
        planete.setFill(Color.RED);
        planete.setCenterX(p.getPositionX());
        planete.setCenterY(p.getPositionX());
        planete.setRadius(p.getTaille()/2);
        return planete;
    }

}

