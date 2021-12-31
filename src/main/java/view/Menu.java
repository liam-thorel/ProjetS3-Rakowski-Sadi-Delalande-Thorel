package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;


public class Menu extends Pane {
    /** Pane mère */
    private VBox menuEtChangeMenu;
    /** BorderPane contenant les pane pour changer de menu, mettre pause et afficher les options */
    private BorderPane changeMenu;
    /** Menu Ajouter */
    private MenuAjouter menuAjouter;
    /** Pane du menu Ajouter */
    private Pane menuAjouterPane;
    /** Pane du menu actuel */
    private Pane menuActuelPane;
    /** Menu Systeme */
    private MenuSysteme menuSysteme;
    /** Pane du menu Systeme */
    private Pane menuSystemePane;
    /** Hbox contenant les boutons Systeme et Ajouter */
    private HBox systemeOuAjouter;
    /** Hbox contenant les boutons jouer, pause et option */
    private HBox playOuPause;
    /**Bouton du menu Systeme */
    private Button systeme;
    /**Bouton du menu ajouter Astre */
    private Button ajouter;
    /** Bouton mettre sur jouer la simulation */
    private Button play = new Button("Jouer");
    /** Bouton mettre sur Pause la simulation */
    private Button pause = new Button("Pause");
    /** Bouton Option */
    private Button option = new Button("Options");
    /** SimulationView courante */
    private SimulationView sV;


    public Menu(SimulationView sV) {
        //initialisation des attributs
        this.sV =sV;
        changeMenu = new BorderPane();
        menuAjouter = new MenuAjouter(this);
        menuSysteme = new MenuSysteme(this);
        menuAjouterPane = menuAjouter.getMenu();
        menuSystemePane = menuSysteme.getMenu();
        menuEtChangeMenu = new VBox();
        systemeOuAjouter = new HBox();
        playOuPause = new HBox();
        systeme = new Button("Systeme");
        ajouter = new Button("Ajouter");

        //échelles des menus ajouter et système
        menuAjouter.setPrefWidth(sV.getApp().getStage().getWidth());
        menuAjouter.setPrefHeight(sV.getApp().getStage().getHeight()*0.27);
        menuSysteme.setPrefWidth(sV.getApp().getStage().getWidth());
        menuSysteme.setPrefHeight(sV.getApp().getStage().getHeight()*0.27);

        /** Si liste vide alors afficher comme menu à l'initialisation le menu ajouter
         * sinon afficher les menu systeme */
        if (sV.getEspace().getListeA().size()==0) {
            menuActuelPane = menuAjouterPane;
        }else{
            menuActuelPane = menuSystemePane;
        }



        // Stylisation des boutons dans changeMenu
        systeme.prefWidth(20);
        systeme.prefHeight(15);
        ajouter.prefWidth(20);
        ajouter.prefHeight(15);
        play.prefWidth(20);
        play.prefHeight(15);
        pause.prefWidth(20);
        pause.prefHeight(15);
        option.setPrefHeight(15);
        option.prefWidth(20);

        // Quand bouton presser faire :
        systeme.setOnAction(onSystemeMenu);
        ajouter.setOnAction(onAjouterMenu);
        play.setOnAction(onPlay);
        pause.setOnAction(onPause);
        option.setOnAction(onOption);


        //Ajout dans systemeOuAjouter les boutons systeme et ajouter pour changer de menu actuel
        systemeOuAjouter.getChildren().addAll(systeme, ajouter);
        //mettre systemeOuAjouter a gauche de change Menu
        changeMenu.setLeft(systemeOuAjouter);
        //mettre playOuPause a gauche de change Menu
        changeMenu.setRight(playOuPause);
        systemeOuAjouter.setSpacing(5);
        playOuPause.setSpacing(5);
        changeMenu.setMaxWidth(sV.getApp().getStage().getWidth()-20);
        playOuPause.getChildren().addAll(play,pause, option);
        /**Ajout de changeMenu contenant les boutons pour effectuer des actions en haut
         * et menuActuelPane en bas
        */
        menuEtChangeMenu.getChildren().addAll(changeMenu, menuActuelPane);




    }
    /** Quand bouton Ajouter pressé affiche le menu Ajouter (enlève le menu actuel de la Pane mère
     * redéfinis le menu ajouter comme pane actuel
     * et ajoute à la Pane mère le menu actuel) */
    private EventHandler<ActionEvent> onAjouterMenu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            menuEtChangeMenu.getChildren().remove(menuActuelPane);
            menuActuelPane = menuAjouterPane;
            menuEtChangeMenu.getChildren().add(menuActuelPane);
        }
    };

    /** Quand bouton Systeme pressé affiche le menu Système (enlève le menu actuel de la Pane mère
     * redéfinis le menu Système comme pane actuel
     * et ajoute à la Pane mère le menu actuel) */
    private EventHandler<ActionEvent> onSystemeMenu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            menuEtChangeMenu.getChildren().remove(menuActuelPane);
            menuActuelPane = menuSystemePane;
            menuEtChangeMenu.getChildren().add(menuActuelPane);
        }
    };
    /** met l'attribut playing d'espace observable à true pour jouer la simulation*/
    private EventHandler<ActionEvent> onPlay = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            sV.getEspace().setPlaying(true);
        }
    };
    /** met l'attribut playing d'espace observable à false pour mettre en pause la simulation*/
    private EventHandler<ActionEvent> onPause = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent){
            sV.getEspace().setPlaying(false);
        }
    };
    /** Affiche les options */
    private EventHandler<ActionEvent> onOption = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(PlaneteApp.debug){
                System.out.println("options ouvertes");
            }
            sV.ouvrirMenuOption();
        }
    };


    public VBox getMenuEtChangeMenu() {
        return menuEtChangeMenu;
    }

    public SimulationView getSimulationView() {
        return sV;
    }

    public MenuAjouter getMenuAjouter() {
        return menuAjouter;
    }

    public MenuSysteme getMenuSysteme() {
        return menuSysteme;
    }
}

