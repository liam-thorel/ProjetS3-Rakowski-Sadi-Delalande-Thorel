package view;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.Simulation;

import java.io.IOException;
import java.net.MalformedURLException;

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
        System.out.println("j'init cF");
        chooseFileView = new ChooseFileView(this);
    }

    public void intitStart(){
        System.out.println("je suis la");

        startView = new StartView(this);

    }

    public void initSimulation(Simulation s){
        simulationView = new SimulationView(s, this);
    }


    public static void main(String[] args) {

        launch(args);

    }
}