package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class OptionView extends Pane {

    private SimulationView sV;
    private GridPane bouttons;
    public OptionView(SimulationView sV){
        this.sV = sV;
        bouttons = new GridPane();
        //le fond legerement transparent
        Rectangle fond = new Rectangle();
        fond.setHeight(sV.getHeight());
        fond.setWidth(sV.getWidth());
        fond.setFill(Color.GREY);
        fond.setOpacity(60);

        //le fond des bouttons
        Rectangle fondBouttons = new Rectangle();
        fondBouttons.setX(200);
        fondBouttons.setY(200);
        fondBouttons.setFill(Color.rgb(64,68,75,1));

        //les bouttons
        Button retour = new Button("Retour à la simulation");
        Button saveEtContinuer = new Button("Sauvegarder et continuer");
        Button saveEtQuit = new Button("Sauvegarder et quitter");
        Button parametre = new Button("Parametres");

        // le gridPane contenant les bouttons
        bouttons.setAlignment(Pos.CENTER);
        bouttons.add(retour, 0, 4);
        bouttons.add(saveEtContinuer, 1, 1);
        bouttons.add(saveEtQuit, 1,2);
        bouttons.add(parametre, 1,3);

        this.getChildren().addAll(fond, fondBouttons, bouttons);

    }

}
