package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Simulation;

import java.io.File;
import java.io.IOException;

public class OptionView extends Pane {


    private static OptionView optionView;
    private SimulationView sV;
    private HBox bouttons;
    public OptionView(SimulationView sV){
        this.sV = sV;
        bouttons = new HBox();
        //le fond legerement transparent
        this.setBackground(new Background(new BackgroundFill(Color.rgb(47, 49, 54,0.8), null, null)));
        /*Rectangle fond = new Rectangle();

        fond.setHeight(sV.getApp().getDimension().getHeight());
        fond.setWidth(sV.getApp().getDimension().getWidth());
        fond.setFill(Color.GREY);
        fond.setOpacity(0.6);*/


        //les bouttons
        Button retour = new Button("Retour à la simulation");
        Button saveEtContinuer = new Button("Sauvegarder et continuer");
        saveEtContinuer.setAlignment(Pos.CENTER);
        Button saveEtQuit = new Button("Sauvegarder et quitter");
        saveEtQuit.setAlignment(Pos.CENTER);
        Button parametre = new Button("Parametres");
        parametre.setAlignment(Pos.CENTER);


        //event pour les save
        EventHandler<ActionEvent> onSave = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                fileChooser.setTitle("Sauvegarder");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Simu", "*.simu"));
                File s =fileChooser.showSaveDialog(new Stage());
                try {
                    sV.getSimulation().saveListeAstre(s);
                    if(actionEvent.getSource().equals(saveEtQuit)){
                        sV.getApp().initStart();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        //event pour les param
        EventHandler<ActionEvent> onSettings = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HBox ancient = new HBox();
                ancient.getChildren().addAll(bouttons.getChildren());

                bouttons.getChildren().clear();
                bouttons.getChildren().add(new Label("les parametres arrivent"));
                Button retourOptions= new Button("retour");
                EventHandler<ActionEvent> onRetourOption = new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent actionEvent) {
                        bouttons.getChildren().setAll(ancient);
                   }
               };

                retourOptions.setOnAction(onRetourOption);
                bouttons.getChildren().add(retourOptions);
            }
        };

        //event retour
        EventHandler<ActionEvent> onRetourSimu = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sV.setOptionsOuvertes(false);
            }
        };

        saveEtContinuer.setOnAction(onSave);
        saveEtQuit.setOnAction(onSave);
        parametre.setOnAction(onSettings);
        retour.setOnAction(onRetourSimu);

        // le hBox contenant les bouttons
        bouttons.setBackground(new Background(new BackgroundFill(Color.rgb(64,68,75,1), null, null)));
        bouttons.setAlignment(Pos.CENTER);
        bouttons.setPadding(new Insets(50, 20, 50 , 20));
        bouttons.getChildren().addAll(saveEtContinuer, saveEtQuit, parametre, retour);


        this.setPrefSize(sV.getApp().getDimension().getHeight(), sV.getApp().getDimension().getWidth());
        this.getChildren().addAll( bouttons);

    }

    public static OptionView getOptionView(SimulationView sV) {
        if(optionView == null){
            optionView = new OptionView(sV);
        }
        return optionView;
    }

}
