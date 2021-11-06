package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


public class Menu extends Pane {

    private VBox menuEtChangeMenu;
    private BorderPane changeMenu;
    private MenuAjouter menuAjouter;
    private Pane menuAjouterPane;
    private Pane menuActuelPane;
    private MenuSysteme menuSysteme;
    private Pane menuSystemePane;
    private HBox systemeOuAjouter;
    private HBox playOuPause;
    private Button systeme;
    private Button ajouter;
    private Button play = new Button("Play");
    private Button pause = new Button("Pause");
    private SimulationView sV;

    public Menu(SimulationView sV) {
        this.sV =sV;
        setWidth(1500);
        setHeight(200);
        changeMenu = new BorderPane();
        menuAjouter = new MenuAjouter(this);
        menuAjouterPane = menuAjouter.getMenu();
        menuSysteme = new MenuSysteme(this);
        menuSystemePane = menuSysteme.getMenu();
        menuActuelPane = menuAjouterPane;
        menuEtChangeMenu = new VBox();
        systemeOuAjouter = new HBox();
        playOuPause = new HBox();
        systeme = new Button("Systeme");
        systeme.prefWidth(20);
        systeme.prefHeight(15);
        ajouter = new Button("Ajouter");
        ajouter.prefWidth(20);
        ajouter.prefHeight(15);
        play.prefWidth(20);
        play.prefHeight(15);
        pause.prefWidth(20);
        pause.prefHeight(15);

        //apparence des boutons
        //play.setGraphic(new ImageView("../images/PlayButtonYes.png"));
        //pause.setGraphic(new ImageView("../images/PauseButtonOFF.png"));
        // Que font les boutons ?
        play.setText("Play");
        pause.setText("Pause");
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
        changeMenu.setMaxWidth(sV.getApp().getDimension().getWidth()-20);
        playOuPause.getChildren().addAll(play,pause);
        menuEtChangeMenu.getChildren().addAll(changeMenu, menuActuelPane);




    }
    private EventHandler<ActionEvent> onAjouterMenu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            menuEtChangeMenu.getChildren().remove(menuActuelPane);
            menuActuelPane = menuAjouterPane;
            menuEtChangeMenu.getChildren().add(menuActuelPane);
        }
    };

    private EventHandler<ActionEvent> onSystemeMenu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            menuEtChangeMenu.getChildren().remove(menuActuelPane);
            menuActuelPane = menuSystemePane;
            menuEtChangeMenu.getChildren().add(menuActuelPane);
        }
    };

    private EventHandler<ActionEvent> onPlay = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            sV.getEspace().setPlaying(true);
            //play.setGraphic(new ImageView("src/main/resources/images/PlayButtonYes.png"));
            //pause.setGraphic(new ImageView("src/main/resources/images/PauseButtonOFF.png"));
        }
    };

    private EventHandler<ActionEvent> onPause = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            sV.getEspace().setPlaying(false);
            //play.setGraphic(new ImageView("src/main/resources/images/PlayButtonNo.png"));
            //pause.setGraphic(new ImageView("src/main/resources/images/PauseButtonON.png"));
        }
    };


    public VBox getMenuEtChangeMenu() {
        return menuEtChangeMenu;
    }

    public SimulationView getSimulationView() {
        return sV;
    }

    public MenuAjouter getMenuAjouter() {
        return menuAjouter;
    }

    public MenuSysteme getMenuSysteme() {
        return menuSysteme;
    }
}

