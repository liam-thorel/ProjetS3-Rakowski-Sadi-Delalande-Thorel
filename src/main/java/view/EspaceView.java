package view;


import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import logic.Astre;
import logic.Simulation;


import java.util.*;

public class EspaceView extends Pane {

    //liste des astres de la simulation
    public ObservableList<Astre> listeA;
    private Simulation s;
    //verifie si la simulation est en cours
    private BooleanProperty playing = new SimpleBooleanProperty();
    //pour avoir le cercle d'un astre
    private HashMap <Astre, Circle> listeAetC;
    //pour avoir l'astre d'un cercle
    private HashMap <Circle, Astre> listeCetA;
    //boolean de la trajectoire
    private BooleanProperty showingT = new SimpleBooleanProperty();
    //timer de l'animation
    private AnimationTimer timer;
    private long previousL = 0;
    private SimulationView sV;



    public EspaceView(SimulationView sV){
        this.sV = sV;
        this.s = sV.getSimulation();
        listeAetC = new HashMap<>();
        listeCetA = new HashMap<>();
        listeA = FXCollections.observableArrayList();
        listeA.addListener(addingOrRemovingAstres);
        listeA.addAll(sV.getSimulation().getListeAstre());
        System.out.println(listeA);

        playing.setValue(false);
        showingT.setValue(true);
        showingT.addListener(onShowingT);

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
            //System.out.println("ancienne position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            a.addVitesse(Simulation.getOther(a, s.getListeAstre()));
            a.setPositions();
            //System.out.println("nouvelle position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            Circle currentC = listeAetC.get(a);
            currentC.relocate(a.getPositionX() - a.getTaille()/2, a.getPositionY() - a.getTaille()/2);
            if(showingT.getValue()){
                tracerTrajectoire(a);
            }
        }
    }



    //le listener des ajouts ou des suppression d'astres
    public ListChangeListener<Astre> addingOrRemovingAstres = new ListChangeListener<>() {
        @Override
        public void onChanged(Change<? extends Astre> change) {
            change.next();
            if(change.wasAdded()) {
                for (Astre a : change.getAddedSubList()) {
                    Circle c = creerPlaneteCercle(a);
                    listeAetC.put(a, c);
                    listeCetA.put(c, a);
                    getChildren().add(c);
                    c.relocate(c.getCenterX(), c.getCenterY());
                    c.setOnMouseClicked(selected);
                    if(!s.getListeAstre().contains(a)){
                    s.getListeAstre().add(a);}

                }
            }
            if (change.wasRemoved()) {
                for (Astre a : change.getRemoved()) { // je crois ça vas ressembler à un truc du genre
                    getChildren().remove(listeAetC.get(a));
                    s.getListeAstre().remove(a);
                    listeCetA.remove(listeAetC.get(a));
                    listeAetC.remove(a);

                }
            }
        }
    };


    //eventHandler de la selection des cercles
    public EventHandler<MouseEvent> selected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle selectedC = (Circle) mouseEvent.getSource();
            Astre selectedA = listeCetA.get(selectedC);
            Button supprimer = new Button("Supprimer " + selectedA.getNom());
            EventHandler<ActionEvent> supression = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    listeA.remove(selectedA);
                }
            };
            supprimer.setOnAction(supression);
            sV.getMenu().getMenuSysteme().afficherInfos(selectedA.getArgString(), supprimer);

        }
    };

    private ArrayList<Circle> listeTrajectoires = new ArrayList<>();

    public void tracerTrajectoire(Astre a){
        Circle p = new Circle();
        p.setFill(Color.RED);
        p.setCenterX(a.getPositionX());
        p.setCenterY(a.getPositionY());
        p.setRadius(1);
        ArrayList<Circle> trajectoire;
        listeTrajectoires.add(p);

        getChildren().add(p);
    }


    private ChangeListener<Boolean> onShowingT = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            if(!t1){
                effacerAllTrajectoire();
            }
        }
    };

    public void effacerAllTrajectoire(){
        getChildren().removeAll(listeTrajectoires);
        listeTrajectoires.clear();


    }

    public void setShowingT(boolean showingT) {
        this.showingT.setValue(showingT);
    }

    public boolean isShowingT() {
        return showingT.getValue();
    }


    public void setPlaying(boolean playing) {
        this.playing.setValue(playing);
    }
}

