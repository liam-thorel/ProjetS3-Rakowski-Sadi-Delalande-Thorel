package view;


import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import model.Astre;
import model.Simulation;


import java.util.*;

public class EspaceView extends Pane {

    /**liste des astres de la simulation*/
    public ObservableList<Astre> listeA;
    private Simulation s;
    /**verifie si la simulation est en cours*/
    private BooleanProperty playing = new SimpleBooleanProperty();
    /**pour avoir le cercle d'un astre*/
    private HashMap <Astre, Circle> listeAetC;
    /**pour avoir l'astre d'un cercle*/
    private HashMap <Circle, Astre> listeCetA;
    /**boolean de la trajectoire*/
    private BooleanProperty showingT = new SimpleBooleanProperty();
    /**timer de l'animation*/
    private AnimationTimer timer;
    private long previousL = 0;
    private SimulationView sV;
    /**boolean de colision activé ou pas*/
    private boolean colisionBoolean;



    public EspaceView(SimulationView sV){
        this.setOnMouseClicked(nothingSelected);
        this.sV = sV;
        this.s = sV.getSimulation();
        listeAetC = new HashMap<>();
        listeCetA = new HashMap<>();
        listeA = FXCollections.observableArrayList();
        listeA.addListener(addingOrRemovingAstres);
        listeA.addAll(sV.getSimulation().getListeAstre());
        if (sV.getApp().getDebug())System.out.println(listeA);

        playing.setValue(false);
        showingT.setValue(true);
        colisionBoolean = true;
        showingT.addListener(onShowingT);

        /**a chaque frame de la simulation la position des planetes est mise à jour avec move()*/
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(playing.getValue()){
                    move();
                    if (sV.getApp().getDebug())System.out.println(System.currentTimeMillis());
                }
                previousL = l;

            }
        };


        timer.start();

    }

    /**
     * Constructeur pour les test unitaires
     * */
    public EspaceView(Simulation simu){
        this.s = simu;
        listeAetC = new HashMap<>();
        listeCetA = new HashMap<>();
        listeA = FXCollections.observableArrayList();
        listeA.addListener(addingOrRemovingAstres);
        listeA.addAll(s.getListeAstre());

        playing.setValue(false);
        showingT.setValue(true);
        showingT.addListener(onShowingT);


    }

    /**
     * créer un cercle représentant graphiquement un astre
     * @param astre l'astre que l'on va representer
     */
    public static Circle creerPlaneteCercle(Astre astre){
        Circle planete = new Circle();
        Color c;
        if(astre.getColor() == null){
            c = new Color(new Random().nextFloat(),new Random().nextFloat(), new Random().nextFloat(), 1);
            astre.setColor(c);
        }else{
            c = astre.getColor();
        }

        planete.setFill(c);
        planete.setStrokeWidth(0.5);
        planete.setStroke(Color.BLUE);
        planete.setCenterX(astre.getPositionX() - astre.getTaille()/2);
        planete.setCenterY(astre.getPositionY() - astre.getTaille()/2);
        planete.setRadius(astre.getTaille()/2);
        return planete;
    }


    /**
     * Gestion du mouvement des astres:
     *  Calcule la nouvelle position de cahque astre par rapport aux autres avec Astre.addVitesse() et Astre.setPosition()
     *  Relocalise à la nouvelle position le cercle associé
     *  Vérifie que l'astre n'entre pas en colision avec un autre
     *    si(oui) -> il appelle collisionFusion(laplusgrandeMasse, lapluspetite) et supprime lapluspetite de listeA
     *  Vérifie que l'on veux tracer les trajectoires
     *    si(oui) -> appelle tracerTrajectoire()
     * */
    public void move(){

        for(Astre a : listeAetC.keySet()) {
            if (sV.getApp().getDebug())System.out.println("ancienne position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
            Astre.addVitesse(Simulation.getOther(a, s.getListeAstre()), a);
            Astre.setPositions(a);

            if(PlaneteApp.debug) {
                System.out.println(a.hashCode());
                System.out.println("nouvelle position de " + a.getNom() + " " + a.getPositionX() + " " + a.getPositionY());
                System.out.println("=============================" + Simulation.getSimuRate() + "===================");}

            Circle currentC = listeAetC.get(a);
            currentC.relocate(a.getPositionX() - a.getTaille()/2, a.getPositionY() - a.getTaille()/2);
            for (Astre autre : Simulation.getOther(a, listeAetC.keySet())){
                if(Astre.verifCollision(a,autre) && colisionBoolean){
                    if (a.getMasse()>=autre.getMasse()){
                        Astre.collisionFusion(a,autre);
                        listeA.remove(autre);
                    } else{
                        Astre.collisionFusion(autre,a);
                        listeA.remove(a);
                    }
                    sV.getMenu().getMenuSysteme().afficherList();

                }
            }
            if(showingT.getValue()){
                tracerTrajectoire(a);
            }
        }
    }

    public HashMap<Astre, Circle> getListeAetC() {
        return listeAetC;
    }

    /**Le listener pour les changements de listeA:
     *   si(ajout) -> pour chaque ajout on creer un cercle avec creerPlaneteCercle(), on les ajoutes aux HashMap, on place le cercle dans l'espace,
     *      et on ajoute le nouvel astre a listeAstre de Simulation
     *   si(suppression) -> pour chaque suppression on enleve l'astre des HashMap et de la listeAstre de Simulation et on retire le cercle de l'espace
     * */
    public ListChangeListener<Astre> addingOrRemovingAstres = new ListChangeListener<>() {
        @Override
        public void onChanged(Change<? extends Astre> change) {
            change.next();
            if(change.wasAdded()) {
                for (Astre a : change.getAddedSubList()) {
                    Circle c = creerPlaneteCercle(a);
                    listeAetC.put(a, c);
                    listeCetA.put(c, a);
                    getChildren().add(c);
                    c.relocate(c.getCenterX(), c.getCenterY());
                    setSelectedListener(listeCetA, c);
                    if(!s.getListeAstre().contains(a)){
                    s.getListeAstre().add(a);}

                }
            }
            if (change.wasRemoved()) {
                for (Astre a : change.getRemoved()) {
                    getChildren().remove(listeAetC.get(a));
                    s.getListeAstre().remove(a);
                    listeCetA.remove(listeAetC.get(a));
                    listeAetC.remove(a);

                }
            }

        }
    };

    public HashMap<Circle, Astre> getListeCetA() {
        return listeCetA;
    }

    /**creer et ajoute un listener à un cercle
     * @param hashMap pour avoir l'astre correspondant au cercle
     * @param c le cercle auquel on va ajouter le listener
     * */
    public void setSelectedListener(HashMap<Circle, Astre> hashMap, Circle c){

        //eventHandler de la selection des cercles: si un cercle est selectionné on affiche ses infos
         EventHandler<MouseEvent> selected = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Circle selectedC = (Circle) mouseEvent.getSource();
                Astre selectedA = hashMap.get(selectedC);
                Button supprimer = new Button("Supprimer " + selectedA.getNom());
                Button modifier = new Button("Modifier " + selectedA.getNom());

                //eventHandler de la suppression pour le boutton supprimer
                EventHandler<ActionEvent> supression = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        listeA.remove(selectedA);
                        sV.getMenu().getMenuSysteme().rmv();
                    }
                };

                //eventHandler de la modification pour le boutton modifier
                EventHandler<ActionEvent> onModify = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        sV.getMenu().getMenuSysteme().modifierInfos(selectedA, supprimer, modifier);
                    }
                };

                supprimer.setOnAction(supression);
                modifier.setOnAction(onModify);

                //affichage des infos avec les bouttons associés
                sV.getMenu().getMenuSysteme().afficherInfos(selectedA, supprimer, modifier);

            }
        };
         c.setOnMouseClicked(selected);
    }

    private ArrayList<Circle> listeTrajectoires = new ArrayList<>();

    /**trace la trajectoire de chaque astre
     * @param a l'astre dont l'on va tracer la trajectoire
     * */
    public void tracerTrajectoire(Astre a){
        Circle p = new Circle();
        p.setFill(Color.RED);
        p.setCenterX(a.getPositionX());
        p.setCenterY(a.getPositionY());
        p.setRadius(0.5);
        listeTrajectoires.add(p);

        getChildren().add(p);
    }


    /**listener de l'etat des trajectoires, si(false) -> appel de effacerAllTrajectoire */
    private ChangeListener<Boolean> onShowingT = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
            if(!t1){
                effacerAllTrajectoire();
            }
        }
    };

    /**méthode pour savoir si la souris est dans un astre
     * @return true si la souris est dans un astre false sinon */
    public boolean mouseInAstre(double mX, double mY){
        boolean isInAstre = false;
        for(Astre a : listeCetA.values()){
            if(a.getPositionX() - a.getTaille() < mX && mX< a.getPositionX()+ a.getTaille()){
                if( a.getPositionY() - a.getTaille() < mY && mY < a.getPositionY()+a.getTaille()){
                    isInAstre = true;
                }
            }
        }

        return isInAstre;

    }

    /**eventHandler quand rien n'est selectionné */
    private EventHandler<MouseEvent> nothingSelected = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(!mouseInAstre(mouseEvent.getX(), mouseEvent.getY())) {

                sV.getMenu().getMenuSysteme().afficherList();
            }
        }
    };




    public void effacerAllTrajectoire(){
        getChildren().removeAll(listeTrajectoires);
        listeTrajectoires.clear();


    }

    public void setShowingT(boolean showingT) {
        this.showingT.setValue(showingT);
    }

    public boolean isShowingT() {
        return showingT.getValue();
    }

    public boolean isColisionBoolean() {
        return colisionBoolean;
    }

    public void setPlaying(boolean playing) {
        this.playing.setValue(playing);
    }

    public ObservableList<Astre> getListeA() {
        return listeA;
    }

    public void setColisionBoolean(boolean colisionBoolean) {
        this.colisionBoolean = colisionBoolean;
    }
}

