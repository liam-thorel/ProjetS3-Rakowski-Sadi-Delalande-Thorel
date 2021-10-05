package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Play {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Simulation s = new Simulation();

        Planete p1 = new Planete("blue",25,25);
        Planete p2 = new Planete("green",50,50);
        Planete p3 = new Planete("red", 30,30);
        ArrayList<Astre> listeA = new ArrayList<>();
        listeA.add(p1);
        listeA.add(p2);
        listeA.add(p3);

        s.setListeAstre(listeA);
        s.saveListeAstre(new FileOutputStream("save.txt"));

        Simulation s2 = new Simulation(new FileInputStream("save.txt"));
        System.out.println(s2);
        System.out.println(s);

    }
}
