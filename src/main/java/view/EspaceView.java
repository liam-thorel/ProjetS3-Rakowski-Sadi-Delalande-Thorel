package view;

import javafx.animation.*;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import logic.Astre;
import logic.Simulation;


import java.lang.reflect.Array;
import java.util.*;

public class EspaceView extends Pane {

    public ObservableList<Astre> listeA;
    private Simulation s;
    private BooleanProperty playing = new SimpleBooleanProperty();
    private HashMap <Astre, Circle> listeAetC;
    private AnimationTimer timer;
    private long previousL = 0;



    public EspaceView(SimulationView s){

        listeAetC = new HashMap<>();
        listeA = FXCollections.observableArrayList();
        listeA.addAll(s.getSimulation().getListeAstre());

        this.s = s.getSimulation();
        playing.setValue(null);
        listeA.addListener(addingAstre);
        //playing.addListener(playOrStop);
        /*for (Astre a: listeA) {
            Circle p = creerPlaneteCercle(a);
            listeAetC.put(a, p);
            getChildren().add(p);
            p.relocate(p.getCenterX(), p.getCenterY());
        }*/

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
    // prend un Astre en paramètre et créer un cercle le représentant graphiquement
    public static Circle creerPlaneteCercle(Astre p){
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

        for(Astre a : listeAetC.keySet()) {
            System.out.println("ancienne position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            a.addVitesse(Simulation.getOther(a, s.getListeAstre()));
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



    public void addAstre(Astre a ){
        listeA.set(listeA.size(),a);
    }

    public ListChangeListener<Astre> addingAstre = new ListChangeListener<>() {
        @Override
        public void onChanged(Change<? extends Astre> change) {
            change.next();
            if (change.wasAdded()) {
                for (Astre a : change.getAddedSubList()) {
                    Circle c = creerPlaneteCercle(a);
                    listeAetC.put(a, c);
                    getChildren().add(c);
                    c.relocate(c.getCenterX(), c.getCenterY());
                    s.getListeAstre().add(a);

                }
            }
            if (change.wasRemoved()){
                for (Astre a : change.getRemoved()) { // je crois ça vas ressembler à un truc du genre
                    getChildren().remove(listeAetC.get(a));
                    s.getListeAstre().remove(a);
                    listeAetC.remove(a);
                }
            }
        }
    };

    public void setPlaying(boolean playing) {
        this.playing.setValue(playing);
    }
}

