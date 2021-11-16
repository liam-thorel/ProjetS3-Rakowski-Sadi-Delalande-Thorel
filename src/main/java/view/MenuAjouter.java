package view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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


    public MenuAjouter(Menu m){
        this.m = m;

        menuAstreBg = new Pane();
        menuAddAstreBg = new Pane();
        menuAstre = new HBox();
        addAstre = new MenuAddAstre(this);
        menu=new HBox();

        //Création et affectation du réctangle d'arrière plan derrière le menu
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(m.getSimulationView().getApp().getDimension().getWidth()-15);
        rectangle.setHeight(m.getSimulationView().getApp().getDimension().getHeight()-690);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.rgb(47, 49, 54, 0.9));
        menuBg.getChildren().add(rectangle);
        menuBg.getChildren().add(menu);

        /////////////// Pour afficher les planètes courantes ;) ///////////////
        // Orientation pour la ListView
        mesAstres.setOrientation(Orientation.HORIZONTAL);
        // taille ListView
        mesAstres.setPrefSize(500, 70);
        //écoute l'index de la planète sélectionnée
        mesAstres.getSelectionModel().selectedIndexProperty().addListener(observable -> System.out.printf("Indice sélectionné: %d", mesAstres.getSelectionModel().getSelectedIndex()).println());
        int selectedIndex = mesAstres.getSelectionModel().getSelectedIndex();
        ///// Listener /////
        mesPlanetesCourantes.addListener(new ListChangeListener<Circle>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Circle> change) {
                while(change.next()) {
                    if (change.wasAdded()) {
                        System.out.println("++Ajout");
                    }
                    if (change.wasRemoved()) {
                        System.out.println("++Retrait");
                        change.getRemoved().forEach(value -> mesPlanetesCourantes.remove(value));
                    }
                }
            }
        });
        ///// DRAG N' DROP /////
        mesAstres.setOnDragDetected(mouseEvent -> {
            System.out.println("Drag n' Drop detecté mon ptit pote");
            final Dragboard dragBroard = mesAstres.startDragAndDrop(TransferMode.MOVE);
            // Remlissage du contenu.
            ClipboardContent content = new ClipboardContent();
            /*
            // Exporter en tant que texte.
            content.putString("Planète");*/
            // Exporter en tant que couleur ARGB.
            DataFormat paintFormat = DataFormat.lookupMimeType(Paint.class.getName());
            paintFormat = (paintFormat == null) ? new DataFormat(Paint.class.getName()) : paintFormat;
            Color color = Color.BLUE;//mesAstres.getSelectionModel().getSelectedIndex().getFill();
            int red = (int) (255 * color.getRed());
            int green = (int) (255 * color.getGreen());
            int blue = (int) (255 * color.getBlue());
            int alpha = (int) (255 * color.getOpacity());
            int argb = alpha << 24 | red << 16 | green << 8 | blue << 0;
            content.put(paintFormat, argb);
            // Exporter en tant qu'image.
            WritableImage capturePlanete = mesAstres.snapshot(null, null);
            content.putImage(capturePlanete);
            //
            dragBroard.setContent(content);
            mouseEvent.consume();
        });



        menuAstre.setSpacing(10); // espacement 10 pixels
        menuAstre.getChildren().add(mesAstres);

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
        rectangleAddAstres.setWidth(695);
        rectangleAddAstres.setFill(Color.rgb(64, 68, 75, 1));

        menuAddAstreBg.getChildren().add(rectangleAddAstres);
        menuAddAstreBg.getChildren().add(addAstre);


        //eloignement des différents Menu
        menu.setSpacing(20);
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
