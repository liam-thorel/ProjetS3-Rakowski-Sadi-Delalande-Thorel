package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CreditView extends Stage {
    private Pane root;
    private BorderPane all;
    private Button retourPageAccueil;
    private Label remerciement;
    private Label tuteur;
    private Label createur;
    private Label createurs;
    private PlaneteApp app;

    public CreditView(PlaneteApp app){
        this.app=app;
        root = new Pane();
        all = new BorderPane();

        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        retourPageAccueil = new Button("Retour à la Page d'accueil");
        retourPageAccueil.setOnAction(pageAccueil);
        retourPageAccueil.setStyle("-fx-font: 35 arial;-fx-font-family: OpenSymbol;-fx-font-weight: bold;");
        retourPageAccueil.setPrefSize(500,70);

        all.setBottom(retourPageAccueil);

        root.getChildren().add(all);

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
