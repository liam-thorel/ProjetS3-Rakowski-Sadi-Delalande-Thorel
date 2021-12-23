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

    /** Application courante */
    private PlaneteApp app;
    /** Simulation Courante */
    private static Simulation s;
    /** Pane espace*/
    private static VBox root;
    /** Pane mère*/
    private static Pane all;
    /** vue de l'espace */
    private EspaceView espace;
    /** vue du menu qui contient tous les menus */
    private Menu menu;
    /** Menu des options */
    private OptionView menuOption;
    /** Verfie si les options sont ouvertes ou non */
    private BooleanProperty optionsOuvertes = new SimpleBooleanProperty();

    public SimulationView(Simulation myS,PlaneteApp app) {
        //Initialisation des attributs
        s = myS;
        this.app = app;
        root = new VBox();
        all = new Pane();
        menuOption = OptionView.getOptionView(this);
        espace = new EspaceView(this);
        menu = new Menu(this);

        //Ecoute le changement sur le booléen et fait :
        optionsOuvertes.addListener(optionOpenOrClose);

        //Mise à l'echelle sur l'écrans des différentes vue
        espace.setPrefHeight(app.getStage().getHeight()*0.73);
        espace.setPrefWidth(app.getStage().getWidth());

        menu.setPrefHeight(app.getStage().getHeight()*0.27);
        menu.setPrefWidth(app.getStage().getWidth());
        menu.toBack();

        //Mise en  place des vue à leur emplacement
        root.getChildren().add(espace);
        root.getChildren().add(menu.getMenuEtChangeMenu());
        root.setId("bg");
        root.getStylesheets().add(this.getClass().getResource("/Css/simulation.css").toExternalForm());

        all.getChildren().add(root);
        Scene scene = new Scene(all);

        /** Si bouton Echap ou F1 pressé afficher ou désafficher les options*/
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
        // met la scène de la fenêtre courante à SimulationView
        app.getStage().setScene(scene);
    }

    /**le listener de l'ouverture des options*/
    private ChangeListener<Boolean> optionOpenOrClose = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            menuOption.setVisible(t1);
            if (app.getDebug()) System.out.println("visible ? " + t1);
        }
    };

    /**méthode d'ouverture du menu des options
     * */
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
