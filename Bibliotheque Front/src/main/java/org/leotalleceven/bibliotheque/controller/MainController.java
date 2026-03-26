package org.leotalleceven.bibliotheque.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.leotalleceven.bibliotheque.Launcher;

import java.io.IOException;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onClientButtonClick() throws IOException {
        welcomeText.setText("You're a Client");

        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("clientConnexion.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) welcomeText.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void onBiblioButtonClick() throws IOException {
        welcomeText.setText("You're a bookshelf");

        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("biblioConnexion.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) welcomeText.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
