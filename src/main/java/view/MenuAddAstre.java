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
        nomtxt = new Label("nom");
        VBox nameAll = new VBox();
        nameAll.getChildren().addAll(nomtxt,nom);

        taille = new TextField();
        masse = new TextField();
        positionX = new TextField();
        positionY = new TextField();
        vitesseX = new TextField();
        vitesseY = new TextField();
        estFixe = new TextField();
        getChildren().addAll(nameAll, taille, masse, positionX, positionY, vitesseX, vitesseY, estFixe);
        newAstre.setText("Ajouter Astre");
        getChildren().add(newAstre);
    }
}
