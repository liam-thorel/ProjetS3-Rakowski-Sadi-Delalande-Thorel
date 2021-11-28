package view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Simulation;


public class SimulationView extends Stage {

    private PlaneteApp app;
    private static Simulation s;
    private static VBox root;
    private static Pane all;



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
        espace.setPrefHeight(app.getStage().getHeight()*0.73);
        espace.setPrefWidth(app.getStage().getWidth());
        contener = new HBox();
        contener.setAlignment(Pos.CENTER);
        root.getChildren().add(contener);
        menu = new Menu(this);
        menu.setPrefHeight(app.getStage().getHeight()*0.27);
        menu.setPrefWidth(app.getStage().getWidth());
        menu.toBack();
        root.getChildren().add(espace);
        root.getChildren().add(menu.getMenuEtChangeMenu());
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        all.getChildren().add(root);
        Scene scene = new Scene(all);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode() == KeyCode.F1 ||ke.getCode() == KeyCode.ESCAPE ) {
                if (!optionsOuvertes.getValue()) {
                    ouvrirMenuOption();
                    ke.consume();
                }else {
                    optionsOuvertes.set(false);
                    ke.consume();
                }
            }


        });
        app.getStage().setScene(scene);
    }

    private ChangeListener<Boolean> optionOpenOrClose = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            menuOption.setVisible(t1);
            if (app.getDebug()) System.out.println("visible ? " + t1);
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

    public static Simulation getS() {
        return s;
    }

}
