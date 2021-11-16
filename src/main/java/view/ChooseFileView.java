package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Simulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ChooseFileView extends Stage {

    private Button chargerFile, chargerSystemeSolaire;
    private PlaneteApp app;
    private BorderPane root;
    public File fichierSimu;
    private VBox boutons;
    private Label erreur;

    public ChooseFileView(PlaneteApp app) {
        this.app = app;
        root = new BorderPane();
        boutons = new VBox();

        chargerSystemeSolaire = new Button("charger Système Solaire");
        chargerSystemeSolaire.setOnAction(chargerSystemeSol);



        chargerFile = new Button("charger simulation");
        chargerFile.setOnAction(chargerFileEvent);
        boutons.getChildren().add(chargerFile);

        root.setCenter(boutons);
        root.setTop(chargerSystemeSolaire);
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        Scene scene = new Scene(root);
        app.getStage().setScene(scene);


    }

    private EventHandler<ActionEvent> chargerFileEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            boutons.getChildren().remove(erreur);
            boutons.getChildren().add(erreur = new Label(app.getfilechooser()));
        }
    };

    private EventHandler<ActionEvent> chargerSystemeSol = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                app.initSimulation(new Simulation(new File("presets/systeme solaire.simu")));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    public File getFichierSimu() {
        return fichierSimu;
    }
}
