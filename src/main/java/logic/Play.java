package logic;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;

public class Play {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Simulation s = new Simulation();

        CreateurPlanete p = new CreateurPlanete();
        Planete p1 = p.factory("blue",25,25, 0, 0, 50, 20, false);
        Planete p2 = p.factory("green", 50, 50, 10, 25, 0,0,true);
        Planete p3 = p.factory("red", 30, 30, 60, 70, 0, 0, false);

        ArrayList<Astre> listeA = new ArrayList<>();
        listeA.add(p1);
        listeA.add(p2);
        listeA.add(p3);

        s.setListeAstre(listeA);

        Simulation s2 = new Simulation(new File("saves/save.simu"));
        s2.saveListeAstre(new File("saves/save2.simusimu"));
        System.out.println(s2);
        System.out.println(s);

        System.out.println("------- Testes positions -------");
        Astre planete = new Planete("planete", 50, 30, 200, 200, 0 , 0 , true);
        Astre lune = new Planete("lune", 20, 10, 300, 350, 20, 20, false);
        ArrayList<Astre> listeAstresTeste = new ArrayList<>();
        listeAstresTeste.add(planete);
        listeAstresTeste.add(lune);
        lune.setVistesse(listeAstresTeste);
        lune.setPositions();
        planete.setVistesse(listeAstresTeste);
        planete.setPositions();

    }
}
