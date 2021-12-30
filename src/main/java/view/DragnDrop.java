package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Astre;
import model.Planete;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class DragnDrop extends HBox{

    private MenuAjouter m;
    private ObservableList<Circle> planetesCourantes = FXCollections.observableArrayList();
    private ListView<Circle> mesAstres = new ListView<>(planetesCourantes);
    private Circle aAjouter = new Circle();
    private MenuAddAstre ma;

    public DragnDrop(MenuAjouter menuA){
        this.m = menuA;
        this.ma = this.m.getAddAstre();
        this.getChildren().add(mesAstres);
        //dropped = new MouseAdapter(m.getM().getSimulationView().getEspace());
        //planetesCourantes = FXCollections.observableArrayList();
        //mesAstres.setItems(planetesCourantes);


        ///// Listener /////
        planetesCourantes.addListener(new ListChangeListener<Circle>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Circle> change) {
                while(change.next()) {
                    if (change.wasAdded()) {
                        mesAstres.setItems(planetesCourantes);
                        System.out.println(change);
                        System.out.println("++Ajout");
                    }
                    if (change.wasRemoved()) {
                        planetesCourantes.remove(aAjouter);
                        System.out.println("++Retrait");
                    }
                }
            }
        });

        /////////////// Paramètres pour l'affichage les planètes en attentes ///////////////
        mesAstres.setOrientation(Orientation.HORIZONTAL); // Orientation pour la ListView
        setPrefSize(500, 70); // taille ListView

        //écoute l'index de la planète sélectionnée
        mesAstres.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Circle> ov, Circle old_val, Circle new_val) -> {
        aAjouter = new_val;
        if ( aAjouter != null){System.out.println(aAjouter);}
        });
        //mesAstres.getSelectionModel().selectedIndexProperty().addListener(observable -> System.out.printf("Indice sélectionné: %d", mesAstres.getSelectionModel().getSelectedIndex()).println());
        //int selectedIndex = mesAstres.getSelectionModel().getSelectedIndex();


                                            ///// DRAG N' DROP /////
        mesAstres.setOnDragDetected(mouseEvent -> {
        System.out.println("Drag n' Drop detecté mon ptit pote");
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

        m.getM().getSimulationView().getEspace().setOnDragOver(new EventHandler<DragEvent>() {
        public void handle(DragEvent event) {
            System.out.println("onDragOver détecté sur sa cible mon ptit pote");
            /* accept it only if it is  not dragged from the same node
             * and if it has a string data */
            if (event.getGestureSource() != m.getM().getSimulationView().getEspace() && event.getDragboard().hasString()) {
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

        m.getM().getSimulationView().getEspace().setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) { //PLANETE DEPOSEE
                    System.out.println("onDragDropped laché sur la cible mon ptit pote");
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasString()) {
                        System.out.println("c'est bon mon ptit pote");
                        PointerInfo pointer = MouseInfo.getPointerInfo();
                        Point location = pointer.getLocation();
                        System.out.println("La souris se trouve en " + location);
                        ma.getNewC().setPositionX(location.getX());
                        ma.getNewC().setPositionY(location.getY());
                        m.getM().getSimulationView().getEspace().listeA.add(ma.getNewC());
                        planetesCourantes.remove(aAjouter);
                        System.out.println("c'est CARRE");
                    } else {
                        event.setDropCompleted(false);
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }

        });
    }

/*
    private MouseEvent planeteLachee = new MouseEvent() {
        public void mouseReleased( MouseEvent e ) {
        //if(e.getButton()==MouseButton.PRIMARY);
        //{
            // Récupération de la position
        double pX = e.getX();
        double pY = e.getY();
        //}
    }

    };
    private Pane esp = m.getM().getSimulationView().getEspace();
    esp.addMouseEvent(planeteLachee);*/

    public Circle getaAjouter() {return aAjouter;}

    public ObservableList<Circle> getPlanetesCourantes() {return planetesCourantes;}

    public void setMesAstres(ListView<Circle> mesAstres) {this.mesAstres = mesAstres;}

    public ListView<Circle> getMesAstres() {return mesAstres;}

    public MenuAjouter getM() {return m;}
}
