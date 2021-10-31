package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuAddAstre extends HBox {
    private Button newAstre;
    private TextField nom, taille, masse, positionX, positionY, vitesseX, vitesseY, estFixe;
    private Label nomtxt, tailletxt, massetxt, positionXtxt, positionYtxt, vitesseXtxt, vitesseYtxt, estFixetxt;

    public MenuAddAstre(MenuAjouter mA) {
        newAstre = new Button();

        //contenu de Add Astre
        nom = new TextField();
        nomtxt = new Label("Nom");
        VBox nameAll = new VBox();
        nameAll.getChildren().addAll(nomtxt,nom);

        taille = new TextField();
        tailletxt = new Label("Taille");
        VBox tailleAll = new VBox();
        tailleAll.getChildren().addAll(tailletxt,taille);

        masse = new TextField();
        massetxt = new Label("Masse");
        VBox masseAll = new VBox();
        masseAll.getChildren().addAll(massetxt,masse);

        positionX = new TextField();
        positionXtxt = new Label("Position X");
        VBox positionXAll = new VBox();
        positionXAll.getChildren().addAll(positionXtxt,positionX);

        positionY = new TextField();
        positionYtxt = new Label("Position Y");
        VBox positionYAll = new VBox();
        positionYAll.getChildren().addAll(positionYtxt,positionY);

        vitesseX = new TextField();
        vitesseXtxt = new Label("Vitesse X");
        VBox vitesseXAll = new VBox();
        vitesseXAll.getChildren().addAll(vitesseXtxt,vitesseX);

        vitesseY = new TextField();
        vitesseYtxt = new Label("Vitesse Y");
        VBox vitesseYAll = new VBox();
        vitesseYAll.getChildren().addAll(vitesseYtxt,vitesseY);

        estFixe = new TextField();
        estFixetxt = new Label("fixe ?");
        VBox estFixeAll = new VBox();
        estFixeAll.getChildren().addAll(estFixetxt,estFixe);

        estFixe = new TextField();
        getChildren().addAll(nameAll, tailleAll, masseAll, positionXAll, positionYAll, vitesseXAll, vitesseYAll, estFixeAll);
        newAstre.setText("Ajouter Astre");
        getChildren().add(newAstre);
    }
}
