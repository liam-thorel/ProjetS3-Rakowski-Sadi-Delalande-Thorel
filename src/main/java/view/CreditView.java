package view;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CreditView extends Stage {
    private Pane root;
    private VBox all;
    private Button retourEcranAccueil;
    private Label remerciement;
    private Label tuteur;
    private Label createur;
    private Label createurs;

    public CreditView(PlaneteApp app){
        root = new Pane();
        Label test = new Label("TEST");

        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        root.getChildren().add(test);

        Scene scene = new Scene(root);
        app.getStage().setScene(scene);
    }

}
