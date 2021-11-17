package logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.lang.Math;

public class Simulation {

    public static double g =   6.6742e-11;
    public static float simuRate = 0.001f;
    private ArrayList<Astre> listeAstre;
    public static double scaleDistance = Math.pow(10, 9); //m
    public static double scaleMasse = Math.pow(10,22); //kg
    public static double scaleTemps = 315360000; // 1 frame ~~ 1 mois
    private File file;

    public Simulation(){
        listeAstre = new ArrayList<>();
    }

    public Simulation(File save) throws IOException, ClassNotFoundException {
        listeAstre = setAPartirDunFichier(save);
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

    /** construire une liste d'astre a partir d'un fichier
     * @param save fichier a partir duquel on va construire
     * @return la liste construite a partire de save
     * */
    public ArrayList<Astre> setAPartirDunFichier(File save) throws IOException {
        ArrayList<Astre> listeA = new ArrayList<>();
        CreateurAstre p = new CreateurPlanete();
        for (String ligne : Files.readAllLines(save.toPath())){
            String name = "";
            int taille =0;
            int masse = 0;
            int pX = 0;
            int pY = 0;
            int vitesseX = 0;
            int vitesseY = 0;
            boolean isFixed = false;

            boolean creer = true;
            String [] arguments = ligne.split(" ");

                if (!(arguments[0].equals("//"))){
                    name = arguments[0];
                    taille = Integer.parseInt(arguments[1]);
                    masse = Integer.parseInt(arguments[2]);
                    pX = Integer.parseInt(arguments[3]);
                    pY = Integer.parseInt(arguments[4]);
                    vitesseX = Integer.parseInt(arguments[5]);
                    vitesseY = Integer.parseInt(arguments[6]);
                    isFixed = Boolean.parseBoolean(arguments[7]);

                }else{
                    creer = false;
                }


            if(creer){
                listeA.add(p.factory(name, taille, masse, pX, pY, vitesseX, vitesseY, isFixed));
            }
        }
        return  listeA;

    }


    /**sauvegarde de la listes des astres dans un fichier
     * @param save le fichier dans lequel on va sauvegarder
     * */
    public void saveListeAstre(File save) throws IOException {
        save.toPath();
        save.createNewFile();
        String commentaire = "// nom taille masse positionX positionY vitesseX vitesseY estFixe \n";
        Files.write(save.toPath(), commentaire.getBytes(), StandardOpenOption.APPEND);
        for(Astre a : listeAstre){
            String arg = a.getArgString();
            Files.write(save.toPath(), arg.getBytes(), StandardOpenOption.APPEND);


        }
    }

    /** pour avoir les autres astres de a d'une listeA
     * @param a l'astre qu'on ne veut pas
     * @param listeA la liste de tout les astres
     * @return la liste des autres astres
     * */

    public static ArrayList<Astre> getOther(Astre a, ArrayList<Astre> listeA){
        ArrayList<Astre> r = new ArrayList<>(listeA);
        r.remove(a);
        return r;
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
