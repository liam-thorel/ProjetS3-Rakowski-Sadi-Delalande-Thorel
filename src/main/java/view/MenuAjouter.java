package view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.Astre;

public class MenuAjouter extends Pane {

    private HBox menu ;
    private HBox menuAstre;
    private HBox addAstre;
    private Pane menuBg = new Pane();
    private Pane menuAstreBg;
    private Pane menuAddAstreBg;





    public MenuAjouter(Menu m){
        ObservableList<Astre> lstAstre  = FXCollections.observableList(m.getSimulation().getSimulation().getListeAstre());
        lstAstre.addListener(whenListAstreIsUpdate);
        menuAstreBg = new Pane();
        menuAddAstreBg = new Pane();
        menuAstre = new HBox();
        addAstre = new MenuAddAstre(this);
        menu=new HBox();



        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(m.getSimulation().getApp().getDimension().getWidth()-15);
        rectangle.setHeight(m.getSimulation().getApp().getDimension().getHeight()-500);
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

    // Regarde si la liste d'astre change, si c'est le cas ajoute a menu astre la représentation de l'astre
    // et inversemment si l'astre est supprimé
    private ListChangeListener<Astre> whenListAstreIsUpdate = new ListChangeListener<Astre>() {
        @Override
        public void onChanged(Change<? extends Astre> change) {
            while(change.next()){
                if(change.wasAdded()){
                    for (Astre a : change.getAddedSubList()){
                        menuAstre.getChildren().add(new Circle(a.getTaille()/2));
                    }
                }else if (change.wasRemoved()){
                    for (Astre a : change.getRemoved()){
                        menuAstre.getChildren().remove(a);
                    }
                }
            }
        }
    };

    public Pane getMenu() {
        return menuBg;
    }
}
