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


public class Menu extends Pane {

    private VBox menuEtChangeMenu;
    private BorderPane changeMenu;
    private Pane menuAjouter;
    private Pane menuActuel;
    private Pane menuSysteme;
    private HBox systemeOuAjouter;
    private HBox playOuPause;
    private Button systeme;
    private Button ajouter;
    private Button play;
    private Button pause;
    private SimulationView s;

    public Menu(SimulationView s) {
        this.s=s;
        setWidth(1500);
        setHeight(200);
        changeMenu = new BorderPane();
        menuAjouter = new MenuAjouter().getMenuBg();
        menuSysteme = new Pane();
        menuActuel = menuAjouter;
        menuEtChangeMenu = new VBox();
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

        // Que font les boutons ?
        systeme.setOnAction(onSystemeMenu);
        ajouter.setOnAction(onAjouterMenu);
        play.setOnAction(onPlay);
        pause.setOnAction(onPause);

        //
        systemeOuAjouter.getChildren().addAll(systeme, ajouter);
        changeMenu.setLeft(systemeOuAjouter);
        changeMenu.setRight(playOuPause);
        systemeOuAjouter.setSpacing(5);
        playOuPause.setSpacing(5);
        changeMenu.setMaxWidth(1480);
        playOuPause.getChildren().addAll(play,pause);
        menuEtChangeMenu.getChildren().addAll(changeMenu,menuActuel);




    }
        private EventHandler<ActionEvent> onAjouterMenu = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                menuEtChangeMenu.getChildren().remove(menuActuel);
                menuActuel=menuAjouter;
                menuEtChangeMenu.getChildren().add(menuActuel);
            }
        };

    private EventHandler<ActionEvent> onSystemeMenu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            menuEtChangeMenu.getChildren().remove(menuActuel);
            menuActuel=menuSysteme;
            menuEtChangeMenu.getChildren().add(menuActuel);
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

