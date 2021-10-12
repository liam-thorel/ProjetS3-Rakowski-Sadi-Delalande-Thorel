package logic;

import java.io.*;
import java.util.ArrayList;

public class Play {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Simulation s = new Simulation();

        CreateurPlanete p = new CreateurPlanete();
        Planete p1 = p.factory("blue",25,25, 0, 0);
        Planete p2 = p.factory("green", 50, 50, 10, 25);
        Planete p3 = p.factory("red", 30, 30, 60, 70);

        ArrayList<Astre> listeA = new ArrayList<>();
        listeA.add(p1);
        listeA.add(p2);
        listeA.add(p3);

        s.setListeAstre(listeA);

        Simulation s2 = new Simulation(new File("save.txt"));
        s2.saveListeAstre(new File("save2.txt"));
        System.out.println(s2);
        System.out.println(s);
    }
}
