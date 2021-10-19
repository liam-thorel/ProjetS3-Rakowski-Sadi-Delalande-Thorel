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
    private Button play;
    private Button pause;
    private BooleanProperty playing = new SimpleBooleanProperty();

    public EspaceView(SimulationView s){
        listeA = s.getSimulation().getListeAstre();
        listeC = new ArrayList<>();
        play = new Button("Play");
        play.setOnAction(lancer);
        pause = new Button("Pause");
        pause.setOnAction(stop);
        this.s = s.getSimulation();
        playing.setValue(false);
        playing.addListener(playOrStop);
        for (Astre a: listeA) {
            Circle p = creerPlaneteCercle(a);
            listeC.add(p);
            getChildren().add(p);
            p.relocate(a.getPositionX(), a.getPositionY());
        }

        GridPane boutons = new GridPane();
        boutons.setAlignment(Pos.BASELINE_RIGHT);
        boutons.add(play, 0, 1);
        boutons.add(pause, 0, 2);

        getChildren().addAll(boutons);

        // mettre a jour avec un relocate a chaque changement de timeline (envent ?)
        //move();


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private EventHandler<ActionEvent> lancer = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(isPressed()) {
                playing.setValue(true);
            }
        }
    };

    private EventHandler<ActionEvent> stop = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(isPressed()) {
                playing.setValue(false);
                System.out.println("eho stooop");
            }
        }
    };

    private ChangeListener<Boolean> playOrStop = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            if(t1){
                jouer();
            }


        }

    };


    private void jouer(){
        while(playing.getValue()){
            move();
        }

    }


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
    public void move(){
        listeT = new ArrayList<>();


        for(int i =0; i<listeA.size(); i++){
            Astre current = listeA.get(i);
            System.out.println("ancienne position de " + current.getNom()+ " "+current.getPositionX() + " " + current.getPositionY());
            current.setVistesse(getOther(current));
            current.setPositions();
            System.out.println("nouvelle position de " + current.getNom()+ " "+current.getPositionX() + " " + current.getPositionY());

            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(listeC.get(i).centerXProperty(), 0)),
                    new KeyFrame(Duration.ZERO, new KeyValue(listeC.get(i).centerYProperty(), 0)),
                    new KeyFrame(new Duration (50), new KeyValue(listeC.get(i).centerXProperty(), current.calculerAcc(getOther(current)))),
                    new KeyFrame(new Duration (50), new KeyValue(listeC.get(i).centerYProperty(), current.calculerAcc(getOther(current))))
            );


            /*Ellipse path = new Ellipse();
            path.setStrokeWidth(0.5);
            path.setStroke(Color.RED);
            path.setFill(Color.TRANSPARENT);
            path.setRadiusX(listeA.get(listeA.size() - i -1).getPositionX());
            path.setRadiusY(listeA.get(listeA.size() - i -1).getPositionY());

            PathTransition pathTransition = new PathTransition();
            pathTransition.setNode(listeC.get(i));
            pathTransition.setPath(path);
            pathTransition.setInterpolator(Interpolator.LINEAR);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
           // pathTransition.setAutoReverse(true);
            pathTransition.setDuration(Duration.seconds(1));
            pathTransition.setCycleCount(Transition.INDEFINITE);

            listeT.add(pathTransition);*/




        }

       /* for(PathTransition t : listeT){
            t.play();


        }*/




        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

    }


}

