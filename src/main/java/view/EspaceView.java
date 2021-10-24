package view;

import eu.hansolo.tilesfx.events.TimeEvent;
import eu.hansolo.tilesfx.events.TimeEventListener;
import javafx.animation.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import logic.Astre;
import logic.Planete;
import logic.Simulation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EspaceView extends Pane {

    private ArrayList<Astre> listeA;
    private Simulation s;
    private BooleanProperty playing = new SimpleBooleanProperty();
    private HashMap <Astre, Circle> listeAetC;
    private AnimationTimer timer;

    public EspaceView(SimulationView s){

        listeAetC = new HashMap<>();
        listeA = s.getSimulation().getListeAstre();
        this.s = s.getSimulation();
        playing.setValue(null);
        //playing.addListener(playOrStop);
        for (Astre a: listeA) {
            Circle p = creerPlaneteCercle(a);
            listeAetC.put(a, p);
            getChildren().add(p);
            p.relocate(a.getPositionX(), a.getPositionY());
        }

        // mettre a jour avec un relocate a chaque changement de timeline (envent ?)
        //move();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(playing.getValue()){
                    move();
                }
            }
        };

        timer.start();

    }

    /*private ChangeListener<Boolean> playOrStop = (observableValue, aBoolean, t1) -> {
        System.out.println(playing.getValue());
        if(t1){
            move = new MoveThread();
            move.start();
        }
        else{
            move.stopThread();
        }
    };*/




    public Circle creerPlaneteCercle(Astre p){
        Circle planete = new Circle();
        planete.setFill(new Color(new Random().nextFloat(),new Random().nextFloat(), new Random().nextFloat(), 1));
        planete.setStrokeWidth(2);
        planete.setStroke(Color.BLUE);
        planete.setCenterX(p.getPositionX());
        planete.setCenterY(p.getPositionX());
        planete.setRadius(p.getTaille()/2);
        return planete;
    }



    public void move(){
        for(Astre a : listeA) {
            System.out.println("ancienne position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            a.setVistesse(Simulation.getOther(a, listeA));
            a.setPositions();
            System.out.println("nouvelle position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            listeAetC.get(a).relocate(a.getPositionX(), a.getPositionY());
        }
    }
    public void setPlaying(boolean playing) {
        this.playing.setValue(playing);
    }
}

