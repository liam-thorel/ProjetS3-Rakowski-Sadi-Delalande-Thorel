package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MenuAjouter extends Pane {

    private HBox menu ;
    private HBox menuAstre;
    private VBox addAstre;
    private Pane menuBg = new Pane();
    private Pane menuAstreBg;
    private Pane menuAddAstreBg;
    private Menu m;
    private ObservableList<Circle> mesPlanetesCourantes = FXCollections.observableArrayList();
    ListView<Circle> mesAstres = new ListView<Circle>(mesPlanetesCourantes);// créer ListView pour astres


    public MenuAjouter(Menu m){
        this.m = m;

        menuAstreBg = new Pane();
        menuAddAstreBg = new Pane();
        menuAstre = new HBox();
        addAstre = new MenuAddAstre(this);
        menu=new HBox();



        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(m.getSimulationView().getApp().getDimension().getWidth()-15);
        rectangle.setHeight(m.getSimulationView().getApp().getDimension().getHeight()-690);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.rgb(47, 49, 54, 0.9));
        menuBg.getChildren().add(rectangle);
        menuBg.getChildren().add(menu);

        //Création et éffectation du réctangle d'arrière plan derrière Mes astres
        Rectangle rectangleMenuAstres = new Rectangle();
        rectangleMenuAstres.setHeight(70);
        rectangleMenuAstres.setWidth(695);
        rectangleMenuAstres.setFill(Color.rgb(64,68,75,1));
        menuAstreBg.getChildren().add(rectangleMenuAstres);
        menuAstreBg.getChildren().add(menuAstre);

        //Création et éffectation du réctangle d'arrière plan derrière Add astres
        Rectangle rectangleAddAstres = new Rectangle();
        rectangleAddAstres.setHeight(70);
        rectangleAddAstres.setWidth(695);
        rectangleAddAstres.setFill(Color.rgb(64,68,75,1));

        menuAddAstreBg.getChildren().add(rectangleAddAstres);
        menuAddAstreBg.getChildren().add(addAstre);


        //eloignement des différents Menu
        menu.setSpacing(100);
        menu.setPadding(new Insets(25));

        //ajout a menu de l'ensemble des sous menu
        menu.getChildren().addAll(menuAstreBg, menuAddAstreBg);

    }

    public void setMesAstres(ListView<Circle> mesAstres) {this.mesAstres = mesAstres;}

    public ListView<Circle> getMesAstres() {return mesAstres;}

    public Pane getMenu() {
        return menuBg;
    }

    public Menu getM() {
        return m;
    }
}
