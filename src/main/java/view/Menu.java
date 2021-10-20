package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import logic.Simulation;

import java.awt.*;

public class Menu extends Pane {

    private VBox menuEtChangeMenu;
    private BorderPane changeMenu;
    private HBox menu;
    private HBox menuAstre;
    private HBox addAstre;
    private HBox systemeOuAjouter;
    private HBox playOuPause;
    private Button systeme;
    private Button ajouter;
    private Button play;
    private Button pause;
    private SimulationView s;
    private Pane menuBg;
    private Pane menuAstreBg;
private Pane menuAddAstreBg;

    public Menu(SimulationView s) {
        this.s=s;
        setWidth(1500);
        setHeight(200);
        changeMenu = new BorderPane();
        menu = new HBox();
        menuAstreBg = new Pane();
        menuAddAstreBg = new Pane();
        menuEtChangeMenu = new VBox();
        menuAstre = new HBox();
        addAstre = new HBox();
        systemeOuAjouter = new HBox();
        playOuPause = new HBox();
        systeme = new Button("Systeme");
        systeme.prefWidth(20);
        systeme.prefHeight(15);
        ajouter = new Button("Ajouter");
        ajouter.prefWidth(20);
        ajouter.prefHeight(15);
        play = new Button("Play");
        play.prefWidth(20);
        play.prefHeight(15);
        pause = new Button("Pause");
        pause.prefWidth(20);
        pause.prefHeight(15);
        menuBg = new Pane();

        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(1485);
        rectangle.setHeight(350);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.rgb(190, 190, 190, 0.9));
        menuBg.getChildren().add(rectangle);
        menuBg.getChildren().add(menu);

        //Création et éffectation du réctangle d'arrière plan derrière Mes astres
        Rectangle rectangleMenuAstres = new Rectangle();
        rectangleMenuAstres.setHeight(70);
        rectangleMenuAstres.setWidth(525);
        rectangleMenuAstres.setFill(Color.rgb(220,220,190,1));
        menuAstreBg.getChildren().add(rectangleMenuAstres);
        menuAstreBg.getChildren().add(menuAstre);

        //Création et éffectation du réctangle d'arrière plan derrière Mes astres
        Rectangle rectangleAddAstres = new Rectangle();
        rectangleAddAstres.setHeight(70);
        rectangleAddAstres.setWidth(525);
        rectangleAddAstres.setFill(Color.rgb(220,220,190,1));
        menuAddAstreBg.getChildren().add(rectangleAddAstres);
        menuAddAstreBg.getChildren().add(addAstre);

        //eloignement des différents Menu
        menu.setSpacing(380);
        menu.setPadding(new Insets(25));

        systeme.setOnAction(onSystemeMenu);
        ajouter.setOnAction(onAjouterMenu);
        play.setOnAction(onPlay);
        pause.setOnAction(onPause);
        systemeOuAjouter.getChildren().addAll(systeme, ajouter);
        changeMenu.setLeft(systemeOuAjouter);
        changeMenu.setRight(playOuPause);
        systemeOuAjouter.setSpacing(5);
        playOuPause.setSpacing(5);
        changeMenu.setMaxWidth(1480);
        playOuPause.getChildren().addAll(play,pause);
        menu.getChildren().addAll(menuAstreBg, menuAddAstreBg);
        menuEtChangeMenu.getChildren().addAll(changeMenu, menuBg);




    }
        private EventHandler<ActionEvent> onAjouterMenu = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                System.out.println("je fais rien je suis deja en vue ajouter");
            }
        };

    private EventHandler<ActionEvent> onSystemeMenu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            System.out.println("j'affiche la vue du menu systeme");
        }
    };

    private EventHandler<ActionEvent> onPlay = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            s.getEspace().setPlaying(true);
        }
    };

    private EventHandler<ActionEvent> onPause = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            s.getEspace().setPlaying(false);
        }
    };


    public VBox getMenuEtChangeMenu() {
        return menuEtChangeMenu;
    }
}

