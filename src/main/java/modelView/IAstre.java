package modelView;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import model.Astre;
import model.Simulation;
import model.Vecteur;

import java.util.ArrayList;

public abstract class IAstre {



    /** calcule la somme des forces que recoit this des autres
     * @param liste les astres qui ont une influence sur this
     * @return le vesteur de l'acceleration de this par rapport aux autres planetes
     * */
    public static Vecteur calculerSommeForces(ArrayList<Astre> liste, Astre current){
        Vecteur vSommeForces = new Vecteur(0,0);
        System.out.println("je calcule");
        for(Astre a : liste){
            double distanceX = (a.getPositionX() - current.getPositionX()) * Simulation.scaleDistance;
            double distanceY = (a.getPositionY() - current.getPositionY()) * Simulation.scaleDistance;
            double distance = Math.sqrt(Math.pow(Math.abs(distanceX), 2) + (Math.pow(Math.abs(distanceY), 2) + 1.f));

            vSommeForces.incrementXBy(Simulation.g * (distanceX * (a.getMasse() * Simulation.scaleMasse)/ Math.pow(distance, 3)/ Simulation.simuRate));
            vSommeForces.incrementYBy(Simulation.g * (distanceY * (a.getMasse() * Simulation.scaleMasse) / Math.pow(distance,3)/ Simulation.simuRate));
        }
        //System.out.println("vSommeForces = " + vSommeForces);
        return vSommeForces;
    }


    /** ajoute la vitesse a this et multiplie par le pas de temps
     * @param listeA la liste des astres qui influent this
     * */

    public static void addVitesse(ArrayList<Astre> listeA, Astre current){
        Vecteur vAcc;
        if(current.isFixed()){
            vAcc = new Vecteur(0, 0);
        }else {

            vAcc = calculerSommeForces(listeA,current);
        }
        //System.out.println("je met a jour la vitesse");
        current.setVitesseX(current.getVitesseX() + (vAcc.getX() * Simulation.scaleTemps) / current.getMasse() *0.0005);
        current.setVitesseY(current.getVitesseY() +(vAcc.getY() * Simulation.scaleTemps) / current.getMasse() *0.0005);

    }

    /**position * le pas de temps*
     **/

    public static void setPositions(Astre current){
        //System.out.println("je met a jour la position de " + current.getNom());
        //System.out.println("vitesseX = " + current.getVitesseX() + "    vitesseY = " + current.getVitesseY());
        current.setPositionX(current.getPositionX() + (current.getVitesseX() *0.0005));
        current.setPositionY(current.getPositionY() + (current.getVitesseY() *0.0005));
        /*double x = current.getPositionX() + (current.getVitesseX() *0.0005);
        double y = current.getPositionY() + (current.getVitesseY() *0.0005);
        System.out.println("positionX " +x );
        System.out.println("positionY " + y);*/
    }


    public static void addListenerAll(Astre a, ChangeListener<Number> changeListener){
        a.positionXProperty().addListener(changeListener);
        a.positionYProperty().addListener(changeListener);
        a.vitesseXProperty().addListener(changeListener);
        a.vitesseYProperty().addListener(changeListener);
    }

}
