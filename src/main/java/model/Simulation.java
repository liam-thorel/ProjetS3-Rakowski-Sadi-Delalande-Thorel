package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.lang.Math;

public class Simulation {

    public static double g =   6.6742e-11;
    public static float simuRate = 0.001f;
    private ArrayList<Astre> listeAstre;
    public static double scaleDistance = Math.pow(10, 9); //m = 1m
    public static double scaleMasse = Math.pow(10,22); //kg = 1kk
    public static double scaleTemps = 315360000; // 1 frame ~~ 1 mois
    private File file;

    public Simulation(){
        listeAstre = new ArrayList<>();
    }

    public Simulation(File save, ArrayList<Astre> listeAstre) throws IOException, ClassNotFoundException {
        this.listeAstre = listeAstre;
        file = save;
    }
    public ArrayList<Astre> getListeAstre() {
        return listeAstre;
    }

    public void addListeAstre(Astre a){
        listeAstre.add(a);
    }

    public void setListeAstre(ArrayList<Astre> listeAstre) {
        this.listeAstre = listeAstre;
    }

    public static void setSimuRate(float simuRate) {
        Simulation.simuRate = simuRate;
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

    public File getFile() {
        return file;
    }
}
