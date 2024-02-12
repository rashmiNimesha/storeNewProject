package com.example.storenewproject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuController {
    @FXML
    Button cateButton;
    @FXML
    Button proManageBtn;
    @FXML
    Button stockButton;
    @FXML
    AnchorPane OuterPane;
    @FXML
    Button homeClose;

    public void categoryBtn() throws IOException {
        Stage categoryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("category.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load());
        categoryStage.setTitle("Category");
        categoryStage.setScene(scene2);
        categoryStage.show();

        Stage secondView = (Stage) cateButton.getScene().getWindow();
        secondView.close();
    }

    public void ProductButton() throws IOException {
        Stage productStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ProductManage.class.getResource("productManagement.fxml"));
        Scene scene_ = new Scene(fxmlLoader.load());
        productStage.setTitle("Product");
        productStage.setScene(scene_);
        productStage.show();

        Stage proView = (Stage) proManageBtn.getScene().getWindow();
        proView.close();

    }
    public void stockBtn() throws IOException {
        Stage stockStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StockController.class.getResource("stock.fxml"));
        Scene scene3 = new Scene(fxmlLoader.load());
        stockStage.setTitle("Stock");
        stockStage.setScene(scene3);
        stockStage.show();

        Stage stockS = (Stage) stockButton.getScene().getWindow();
        stockS.close();

    }
    Stage stage;
    @FXML
    public void logOut(){
        stage =(Stage) OuterPane.getScene().getWindow();
        stage.close();
    }

    public void homeBtn() throws IOException {
        Stage homeStock = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("home.fxml"));
        Scene scene4 = new Scene(fxmlLoader.load());
        homeStock.setScene(scene4);
        homeStock.setTitle("Home");
        homeStock.show();

        Stage home_ = (Stage) homeClose.getScene().getWindow();
        home_.close();

    }
}
