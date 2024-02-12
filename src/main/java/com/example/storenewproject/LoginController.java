package com.example.storenewproject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;


public class LoginController {
    @FXML
    TextField myUsername;
    @FXML
    TextField myPassword;
    public AnchorPane context;
    @FXML
    Button loginViewBtn;



    @FXML
    public void LoginButton() throws IOException {
        DBConnection connection = DBConnection.getConnection();
        MongoCollection collection = connection.getCollectionByName("User");
        String inputUsername = myUsername.getText();
        String inputPassword = myPassword.getText();
        String realUsername = null;
        String realPassword = null;
        Document searchUser = new Document();
        searchUser.put("Username", inputUsername);
        searchUser.put("Password", inputPassword);

        FindIterable<Document> findIterable = collection.find(searchUser);
        for (Document doc :findIterable){
            realUsername = doc.getString("Username");
            realPassword = doc.getString("Password");
        }

        if (inputUsername.equals(realUsername) && inputPassword.equals(realPassword)){
            Stage homeStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            homeStage.setTitle("Menu");
            homeStage.setScene(scene);
            homeStage.show();
            Stage firstView = (Stage) loginViewBtn.getScene().getWindow();
            firstView.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Password & username");
            alert.setHeaderText("Invalid username or password ! ");
            alert.show();
        }
    }
}