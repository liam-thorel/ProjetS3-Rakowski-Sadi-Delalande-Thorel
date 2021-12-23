package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Simulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class ChooseFileView extends Stage {
    /** Bouton charger Fichier */
    private Button chargerFile;
    /** Bouton charger Système solaire */
    private Button chargerSystemeSolaire;
    /** Bouton retour à la page d'accueil */
    private Button retourPageAccueil;
    /** Application courante */
    private PlaneteApp app;
    /** Pane mère */
    private BorderPane root;
    /** Vbox (pane) pour centrer les boutons */
    private VBox boutons;
    /** label pour afficher les erreurs */
    private Label erreur;

    public ChooseFileView(PlaneteApp app) {
        //initialisation des attributs
        this.app = app;
        root = new BorderPane();
        boutons = new VBox();
        chargerSystemeSolaire = new Button("Charger système solaire");
        chargerFile = new Button("Charger simulation");
        retourPageAccueil = new Button("Retour à la Page d'accueil");

        //Stylisation du bouton chargerSystemeSolaire + ajout fonction quand cliquer
        chargerSystemeSolaire.setOnAction(chargerSystemeSol);
        chargerSystemeSolaire.setStyle("-fx-font: 30 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        chargerSystemeSolaire.setPrefSize(500,70);

        //Stylisation du bouton retourPageAccueil + ajout fonction quand cliquer
        retourPageAccueil.setOnAction(pageAccueil);
        retourPageAccueil.setStyle("-fx-font: 30 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        retourPageAccueil.setPrefSize(500,70);

        //Stylisation du bouton chargerFile + ajout fonction quand cliquer
        chargerFile.setOnAction(chargerFileEvent);
        chargerFile.setStyle("-fx-font: 30 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        chargerFile.setPrefSize(500,70);

        //Ajout à la pane mère + stylisation + mise de l'espace entre les boutons
        boutons.getChildren().addAll(chargerSystemeSolaire,chargerFile,retourPageAccueil);
        boutons.setSpacing(50);
        boutons.setAlignment(Pos.CENTER);

        root.setCenter(boutons);
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        //met en scène la pane mère courante
        Scene scene = new Scene(root);
        //affiche la vue courante
        app.getStage().setScene(scene);


    }
    /** Quand bouton ChargerSimulation presser invoque la méthode getfilechooser de PlanetApp pour sélectionner
     * un fichier à charger
     * Affiche une erreur si fichier non sélectionné*/
    private EventHandler<ActionEvent> chargerFileEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            boutons.getChildren().remove(erreur);
            boutons.getChildren().add(erreur = new Label(app.getfilechooser(true)));
        }
    };
    /** Quand bouton charger Systeme Solaire presser charge le système solaire a partir des presets
     * Si le fichier système solaire pas dans preset renvoie une erreur  */
    private EventHandler<ActionEvent> chargerSystemeSol = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                app.initSimulation(Simulation.setAPartirDunFichier(new File("presets/systeme solaire.simu")));
            }catch (NoSuchFileException e) {
                boutons.getChildren().remove(erreur);
                File fichier = new File("presets/systeme solaire.simu");
                boutons.getChildren().add(erreur = new Label("Fichier systeme solaire.simu non trouvé à l'emplacement "  + fichier.getAbsolutePath()));
            }catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    };
    /** Quand bouton retour presser rencoie à la page d'accueil */
    private EventHandler<ActionEvent> pageAccueil = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            app.initStart();
        }
    };

}
