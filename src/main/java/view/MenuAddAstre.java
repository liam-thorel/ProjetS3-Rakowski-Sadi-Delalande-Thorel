package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.Astre;
import model.Planete;

public class MenuAddAstre extends VBox {
    private Button newAstre;
    private TextField nom, taille, masse, positionX, positionY, vitesseX, vitesseY;
    private Label nomtxt, tailletxt, massetxt, positionXtxt, positionYtxt, vitesseXtxt, vitesseYtxt;
    private MenuAjouter mA;
    private VBox nameAll;
    private HBox all = new HBox();
    //Changer error en fonction de l'erreur !!!!!
    private Label error;
    private Label errorProximite;
    private DragnDrop d;
    private Circle newB;
    private Astre newC;

    public Circle getNewB() {return newB;}

    public MenuAjouter getmA() {return mA;}

    public MenuAddAstre(MenuAjouter mA) {
        newAstre = new Button();
        this.mA=mA;

        //contenu de Add Astre
        nom = new TextField();
        nomtxt = new Label("Nom");
        nameAll = new VBox();
        nameAll.getChildren().addAll(nomtxt, nom);
        nom.setMaxWidth(75);
        taille = new TextField();
        tailletxt = new Label("Taille");
        VBox tailleAll = new VBox();
        tailleAll.getChildren().addAll(tailletxt, taille);
        taille.setMaxWidth(75);

        masse = new TextField();
        massetxt = new Label("Masse en 10^20 kg");
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


        VBox button = new VBox();
        button.getChildren().addAll(new Label(), newAstre);
        all.getChildren().addAll(nameAll, tailleAll, masseAll, positionXAll, positionYAll, vitesseXAll, vitesseYAll,button);
        getChildren().add(all);
        newAstre.setText("Ajouter Astre");
        all.setSpacing(8);

        newAstre.setOnAction(onAjouterAstre);
    }


        private EventHandler<ActionEvent> onAjouterAstre = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                String n;
                int pX,pY ;
                float m,t;
                double vX,vY;

                n = nom.getText();
                try {
                    m = Float.parseFloat(masse.getText());
                    t = Float.parseFloat(taille.getText());
                    pX = Integer.parseInt(positionX.getText());
                    pY = Integer.parseInt(positionY.getText());
                    vX = Double.parseDouble(vitesseX.getText());
                    vY = Double.parseDouble(vitesseY.getText());
                    boolean isFixed = false;
                    int nbTropProches = 0;
                    String listeProchesNoms = "";
                    for(Astre a : mA.getM().getSimulationView().getEspace().listeA){
                        if(Math.sqrt(Math.pow(pX-a.getPositionX(),2)+Math.pow(pY-a.getPositionY(),2))<t){
                            listeProchesNoms+= a.getNom() + " ";
                            nbTropProches++;
                        }
                    }
                    if(nbTropProches == 1){
                        getChildren().remove(error);
                        error = new Label("ERREUR : Planète trop proche : " + listeProchesNoms);
                        getChildren().add(error);
                    }else if(nbTropProches > 1){
                        getChildren().remove(error);
                        error = new Label("ERREUR : Planètes trop proches : " + listeProchesNoms);
                        getChildren().add(error);
                    }
                    else{
                        Astre p = new Planete(n,t,m,pX,pY,vX,vY,isFixed);
                        //newC = p;
                        //p.toString();
                        //Circle a = EspaceView.creerPlaneteCercle(p);
                        //newB = a;
                        //mA.getDnd().getPlanetesCourantes().add(a);
                        mA.getM().getSimulationView().getEspace().getListeA().add(p);
                        //if (mA.getM().getSimulationView().getApp().getDebug())System.out.println(mA.getDnd().getMesAstres());
                        getChildren().remove(error);

                    }
                }catch (NumberFormatException e){
                    getChildren().remove(error);
                    error = new Label("Erreur données pas de bon type ou non remplis");
                    getChildren().add(error);
                    if (n.equals("")){
                        error.setText("nom de l'astre non précisé");
                    }


                }
                nom.clear();
                taille.clear();
                masse.clear();
                positionX.clear();
                positionY.clear();
                vitesseX.clear();
                vitesseY.clear();

            }
        };

    public Button getNewAstre() {return newAstre;}

    public Astre getNewC() {return newC;}
}

