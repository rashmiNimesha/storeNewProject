package com.example.storenewproject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;

public class ProductManage {
    @FXML
    Button backProMenu;
    @FXML
    AnchorPane proScene;
    @FXML
    TextField ProName;
    @FXML
    TextField RefNumber;
    @FXML
    TextField editProName;
    @FXML
    ChoiceBox proChoiceBox;
    @FXML
    TableView<ProductTable> proView;
    @FXML
    TableColumn<ProductTable, String> ProRefNoClmn;
    @FXML
    TableColumn<ProductTable, String> ProNameclmn;
    @FXML
    TableColumn<ProductTable, String> proCateClmn;

@FXML
    public void backProductBtn() throws IOException {
        Stage productStage = new Stage();
        FXMLLoader fxmlLoader1 = new FXMLLoader(LoginController.class.getResource("menu.fxml"));
        Scene scene3 = new Scene(fxmlLoader1.load());
        productStage.setTitle("Menu");
        productStage.setScene(scene3);
        productStage.show();

        Stage proView = (Stage) backProMenu.getScene().getWindow();
        proView.close();
    }

    Stage stage;

    public void logout(){
        stage = (Stage)proScene.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addProduct(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection <Document> collection = connection.getCollectionByName("Product Management");
        Document product = new Document();
        product.put("Product Name", ProName.getText() );
        product.put("Reference Number", RefNumber.getText());
        product.put("Category", proChoiceBox.getValue() );
        collection.insertOne(product);
        ProName.setText("");
        RefNumber.setText("");

        proViewTable();


    }

    @FXML
    public void editProduct(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Product Management");
        Bson filter = eq("Product Name", ProName.getText() );
        Bson editPro = Updates.set("Product Name", editProName.getText());
        collection.findOneAndUpdate(filter, editPro);
        ProName.setText("");
        editProName.setText("");

        proViewTable();

    }

    @FXML
    public void deleteProduct(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Product Management");
        Document proDocument = new Document();
        proDocument.put("Product Name", ProName.getText());
        proDocument.put("Reference Number", RefNumber.getText());
        collection.deleteOne(proDocument);
        ProName.setText("");
        RefNumber.setText("");

        proViewTable();

    }

    public void choiseBox(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Category");
        FindIterable<Document> findIterable = collection.find();
        for(Document doc : findIterable){
            String category = (String) doc.getString("Category Name");
            proChoiceBox.getItems().add(category);

        }
    }

    public void initialize(){
        choiseBox();
        proViewTable();

    }

    public void proViewTable(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Product Management");

        ObservableList<ProductTable> list1 = FXCollections.observableArrayList();
        FindIterable<Document> findIterable = collection.find();

        for(Document doc: findIterable){
            String proName = doc.getString("Product Name");
            String proNo = doc.getString("Reference Number");
            String cate = doc.getString("Category");

            ProductTable products = new ProductTable(proName,proNo,cate) ;
            list1.add(products);
        }
        ProNameclmn.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        ProRefNoClmn.setCellValueFactory(new PropertyValueFactory<>("referenceNumber"));
        proCateClmn.setCellValueFactory(new PropertyValueFactory<>("category"));
        proView.setItems(list1);


    }
}
