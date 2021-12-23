package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CreditView extends Stage {
    /**Pane mère */
    private BorderPane root;
    /** retourPageAccueil */
    private Button retourPageAccueil;
    /** Label remerciement */
    private Label remerciement;
    /** Label nom de notre tuteur */
    private Label tuteur;
    /** Label Createur */
    private Label createur;
    /** Application courante */
    private PlaneteApp app;
    /** Totale des label pour créer les wraps */
    private VBox text;
    /** Centre le bouton */
    private HBox centrage;
    /** Label Maelis Rakowski */
    private Label mae;
    /** Label Delaalnde Tom */
    private Label tom;
    /** Label Liam Thore */
    private Label lia;
    /** Label Leina Sadi */
    private Label lei;

    public CreditView(PlaneteApp app){
        // intialisation attributs
        this.app=app;
        root = new BorderPane();
        text = new VBox();
        centrage = new HBox();
        remerciement = new Label("Remerciments :");
        retourPageAccueil = new Button("Retour à la Page d'accueil");


        // Stylisation
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());
        remerciement.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());
        remerciement.setPadding(new Insets(20,0,0,30));

        tuteur = new Label("Notre tuteur pour ce projet Rosenfeld Matthieu");
        tuteur.setStyle("-fx-font-size: 25");
        tuteur.setPadding(new Insets(30,0,0,60));

        createur = new Label("Créateurs :");
        createur.getStylesheets().add(this.getClass().getResource("/Css/accueil.css").toExternalForm());
        createur.setPadding(new Insets(40,0,0,30));

        mae = new Label("• Maelis Rakowski");
        mae.setStyle("-fx-font-size: 25");
        mae.setPadding(new Insets(50,0,0,60));

        tom = new Label("• Tom Delalande");
        tom.setStyle("-fx-font-size: 25");
        tom.setPadding(new Insets(50,0,0,60));

        lia = new Label("• Liam Thorel");
        lia.setStyle("-fx-font-size: 25");
        lia.setPadding(new Insets(50,0,0,60));

        lei = new Label("• Leina Sadi");
        lei.setStyle("-fx-font-size: 25");
        lei.setPadding(new Insets(50,0,0,60));

        retourPageAccueil.setAlignment(Pos.CENTER);

        retourPageAccueil.setOnAction(pageAccueil);
        retourPageAccueil.setStyle("-fx-font: 30 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        centrage.setAlignment(Pos.CENTER);
        centrage.getChildren().add(retourPageAccueil);
        centrage.setPadding(new Insets(0,0,50,0));


        // texte total
        text.getChildren().addAll(createur,tom,mae,lei,lia,remerciement,tuteur);
        //mise sur l'écran des Panes au bon endroit
        root.setCenter(text);
        root.setBottom(centrage);
        //met la Pane mère dans la scène courante
        Scene scene = new Scene(root);
        // affiche la scène courante dans la fenêtre mère
        app.getStage().setScene(scene);
    }
    /** Quand bouton pageAccueil pressé affiche la page d'accueil */
    private EventHandler<ActionEvent> pageAccueil = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            app.initStart();
        }
    };

}
