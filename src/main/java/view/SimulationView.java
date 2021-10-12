package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logic.Planete;
import logic.Simulation;

import java.util.ArrayList;


public class SimulationView extends Stage {

    private PlaneteApp app;
    private Simulation s;
    private VBox root;
    private HBox contener;
    private EspaceView espace;

    public SimulationView(Simulation s,PlaneteApp app) {
        this.s = s;
        this.app = app;
        root = new VBox();
        espace = new EspaceView(this);
        espace.setPrefHeight(600);
        contener = new HBox();
        contener.setAlignment(Pos.CENTER);
        root.getChildren().add(contener);
        MenuAjouter menu = new MenuAjouter(this);
        root.getChildren().add(espace);
        root.getChildren().add(menu.getMenuEtChangeMenu());
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        Scene scene = new Scene(root);

        app.getStage().setScene(scene);
    }
    private void ajouterPlanetes(ArrayList<Planete> listeP){
        for (int i = 0 ; i < listeP.size() ; i++){
            espace.getChildren().add(espace.creerPlaneteCercle(listeP.get(i)));
        }
    }


    public Simulation getSimulation() {
        return s;
    }


}
