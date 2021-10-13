package view;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import logic.Astre;
import logic.Planete;
import logic.Simulation;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.ArrayList;
import java.util.Random;

public class EspaceView extends Pane {

    ArrayList<Astre> listeA;
    ArrayList<Circle> listeC = new ArrayList<>();

    public EspaceView(SimulationView s){
        listeA = s.getSimulation().getListeAstre();

        for (Astre a: listeA) {
            Circle p = creerPlaneteCercle(a);
            p.relocate(a.getPositionX(), a.getPositionY());
            getChildren().add(p);
            a.setN(p);
            listeC.add(p);
            p.relocate(a.getPositionX(), a.getPositionY());
        }
        trajectoire();
    }
    public Circle creerPlaneteCercle(Astre p){
        Circle planete = new Circle();
        planete.setFill(new Color(new Random().nextFloat(),new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()));
        planete.setStrokeWidth(2);
        planete.setStroke(Color.BLUE);
        planete.setRadius(p.getTaille()/2);
        planete.setLayoutX(p.getPositionX());//à la place du random ça marchait pasMath.random() * ( 0 - (1000-p.getTaille())
        planete.setLayoutY(p.getPositionY());//Math.random() * ( 0 - (600-p.getTaille())
        return planete;
    } // voir getPixelWriter pour collision plus tard

    public void trajectoire(){

        Translate translate = new Translate(300, 50, 100);
        for (Circle p: listeC){
            p.getTransforms().add(translate);
        }

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(translate.xProperty(), 0)),//duration=temps
                new KeyFrame(new Duration(1000), new KeyValue(translate.xProperty(), 950))//duration=temps
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
/*
        Rotate rotation = new Rotate(0, 300, 300);
        for (Circle p: listeC){
            p.getTransforms().add(rotation);
        }
        rotation.angleProperty();

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), -1)),
                new KeyFrame(new Duration(1500), new KeyValue(rotation.angleProperty(), 1)) //duration=temps
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
    }

}

