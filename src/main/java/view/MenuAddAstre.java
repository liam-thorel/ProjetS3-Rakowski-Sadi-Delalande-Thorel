package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import model.Astre;
import model.Planete;

public class MenuAddAstre extends VBox {
    /** Bouton Ajouter Astre */
    private Button newAstre;
    /** TextField des données corespondant au nom de l'attribut */
    private TextField nom, taille, masse, positionX, positionY, vitesseX, vitesseY;
    /** texte qui coressepont au TextField */
    private Label nomtxt, tailletxt, massetxt, positionXtxt, positionYtxt, vitesseXtxt, vitesseYtxt;
    /** Menu mère */
    private MenuAjouter mA;
    /**Vbox qui relie texte et TextField */
    private VBox nameAll,tailleAll,masseAll,positionXAll, positionYAll, vitesseXAll, vitesseYAll;
    /** Pane mère */
    private HBox all = new HBox();
    /** label pour afficher une erreur s'il y' a */
    private Label error;
    private Label errorProximite;
    private DragnDrop d;
    private Circle newB;
    private Astre newC;

    public Circle getNewB() {return newB;}

    public MenuAjouter getmA() {return mA;}

    public MenuAddAstre(MenuAjouter mA) {
        //initialisation attributs
        newAstre = new Button();
        this.mA=mA;

        //contenu de Add Astre
        //nom
        nom = new TextField();
        nomtxt = new Label("Nom");
        nameAll = new VBox();
        nameAll.getChildren().addAll(nomtxt, nom);
        nom.setMaxWidth(75);
        //taille
        taille = new TextField();
        tailletxt = new Label("Taille");
        tailleAll = new VBox();
        tailleAll.getChildren().addAll(tailletxt, taille);
        taille.setMaxWidth(75);
        //masse
        masse = new TextField();
        Tooltip masseInfo = new Tooltip("La masse de la Terre est 59736");//quand on survol le textfield ce message apparait
        masse.setTooltip(masseInfo);
        massetxt = new Label("Masse en 10^20 kg");
        masseAll = new VBox();
        masseAll.getChildren().addAll(massetxt, masse);
        masse.setMaxWidth(75);
        //positionX
        positionX = new TextField();

        Tooltip pXinfo = new Tooltip("Le milieu de l'espace est environt à x= " + Math.round(mA.getM().getSimulationView().getApp().getStage().getWidth()/2));
        positionX.setTooltip(pXinfo);
        positionXtxt = new Label("Position X");
        positionXAll = new VBox();
        positionXAll.getChildren().addAll(positionXtxt, positionX);
        positionX.setMaxWidth(50);
        //positionY
        positionY = new TextField();
        if (mA.getM().getSimulationView().getApp().getDebug()) System.out.println(mA.getPrefHeight());
        Tooltip pYinfo = new Tooltip("Le milieu de l'espace est environt à y=" + (Math.round((mA.getM().getSimulationView().getApp().getStage().getHeight()*0.73/2))));
        positionY.setTooltip(pYinfo);
        positionYtxt = new Label("Position Y");
        positionYAll = new VBox();
        positionYAll.getChildren().addAll(positionYtxt, positionY);
        positionY.setMaxWidth(50);
        //VitesseX
        vitesseX = new TextField();
        Tooltip vXinfo = new Tooltip("Minimum 1000 pour voir l'astre bouger ");
        vitesseX.setTooltip(vXinfo);
        vitesseXtxt = new Label("Vitesse X en m/s");
        vitesseXAll = new VBox();
        vitesseXAll.getChildren().addAll(vitesseXtxt, vitesseX);
        vitesseX.setMaxWidth(75);
        //vitesseY
        vitesseY = new TextField();
        Tooltip vYinfo = new Tooltip("Minimum 1000 pour voir l'astre bouger ");
        vitesseY.setTooltip(vYinfo);
        vitesseYtxt = new Label("Vitesse Y en m/s");
        vitesseYAll = new VBox();
        vitesseYAll.getChildren().addAll(vitesseYtxt, vitesseY);
        vitesseY.setMaxWidth(75);

        //garder un bouton centrer avec les textField
        VBox button = new VBox();
        button.getChildren().addAll(new Label(), newAstre);

        //Ajout à la Pane mère
        all.getChildren().addAll(nameAll, tailleAll, masseAll, positionXAll, positionYAll, vitesseXAll, vitesseYAll,button);
        //ajout au menu
        getChildren().add(all);
        newAstre.setText("Ajouter Astre");
        all.setSpacing(8);

        // si bouton newAstre pesser faire :
        newAstre.setOnAction(onAjouterAstre);
    }

        /** Quand Bouton ajouter Pressé ajoute l'astre en fonction des caractèristique précisé dans les texteField */
        private EventHandler<ActionEvent> onAjouterAstre = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                //initialisation des attributs qui vont stocker les cast des textes en leur vrais types
                String n;
                int pX,pY ;
                float m,t;
                double vX,vY;

                n = nom.getText();
                /**vérifie si les attributs sont bien du bon type
                * si dans la masse il n'y a pas de lettre par exemple
                 */
                try {
                    m = Float.parseFloat(masse.getText()); // cast le texte dans masse en Float
                    t = Float.parseFloat(taille.getText());
                    pX = Integer.parseInt(positionX.getText());
                    pY = Integer.parseInt(positionY.getText());
                    vX = Double.parseDouble(vitesseX.getText());
                    vY = Double.parseDouble(vitesseY.getText());
                    int nbTropProches = 0;
                    String listeProchesNoms = "";
                    //vérifie s'il n'y a pas des Astre trop proche déja existant
                    for(Astre a : mA.getM().getSimulationView().getEspace().listeA){
                        if(Math.sqrt(Math.pow(pX-a.getPositionX(),2)+Math.pow(pY-a.getPositionY(),2))<t){
                            listeProchesNoms+= a.getNom() + " ";
                            nbTropProches++;
                        }
                    }
                    //affiche erreur planète déja existante trop proche + nombre
                    if(nbTropProches == 1){
                        getChildren().remove(error);
                        error = new Label("ERREUR : Planète trop proche : " + listeProchesNoms);
                        getChildren().add(error);
                    }else if(nbTropProches > 1){
                        getChildren().remove(error);
                        error = new Label("ERREUR : Planètes trop proches : " + listeProchesNoms);
                        getChildren().add(error);
                        }
                    // insère la planète normalement
                    else{
                        Astre p = new Planete(n,t,m,pX,pY,vX,vY);
                        //newC = p;
                        //p.toString();
                        //Circle a = EspaceView.creerPlaneteCercle(p);
                        //newB = a;
                        //mA.getDnd().getPlanetesCourantes().add(a);
                        mA.getM().getSimulationView().getEspace().getListeA().add(p);
                        //if (mA.getM().getSimulationView().getApp().getDebug())System.out.println(mA.getDnd().getMesAstres());
                        getChildren().remove(error);

                    }
                    //affiche une erreur, car donnée(s) pas de bon type
                }catch (NumberFormatException e){
                    getChildren().remove(error);
                    error = new Label("Erreur données pas de bon type ou non remplis");
                    getChildren().add(error);
                    if (n.equals("")){
                        error.setText("nom de l'astre non précisé");
                    }


                }
                //supprime toute les données qui ont été précédemment inséré
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

