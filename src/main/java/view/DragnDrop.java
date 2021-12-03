package view;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import model.Astre;
import model.Planete;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

public class DragnDrop extends ListView{

    private MenuAjouter m;
    private ObservableList<Circle> planetesCourantes = FXCollections.observableArrayList();
    private ListView<Circle> mesAstres = new ListView<>(planetesCourantes);
    private Circle aAjouter = new Circle();
    private MenuAddAstre ma;

    public DragnDrop(MenuAjouter menuA, MenuAddAstre menueAA){
        this.m = menuA;
        this.ma = this.m.getAddAstre();
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
                if (m.getM().getSimulationView().getApp().getDebug())System.out.println("onDragDropped laché sur la cible mon ptit pote");
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    if (m.getM().getSimulationView().getApp().getDebug())System.out.println("c'est bon mon ptit pote");
                    m.getM().getSimulationView().getEspace().listeA.add(ma.getNewC());
                    planetesCourantes.remove(aAjouter);
                    if (!m.getM().getSimulationView().getSimulation().getListeAstre().contains(m.getM().getSimulationView().getEspace().getListeCetA().get(ma.getNewB()))) {
                        if (m.getM().getSimulationView().getApp().getDebug())System.out.println("c'est CARRE");
                    }
                }
                else {event.setDropCompleted(false);}
                event.setDropCompleted(success);
                event.consume();
            }
        });


    }

    public Circle getaAjouter() {return aAjouter;}

    public ObservableList<Circle> getPlanetesCourantes() {return planetesCourantes;}

    public void setMesAstres(ListView<Circle> mesAstres) {this.mesAstres = mesAstres;}

    public ListView<Circle> getMesAstres() {return mesAstres;}

    public MenuAjouter getM() {return m;}
}
