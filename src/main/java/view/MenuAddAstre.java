package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
        nameAll.getChildren().addAll(nomtxt, nom);
        nom.setMaxWidth(75);
        taille = new TextField();
        tailletxt = new Label("Taille");
        VBox tailleAll = new VBox();
        tailleAll.getChildren().addAll(tailletxt, taille);
        taille.setMaxWidth(75);

        masse = new TextField();
        massetxt = new Label("Masse");
        VBox masseAll = new VBox();
        masseAll.getChildren().addAll(massetxt, masse);
        masse.setMaxWidth(75);

        positionX = new TextField();
        positionXtxt = new Label("Position X");
        VBox positionXAll = new VBox();
        positionXAll.getChildren().addAll(positionXtxt, positionX);
        positionX.setMaxWidth(50);

        positionY = new TextField();
        positionYtxt = new Label("Position Y");
        VBox positionYAll = new VBox();
        positionYAll.getChildren().addAll(positionYtxt, positionY);
        positionY.setMaxWidth(50);

        vitesseX = new TextField();
        vitesseXtxt = new Label("Vitesse X");
        VBox vitesseXAll = new VBox();
        vitesseXAll.getChildren().addAll(vitesseXtxt, vitesseX);
        vitesseX.setMaxWidth(75);

        vitesseY = new TextField();
        vitesseYtxt = new Label("Vitesse Y");
        VBox vitesseYAll = new VBox();
        vitesseYAll.getChildren().addAll(vitesseYtxt, vitesseY);
        vitesseY.setMaxWidth(75);

        estFixe = new TextField();
        estFixetxt = new Label("fixe ?");
        VBox estFixeAll = new VBox();
        estFixeAll.getChildren().addAll(estFixetxt, estFixe);
        estFixe.setMaxWidth(50);

        VBox button = new VBox();
        button.getChildren().addAll(new Label(), newAstre);

        getChildren().addAll(nameAll, tailleAll, masseAll, positionXAll, positionYAll, vitesseXAll, vitesseYAll, estFixeAll, button);
        newAstre.setText("Ajouter Astre");
        setSpacing(8);

        newAstre.setOnAction(onAjouterAstre);
    }

        private EventHandler<ActionEvent> onAjouterAstre = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                String n,t,m,pX,pY,vX,vY,f;
                n = nom.getText();
                t = taille.getText();
                m = masse.getText();
                pX = positionX.getText();
                pY = positionY.getText();
                vX=vitesseX.getText();
                vY=vitesseY.getText();
                f=estFixe.getText();

                if (f.equals("oui")||f.equals("non")||f.equals("true")||f.equals("false")){

                }

            }


        };


}

