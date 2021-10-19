package logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.lang.Math;

public class Simulation {

    public static double g =  6.6742 * Math.pow(10, -11);
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
        ArrayList<Astre> listeA = new ArrayList<>();
        CreateurAstre p = new CreateurPlanete();
        for (String ligne : Files.readAllLines(save.toPath())){
            String name = "";
            int taille =0;
            int masse = 0;
            int pX = 0;
            int pY = 0;
            int vIinit =0;

            boolean creer = true;
            String [] arguments = ligne.split(" ");

                if (!(arguments[0].equals("//"))){
                    name = arguments[0];
                    System.out.println(arguments[0]);
                    taille = Integer.parseInt(arguments[1]);
                    masse = Integer.parseInt(arguments[2]);
                    pX = Integer.parseInt(arguments[3]);
                    pY = Integer.parseInt(arguments[4]);
                    vIinit = Integer.parseInt(arguments[5]);

                }else{
                    creer = false;
                }


            if(creer){
                listeA.add(p.factory(name, taille, masse, pX, pY, vIinit));
            }
        }
        return  listeA;

    }


    public void saveListeAstre(File save) throws IOException {
        if(save.exists()){
            save.delete();
        }
        System.out.println(save.getPath());
        save.toPath();
        save.createNewFile();
        String commentaire = "// nom taille masse positionX positionY vitesseInitiale \n";
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
