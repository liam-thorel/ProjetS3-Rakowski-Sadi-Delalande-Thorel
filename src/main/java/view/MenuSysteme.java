package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Astre;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuSysteme extends Pane {

    private HBox menu;
    private GridPane menuSys;
    private Pane menuBg = new Pane();
    private Pane menuSysBg;
    private Menu menuGen;


    public MenuSysteme(Menu m) {
        menuSysBg = new Pane();
        menuSys = new GridPane();
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

        //au debut on affiche la liste de tout
        afficherList();

    }

    public Pane getMenu() {
        return menuBg;
    }

    public void afficherInfos(Astre a, Button supprimer, Button modifier){
        menuSys.getChildren().clear();

        Label nom = new Label(a.getNom());
        nom.setAlignment(Pos.CENTER);
        Label ln = new Label("Nom  ");
        ln.setAlignment(Pos.CENTER);
        menuSys.add(ln, 1,0);
        menuSys.add(nom,1,1);

        Label taille = new Label(String.valueOf((int)  a.getTaille()));
        taille.setAlignment(Pos.CENTER);
        Label lt = new Label(" Taille en 10^9 m ");
        lt.setAlignment(Pos.CENTER);
        menuSys.add(lt, 2, 0);
        menuSys.add(taille, 2, 1);

        Label masse = new Label(String.valueOf((int)a.getMasse()));
        masse.setAlignment(Pos.CENTER);
        Label lm = new Label(" Masse en 10^20 kg ");
        lm.setAlignment(Pos.CENTER);
        menuSys.add(lm, 3, 0);
        menuSys.add(masse, 3, 1);

        Label pX = new Label(String.valueOf( (int) Math.round(a.getPositionX()*100.0)/100.0));
        pX.setAlignment(Pos.CENTER);
        Label lpx = new Label(" Position x ");
        lpx.setAlignment(Pos.CENTER);
        menuSys.add(lpx, 4, 0);
        menuSys.add(pX, 4, 1);

        Label pY = new Label(String.valueOf( (int) Math.round(a.getPositionY()*100.0)/100.0));
        pY.setAlignment(Pos.CENTER);
        Label lpY = new Label(" Position y ");
        lpY.setAlignment(Pos.CENTER);
        menuSys.add(lpY, 5, 0);
        menuSys.add(pY, 5, 1);

        Label vX = new Label(String.valueOf((int) Math.round(a.getVitesseX()*100.0)/100.0));
        vX.setAlignment(Pos.CENTER);
        Label lvx = new Label(" Vitesse x ");
        lvx.setAlignment(Pos.CENTER);
        menuSys.add(lvx, 6, 0);
        menuSys.add(vX, 6, 1);

        Label vY = new Label(String.valueOf((int) Math.round(a.getVitesseY()*100.0)/100.0));
        vY.setAlignment(Pos.CENTER);
        Label lvy = new Label(" Vitesse y ");
        lvy.setAlignment(Pos.CENTER);
        menuSys.add(lvy, 7, 0);
        menuSys.add(vY, 7, 1);

        supprimer.setAlignment(Pos.CENTER_RIGHT);
        menuSys.setPadding(new Insets(20, 20, 20 , 20));
        //menuSys.getChildren().addAll(nom, taille, masse, pX, pY, vX, vY);

        menuSys.add(supprimer, 8, 0);
        menuSys.add(modifier, 8, 1);

        ChangeListener<Number> onChange = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                pX.setText(String.valueOf( (int) Math.round(a.getPositionX()*100.0)/100.0));
                pY.setText(String.valueOf( (int) Math.round(a.getPositionY()*100.0)/100.0));
                vX.setText(String.valueOf((int) Math.round(a.getVitesseX()*100.0)/100.0));
                vY.setText(String.valueOf((int) Math.round(a.getVitesseY()*100.0)/100.0));
            }
        };
        Astre.addListenerAll(a, onChange);
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
        Label ln = new Label("Nom  ");
        ln.setAlignment(Pos.CENTER);
        menuSys.add(ln, 1,0);
        menuSys.add(nom,1,1);

        TextField taille = new TextField(String.valueOf((int) a.getTaille()));
        taille.setAlignment(Pos.CENTER);
        Label lt = new Label(" Taille en 10^9 m ");
        lt.setAlignment(Pos.CENTER);
        menuSys.add(lt, 2, 0);
        menuSys.add(taille, 2, 1);

        TextField masse = new TextField(String.valueOf((int) a.getMasse()));
        masse.setAlignment(Pos.CENTER);
        Label lm = new Label(" Masse en 10^20 kg ");
        lm.setAlignment(Pos.CENTER);
        menuSys.add(lm, 3, 0);
        menuSys.add(masse, 3, 1);

        TextField pX = new TextField(String.valueOf((int) a.getPositionX()));
        pX.setAlignment(Pos.CENTER);
        Label lpx = new Label(" Position x ");
        lpx.setAlignment(Pos.CENTER);
        menuSys.add(lpx, 4, 0);
        menuSys.add(pX, 4, 1);

        TextField pY = new TextField(String.valueOf((int) a.getPositionY()));
        pY.setAlignment(Pos.CENTER);
        Label lpY = new Label(" Position y ");
        lpY.setAlignment(Pos.CENTER);
        menuSys.add(lpY, 5, 0);
        menuSys.add(pY, 5, 1);

        TextField vX = new TextField(String.valueOf((int) a.getVitesseX()));
        vX.setAlignment(Pos.CENTER);
        Label lvx = new Label(" Vitesse x ");
        lvx.setAlignment(Pos.CENTER);
        menuSys.add(lvx, 6, 0);
        menuSys.add(vX, 6, 1);

        TextField vY = new TextField(String.valueOf((int) a.getVitesseY()));
        vY.setAlignment(Pos.CENTER);
        Label lvy = new Label(" Vitesse y ");
        lvy.setAlignment(Pos.CENTER);
        menuSys.add(lvy, 7, 0);
        menuSys.add(vY, 7, 1);

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
                supprimer.setText("Supprimer " + a.getNom());
                modifier.setText("Modifier " + a.getNom());
                afficherInfos(a, supprimer, modifier);
            }
        };

        apply.setOnAction(onConfirmerModif);
        menuSys.setPadding(new Insets(15, 15, 15, 15));
        menuSys.add(apply, 8, 0);
    }


    HashMap<Circle, Astre> listeCetA;


    public void afficherList(){

        menuSys.getChildren().clear();
        ArrayList<Astre> listeA = menuGen.getSimulationView().getSimulation().getListeAstre();

        int count = 0;
        for(Astre a : listeA){
            Circle c = new Circle();
            c.setFill(a.getColor());
            c.setRadius(20);
            listeCetA.put(c, a);

            Label nom = new Label(a.getNom());
            nom.setAlignment(Pos.CENTER);
            VBox box = new VBox();
            box.setSpacing(3);
            box.getChildren().addAll(nom, c);
            menuSys.add(box, count, 0);
            count++;
        }

        for(Circle c : listeCetA.keySet()){
            menuGen.getSimulationView().getEspace().setSelectedListener(listeCetA, c);
        }

    }
}
