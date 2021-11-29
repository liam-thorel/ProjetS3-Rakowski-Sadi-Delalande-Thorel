package modelView;

import javafx.beans.value.ChangeListener;
import model.Astre;
import model.Planete;

import java.util.ArrayList;

public class IPlanete extends IAstre {


    public static void addVitesse(ArrayList<Astre> listeA, Astre current){
        IAstre.addVitesse(listeA, current);
    }

    public static void setPositions(Astre current){
        IAstre.setPositions(current);
        System.out.println("je passe par planete");
    }

    public static void addListenerAll(Astre a, ChangeListener<Number> changeListener){
        IAstre.addListenerAll(a, changeListener);
    }

}
