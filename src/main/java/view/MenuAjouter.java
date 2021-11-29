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
    private Circle aAjouter = new Circle();

    public Circle getaAjouter() {
        return aAjouter;
    }

    public MenuAjouter(Menu m){
        this.m = m;

        menuAstreBg = new Pane();
        menuAddAstreBg = new Pane();
        menuAstre = new HBox();
        addAstre = new MenuAddAstre(this);
        menu=new HBox();

        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(m.getSimulationView().getApp().getStage().getWidth());
        rectangle.setHeight(m.getSimulationView().getApp().getStage().getHeight()*0.27);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.rgb(47, 49, 54, 0.9));
        menuBg.getChildren().add(rectangle);
        menuBg.getChildren().add(menu);

        /////////////// Pour afficher les planètes courantes ;) ///////////////
        mesAstres.setOrientation(Orientation.HORIZONTAL); // Orientation pour la ListView
        mesAstres.setPrefSize(500, 70); // taille ListView
        //écoute l'index de la planète sélectionnée
        mesAstres.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Circle> ov, Circle old_val, Circle new_val) -> {
                    aAjouter = new_val;
                    if ( aAjouter != null){if(m.getSimulationView().getApp().getDebug())System.out.println(aAjouter);}
                });
        //mesAstres.getSelectionModel().selectedIndexProperty().addListener(observable -> System.out.printf("Indice sélectionné: %d", mesAstres.getSelectionModel().getSelectedIndex()).println());
        //int selectedIndex = mesAstres.getSelectionModel().getSelectedIndex();
        ///// Listener /////
        mesPlanetesCourantes.addListener(new ListChangeListener<Circle>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Circle> change) {
                while(change.next()) {
                    if (change.wasAdded()) {
                        if(m.getSimulationView().getApp().getDebug())System.out.println("++Ajout");
                    }
                    if (change.wasRemoved()) {
                        mesPlanetesCourantes.remove(aAjouter);
                        if(m.getSimulationView().getApp().getDebug())System.out.println("++Retrait");
                    }
                }
            }
        });

        ///// DRAG N' DROP /////
        mesAstres.setOnDragDetected(mouseEvent -> {
            if(m.getSimulationView().getApp().getDebug())System.out.println("Drag n' Drop detecté mon ptit pote");
            Dragboard db = mesAstres.startDragAndDrop(TransferMode.MOVE);//autorise le mode de transfert déplacement
            ClipboardContent content = new ClipboardContent();// put a string on dragboard
            content.putString("Planète");
            db.setContent(content);
            mouseEvent.consume();
            // Exporter en tant qu'image.
            WritableImage capturePlanete = aAjouter.snapshot(null, null);
            content.putImage(capturePlanete);
            //
            db.setContent(content);
            mouseEvent.consume();
        });

        m.getSimulationView().getEspace().setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if(m.getSimulationView().getApp().getDebug())System.out.println("onDragOver détecté sur sa cible mon ptit pote");
                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != m.getSimulationView().getEspace() && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE); //accepte les déplacements
                }
                event.consume();
            }
        });


        mesAstres.setOnDragDone(dragEvent -> {
            if (dragEvent.getTransferMode() == TransferMode.MOVE) {
                // retirer la planete en attente dans la liste view
            }
            dragEvent.consume();
        });

        menuAstre.setSpacing(10); // espacement 10 pixels
        menuAstre.getChildren().add(mesAstres);
        //eloignement des différents Menu


        //Création et éffectation du réctangle d'arrière plan derrière Mes astres
        Rectangle rectangleMenuAstres = new Rectangle();
        rectangleMenuAstres.setHeight(70);
        rectangleMenuAstres.setWidth(500);
        rectangleMenuAstres.setFill(Color.WHITE);//rgb(64, 68, 75, 1));
        menuAstreBg.getChildren().add(rectangleMenuAstres);
        menuAstreBg.getChildren().add(menuAstre);




        //Création et éffectation du réctangle d'arrière plan derrière Add astres
        Rectangle rectangleAddAstres = new Rectangle();
        rectangleAddAstres.setHeight(70);
        rectangleAddAstres.setWidth(800);
        rectangleAddAstres.setFill(Color.rgb(64, 68, 75, 1));

        menuAddAstreBg.getChildren().add(rectangleAddAstres);
        menuAddAstreBg.getChildren().add(addAstre);


        //eloignement des différents Menu
        menu.setSpacing(200);
        menu.setPadding(new Insets(25));

        //ajout a menu de l'ensemble des sous menu
        menu.getChildren().addAll(menuAstreBg, menuAddAstreBg);
    }

    public ObservableList<Circle> getMesPlanetesCourantes() {return mesPlanetesCourantes;}

    public void setMesAstres(ListView<Circle> mesAstres) {this.mesAstres = mesAstres;}

    public ListView<Circle> getMesAstres() {return mesAstres;}

    public Pane getMenu() {return menuBg;}

    public Menu getM() {return m;}
}
