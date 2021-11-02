package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.Astre;
import logic.Planete;

import java.util.Locale;

public class MenuAddAstre extends HBox {
    private Button newAstre;
    private TextField nom, taille, masse, positionX, positionY, vitesseX, vitesseY, estFixe;
    private Label nomtxt, tailletxt, massetxt, positionXtxt, positionYtxt, vitesseXtxt, vitesseYtxt, estFixetxt;
    private MenuAjouter mA;

    public MenuAddAstre(MenuAjouter mA) {
        newAstre = new Button();
        this.mA=mA;

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
                String n,f;
                int m,t,pX,pY;
                double vX,vY;

                n = nom.getText();
                f=estFixe.getText();
                try {
                    m = Integer.parseInt(masse.getText());
                    t = Integer.parseInt(taille.getText());
                    pX = Integer.parseInt(positionX.getText());
                    pY = Integer.parseInt(positionY.getText());
                    vX = Double.parseDouble(vitesseX.getText());
                    vY = Double.parseDouble(vitesseY.getText());
                    f = f.toLowerCase(Locale.ROOT);
                    if (f.equals("oui")||f.equals("true")||f.equals("yes")){
                       Astre p = new Planete(n,t,m,pX,pY,vX,vY,true);
                       mA.getM().getSimulation().getSimulation().addListeAstre(p);
                    }
                    if (f.equals("non")||f.equals("false")||f.equals("no")){
                       Astre p =  new Planete(n,t,m,pX,pY,vX,vY,false);
                       mA.getM().getSimulation().getSimulation().addListeAstre(p);
                    }
                    for (Astre a :mA.getM().getSimulation().getSimulation().getListeAstre()){
                        System.out.println(a);
                    }


                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                nom.clear();
                taille.clear();
                masse.clear();
                positionX.clear();
                positionY.clear();
                vitesseX.clear();
                vitesseY.clear();
                estFixe.clear();
            }
        };


}

