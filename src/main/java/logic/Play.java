package logic;

import java.io.*;
import java.util.ArrayList;

public class Play {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Simulation s = new Simulation();

        /*Planete p1 = new Planete("blue",25,25, 10, 50);
        Planete p2 = new Planete("green", 50, 50, 25, 12);
        Planete p3 = new Planete("red", 30, 30, 0, 0);*/

        Planete p1 = new Planete("blue",25,25, 0, 0);
        Planete p2 = new Planete("green", 50, 50, 10, 25);
        Planete p3 = new Planete("red", 30, 30, 60, 70);

        ArrayList<Astre> listeA = new ArrayList<>();
        listeA.add(p1);
        listeA.add(p2);
        listeA.add(p3);

        s.setListeAstre(listeA);
        //s.saveListeAstre(new FileOutputStream(new File("save.txt")));

        Simulation s2 = new Simulation(new File("save.txt"));
        System.out.println(s2);
        System.out.println(s);
    }
}
