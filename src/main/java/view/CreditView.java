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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CreditView extends Stage {
    private BorderPane root;
    private Button retourPageAccueil;
    private Label remerciement;
    private Label tuteur;
    private Label createur;
    private PlaneteApp app;
    private VBox text;
    private HBox centrage;
    private Label mae;
    private Label tom;
    private Label lia;
    private Label lei;

    public CreditView(PlaneteApp app){
        this.app=app;
        root = new BorderPane();
        text = new VBox();
        centrage = new HBox();
        remerciement = new Label("Remerciments :");
        retourPageAccueil = new Button("Retour à la Page d'accueil");



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



        text.getChildren().addAll(remerciement,tuteur,createur,tom,mae,lei,lia);
        root.setCenter(text);
        root.setBottom(centrage);

        Scene scene = new Scene(root);
        app.getStage().setScene(scene);
    }

    private EventHandler<ActionEvent> pageAccueil = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            app.initStart();
        }
    };

}
