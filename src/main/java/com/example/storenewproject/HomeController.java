package com.example.storenewproject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    Button menuBack;

@FXML
    public void setMenuBack() throws IOException {
        Stage menuStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        menuStage.setTitle("Menu");
        menuStage.setScene(menuScene);
        menuStage.show();

        Stage backMenu = (Stage) menuBack.getScene().getWindow();
        backMenu.close();

    }


    private class IOException extends Exception {
    }
}
