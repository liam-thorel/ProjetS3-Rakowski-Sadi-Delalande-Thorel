package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Simulation;

import java.io.IOException;

public class StartView extends Pane {
    private Label welcomeText;
    private Label systemText;
    private Button charger;
    private Button nouveau;
    private BorderPane root;
    private PlaneteApp app;
    private HBox field;

    public StartView(PlaneteApp app) {
        this.app = app;
        setWidth(1500);
        setHeight(750);
        welcomeText = new Label();
        root = new BorderPane();
        field = new HBox();
        nouveau = new Button("Nouveau Systeme");
        charger = new Button("Charger Systeme");
        charger.setOnAction(onChargerSimulation);
        nouveau.setOnAction(onNouvelleSimulation);

        //ImageView background = new ImageView("src/main/resources/images/background.png");

        field.setAlignment(Pos.CENTER);
        //background.setFitWidth(1550);
        //background.setFitHeight(750);


        field.getChildren().addAll(nouveau, charger);

        root.setCenter(field);
        root.setBottom(welcomeText);
        Scene scene = new Scene(root);

        app.getStage().setScene(scene);




    }



    private EventHandler<ActionEvent> onChargerSimulation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            welcomeText.setText("Chargement du fichier...");
            app.initChooseFile();
        }


    };

    private EventHandler<ActionEvent> onNouvelleSimulation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            welcomeText.setText("Generation de la nouvelle simulation...");
            Simulation s = new Simulation();
            app.initSimulation(s);

        }
    };




}