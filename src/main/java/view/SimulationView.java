package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Simulation;



public class SimulationView extends Stage {

    private PlaneteApp app;
    private Simulation s;
    private VBox root;
    private HBox contener;

    public SimulationView(Simulation s,PlaneteApp app) {
        this.s = s;
        this.app = app;
        root = new VBox();
        contener = new HBox();
        StackPane test = new StackPane();
        Label system = new Label(s.toString());
        contener.getChildren().add(system);
        contener.setAlignment(Pos.CENTER);
        Border border1 = new Border(
                new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(1),
                        new Insets(0) ));
        contener.setBorder(border1);
        root.getChildren().add(contener);
        MenuAjouter menu = new MenuAjouter(this);
        Border border2 = new Border(
                new BorderStroke(Color.GREEN,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        new BorderWidths(1),
                        new Insets(0) ));
        menu.setBorder(border2);

        root.getChildren().add(menu.getMenuEtChangeMenu());
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        Scene scene = new Scene(root);

        app.getStage().setScene(scene);
    }
}
