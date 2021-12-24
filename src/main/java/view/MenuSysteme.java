package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.converter.NumberStringConverter;
import model.Astre;
import model.Simulation;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuSysteme extends Pane {
    /** Pane mère */
    private HBox menu;
    /** Pane qui affiche les données planète */
    private GridPane menuSys;
    /** arrière-Plan du menu courant */
    private Pane menuBg = new Pane();
    /** Arrière plan menuSystème + menu Sytème*/
    private Pane menuSysBg;
    /** menu mère  */
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

        /**Le control de la vitesse*/
        VBox vitesseField = new VBox();

        //label de presentation
        Label presentation = new Label("Vitesse de la simulation");
        //textField
        HBox simuRateEtfois = new HBox();
        simuRateEtfois.setSpacing(5);
        Label fois = new Label("x");
        TextField rateField = new TextField();
        rateField.setAlignment(Pos.CENTER);
        rateField.setMaxSize(50,20);
        rateField.setText("1.0");
        simuRateEtfois.getChildren().addAll(fois, rateField);
        //warning de depassement
        Label limiteDepassee = new Label("");
        limiteDepassee.setTextFill(Color.WHITE);

        /** ecoute la valeur du simuRate*/
        ChangeListener<String> whenSimuRateIsChange = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (Float.parseFloat(t1) <= 10) {
                    Simulation.setSimuRate(Float.parseFloat(t1));
                    limiteDepassee.setText("");
                } else {
                    Simulation.setSimuRate(10f);
                    limiteDepassee.setText("la limite est de x10");
                }
                if (PlaneteApp.debug) {
                    System.out.println("===========================================================");
                    System.out.println(Simulation.getSimuRate());
                }
            }
        };

        rateField.textProperty().addListener(whenSimuRateIsChange);
        vitesseField.setSpacing(10);
        vitesseField.setPadding(new Insets(40, 20, 10, 10));
        vitesseField.getChildren().addAll(presentation, simuRateEtfois, limiteDepassee);
        menuBg.getChildren().add(vitesseField);



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

    /**affichages des informations d'un astre
     * @param a astre sélectionné
     * @param modifier boutton de modification associé à l'astre
     * @param supprimer boutton de supression associé à l'astre
     * */
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

        //listener des valeurs de la position et de la vitesse pour qu'elles changent en temps réél
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
    /**modification des champs d'un astre
     * @param a astre sélectionné
     * @param modifier boutton de modification associé à l'astre
     * @param supprimer boutton de supression associé à l'astre
     */
    public void modifierInfos(Astre a, Button supprimer, Button modifier){
        menuSys.getChildren().clear();

        //nom
        TextField nom = new TextField(a.getNom());
        nom.setAlignment(Pos.CENTER);
        Label ln = new Label("Nom  ");
        ln.setAlignment(Pos.CENTER);
        menuSys.add(ln, 1,0);
        menuSys.add(nom,1,1);

        //taille
        TextField taille = new TextField(String.valueOf((int) a.getTaille()));
        taille.setAlignment(Pos.CENTER);
        Label lt = new Label(" Taille en 10^9 m ");
        lt.setAlignment(Pos.CENTER);
        menuSys.add(lt, 2, 0);
        menuSys.add(taille, 2, 1);

        //masse
        TextField masse = new TextField(String.valueOf((int) a.getMasse()));
        Tooltip masseInfo = new Tooltip("La masse de la Terre est 59736");//quand on survol le textfield ce message apparait
        masse.setTooltip(masseInfo);
        masse.setAlignment(Pos.CENTER);
        Label lm = new Label(" Masse en 10^20 kg ");
        lm.setAlignment(Pos.CENTER);
        menuSys.add(lm, 3, 0);
        menuSys.add(masse, 3, 1);

        //positionX
        TextField pX = new TextField(String.valueOf((int) a.getPositionX()));
        Tooltip pXinfo = new Tooltip("Le milieu de l'espace est environt à x=730");
        pX.setTooltip(pXinfo);
        pX.setAlignment(Pos.CENTER);
        Label lpx = new Label(" Position x ");
        lpx.setAlignment(Pos.CENTER);
        menuSys.add(lpx, 4, 0);
        menuSys.add(pX, 4, 1);

        //positionY
        TextField pY = new TextField(String.valueOf((int) a.getPositionY()));
        Tooltip pYinfo = new Tooltip("Le milieu de l'espace est environt à y=360");
        pY.setTooltip(pYinfo);
        pY.setAlignment(Pos.CENTER);
        Label lpY = new Label(" Position y ");
        lpY.setAlignment(Pos.CENTER);
        menuSys.add(lpY, 5, 0);
        menuSys.add(pY, 5, 1);

        //vitesseX
        TextField vX = new TextField(String.valueOf((int) a.getVitesseX()));
        Tooltip vXinfo = new Tooltip("Minimum 1000 pour voir l'astre bouger ");
        vX.setTooltip(vXinfo);
        vX.setAlignment(Pos.CENTER);
        Label lvx = new Label(" Vitesse x ");
        lvx.setAlignment(Pos.CENTER);
        menuSys.add(lvx, 6, 0);
        menuSys.add(vX, 6, 1);

        //vitesseY
        TextField vY = new TextField(String.valueOf((int) a.getVitesseY()));
        Tooltip vYinfo = new Tooltip("Minimum 1000 pour voir l'astre bouger ");
        vY.setTooltip(vYinfo);
        vY.setAlignment(Pos.CENTER);
        Label lvy = new Label(" Vitesse y ");
        lvy.setAlignment(Pos.CENTER);
        menuSys.add(lvy, 7, 0);
        menuSys.add(vY, 7, 1);

        Button apply = new Button("Confirmer");

        //event de confirmation des changements,
        EventHandler <ActionEvent> onConfirmerModif = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int index = 0;
                //si rien n'a été changé on sort
                for( Astre astre : getMenuGen().getSimulationView().getEspace().getListeA()){
                    if (astre == a){
                        break;
                    }
                    index++;
                }

                a.setAll(Float.parseFloat(taille.getText()), Float.parseFloat(masse.getText()), nom.getText(), Integer.parseInt(pX.getText()), Integer.parseInt(pY.getText()), Double.parseDouble(vX.getText()), Double.parseDouble(vY.getText()));

                //on supprime l'ancien astre
                getMenuGen().getSimulationView().getEspace().getListeA().remove(index);
                //puis on ajoute le nouveau
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

    /**affiche tout les astres sous la formes d'icones cliquables*/
    public void afficherList(){

        listeCetA = new HashMap<>();
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
