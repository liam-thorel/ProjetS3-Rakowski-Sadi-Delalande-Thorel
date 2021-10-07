package view;


import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class MenuAjouter extends Pane {

    private VBox menuEtChangeMenu;
    private HBox changeMenu;
    private HBox menu;
    private HBox menuAstre;
    private HBox addAstre;

    public MenuAjouter() {
        changeMenu = new HBox();
        menu = new HBox();
        menuEtChangeMenu = new VBox();
        menuAstre = new HBox();
        addAstre = new HBox();
        menu.getChildren().addAll(menuAstre,addAstre);
        menuEtChangeMenu.getChildren().addAll(changeMenu,menu);

    }
}
