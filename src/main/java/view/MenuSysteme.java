package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.Astre;

public class MenuSysteme extends Pane {

    private HBox menu;
    private HBox menuSys;
    private Pane menuBg = new Pane();
    private Pane menuSysBg;
    private Menu menuGen;


    public MenuSysteme(Menu m) {
        menuSysBg = new Pane();
        menuSys = new HBox();
        menu = new HBox();
        menuGen = m;

        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(m.getSimulationView().getApp().getStage().getWidth());
        rectangle.setHeight(m.getSimulationView().getApp().getStage().getHeight()*0.27);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.rgb(47, 49, 54, 0.9));
        menuBg.getChildren().add(rectangle);
        menuBg.getChildren().add(menu);

        //Création et affectation du rectangle d'arrière plan derrière Mes astres
        Rectangle rectangleMenuAstres = new Rectangle();
        rectangleMenuAstres.setHeight(80);
        rectangleMenuAstres.setWidth(1200);
        rectangleMenuAstres.setFill(Color.rgb(64,68,75, 1));
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

    public void afficherInfos(Astre a, Button supprimer, Button modifier){
        menuSys.getChildren().clear();

        Label nom = new Label(a.getNom());
        nom.setAlignment(Pos.CENTER);
        Label taille = new Label(String.valueOf((int) a.getTaille()));
        taille.setAlignment(Pos.CENTER);
        Label masse = new Label(String.valueOf((int)a.getMasse()));
        masse.setAlignment(Pos.CENTER);
        Label pX = new Label(String.valueOf((a.getPositionX())));
        pX.setAlignment(Pos.CENTER);
        Label pY = new Label(String.valueOf(a.getPositionY()));
        pY.setAlignment(Pos.CENTER);
        Label vX = new Label(String.valueOf(a.getVitesseX()));
        vX.setAlignment(Pos.CENTER);
        Label vY = new Label(String.valueOf(a.getVitesseY()));
        vY.setAlignment(Pos.CENTER);

        supprimer.setAlignment(Pos.CENTER_RIGHT);
        menuSys.setSpacing(20);
        menuSys.getChildren().addAll(nom, taille, masse, pX, pY, vX, vY);

        menuSys.getChildren().add(supprimer);
        menuSys.getChildren().add(modifier);
    }

    public Menu getMenuGen() {
        return menuGen;
    }

    public void rmv(){
        menuSys.getChildren().clear();
    }

    public void modifierInfos(Astre a, Button supprimer, Button modifier){
        menuSys.getChildren().clear();

        TextField nom = new TextField(a.getNom());
        nom.setAlignment(Pos.CENTER);
        TextField taille = new TextField(String.valueOf((int) a.getTaille()));
        taille.setAlignment(Pos.CENTER);
        TextField masse = new TextField(String.valueOf((int) a.getMasse()));
        masse.setAlignment(Pos.CENTER);
        TextField pX = new TextField(String.valueOf((int) a.getPositionX()));
        pX.setAlignment(Pos.CENTER);
        TextField pY = new TextField(String.valueOf((int) a.getPositionY()));
        pY.setAlignment(Pos.CENTER);
        TextField vX = new TextField(String.valueOf((int) a.getVitesseX()));
        vX.setAlignment(Pos.CENTER);
        TextField vY = new TextField(String.valueOf((int) a.getVitesseY()));
        vY.setAlignment(Pos.CENTER);

        Button apply = new Button("Confirmer");

        EventHandler <ActionEvent> onConfirmerModif = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int index = 0;
                for( Astre astre : getMenuGen().getSimulationView().getEspace().getListeA()){
                    if (astre == a){
                        break;
                    }
                    index++;
                }


                a.setAll(Float.parseFloat(taille.getText()), Float.parseFloat(masse.getText()), nom.getText(), Integer.parseInt(pX.getText()), Integer.parseInt(pY.getText()), Double.parseDouble(vX.getText()), Double.parseDouble(vY.getText()));

                getMenuGen().getSimulationView().getEspace().getListeA().remove(index);
                getMenuGen().getSimulationView().getEspace().getListeA().add(a);

                afficherInfos(a, supprimer, modifier);
            }
        };

        apply.setOnAction(onConfirmerModif);
        menuSys.setSpacing(15);
        menuSys.getChildren().addAll(nom, taille, masse, pX, pY, vX, vY);
        menuSys.getChildren().add(apply);
    }


}
