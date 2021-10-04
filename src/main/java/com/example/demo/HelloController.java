package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onChargerSimulation() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onNouvelleSimulation() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}