package view;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuSysteme extends Pane {

    private HBox menu;
    private HBox menuSys;
    private Pane menuBg = new Pane();
    private Pane menuSysBg;


    public MenuSysteme() {
        menuSysBg = new Pane();
        menuSys = new HBox();
        menu = new HBox();

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
        rectangleMenuAstres.setHeight(80);
        rectangleMenuAstres.setWidth(1200);
        rectangleMenuAstres.setFill(Color.rgb(220, 220, 190, 1));
        menuSysBg.getChildren().add(rectangleMenuAstres);
        menuSysBg.getChildren().add(menuSys);

        //eloignement des différents Menu
        menu.setPadding(new Insets(25));
        menu.setLayoutX(200);

        //ajout a menu de l'ensemble des sous menu
        menu.getChildren().addAll(menuSysBg);

    }

    public Pane getMenu() {
        return menuBg;
    }
}
