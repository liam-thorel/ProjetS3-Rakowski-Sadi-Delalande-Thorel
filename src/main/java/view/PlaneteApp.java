package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Simulation;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PlaneteApp extends Application {

    private ChooseFileView chooseFileView;
    private Stage stage;
    private StartView startView;
    private SimulationView simulationView;
    private Simulation simulation;
    private Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();




    public PlaneteApp() {
    }

    @Override
    public void start(Stage stage)  {
        this.stage = stage;

        if (System.getProperty("os.name").startsWith("Windows")) stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                this.onStopGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        });
        initStart();
        stage.setTitle("Simulation Planète");
        stage.setHeight(dimension.getHeight()-30);
        stage.setWidth(dimension.getWidth());
        stage.show();


    }

    public Stage getStage() {
        return stage;
    }

    public void initChooseFile(){
        chooseFileView = new ChooseFileView(this);
    }

    public void initStart(){
        startView = new StartView(this);

    }

    public void initSimulation(Simulation s){
        this.simulation=s;
        simulationView = new SimulationView(s, this);
    }



    public static void main(String[] args) {

        launch(args);

    }

    public void onStopGame() throws IOException {
        if (!(simulation==null)) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            ButtonType nosave = new ButtonType("Quitter sans sauvegarder");
            ButtonType save = new ButtonType("Quitter et sauvegarder");
            ButtonType annuler = new ButtonType("Annuler");
            alert.setTitle("Attention");
            alert.setContentText("ATTENTION Voulez vous vraiment arreter la simulation O_o ?");
            alert.getButtonTypes().addAll(save,nosave,annuler);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == nosave) {
                Platform.exit();
            }
            if (result.isPresent() && result.get() == save) {
                alert.close();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                fileChooser.setTitle("Sauvegarder");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Simu", "*.simu"));
                File s =fileChooser.showSaveDialog(new Stage());
                simulation.saveListeAstre(s);
                Platform.exit();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("ATTENTION Voulez vous vraiment arreter la simulation 😭 ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Platform.exit();
            }
        }
    }

    public Dimension getDimension() {
        return dimension;
    }

    public String getfilechooser () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(//
                new FileChooser.ExtensionFilter("Simu", "*.simu"), //
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        fileChooser.setTitle("Selectionner un fichier");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File fichierSimu = fileChooser.showOpenDialog(new Stage());
        try {
            initSimulation(new Simulation(fichierSimu));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            return "erreur fichier non choisit";
        }
        return "";
    }
}