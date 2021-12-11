package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Simulation;
import modelView.ISimulation;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PlaneteApp extends Application {

    private ChooseFileView chooseFileView;
    private Stage stage;
    private StartView startView;
    private SimulationView simulationView;
    private Simulation simulation;
    private File emplacement;
    private Boolean debug = true;
    private CreditView creditView;




    public PlaneteApp() {
    }

    @Override
    public void start(Stage stage)  {
        this.stage = stage;

        stage.setMaximized(true);
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
        stage.show();


    }

    public Stage getStage() {
        return stage;
    }

    public void initChooseFile(){
        stage.setMaximized(false);
        chooseFileView = new ChooseFileView(this);
        stage.setMaximized(true);
    }

    public void initStart(){
        stage.setMaximized(false);
        startView = new StartView(this);
        stage.setMaximized(true);

    }

    public void initCredit(){
        stage.setMaximized(false);
        creditView = new CreditView(this);
        stage.setMaximized(true);

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


    public String getfilechooser (Boolean type) { // si true alors enregistrer si false alors sauvegarder
        FileChooser fileChooser = new FileChooser();
        if (type) {
            fileChooser.getExtensionFilters().addAll(//
                    new FileChooser.ExtensionFilter("Simu", "*.simu"), //
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            fileChooser.setTitle("Selectionner un fichier à charger");
            if (emplacement!=null){
               if(debug)System.out.println(emplacement.getAbsolutePath() + System.getProperty("file.separator") + ".." );
            }
            else {
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            File s = fileChooser.showOpenDialog(new Stage());
            try {
                emplacement = s;
                initSimulation(ISimulation.setAPartirDunFichier(s));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                return "erreur fichier non choisi";
            }
            return "";
        }
        else {
            fileChooser.setTitle("Selectionner un endroit ou enregistrer le fichier");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Simu", "*.simu"));
            if (emplacement != null){
                fileChooser.setInitialDirectory(new File (emplacement.getAbsolutePath()  + System.getProperty("file.separator") + ".."));
            }
            else {
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            File s =fileChooser.showSaveDialog(new Stage());
            try {
                ISimulation.saveListeAstre(s, simulation.getListeAstre());
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

    public Boolean getDebug() {
        return debug;
    }
}