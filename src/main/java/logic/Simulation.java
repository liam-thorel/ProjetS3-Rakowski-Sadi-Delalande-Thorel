package logic;

import java.io.*;
import java.util.ArrayList;
import logic.Astre;


public class Simulation implements Serializable {

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
        ArrayList<Astre> listeA = (ArrayList<Astre>) ois.readObject();
        listeAstre = listeA;
        ois.close();
    }

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
