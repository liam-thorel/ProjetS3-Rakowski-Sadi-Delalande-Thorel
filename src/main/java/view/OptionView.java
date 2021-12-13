package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;

import javafx.event.ActionEvent;
import model.Simulation;

import java.io.IOException;

public class OptionView extends Pane {


    private static OptionView optionView;
    private SimulationView sV;
    private VBox bouttons;
    private Label error;
    public OptionView(SimulationView sV){
        this.sV = sV;
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

        //pour cacher l'erreur
        EventHandler<MouseEvent> onClickError = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                error.setText("");
            }
        };
        //event pour save
        EventHandler<ActionEvent> onSave = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bouttons.getChildren().remove(error);
                error = new Label (sV.getApp().getfilechooser(false));
                error.setTextFill(Color.WHITE);
                bouttons.getChildren().add(error);

            }
        };

        //event pour le quit
        EventHandler<ActionEvent> onQuit = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sV.getApp().initStart();
            }
        };

        //event pour les param
        EventHandler<ActionEvent> onSettings = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox ancient = new VBox();
                ancient.getChildren().addAll(bouttons.getChildren());

                bouttons.getChildren().clear();
                bouttons.getChildren().add(new Label("les parametres arrivent"));
                Button retourOptions= new Button("retour");
                CheckBox showTraj = new CheckBox("afficher les trajectoires");
                showTraj.setSelected(sV.getEspace().isShowingT());
                showTraj.setTextFill(Color.WHITE);
                EventHandler<ActionEvent> onRetourOption = new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent actionEvent) {
                       ancient.setAlignment(Pos.CENTER);
                        bouttons.getChildren().setAll(ancient);
                   }
               };

                EventHandler<ActionEvent> onShowingTParam = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sV.getEspace().setShowingT(showTraj.isSelected());
                    }
                };

                retourOptions.setOnAction(onRetourOption);
                showTraj.setOnAction(onShowingTParam);
                bouttons.getChildren().addAll(showTraj,retourOptions);
            }
        };

        //event retour
        EventHandler<ActionEvent> onRetourSimu = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sV.setOptionsOuvertes(false);
            }
        };

        EventHandler<ActionEvent> onReset = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                    try {
                        sV.getApp().initSimulation(Simulation.setAPartirDunFichier(sV.getSimulation().getFile()));
                    } catch (IOException | NullPointerException | ClassNotFoundException e) {
                        sV.getApp().initSimulation(new Simulation());
                    }
            }
        };


        save.setOnAction(onSave);
        quit.setOnAction(onQuit);
        parametre.setOnAction(onSettings);
        retour.setOnAction(onRetourSimu);
        reset.setOnAction(onReset);

        error.setOnMouseClicked(onClickError);
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



    public static OptionView getOptionView(SimulationView sV) {
        if(optionView == null){
            optionView = new OptionView(sV);
        }
        return optionView;
    }


}
