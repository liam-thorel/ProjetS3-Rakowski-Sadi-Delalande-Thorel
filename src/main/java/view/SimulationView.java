package view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import logic.Planete;
import logic.Simulation;

import java.util.ArrayList;


public class SimulationView extends Stage {

    private PlaneteApp app;
    private static Simulation s;
    private static VBox root;
    private static Pane all;

    public static Simulation getS() {
        return s;
    }

    private HBox contener;
    private EspaceView espace;
    private Menu menu;
    private OptionView menuOption;
    private BooleanProperty optionsOuvertes = new SimpleBooleanProperty();

    public SimulationView(Simulation myS,PlaneteApp app) {
        s = myS;
        this.app = app;
        root = new VBox();
        all = new Pane();
        optionsOuvertes.addListener(optionOpenOrClose);
        menuOption = OptionView.getOptionView(this);
        espace = new EspaceView(this);
        espace.setPrefHeight(600);
        contener = new HBox();
        contener.setAlignment(Pos.CENTER);
        root.getChildren().add(contener);
        menu = new Menu(this);
        menu.toBack();
        root.getChildren().add(espace);
        root.getChildren().add(menu.getMenuEtChangeMenu());
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        all.getChildren().add(root);
        Scene scene = new Scene(all);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.F1) {
                ouvrirMenuOption();
                ke.consume();
            }

        });
        app.getStage().setScene(scene);
    }

    private ChangeListener<Boolean> optionOpenOrClose = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            menuOption.setVisible(t1);
            System.out.println("visible ? " + t1);
        }
    };

    public void ouvrirMenuOption(){
        espace.setPlaying(false);
        optionsOuvertes.setValue(true);
        menuOption.toFront();
        menuOption.relocate(0, 0);
        if(!all.getChildren().contains(menuOption)){
        all.getChildren().add(menuOption);}
    }

    public Simulation getSimulation() {
        return s;
    }

    public VBox getRoot() {
        return root;
    }

    public EspaceView getEspace() {
        return espace;
    }

    public PlaneteApp getApp() {
        return app;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setOptionsOuvertes(boolean optionsOuvertes) {
        this.optionsOuvertes.setValue(optionsOuvertes);
    }


}
