package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
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
    private File emplacement;




    public PlaneteApp() {
    }

    @Override
    public void start(Stage stage)  {
        this.stage = stage;

        if (System.getProperty("os.name").startsWith("Windows")) stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            try {
                this.onStopGame("ATTENTION Voulez vous vraiment arreter la simulation ?");
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

    public void onStopGame(String titre) throws IOException {
        if (!(simulation==null)) {
            Alert alert = new Alert(Alert.AlertType.NONE);
            ButtonType nosave = new ButtonType("Quitter sans enregistrer");
            ButtonType save = new ButtonType("Quitter et enregistrer");
            ButtonType annuler = new ButtonType("Annuler");
            alert.setTitle("Attention");
            alert.setContentText(titre);
            alert.getButtonTypes().addAll(save,nosave,annuler);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == nosave) {
                Platform.exit();
            }
            if (result.isPresent() && result.get() == save) {
                String erreur = getfilechooser(false);
                if (erreur.equals("")){
                    Platform.exit();
                }
                else {
                    onStopGame(erreur);
                }
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

    public String getfilechooser (Boolean type) { // si true alors enregistrer si false alors sauvegarder
        if (type) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(//
                    new FileChooser.ExtensionFilter("Simu", "*.simu"), //
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            fileChooser.setTitle("Selectionner un fichier à charger");
            if (emplacement!=null){
                System.out.println(emplacement.getAbsolutePath()); // faut changer pour que ce soit le vrais emplacement la ça return avec le fichier genre a.simu
            }
            else {
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            File s = fileChooser.showOpenDialog(new Stage());
            try {
                emplacement = s;
                initSimulation(new Simulation(s));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                return "erreur fichier non choisit";
            }
            return "";
        }
        else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selectionner un endroit ou enregistrer le fichier");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Simu", "*.simu"));
            if (emplacement != null){
                fileChooser.setInitialDirectory(new File (emplacement.getAbsolutePath())); // faut changer pour que ce soit le vrais emplacement
            }
            else {
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            File s =fileChooser.showSaveDialog(new Stage());
            try {
                simulation.saveListeAstre(s);
                emplacement = s;
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e){
                return "erreur emplacement d'enregistrement non spécifié";
            }
        }

        return "";
    }
}