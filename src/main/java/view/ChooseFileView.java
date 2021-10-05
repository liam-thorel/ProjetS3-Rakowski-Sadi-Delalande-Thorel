package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.Simulation;
import java.io.FileInputStream;
import java.io.IOException;

public class ChooseFileView extends Stage {


    private TextArea fileName;
    private Button chargerFile;
    private Label systemText;
    private PlaneteApp app;
    private HBox field;
    private GridPane root;

    public ChooseFileView(PlaneteApp app) {
        System.out.println("je sui sdans choose file");
        this.app = app;
        //field = new HBox();
        root = new GridPane();
        fileName = new TextArea();
        fileName.setPrefSize(200,50);

        chargerFile = new Button();
        chargerFile.setText("charger simulation");
        chargerFile.setAlignment(Pos.CENTER);
        chargerFile.setOnAction(chargerFileEvent);

        systemText = new Label();
        root.setAlignment(Pos.CENTER);
        root.add(fileName, 0,0);
        root.add(chargerFile, 0, 1);
        root.add(systemText,0,2);

        Scene scene = new Scene(root);
        app.getStage().setScene(scene);





    }

    private EventHandler<ActionEvent> chargerFileEvent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            String name = fileName.getText();
            Simulation s = null;
            try {
                s = new Simulation(new FileInputStream(name));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            app.initSimulation(s);
        }
    };


}
