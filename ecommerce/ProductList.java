package com.example.ecommerce;

/*import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import javax.xml.namespace.QName;

public class ProductList {
    public static TableView<Product> productTable;
    public Pane getAllProducts(){



        ObservableList<Product> productList = Product.getAllProducts();

       // productTable = new TableView<>();

        return createTableFromList(productList);
    }
    public Pane createTableFromList(ObservableList<Product> productList){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));//property value factory is actual
        // attributes of name or id variable attach attribute in product table
        TableColumn name = new TableColumn("Product");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

       /* ObservableList<Product> data = FXCollections.observableArrayList();
        data.addAll(new Product(123,"Laptop","11234"),
                new Product(1245,"Keyboard","234") ,
                new Product(1145,"Mouse","124"),
                new Product(124,"PenDrive","600"),
                new Product(120,"Mobile","16000"),
                new Product(113,"Tablet","20034") ,
                new Product(110,"Digital Watch","1284"),
                new Product(105,"Engineering Books","600"),
                new Product(1200,"medicine","1600"));*/


       /* productTable = new TableView<>();
        productTable.setItems(productList);
        productTable.getColumns().addAll(id,name,price);
       // productTable.getColumns().addAll(name);
       // productTable.getColumns().addAll(price);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);
        return tablePane;
    }
    public Pane productsInCart(ObservableList<Product>productList){
        return createTableFromList(productList);
    }

public static Product getSelectedProduct(){// for fetching only selected item means getting selected item
        return productTable.getSelectionModel().getSelectedItem();
    }
}*/
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductList {

    public static TableView<Product> productTable;

    public static void removerow() {
        Product pro=productTable.getSelectionModel().getSelectedItem();
        productTable.getItems().remove(pro);
    }


    public Pane getAllProducts(){
        TableColumn id = new TableColumn("Item");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Mobile");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        ObservableList<Product> productsList = Product.getAllProducts();

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(id, name, price, quantity);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
    public Pane getorderedlist(Customer loggedInCustomer){
        TableColumn id = new TableColumn("Item");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Laptop");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        String query = "select * from yourorders where cid="+ loggedInCustomer.getId();
        ObservableList<Product> productsList = Product.getorders(query);
        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(id, name, price);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
    public Pane getAllSearchedProducts(String searchName){
        TableColumn id = new TableColumn("Item");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Mobile");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn quantity = new TableColumn("Quantity");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // select * from products where name like 'a%';
        String query = "select * from products where name like '" + searchName + "%'";
        ObservableList<Product> productsList = Product.getProducts(query);

        productTable = new TableView<>();
        productTable.setItems(productsList);
        productTable.getColumns().addAll(id, name, price, quantity);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);

        return tablePane;
    }
    public Product getSelectedProduct() {
        // getting selected item
        return productTable.getSelectionModel().getSelectedItem();
    }
}
