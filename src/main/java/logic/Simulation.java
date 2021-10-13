package logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.Astre;
import view.SimulationView;

import static javafx.scene.paint.Color.WHITE;


public class Simulation {

    private ArrayList<Astre> listeAstre;



    public Simulation(){
        listeAstre = new ArrayList<>();
    }

    public Simulation(File save) throws IOException, ClassNotFoundException {
        listeAstre = setAPartireDunFichier(save);
    }
    public ArrayList<Astre> getListeAstre() {
        return listeAstre;
    }

    public void setListeAstre(ArrayList<Astre> listeAstre) {
        this.listeAstre = listeAstre;
    }

    public ArrayList<Astre> setAPartireDunFichier(File save) throws IOException {
        /*ObjectInputStream ois = new ObjectInputStream(save);
        listeAstre = (ArrayList<Astre>) ois.readObject();
        ois.close();*/
        ArrayList<Astre> listeA = new ArrayList<>();
        CreateurAstre p = new CreateurPlanete();
        for (String ligne : Files.readAllLines(save.toPath())){
            int cpt =1;
            String name = "";
            int taille =0;
            int masse = 0;
            int pX = 0;
            int pY = 0;
            boolean creer = true;
            for(String chaine : ligne.split(" ")){
                if (!chaine.contains("//")){
                    switch (cpt){
                        case 1:
                            name = chaine;
                            break;
                        case 2 :
                            taille = Integer.parseInt(chaine);
                            break;
                        case 3 :
                            masse = Integer.parseInt(chaine);
                            break;
                        case 4 :
                            pX = Integer.parseInt(chaine);
                            break;
                        case 5 :
                            pY = Integer.parseInt(chaine);
                            break;
                    }
                }else{
                    creer = false;
                }
                cpt++;
            }
            if(creer){
                listeA.add(p.factory(name, taille, masse, pX, pY));
            }
        }
        return  listeA;

    }


    //getLine()
    //splitString : .split(avec quoi on split)

    public void saveListeAstre(File save) throws IOException {
        if(save.exists()){
            save.delete();
        }
        save.createNewFile();
        String commentaire = "//nom_taille_masse_positionX_positionY \n";
        Files.write(save.toPath(), commentaire.getBytes(), StandardOpenOption.APPEND);
        for(Astre a : listeAstre){
            String arg = a.getArgString();
            Files.write(save.toPath(), arg.getBytes(), StandardOpenOption.APPEND);


        }
    }

    @Override
    public String toString() {
        if (listeAstre.isEmpty()){
            return "la simulation est vide";
        }
        return "Simulation{ " +
                 listeAstre +
                '}';
    }
}
