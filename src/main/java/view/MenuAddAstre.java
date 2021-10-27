package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MenuAddAstre extends HBox {
    private Button newAstre;
    private TextField nom, taille, masse, positionX, positionY, vitesseX, vitesseY, estFixe;

    public MenuAddAstre(MenuAjouter mA) {
        newAstre = new Button();

        //contenu de Add Astre
        nom = new TextField();
        taille = new TextField();
        masse = new TextField();
        positionX = new TextField();
        positionY = new TextField();
        vitesseX = new TextField();
        vitesseY = new TextField();
        estFixe = new TextField();
        getChildren().addAll(nom, taille, masse, positionX, positionY, vitesseX, vitesseY, estFixe);
        newAstre.setText("Ajouter Astre");
        getChildren().add(newAstre);
    }
}
