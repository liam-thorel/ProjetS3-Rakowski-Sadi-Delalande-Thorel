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
    private Label createurs;
    private PlaneteApp app;
    private VBox text;
    private HBox centrage;

    public CreditView(PlaneteApp app){
        this.app=app;
        root = new BorderPane();
        text = new VBox();
        centrage = new HBox();
        remerciement = new Label("Remerciments :");
        retourPageAccueil = new Button("Retour à la Page d'accueil");

        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        retourPageAccueil.setAlignment(Pos.CENTER);

        retourPageAccueil.setOnAction(pageAccueil);
        retourPageAccueil.setStyle("-fx-font: 30 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        centrage.setAlignment(Pos.CENTER);
        centrage.getChildren().add(retourPageAccueil);
        centrage.setPadding(new Insets(0,0,50,0));



        text.getChildren().add(remerciement);
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
