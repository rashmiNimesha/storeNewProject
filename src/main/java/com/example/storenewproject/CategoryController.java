package com.example.storenewproject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
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
import org.bson.conversions.Bson;

import java.io.IOException;

import static com.mongodb.client.model.Filters.eq;


public class CategoryController {

    @FXML
    Button BackMenu;
    @FXML
    AnchorPane catePane;
    @FXML
    TextField addCategory;
    @FXML
    TextField referenceNo;
    @FXML
    TextField description;
    @FXML
    TableView<CategoryTable> tableView;
    @FXML
    TableColumn<CategoryTable, String> refNumber;
    @FXML
    TableColumn<CategoryTable, String> cateName;
    @FXML
    TableColumn<CategoryTable, String> desc;
    @FXML
    TextField editCategory;

    @FXML
    public void initialize(){
        viewTable();
    }


    public void MenuBack() throws IOException {
        Stage menuStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        menuStage.setTitle("Menu");
        menuStage.setScene(menuScene);
        menuStage.show();

        Stage backMenu = (Stage) BackMenu.getScene().getWindow();
        backMenu.close();
    }
    Stage stage;
    @FXML
    public void logout(){
        stage =(Stage) catePane.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void insertCategory(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Category");
        Document category = new Document();
        category.put("Category Name", addCategory.getText() );
        category.put("Reference Number", referenceNo.getText());
        category.put("Description", description.getText());
        collection.insertOne(category);
        addCategory.setText("");
        referenceNo.setText("");
        description.setText("");
        viewTable();
    }

    @FXML
    public void DeleteCategoryTable(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Category");
        Document category = new Document();
        category.put("Category Name", addCategory.getText() );
        category.put("Reference Number", referenceNo.getText());
        collection.deleteOne(category);
        addCategory.setText("");
        referenceNo.setText("");
        viewTable();
    }

    @FXML
    public void EditCategoryTable(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Category");
        Bson filtering = eq ("Category Name", addCategory.getText());
        Bson updateName = Updates.set("Category Name", editCategory.getText());
        collection.findOneAndUpdate(filtering, updateName);
        addCategory.setText("");
        editCategory.setText("");
        viewTable();

    }


    @FXML
    public void viewTable(){
        DBConnection connection = DBConnection.getConnection();
        MongoCollection<Document> collection = connection.getCollectionByName("Category");

        ObservableList<CategoryTable> list = FXCollections.observableArrayList();
        FindIterable <Document> findIterable = collection.find();
        for (Document doc: findIterable){
            String cateName = doc.getString("Category Name");
            String refNumber = doc.getString("Reference Number");
            String desc = doc.getString("Description");

            CategoryTable categoryObject = new CategoryTable(cateName, refNumber, desc);
            list.add(categoryObject);
        }
        refNumber.setCellValueFactory(new PropertyValueFactory<>("referenceNo"));
        cateName.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableView.setItems(list);
    }
}
