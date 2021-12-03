package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.PropertySheet;

public class MenuAjouter extends Pane {

    private HBox menu ;
    private HBox menuAstre;
    private MenuAddAstre addAstre;
    private Pane menuBg = new Pane();
    private Pane menuAstreBg;
    private Pane menuAddAstreBg;
    private Menu m;
    private DragnDrop dnd;

    public MenuAjouter(Menu m) {
        this.m = m;

        menuAstreBg = new Pane();
        menuAddAstreBg = new Pane();
        menuAstre = new HBox();
        addAstre = new MenuAddAstre(this);
        menu=new HBox();
        dnd = new DragnDrop(this, addAstre);

        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(m.getSimulationView().getApp().getStage().getWidth());
        rectangle.setHeight(m.getSimulationView().getApp().getStage().getHeight()*0.27);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.rgb(47, 49, 54, 0.9));
        menuBg.getChildren().add(rectangle);
        menuBg.getChildren().add(menu);

        menuAstre.setSpacing(10); // espacement 10 pixels

        menuAstreBg.getChildren().add(dnd);
        menuAstreBg.getChildren().add(menuAstre);

        //Création et éffectation du réctangle d'arrière plan derrière Add astres
        Rectangle rectangleAddAstres = new Rectangle();
        rectangleAddAstres.setHeight(70);
        rectangleAddAstres.setWidth(695);
        rectangleAddAstres.setFill(Color.rgb(64, 68, 75, 1));

        menuAddAstreBg.getChildren().add(rectangleAddAstres);
        menuAddAstreBg.getChildren().add(addAstre);

        //eloignement des différents Menu
        menu.setSpacing(200);
        menu.setPadding(new Insets(25));

        //ajout a menu de l'ensemble des sous menu
        menu.getChildren().addAll(menuAstreBg, menuAddAstreBg);
    }

    public Pane getMenu() {return menuBg;}

    public Menu getM() {return m;}

    public DragnDrop getDnd() {
        return dnd;
    }

    public MenuAddAstre getAddAstre() {
        return addAstre;
    }

    public Pane getMenuAstreBg() {
        return menuAstreBg;
    }

    public HBox getMenuAstre() {
        return menuAstre;
    }
}
