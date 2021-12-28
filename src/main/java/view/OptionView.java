package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import javafx.event.ActionEvent;
import javafx.scene.paint.Paint;
import model.Simulation;

import java.io.IOException;

public class OptionView extends Pane {

    private static OptionView optionView;

    private SimulationView sV;
    /**vBox des bouttons*/
    private VBox bouttons;
    /**text d'erreur*/
    private Label error;
    /**titre*/
    private Label titre;

    public OptionView(SimulationView mySv){
        this.sV = mySv;
        bouttons = new VBox();
        error = new Label();

        //le fond legerement transparent
        this.setBackground(new Background(new BackgroundFill(Color.rgb(47, 49, 54,0.8), null, null)));

        //les bouttons
        Button retour = new Button("Retour à la simulation");
        Button save = new Button("Enregistrer");
        save.setAlignment(Pos.CENTER);
        Button quit = new Button("Quitter");
        quit.setAlignment(Pos.CENTER);
        Button parametre = new Button("Parametres");
        parametre.setAlignment(Pos.CENTER);
        Button reset = new Button("Reset");
        parametre.setAlignment(Pos.CENTER);

        //set des differend eventHandler
        save.setOnAction(onSave);
        quit.setOnAction(onQuit);
        parametre.setOnAction(onSettings);
        retour.setOnAction(onRetourSimu);
        reset.setOnAction(onReset);

        // le hBox contenant les bouttons
        bouttons.setBackground(new Background(new BackgroundFill(Color.rgb(64,68,75,1), null, null)));
        bouttons.setMinWidth(250);
        bouttons.setAlignment(Pos.CENTER);
        bouttons.setPadding(new Insets(50, 20, 50 , 20));
        bouttons.relocate(625,200);
        bouttons.setSpacing(10);
        bouttons.getChildren().addAll( save, quit, parametre, reset,retour);

        this.setPrefSize(sV.getApp().getStage().getWidth(), sV.getApp().getStage().getHeight());
        this.getChildren().addAll( bouttons, error);

    }


    /**EventHandler pour sauvegarder*/
    private EventHandler<ActionEvent> onSave = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            bouttons.getChildren().remove(error);
            error = new Label (sV.getApp().getfilechooser(false));
            error.setTextFill(Color.WHITE);
            bouttons.getChildren().add(error);

        }
    };

    /**EventHandler pour quitter la similation et revenir au menu principale*/
    private EventHandler<ActionEvent> onQuit = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            sV.getApp().initStart();
        }
    };


    /**EventHandler de l'ouverture des parametres*/
    private EventHandler<ActionEvent> onSettings = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            VBox ancient = new VBox();
            ancient.getChildren().addAll(bouttons.getChildren());

            bouttons.getChildren().clear();
            titre = new Label("Paramètres");
            titre.setTextFill(Paint.valueOf("white"));
            titre.setStyle("-fx-font-size: 15");
            bouttons.getChildren().add(titre);
            Button retourOptions= new Button("retour");
            CheckBox showTraj = new CheckBox("afficher les trajectoires");
            CheckBox colision = new CheckBox("Activer/Désativer les colisions");
            showTraj.setSelected(sV.getEspace().isShowingT());
            colision.setSelected(sV.getEspace().isColisionBoolean() );
            showTraj.setTextFill(Color.WHITE);
            colision.setTextFill(Color.WHITE);

            //event pour retouner dans les options depuis les parametres
            EventHandler<ActionEvent> onRetourOption = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    ancient.setAlignment(Pos.CENTER);
                    ancient.setSpacing(10);
                    bouttons.getChildren().setAll(ancient);
                }
            };

            //event pour la checkBox de l'option des trajectoires
            EventHandler<ActionEvent> onShowingTParam = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    sV.getEspace().setShowingT(showTraj.isSelected());
                }
            };
            //event pour la checkBox pour l'option des collisions
            EventHandler<ActionEvent> OnColision = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    sV.getEspace().setColisionBoolean(colision.isSelected());
                }
            };

            retourOptions.setOnAction(onRetourOption);
            showTraj.setOnAction(onShowingTParam);
            colision.setOnAction(OnColision);
            bouttons.getChildren().addAll(showTraj,colision,retourOptions);
        }
    };

    /**eventHandler du retour à la simulation depuis les options*/
    private EventHandler<ActionEvent> onRetourSimu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            sV.setOptionsOuvertes(false);
        }
    };

    /**eventHandler pour remettre la simulation à son état initial*/
    private EventHandler<ActionEvent> onReset = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            try {
                sV.getApp().initSimulation(Simulation.setAPartirDunFichier(sV.getSimulation().getFile()));
            } catch (IOException | NullPointerException | ClassNotFoundException e) {
                sV.getApp().initSimulation(new Simulation());
            }
        }
    };


    public static OptionView getOptionView(SimulationView sV) {
        if(optionView == null){
            optionView = new OptionView(sV);
        }
        return optionView;
    }


}
