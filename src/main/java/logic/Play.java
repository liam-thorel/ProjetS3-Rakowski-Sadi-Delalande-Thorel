package logic;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.SimulationView;

import static javafx.scene.paint.Color.WHITE;
import javafx.animation.KeyValue;


public class Play {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Simulation s = new Simulation();

        CreateurPlanete p = new CreateurPlanete();
        Planete p1 = p.factory("blue",25,25, 0, 0);
        Planete p2 = p.factory("green", 50, 50, 10, 300);
        Planete p3 = p.factory("red", 30, 30, 500, 70);

        ArrayList<Astre> listeA = new ArrayList<>();
        listeA.add(p1);
        listeA.add(p2);
        listeA.add(p3);

        s.setListeAstre(listeA);

        Simulation s2 = new Simulation(new File("save.txt"));
        s2.saveListeAstre(new File("save2.txt"));
        System.out.println(s2);
        System.out.println(s);


        // Leïna qui va tenter des trucs
        /*
        //@Override
        public void trajectoire() { //stage en paramètre
            final Circle c = new Circle(100, 100, 150, WHITE);
            SimulationView.getRoot().getChildren().add(c);
            Rotate rotation = new Rotate(0, 3, 29);
            c.getTransforms().add(rotation);
            rotation.angleProperty();
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), -45)),
                    new KeyFrame(new Duration(1000), new KeyValue(rotation.angleProperty(), 45))
            );
            timeline.setAutoReverse(true);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

         */
    }
}
