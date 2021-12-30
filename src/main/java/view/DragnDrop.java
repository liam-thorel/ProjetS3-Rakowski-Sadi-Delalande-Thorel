package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import java.awt.*;

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
        // mise en place du dnd avec un mouse event sur la listView
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
        db.setContent(content);
        mouseEvent.consume();
        });

        //onDragOver verifie si l'emplacement de la planete est détectée sur sa cible (ici l'espace)
        m.getM().getSimulationView().getEspace().setOnDragOver(new EventHandler<DragEvent>() {
        public void handle(DragEvent event) {
            /* accept it only if it is  not dragged from the same node
             * and if it has a string data */
            if (event.getGestureSource() != m.getM().getSimulationView().getEspace() && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE); //accepte les déplacements
            }
            event.consume();
        }
        });

        // Planete retirée du dnd (retirer la planete en attente dans la liste view)
        mesAstres.setOnDragDone(dragEvent -> {
        if (dragEvent.getTransferMode() == TransferMode.MOVE) {
        }
        dragEvent.consume();
        });

        // PLANETE RELACHEE SUR L'ESPACE (vérification cible + récup position souris + ajout de la planete au systeme)
        m.getM().getSimulationView().getEspace().setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) { //PLANETE DEPOSEE
                System.out.println("onDragDropped laché sur la cible mon ptit pote");
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    PointerInfo pointer = MouseInfo.getPointerInfo();
                    Point location = pointer.getLocation();
                    ma.getNewC().setPositionX(location.getX());
                    ma.getNewC().setPositionY(location.getY());
                    m.getM().getSimulationView().getEspace().listeA.add(ma.getNewC());
                    planetesCourantes.remove(aAjouter);
                } else {
                    event.setDropCompleted(false);
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    public ObservableList<Circle> getPlanetesCourantes() {return planetesCourantes;}

}
