package org.leotalleceven.bibliotheque.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.leotalleceven.bibliotheque.Launcher;

import java.io.IOException;

public class BiblioCreateClientController {
    public Button btn_Back;

    public void onClientButtonBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("index.fxml"));
        Parent root = loader.load(); // Charger AVANT de récupérer la racine

        // Récupérer la fenêtre actuelle via un élément de l'UI
        Stage stage = (Stage) btn_Back.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
