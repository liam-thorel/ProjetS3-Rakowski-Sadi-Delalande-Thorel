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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Simulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ChooseFileView extends Stage {

    private Button chargerFile,chargerSystemeSolaire;
    private PlaneteApp app;
    private BorderPane root;

    public ChooseFileView(PlaneteApp app) {
        this.app = app;
        root = new BorderPane();

        chargerSystemeSolaire = new Button("charger Système Solaire");


        chargerFile = new Button("charger simulation");
        chargerFile.setOnAction(chargerFileEvent);

        root.setCenter(chargerFile);
        root.setTop(chargerSystemeSolaire);
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        Scene scene = new Scene(root);
        app.getStage().setScene(scene);


    }

    private EventHandler<ActionEvent> chargerFileEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(//
                    new FileChooser.ExtensionFilter("Simu", "*.simu"), //
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            fileChooser.setTitle("Selectionner un fichier");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = fileChooser.showOpenDialog(new Stage());
            try {
                app.initSimulation(new Simulation(file));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };


}
