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
import javafx.scene.layout.VBox;
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
    private VBox field;
    private Label titre;
    private VBox centrage;

    public StartView(PlaneteApp app) {
        this.app = app;
        setWidth(1500);
        setHeight(750);
        welcomeText = new Label();
        root = new BorderPane();
        field = new VBox();
        centrage = new VBox();
        nouveau = new Button("Nouveau Systeme");
        charger = new Button("Charger Systeme");
        titre = new Label("Trajectoire de planètes");
        charger.setOnAction(onChargerSimulation);
        nouveau.setOnAction(onNouvelleSimulation);

        //ImageView background = new ImageView("images/background.png");
        //root.setStyle("-fx-background-image: url(images/background.png);-fx-background-size: 500, 500;-fx-background-repeat: no-repeat;");
        field.setAlignment(Pos.CENTER);
        titre.setAlignment(Pos.CENTER);
        centrage.setAlignment(Pos.CENTER);
        centrage.getChildren().add(titre);
        titre.setStyle("-fx-font-size: 15 px");
        /*background.setFitWidth(1500);
        background.setFitHeight(750);*/


        field.getChildren().addAll(nouveau, charger);

        root.setTop(centrage);
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