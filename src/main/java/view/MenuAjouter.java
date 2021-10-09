package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import logic.Simulation;

public class MenuAjouter extends Pane {

    /*private PlaneteApp app;*/
    private VBox menuEtChangeMenu;
    private HBox changeMenu;
    private HBox menu;
    private HBox menuAstre;
    private HBox addAstre;
    private HBox systemeOuAjouter;
    private HBox playOuPause;
    private Button systeme;
    private Button ajouter;

    public MenuAjouter(SimulationView s) {
       /* this.app=app;
        setWidth(1500);
        setHeight(200);*/
        changeMenu = new HBox();
        menu = new HBox();
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
        ajouter.setOnAction(onAjouterMenu);
        systeme.setOnAction(onSystemeMenu);
        systemeOuAjouter.getChildren().addAll(systeme, ajouter);
        changeMenu.getChildren().addAll(systemeOuAjouter, playOuPause);
        menu.getChildren().addAll(menuAstre, addAstre);
        menuEtChangeMenu.getChildren().addAll(changeMenu, menu);
      /*  Scene scene = new Scene(menuEtChangeMenu);
        app.getStage().setScene(scene);*/
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


    public VBox getMenuEtChangeMenu() {
        return menuEtChangeMenu;
    }
}

