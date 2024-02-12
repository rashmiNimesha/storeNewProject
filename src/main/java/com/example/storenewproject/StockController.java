package com.example.storenewproject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;

public class StockController {

    @FXML
    Button stockBackMenu;
    @FXML
    AnchorPane ScenePane;
    @FXML
    TextField stockName;
    @FXML
    TextField uniPrice;
    @FXML
    TextField quantity;
    @FXML
    TextField supplier;
    @FXML
    TableView<StockTable> stockView;
    @FXML
    TableColumn<StockTable, String> nameClmn;
    @FXML
    TableColumn <StockTable, String> uniPriceClmn;
    @FXML
    TableColumn <StockTable, String> quantityClmn;
    @FXML
    TableColumn <StockTable, String> suppClmn;



    public void stockButton() throws IOException {
        Stage stockStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Scene stockScene = new Scene(fxmlLoader.load());
        stockStage.setScene(stockScene);
        stockStage.show();

        Stage backStock = (Stage) stockBackMenu.getScene().getWindow();
        backStock.close();
    }

    Stage stage;
    public void logout(){
        stage = (Stage) ScenePane.getScene().getWindow();
        stage.close();

    }

    public void addStock(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Stock");
        Document stock = new Document();
        stock.put("Name", stockName.getText());
        stock.put("Unit Price",uniPrice.getText());
        stock.put("Quantity", quantity.getText());
        stock.put("Supplier", supplier.getText());
        collection.insertOne(stock);
        stockName.setText("");
        uniPrice.setText("");
        quantity.setText("");
        supplier.setText("");

        stockViewTable();
    }

    public void removeStock(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Stock");
        Document stock = new Document();
        stock.put("Name", stockName.getText());
        collection.deleteOne(stock);
        stockName.setText("");

        stockViewTable();

    }
    public void initialize(){
        stockViewTable();
    }

    public void stockViewTable(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Stock");

        ObservableList<StockTable> stockList = FXCollections.observableArrayList();
        FindIterable<Document> findIterable = collection.find();

        for (Document doc: findIterable){
            String stockName = doc.getString("Name");
            String unitPrice = doc.getString("Unit Price");
            String quantity = doc.getString("Quantity");
            String supplier = doc.getString("Supplier");

            StockTable stockObject = new StockTable(stockName,unitPrice,quantity,supplier);
            stockList.add(stockObject);
        }

        nameClmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        uniPriceClmn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityClmn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        suppClmn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        stockView.setItems(stockList);

    }
}
