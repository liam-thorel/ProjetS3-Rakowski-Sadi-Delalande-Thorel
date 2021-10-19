package view;

import javafx.animation.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
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
import java.util.Random;

public class EspaceView extends Pane {

    private ArrayList<Astre> listeA;
    private ArrayList<Circle> listeC;
    private Simulation s;
    private Timeline timeline = new Timeline();
    private BooleanProperty playing = new SimpleBooleanProperty();
    private MoveThread move;

    public EspaceView(SimulationView s){
        move = new MoveThread();
        listeA = s.getSimulation().getListeAstre();
        listeC = new ArrayList<>();
        this.s = s.getSimulation();
        playing.setValue(false);
        playing.addListener(playOrStop);
        for (Astre a: listeA) {
            Circle p = creerPlaneteCercle(a);
            listeC.add(p);
            getChildren().add(p);
            p.relocate(a.getPositionX(), a.getPositionY());
        }

        // mettre a jour avec un relocate a chaque changement de timeline (envent ?)
        //move();


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private ChangeListener<Boolean> playOrStop = (observableValue, aBoolean, t1) -> {
        System.out.println(playing.getValue());
        if(t1){
            if(!move.isAlive()){
                move.start();
            }else{
                move.run();
            }
        }
        else{
            try {
                synchronized (move){
                    move.wait();}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };




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



    private ArrayList<Astre> getOther(Astre a){
        ArrayList<Astre> r = new ArrayList<>(listeA);
        if(r.remove(a)){
            return r;
        }
        System.out.println("probleme dans getOther");
        return null;
    }

    private ArrayList<PathTransition> listeT;

    public class MoveThread extends Thread{
        public void run(){
            while(playing.getValue()){
                for(int i =0; i<listeA.size(); i++) {
                    Astre current = listeA.get(i);
                    System.out.println("ancienne position de " + current.getNom() + " " + current.getPositionX() + " " + current.getPositionY());
                    current.setVistesse(getOther(current));
                    current.setPositions();
                    System.out.println("nouvelle position de " + current.getNom() + " " + current.getPositionX() + " " + current.getPositionY());
                    listeC.get(i).relocate(current.getPositionX(), current.getPositionY());
                }
            }
        }
    }

    public void setPlaying(boolean playing) {
        this.playing.setValue(playing);
    }
}

