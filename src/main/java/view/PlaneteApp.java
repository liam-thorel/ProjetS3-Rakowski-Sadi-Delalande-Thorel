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




    public PlaneteApp() {
    }

    @Override
    public void start(Stage stage)  {
        this.stage = stage;
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
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
                Stage popup = new Stage();
                popup.setHeight(105);
                popup.setWidth(500);
                TextArea txta = new TextArea();
                txta.setMaxWidth(480);
                txta.setMaxHeight(30);
                Button bt = new Button("sauvegarder");
                VBox txt = new VBox(txta,bt);
                popup.show();
                popup.setTitle("choisissez un nom de sauvegarde");
                popup.setScene(new Scene(txt));
                bt.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            String s =  txta.getText() + ".txt";
                            if (s.equals(".txt")){
                            s = "default.txt";
                            }
                            s = "saves\\" + s;
                            simulation.saveListeAstre(new File(s));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        popup.hide();
                        Platform.exit();
                    }
                });
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
}