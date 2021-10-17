package view;

import javafx.animation.*;
import javafx.geometry.Point3D;
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
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.util.ArrayList;
import java.util.Random;

public class EspaceView extends Pane {

    private ArrayList<Astre> listeA;
    private ArrayList<Circle> listeC;
    private Simulation s;


    public EspaceView(SimulationView s){
        listeA = s.getSimulation().getListeAstre();
        listeC = new ArrayList<>();
        this.s = s.getSimulation();
        for (Astre a: listeA) {
            Circle p = creerPlaneteCercle(a);
            listeC.add(p);
            getChildren().add(p);
            p.relocate(a.getPositionX(), a.getPositionY());
        }
        move();
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
        Timeline timeline = new Timeline();

        for(int i =0; i<listeA.size(); i++){
            Astre current = listeA.get(i);
            System.out.println("ancienne position de " + current.getNom()+ " "+current.getPositionX() + " " + current.getPositionY());
            current.setVistesse(getOther(current));
            current.setPositions();
            System.out.println("nouvelle position de " + current.getNom()+ " "+current.getPositionX() + " " + current.getPositionY());

            Ellipse path = new Ellipse();
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

            listeT.add(pathTransition);




        }

        for(PathTransition t : listeT){
            t.play();


        }




        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

    }


}

