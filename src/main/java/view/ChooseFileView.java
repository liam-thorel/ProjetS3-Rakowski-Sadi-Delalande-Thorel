package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelView.ISimulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class ChooseFileView extends Stage {

    private Button chargerFile, chargerSystemeSolaire, retourPageAccueil;
    private PlaneteApp app;
    private Pane root;
    public File fichierSimu;
    private VBox boutons;
    private Label erreur;

    public ChooseFileView(PlaneteApp app) {
        this.app = app;
        root = new Pane();
        boutons = new VBox();

        chargerSystemeSolaire = new Button("Charger système solaire");
        chargerSystemeSolaire.setOnAction(chargerSystemeSol);
        chargerSystemeSolaire.setStyle("-fx-font: 35 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        chargerSystemeSolaire.setPrefSize(500,70);

        retourPageAccueil = new Button("Retour à la Page d'accueil");
        retourPageAccueil.setOnAction(pageAccueil);
        retourPageAccueil.setStyle("-fx-font: 35 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        retourPageAccueil.setPrefSize(500,70);

        chargerFile = new Button("Charger simulation");
        chargerFile.setOnAction(chargerFileEvent);
        chargerFile.setStyle("-fx-font: 35 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        chargerFile.setPrefSize(500,70);


        boutons.getChildren().addAll(chargerSystemeSolaire,chargerFile,retourPageAccueil);
        boutons.setSpacing(50);
        double tailleTotale = (chargerFile.getPrefHeight()+retourPageAccueil.getPrefHeight()+chargerSystemeSolaire.getPrefHeight()+boutons.getSpacing()*2);

        boutons.relocate(app.getStage().getWidth()/2+(chargerFile.getPrefWidth()/3),app.getStage().getHeight()-tailleTotale*2);




        boutons.setAlignment(Pos.CENTER);

        root.getChildren().add(boutons);
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        Scene scene = new Scene(root);
        app.getStage().setScene(scene);


    }

    private EventHandler<ActionEvent> chargerFileEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            boutons.getChildren().remove(erreur);
            boutons.getChildren().add(erreur = new Label(app.getfilechooser(true)));
        }
    };

    private EventHandler<ActionEvent> chargerSystemeSol = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                app.initSimulation(ISimulation.setAPartirDunFichier(new File("presets/systeme solaire.simu")));
            }catch (NoSuchFileException e) {
                boutons.getChildren().remove(erreur);
                boutons.getChildren().add(erreur = new Label("Fichier preset systeme solaire.simu non trouvé"));
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> pageAccueil = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            app.initStart();
        }
    };

    public File getFichierSimu() {
        return fichierSimu;
    }
}
