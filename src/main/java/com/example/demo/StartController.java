package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logic.Simulation;

import java.io.FileInputStream;
import java.io.IOException;

public class StartController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label systemText;

    @FXML
    protected void onChargerSimulation() throws IOException, ClassNotFoundException {
        welcomeText.setText("Chargement du fichier...");
        Simulation s = new Simulation(new FileInputStream("save.txt"));
        systemText.setText(s.toString());
    }
    @FXML
    protected void onNouvelleSimulation() {
        welcomeText.setText("Generation de la nouvelle simulation...");
        Simulation s = new Simulation();
        systemText.setText(s.toString());

    }
}