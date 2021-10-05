package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.Simulation;



public class SimulationView extends Stage {

    private PlaneteApp app;
    private Simulation s;
    private BorderPane root;
    private HBox contener;

    public SimulationView(Simulation s,PlaneteApp app) {
        this.s = s;
        this.app = app;
        root = new BorderPane();
        contener = new HBox();
        Label system = new Label(s.toString());
        contener.getChildren().add(system);
        contener.setAlignment(Pos.CENTER);
        root.setCenter(contener);

        Scene scene = new Scene(root);

        app.getStage().setScene(scene);
    }


}
