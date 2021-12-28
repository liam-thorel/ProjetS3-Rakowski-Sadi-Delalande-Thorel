package view;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuAjouter extends Pane {

    /** Pane mère*/
    private HBox menu ;
    /**Pane menuAstre */
    private HBox    menuAstre;
    /** MenuAstre */
    private MenuAddAstre addAstre;
    /** arrière plan du menu courant */
    private Pane menuBg = new Pane();
    /** Arrière-plan du menuAstre + menu Astre */
    private Pane menuAstreBg;
    /** Arrière-plan du menuAddAstre + menu addAstre*/
    private Pane menuAddAstreBg;
    /** Menu courant */
    private Menu m;
    /** menu drag and drop */
    private DragnDrop dnd;

    public MenuAjouter(Menu m) {
        //initialisation des attributs
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

        //ajout au menu son sous menus
        menuAstreBg.getChildren().add(dnd);
        menuAstreBg.getChildren().add(menuAstre);

        //Création et éffectation du réctangle d'arrière plan derrière Add astres
        Rectangle rectangleAddAstres = new Rectangle();
        rectangleAddAstres.setHeight(70);
        rectangleAddAstres.setWidth(695);
        rectangleAddAstres.setFill(Color.rgb(64, 68, 75, 1));

        //ajout au menu add astre sont arrière plan et son sous menu
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
