import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import model.Astre;
import model.CreateurPlanete;
import model.Simulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import view.EspaceView;
import view.PlaneteApp;
import view.SimulationView;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemTest {
    Simulation simu;
    EspaceView espaceView;
    ArrayList<Astre> listeAsimu;
    ObservableList<Astre> listeAEspace;
    HashMap<Astre, Circle> listeAetC;
    HashMap<Circle, Astre> listeCetA;

    @BeforeEach
    public void setUp(){
        CreateurPlanete factoryP = new CreateurPlanete();
        ArrayList<Astre> listeSimu = new ArrayList<>();
        Astre p1 = factoryP.factory("p1", 100, 100, 100, 100, 100, 100, false);
        Astre p2 = factoryP.factory("p2", 100, 100, 100, 100, 100, 100, false);
        Astre p3 = factoryP.factory("p3", 100, 100, 100, 100, 100, 100, false);
        Astre p4 = factoryP.factory("p4", 100, 100, 100, 100, 100, 100, false);
        Astre p5 = factoryP.factory("p5", 100, 100, 100, 100, 100, 100, false);
        listeSimu.add(p1);
        listeSimu.add(p2);
        listeSimu.add(p3);
        listeSimu.add(p4);
        listeSimu.add(p5);
        simu = new Simulation();
        simu.setListeAstre(listeSimu);
        espaceView = new EspaceView(simu);

        listeAsimu = simu.getListeAstre();
        listeAEspace = espaceView.getListeA();
        listeAetC = espaceView.getListeAetC();
        listeCetA = espaceView.getListeCetA();
    }


    //@Disabled
    @Test
    void testListAstreSameEspaceSimu(){


        assertEquals(listeAsimu.size(), listeAEspace.size());
        for(int i = 0; i<listeAsimu.size(); i++){
            assertEquals(listeAsimu.get(i), listeAEspace.get(i));
        }
    }

    //@Disabled
    @Test
    void testSuppression(){
        Astre supprime = listeAsimu.get(0);
        listeAEspace.remove(supprime);

        assertFalse(listeAsimu.contains(supprime));
        assertFalse(listeAEspace.contains(supprime));
        assertFalse(listeAetC.containsKey(supprime));
        assertFalse(listeCetA.containsValue(supprime));
    }

    @Test
    void testAjout(){
        CreateurPlanete factoryP = new CreateurPlanete();
        Astre ajoute = factoryP.factory("ajoute", 100, 100, 100, 100, 100, 100, false);
        listeAEspace.add(ajoute);

        assertTrue(listeAsimu.contains(ajoute));
        assertTrue(listeAEspace.contains(ajoute));
        assertTrue(listeAetC.containsKey(ajoute));
        assertTrue(listeCetA.containsValue(ajoute));
    }

}
