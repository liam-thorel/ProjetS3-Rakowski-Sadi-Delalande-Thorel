package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Simulation;

public class StartView extends Pane {
    private Label welcomeText;
    private Button charger;
    private Button nouveau;
    private BorderPane root;
    private PlaneteApp app;
    private VBox field;
    private Label titre;
    private VBox centrage;
    public StartView(PlaneteApp app) {
        this.app = app;
        welcomeText = new Label();
        root = new BorderPane();
        field = new VBox();
        centrage = new VBox();
        nouveau = new Button("Nouveau Systeme");
        charger = new Button("Charger Systeme");
        titre = new Label("Trajectoire de planètes");
        charger.setOnAction(onChargerSimulation);
        nouveau.setOnAction(onNouvelleSimulation);

        field.setAlignment(Pos.CENTER);
        field.setPadding(new Insets(0,0,200,0));
        titre.setAlignment(Pos.CENTER);
        centrage.setAlignment(Pos.CENTER);
        centrage.getChildren().add(titre);
        titre.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());


        ImageView logo = new ImageView(getClass().getResource("/images/logo.png").toExternalForm());
        logo.setFitWidth(753);
        logo.setFitHeight(409);

        // Boutons
        nouveau.setPrefWidth(500);
        charger.setPrefWidth(500);
        field.setSpacing(10);
        field.getChildren().addAll(nouveau, charger);

        //ajout des éléments à la border pane
        root.setTop(centrage);
        root.setCenter(logo);
        root.setBottom(field);
        root.setId("pane");

        //mise en fond du background
        root.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());
        Scene scene = new Scene(root);

        app.getStage().setScene(scene);
    }



    private EventHandler<ActionEvent> onChargerSimulation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
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