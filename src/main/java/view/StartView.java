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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Simulation;

import java.io.File;
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
    private Pane background;

    public StartView(PlaneteApp app) {
        this.app = app;
        setWidth(1500);
        setHeight(750);
        welcomeText = new Label();
        background = new Pane();
        root = new BorderPane();
        //root.getStylesheets().addAll(this.getClass().getResource("Css/accueil.css").toExternalForm());
        field = new VBox();
        centrage = new VBox();
        nouveau = new Button("Nouveau Systeme");
        charger = new Button("Charger Systeme");
        titre = new Label("Trajectoire de planètes");
        charger.setOnAction(onChargerSimulation);
        nouveau.setOnAction(onNouvelleSimulation);

        //ImageView background = new ImageView("images/background.png");
        //root.setStyle("-fx-background-image: url(https://c.pxhere.com/photos/1a/9d/stars_background_blue_photoshop_color_space_sky_dark-610854.jpg!d);-fx-background-repeat : stretch;-fx-background-position: center center;-fx-effect: dropshadow(three-pass-box, black, 30, 0.5, 0, 0)");
        field.setAlignment(Pos.CENTER);
        titre.setAlignment(Pos.CENTER);
        centrage.setAlignment(Pos.CENTER);
        centrage.getChildren().add(titre);
        titre.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());

        /*background.setFitWidth(1500);
        background.setFitHeight(750);*/

        nouveau.setPrefWidth(500);
        charger.setPrefWidth(500);
        field.setSpacing(50);
        field.getChildren().addAll(nouveau, charger);
        //background.getChildren().add(root);
        root.setTop(centrage);
        root.setCenter(field);
        root.setBottom(welcomeText);
        root.setId("pane");
        root.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());
        Scene scene = new Scene(root);




        app.getStage().setScene(scene);




    }



    private EventHandler<ActionEvent> onChargerSimulation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            /*welcomeText.setText("Chargement du fichier...");*/
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(//
                    new FileChooser.ExtensionFilter("Simu", "*.simu"), //
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            fileChooser.setTitle("Selectionner un fichier");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = fileChooser.showOpenDialog(new Stage());
            try {
                app.initSimulation(new Simulation(file));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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