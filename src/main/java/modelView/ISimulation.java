package modelView;

import model.Astre;
import model.CreateurAstre;
import model.CreateurPlanete;
import model.Simulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ISimulation {

    /** construire une liste d'astre a partir d'un fichier
     * @param save fichier a partir duquel on va construire
     * @return la simulation créée
     * */
    public static Simulation setAPartirDunFichier(File save) throws IOException, ClassNotFoundException {
        ArrayList<Astre> listeA = new ArrayList<>();
        CreateurAstre p = new CreateurPlanete();
        for (String ligne : Files.readAllLines(save.toPath())){
            String name = "";
            float taille =0;
            float masse = 0;
            int pX = 0;
            int pY = 0;
            double vitesseX = 0;
            double vitesseY = 0;
            boolean isFixed = false;

            boolean creer = true;
            String [] arguments = ligne.split(" ");

            if (!(arguments[0].equals("//"))){
                name = arguments[0];
                taille = Float.parseFloat(arguments[1]);
                masse = Float.parseFloat(arguments[2]);
                pX = Integer.parseInt(arguments[3]);
                pY = Integer.parseInt(arguments[4]);
                vitesseX = Double.parseDouble(arguments[5]);
                vitesseY = Double.parseDouble(arguments[6]);
                isFixed = Boolean.parseBoolean(arguments[7]);

            }else{
                creer = false;
            }


            if(creer){
                listeA.add(p.factory(name, taille, masse, pX, pY, vitesseX, vitesseY, isFixed));
            }
        }
        return  new Simulation(save,listeA);

    }


    /**sauvegarde de la listes des astres dans un fichier
     * @param save le fichier dans lequel on va sauvegarder
     * */
    public static void saveListeAstre(File save, ArrayList<Astre> listeAstre) throws IOException {
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
}
