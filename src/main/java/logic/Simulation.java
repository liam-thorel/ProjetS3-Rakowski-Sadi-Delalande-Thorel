package logic;

import java.io.*;
import java.util.ArrayList;

import javafx.stage.Stage;
import logic.Astre;


public class Simulation {

    private ArrayList<Astre> listeAstre;

    public Simulation(){
        listeAstre = new ArrayList<>();
    }

    public Simulation(FileInputStream save) throws IOException, ClassNotFoundException {
        setAPartireDunFichier(save);
    }
    public ArrayList<Astre> getListeAstre() {
        return listeAstre;
    }

    public void setListeAstre(ArrayList<Astre> listeAstre) {
        this.listeAstre = listeAstre;
    }

    public void setAPartireDunFichier(FileInputStream save) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(save);
        listeAstre = (ArrayList<Astre>) ois.readObject();
        ois.close();
    }

    //getLine()
    //splitString : .split(avec quoi on split)

    public void saveListeAstre(FileOutputStream save) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(save);
        oos.writeObject(listeAstre);
        oos.close();
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
