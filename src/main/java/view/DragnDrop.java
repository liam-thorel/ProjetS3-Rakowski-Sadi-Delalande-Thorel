package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DragnDrop extends ListView{

    private MenuAjouter m;
    private ObservableList<Circle> planetesCourantes = FXCollections.observableArrayList();
    private ListView<Circle> mesAstres = new ListView<>(planetesCourantes);
    private Circle aAjouter = new Circle();

    public DragnDrop(MenuAjouter menuA){
        this.m = menuA;
        //planetesCourantes = FXCollections.observableArrayList();
        //mesAstres.setItems(planetesCourantes);


        ///// Listener /////
        planetesCourantes.addListener(new ListChangeListener<Circle>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Circle> change) {
                while(change.next()) {
                    if (change.wasAdded()) {
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

    }

    public Circle getaAjouter() {return aAjouter;}

    public ObservableList<Circle> getPlanetesCourantes() {return planetesCourantes;}

    public void setMesAstres(ListView<Circle> mesAstres) {this.mesAstres = mesAstres;}

    public ListView<Circle> getMesAstres() {return mesAstres;}

    public MenuAjouter getM() {return m;}
}
