package view;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuAjouter extends Pane {

    private HBox menu ;
    private HBox menuAstre;
    private HBox addAstre;
    private Pane menuBg = new Pane();
    private Pane menuAstreBg;
    private Pane menuAddAstreBg;


    public MenuAjouter(Menu m){
        menuAstreBg = new Pane();
        menuAddAstreBg = new Pane();
        menuAstre = new HBox();
        addAstre = new HBox();
        menu=new HBox();

        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(m.getSimulation().getApp().getDimension().getWidth()-15);
        rectangle.setHeight(m.getSimulation().getApp().getDimension().getHeight()-500);
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

        //Création et éffectation du réctangle d'arrière plan derrière Add astres
        Rectangle rectangleAddAstres = new Rectangle();
        rectangleAddAstres.setHeight(70);
        rectangleAddAstres.setWidth(525);
        rectangleAddAstres.setFill(Color.rgb(220,220,190,1));
        menuAddAstreBg.getChildren().add(rectangleAddAstres);
        menuAddAstreBg.getChildren().add(addAstre);

        //eloignement des différents Menu
        menu.setSpacing(380);
        menu.setPadding(new Insets(25));

        //ajout a menu de l'ensemble des sous menu
        menu.getChildren().addAll(menuAstreBg, menuAddAstreBg);

    }

    public Pane getMenu() {
        return menuBg;
    }
}
