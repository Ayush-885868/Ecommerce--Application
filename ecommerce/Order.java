package com.example.ecommerce;

/*import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


public class Order {
    TableView<Product> orderTable;
    public  boolean placeOrder(Customer customer, Product product) {
        try {
            String placeOrder="INSERT INTO orders(customer_id,product_id, status) VALUES(" +customer.getId()+","+ product.getId() +  ", 'Ordered')";
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(placeOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /*--public static boolean removeorder(Customer customer, Product product) {
        try {
            String MyOrder="Delete from yourorders where pid=" + product.getId() + " and cid=" +customer.getId();
            System.out.println(product.getId()+" "+ customer.getId());
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(MyOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/
   /* --public  boolean placeOrder(Customer customer, Product product) {
        try {
            String placeOrder = "INSERT INTO orders(customer_id, products_id, status) VALUES(" + customer.getId() + "," + product.getId() + ", 'Ordered')";
            DatabaseConnection dbConn = new DatabaseConnection();
           // System.out.println( product.getId() + " "+product.getName()+" "+product.getPrice());
            return dbConn.insertUpdate(placeOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/
   /* public  int placeOrderMultipleProducts(ObservableList<Product> productObservableList, Customer customer){
        int count =0;
        for(Product product:productObservableList){
            if(placeOrder(customer, product))
                count++;
        }
        return count;
    }
    public Pane getOrder(){
        ObservableList<Product> productList = Product.getAllProducts();
        return createTableFromList(productList);
    }

    public Pane createTableFromList(ObservableList<Product> orderList){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Product");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderTable = new TableView<>();
        orderTable.setItems(orderList);
        orderTable.getColumns().addAll(id,name,price);
        Pane tablePane = new Pane();
        tablePane.getChildren().add(orderTable);
        return tablePane;
    }
    public Pane getOrders(Customer customer){
        String order = "SELECT orders.oid,products.name, products.price FROM orders INNER JOINS orders INNER JOIN products ON orders.product_id = products.pid WHERE customer_id = 1 ";
        ObservableList< Product> orderList = Product.getProducts(order);
        return createTableFromList(orderList);
    }


}*/
public class Order {
    public static boolean myorder(Customer customer, Product product) {
        try {
            String MyOrder="INSERT INTO yourorders(cid,pid, name, price) VALUES(" +customer.getId()+","+ product.getId() + ", '" + product.getName() + "'," + product.getPrice() +")";
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(MyOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean removeorder(Customer customer, Product product) {
        try {
            String MyOrder="Delete from yourorders where pid=" + product.getId() + " and cid=" +customer.getId();
            System.out.println(product.getId()+" "+ customer.getId());
            DatabaseConnection dbConn = new DatabaseConnection();
            return dbConn.insertUpdate(MyOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean placeOrder(Customer customer, Product product) {
        try {
            String placeOrder = "INSERT INTO orders(customer_id, products_id, status) VALUES(" + customer.getId() + "," + product.getId() + ", 'Ordered')";
            DatabaseConnection dbConn = new DatabaseConnection();
            System.out.println( product.getId() + " "+product.getName()+" "+product.getPrice());
            return dbConn.insertUpdate(placeOrder);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
