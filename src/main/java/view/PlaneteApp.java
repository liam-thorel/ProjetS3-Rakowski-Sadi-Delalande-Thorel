package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Simulation;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class PlaneteApp extends Application {
    /**vue pour choisir un fichier*/
    private ChooseFileView chooseFileView;
    /**fenêtre d'application courrante*/
    private Stage stage;
    /**vue afficher au démarage de l'application*/
    private StartView startView;
    /**vue de la simulation*/
    private SimulationView simulationView;
    /**Logique de la simulation*/
    private Simulation simulation;
    /**emplacement courant de chargement du dernier fichier*/
    private File emplacement;
    /**attribut activer/desactiver le debug*/
    public static Boolean debug = false;
    /**vue des crédits*/
    private CreditView creditView;

    /** Methode start de Class abstraite Application */
    @Override
    public void start(Stage stage)  {
        //initialisation fenêtre courante
        this.stage = stage;

        //mise en grand de la fenêtre
        stage.setMaximized(true);
        /**Vérifie si une personne clique sur la croix rouge pour fermer la fenêtre et affiche
         * une fenêtre de confirmation
         */
        stage.setOnCloseRequest(event -> {
            try {
                this.onStopGame("ATTENTION Voulez vous vraiment arreter la simulation ?");
            } catch (IOException e) {
                e.printStackTrace();
            }
            event.consume();
        });
        //Lance la simulation
        initStart();
        //Met le titre Simulation Planète à la fenêtre
        stage.setTitle("Simulation Planète");
        //affiche la fenêtre
        stage.show();
    }

    /**Lance l'application*/
    public static void main(String[] args) {
        launch(args);
    }

    /**Crée la vue ChooseFileView*/
    public void initChooseFile(){
        stage.setMaximized(false);
        chooseFileView = new ChooseFileView(this);
        stage.setMaximized(true);
    }

    /**Crée la vue afficher au démarrage de l'application*/
    public void initStart(){
        stage.setMaximized(false);
        if(simulationView != null){
            simulationView.detroy();
            simulationView = null;
            simulation = null;
        }
        startView = new StartView(this);

        stage.setMaximized(true);

    }

    /**Crée la vue crédit*/
    public void initCredit(){
        stage.setMaximized(false);
        creditView = new CreditView(this);
        stage.setMaximized(true);
    }

    /**Initialise et la logique de la simulation et crée la vue de la simulation*/
    public void initSimulation(Simulation s){
        if(simulationView != null){
            simulationView.detroy();
            simulationView = null;
        }
        this.simulation=s;
        simulationView = new SimulationView(s, this);
    }

    /** Affiche un Pop-up lorsque l'on veut fermer la fenêtre,
     * si une simulation est en cours une fenêtre si l'on veut quitter et sauvegarder ou non,
     * sinon si l'on veut vraiment quitter ou non
     * @param titre Texte à afficher
     * */
    public void onStopGame(String titre) throws IOException {
        /**Si une simulation est en cours une fenêtre si l'on veut quitter et sauvegarder ou non*/
        if (!(simulation==null)) {
            //Crée une alerte
            Alert alert = new Alert(Alert.AlertType.NONE);
            //Initialisation des boutons dans l'alerte
            ButtonType nosave = new ButtonType("Quitter sans enregistrer");
            ButtonType save = new ButtonType("Quitter et enregistrer");
            ButtonType annuler = new ButtonType("Annuler");
            //Titre
            alert.setTitle("Attention");
            //affiche le texte décider (l'erreur si erreur il y'a eu)
            alert.setContentText(titre);
            //Ajout des boutons à l'alerte
            alert.getButtonTypes().addAll(save,nosave,annuler);
            //Attendre qu'un bouton soit presser
            Optional<ButtonType> result = alert.showAndWait();
            //Si bouton noSave presser fermer la fenêtre
            if (result.isPresent() && result.get() == nosave) {
                Platform.exit();
            }
            //Si bouton save presser sauvegarder et fermer la fenêtre
            if (result.isPresent() && result.get() == save) {
                //Affiche la fenêtre pour sauvegarder si erreur il y'a eu renvoie l'erreur
                String erreur = getfilechooser(false);
                //Vérifie s'il n'y pas eu d'erreur et ferme la fenêtre
                if (erreur.equals("")){
                    Platform.exit();
                }
                //Réaffiche la pop-up de fermeture de fenêtre mais avec l'erreur générer.
                else {
                    onStopGame(erreur);
                }
            }
        }
        /**Si il n'y pas de simulation en cours si l'on veut vraiment quitter ou non*/
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("ATTENTION Voulez vous vraiment arreter la simulation ?");
            Optional<ButtonType> result = alert.showAndWait();
            //Si appuie sur boutton quitter alors quitter la fenêtre
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Platform.exit();
            }
        }
    }



    /** Enregistre ou charge une Simulation
     * @param type le type de chooseFile que l'on veut,si true alors on veut Charger une simulation
     *             si false alors on veut enregistrer la simulation courante
     * @return une erreur s'il y'en a eu une sinon un string vide
     * */
    public String getfilechooser (Boolean type) {
        //créer une fenêtre de choix d'emplacement
        FileChooser fileChooser = new FileChooser();
        if (type) {
            //Ajout de l'extension .simu comme unique extension lisible.
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Simu", "*.simu"));
            //Titre de la fenêtre
            fileChooser.setTitle("Selectionner un fichier à charger");
            //Si un fichier a deja était chargé précédemment afficher la fenêtre sur le même répertoire que précédemment
            if (emplacement!=null){
               if(debug)System.out.println(emplacement.getAbsolutePath() + System.getProperty("file.separator") + ".." );
            }
            //Afficher la fenêtre sur le répertoire de l'utilisateur
            else {
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            //récupère le fichier choisi
            File s = fileChooser.showOpenDialog(new Stage());
            //Essaye de charger le fichier et lancer la simulation
            try {
                emplacement = s;
                initSimulation(Simulation.setAPartirDunFichier(s));
            }
            //gestion de l'erreur au cas ou (ça n'arrive jamais normalement)
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            //Si aucun fichier choisi renvoie l'erreur fichier non choisi
            catch (NullPointerException e) {
                return "erreur fichier non choisi";
            }
            //Si tout ce passe bien renvoie aucune erreur
            return "";
        }
        else {
            fileChooser.setTitle("Selectionner un endroit ou enregistrer le fichier");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Simu", "*.simu"));
            //Si un fichier a deja était chargé précédemment afficher la fenêtre sur le même répertoire que précédemment
            if (emplacement != null){
                fileChooser.setInitialDirectory(new File (emplacement.getAbsolutePath()  + System.getProperty("file.separator") + ".."));
            }
            //Afficher la fenêtre sur le répertoire de l'utilisateur
            else {
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            }
            File s =fileChooser.showSaveDialog(new Stage());
            //Essaye d'enregistrer la simulation courante en tant que fichier pour charger plus tard
            try {
                Simulation.saveListeAstre(s, simulation.getListeAstre());
                emplacement = s;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //renvoie une erreur sinon
            catch (NullPointerException e){
                return "erreur emplacement d'enregistrement non spécifié";
            }
        }
        //Tout s'est bien passé
        return "";
    }

    /** Verifie si débug mode ou non
     * @return booléen debug
     * */
    public Boolean getDebug() {
        return debug;
    }

    public Stage getStage() {
        return stage;
    }
}