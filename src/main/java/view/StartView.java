package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Simulation;

public class StartView extends Pane {
    //Pane mère
    private BorderPane root;
    //application courante
    private PlaneteApp app;
    //Bouton pour charger une simulation
    private Button charger;
    //Bouton pour créer une nouvelle simulation
    private Button nouvelle;
    //Bouton d'accès au crédit
    private Button credit;
    //Conteneur des Boutons
    private VBox field;
    //Texte du Titre
    private Label titre;
    //Conteneur pour centrer le titre
    private VBox centrage;
    //Logo
    private ImageView logo;
    public StartView(PlaneteApp app) {

        //Initialisation des attributs
        this.app = app;
        root = new BorderPane();
        field = new VBox();
        centrage = new VBox();
        nouvelle = new Button("Nouveau Systeme");
        charger = new Button("Charger Systeme");
        credit = new Button("Crédit");
        titre = new Label("Trajectoire de planètes");

        //Quand bouton presser utiliser la méthode :
        charger.setOnAction(onChargerSimulation);
        nouvelle.setOnAction(onNouvelleSimulation);
        credit.setOnAction(onCredit);

        //Stylisation du conteneur des boutons
        field.setAlignment(Pos.CENTER);
        field.setPadding(new Insets(0,0,200,0));
        titre.setAlignment(Pos.CENTER);
        centrage.setAlignment(Pos.CENTER);
        centrage.getChildren().add(titre);
        titre.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());

        //Chargement du logo et définition de la taille
        logo = new ImageView(getClass().getResource("/images/logo.png").toExternalForm());
        logo.setFitWidth(753);
        logo.setFitHeight(409);

        //stylisation des Boutons
        nouvelle.setPrefWidth(500);
        charger.setPrefWidth(500);
        credit.setPrefWidth(500);
        field.setSpacing(10);
        field.getChildren().addAll(nouvelle, charger,credit);

        //ajout des éléments à la pane mère
        root.setTop(centrage);
        root.setCenter(logo);
        root.setBottom(field);
        root.setId("pane");

        //mise en fond du background
        root.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());
        Scene scene = new Scene(root);

        app.getStage().setScene(scene);
    }


    //Quand le bouton charger est pressé initialise la vue ChooseFileView
    private EventHandler<ActionEvent> onChargerSimulation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            app.initChooseFile();
        }


    };

    // Quand le bouton nouvelle est pressé initialise une nouvelle simulation
    private EventHandler<ActionEvent> onNouvelleSimulation = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Simulation s = new Simulation();
            app.initSimulation(s);

        }
    };

    // Quand le bouton credit est pressé initialise la vue crédit
    private EventHandler<ActionEvent> onCredit = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            app.initCredit();
        }
    };




}