package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import logic.Simulation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

public class PlaneteApp extends Application {

    private ChooseFileView chooseFileView;
    private Stage stage;
    private StartView startView;
    private SimulationView simulationView;



    public PlaneteApp() {
    }

    @Override
    public void start(Stage stage)  {
        this.stage = stage;
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            this.onStopGame();
            event.consume();
        });
        intitStart();
        //FXMLLoader fxmlLoader = new FXMLLoader(PlaneteApp.class.getResource("start-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
        stage.setTitle("Simulation Planète");
        stage.setHeight(750);
        stage.setWidth(1500);
        stage.show();


    }

    public Stage getStage() {
        return stage;
    }

    public void initChooseFile(){
        chooseFileView = new ChooseFileView(this);
    }

    public void intitStart(){
        startView = new StartView(this);

    }

    public void initSimulation(Simulation s){
        simulationView = new SimulationView(s, this);
    }


    public static void main(String[] args) {

        launch(args);

    }

    public void onStopGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Voulez vous vraiment nous quitter 😭 ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}