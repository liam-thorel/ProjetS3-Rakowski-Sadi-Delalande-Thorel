package view;


import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class MenuAjouter extends Pane {

    private VBox menuEtChangeMenu;
    private HBox changeMenu;
    private HBox menu;
    private HBox menuAstre;
    private HBox addAstre;
    private HBox systemeOuAjouter;
    private HBox playOuPause;
    private Button systeme;
    private Button ajouter;

    public MenuAjouter() {
        changeMenu = new HBox();
        menu = new HBox();
        menuEtChangeMenu = new VBox();
        menuAstre = new HBox();
        addAstre = new HBox();
        systemeOuAjouter = new HBox();
        playOuPause = new HBox();
        systeme = new Button();
        ajouter = new Button();
        systemeOuAjouter.getChildren().addAll(systeme,ajouter);
        changeMenu.getChildren().addAll(systemeOuAjouter,playOuPause);
        menu.getChildren().addAll(menuAstre,addAstre);
        menuEtChangeMenu.getChildren().addAll(changeMenu,menu);

    }
}
