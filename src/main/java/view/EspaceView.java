package view;

import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.PointLight;
import javafx.scene.effect.Light;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import logic.Astre;
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
    private long previousL = 0;

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
            p.relocate(p.getCenterX(), p.getCenterY());
        }

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                long delay = l - previousL;
                if(playing.getValue()){
                    move();
                }
                previousL = l;

            }
        };


        timer.start();

    }

    public Circle creerPlaneteCercle(Astre p){
        Circle planete = new Circle();
        planete.setFill(new Color(new Random().nextFloat(),new Random().nextFloat(), new Random().nextFloat(), 1));
        planete.setStrokeWidth(2);
        planete.setStroke(Color.BLUE);
        planete.setCenterX(p.getPositionX() - p.getTaille()/2);
        planete.setCenterY(p.getPositionY() - p.getTaille()/2);
        planete.setRadius(p.getTaille()/2);
        return planete;
    }


    //mettre un pas de temps en arg dans move pour utiliser dans add vitesse

    public void move(){
        for(Astre a : listeA) {
            System.out.println("ancienne position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            a.addVistesse(Simulation.getOther(a, listeA));
            a.setPositions();
            System.out.println("nouvelle position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            Circle currentC = listeAetC.get(a);
            currentC.relocate(a.getPositionX() - a.getTaille()/2, a.getPositionY() - a.getTaille()/2);
            Circle p = new Circle();
            p.setFill(Color.RED);
            p.setCenterX(a.getPositionX());
            p.setCenterY(a.getPositionY());
            p.setRadius(1);

            getChildren().add(p);

        }
    }
    public void setPlaying(boolean playing) {
        this.playing.setValue(playing);
    }
}

