package com.example.ecommerce;

/*import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
//import java.security.NoSuchAlgorithmException;

public class Ecommerce extends Application {
    //Login login = new Login();
    private final int width = 500, height =480, headerLine = 50;
    ProductList productlist = new ProductList();
    Pane bodyPane ;
    GridPane footerBar;
    Order order = new Order();
    ObservableList<Product> cartItemList = FXCollections.observableArrayList();
    Button signInButton = new Button("Sign in");
    Button placeOrderButton = new Button("Place Order");
    Label welcomeLabel = new Label("Welcome Customer");
    Customer loggedInCustomer = null;
    private void addItemsToCart(Product product){
        if(cartItemList.contains(product))
            return;
        cartItemList.add(product);
        System.out.println("Products in Cart"+ cartItemList.stream().count());
    }

    private GridPane headerBar(){
        //GridPane header = new GridPane();
        TextField searchBar = new TextField();
        Button searchButton = new Button("search");
        Button cartButton = new Button("Cart");
        Button ordersButton = new Button("orders");
        ordersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();//remove all the children
                bodyPane.getChildren().add(order.getOrders());
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();//remove all the children
                bodyPane.getChildren().add(productlist.getAllProducts());
            }
        });
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productlist.productsInCart(cartItemList));
            }
        });
        GridPane header = new GridPane();
        header.setHgap(10);
        header.add(searchBar,0,0);
        header.add(searchButton,1,0);
        header.add(signInButton,2,0);
        header.add(welcomeLabel,3,0);
        header.add(cartButton,4,0);
        header.add(ordersButton,5,0);
         return header;

    }

    private GridPane loginPage() {
        Label userLabel = new Label("User Name");
        Label passLabel = new Label("password");
        TextField userName = new TextField();
        userName.setText("shukla123@gmail.com");
        userName.setPromptText("Enter your name");//setprompttext for shown whatever we have typed here
        PasswordField password = new PasswordField();
        password.setText("abc");
        password.setPromptText("Enter password");
        Button loginButton = new Button("Login");
        Label messageLabel = new Label("Login-Message");// message level to display that it is successful or not
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = password.getText();
                loggedInCustomer = login.customerlogin(user, pass);// if there is customer then logged in.
                if (loggedInCustomer != null){
                    messageLabel.setText("Login successful");
                    welcomeLabel.setText("Welcome" + loggedInCustomer.getName());
                }
                else{
                    messageLabel.setText("Login failed");
                }

                }
        });
        GridPane loginPane = new GridPane();
        loginPane.setTranslateY(50);
        loginPane.setTranslateX(50);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.add(userLabel, 0, 0);
        loginPane.add(userName, 1, 0);
        loginPane.add(passLabel, 0, 1);
        loginPane.add(password, 1, 1);
        loginPane.add(loginButton, 0, 2);
        loginPane.add(messageLabel, 1, 2);
        return loginPane;

    }
    private void showDialogue(String message){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Order Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    private  GridPane footerBar(){
        Button buyNowButton = new Button("Buy Now");
        Button addToCartButton = new Button("Add to cart");

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                boolean orderStatus = false;
                if (product != null && loggedInCustomer != null) {
                    orderStatus = order.placeOrder(loggedInCustomer,product);
                }
                if(orderStatus == true){
                    showDialogue("order successful");

                }
                else{
                    //


                }
            }
        });
        addToCartButton.setOnAction(new EventHandler<ActionEvent>(){
           // private ProductList productList;

            @Override
            public void handle(ActionEvent actionEvent){
               Product product = productlist.getSelectedProduct();
               addItemsToCart(product);
            }
        });
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int orderCount = 0;
                if (!cartItemList.isEmpty()  && loggedInCustomer != null) {
                    orderCount = order.placeOrderMultipleProducts(cartItemList,loggedInCustomer);
                }
                if(orderCount >0){
                    showDialogue("Order for " + orderCount +"placed Successfully");

                }
                else{
                    //


                }
            }
        });
        GridPane footer = new GridPane();
        footer.setHgap(10);
        footer.setTranslateY(headerLine+height);
        footer.add(buyNowButton,0,0);
        footer.add(addToCartButton,1,0);
        footer.add(placeOrderButton,2,0);
        return footer;
    }

    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width, height + 2 * headerLine);
        bodyPane = new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);
        bodyPane.getChildren().add(loginPage());
        footerBar = footerBar();
        root.getChildren().addAll(headerBar()
               // ,loginPage()
               // ,productlist.getAllProducts()
                 ,bodyPane
                ,footerBar);

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}*/



        //package com.example.ecommerce;

        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.Pane;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.security.NoSuchAlgorithmException;

public class Ecommerce extends Application {
    ProductList productList = new ProductList();
    Pane root;
    Pane bodyPane;
    Pane allOrders;
    Button back=new Button("Back");
    Button signInButton = new Button("Sign In");
    Button signOutButton = new Button("Sign Out");
    Button buyNowButton = new Button("Buy Now");
    Button removedButton = new Button("Remove");
    Button closeButton = new Button("Exit");
    Button ordersButton = new Button("Your Orders");
    Label welcomeLabel = new Label("");
    Customer loggedInCustomer = null;
    private final int width = 500;
    private final int height = 500;
    private final int headerLine = 50;
    private GridPane headerBar(Button button) {
        GridPane header = new GridPane();
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search an item");
        Button searchButton = new Button("Search");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // all products to show for now later will change
                bodyPane.getChildren().clear();
                // only searched products to show
                bodyPane.getChildren().add(productList.getAllSearchedProducts(searchBar.getText()));
                root.getChildren().clear();
                if (loggedInCustomer == null)
                    root.getChildren().addAll(headerBar(signInButton), bodyPane, footerBar());
                else root.getChildren().addAll(headerBar(signOutButton), bodyPane, footerBar());
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { // sign in button will disaapper
                bodyPane.getChildren().clear();
                bodyPane.getChildren().addAll(loginPage());
                root.getChildren().clear();
                root.getChildren().addAll(headerBar(closeButton), bodyPane);
            }
        });

        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) { // sign out button will disappear
                loggedInCustomer = null;
                welcomeLabel.setText("");
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                root.getChildren().clear();
                root.getChildren().addAll(headerBar(closeButton), bodyPane);
            }
        });

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleCloseButtonAction();
            }
        });
        header.setHgap(10);
        header.add(searchBar, 0, 0);
        header.add(searchButton, 1, 0);
        header.add(button, 2, 0);
        header.add(welcomeLabel, 3, 0);

//        header.add(signInButton, 2, 0);
//        header.add(signOutButton, 3, 0);
//        header.add(welcomeLabel, 0, 1);

        return header;
    }
    private GridPane signupPage() {
        Label userLabel = new Label("Name : ");
        Label mobileLabel = new Label("Mobile : ");
        Label emailLabel = new Label("E-mail : ");
        Label passLabel = new Label("Password : ");
        Label addressLabel = new Label("Address : ");

        TextField userName = new TextField();
        TextField mobileNumber = new TextField();
        TextField emailName = new TextField();
        PasswordField userPassword = new PasswordField();
        TextField userAddress = new TextField();

        userName.setPromptText("Enter Your User Name");
        mobileNumber.setPromptText("Enter Mobile Number");
        emailName.setPromptText("Enter Your E-mail here");
        userPassword.setPromptText("Enter Your Password");
        userAddress.setPromptText("Fill Your Address");

        Button signupButton = new Button("Sign Up");
        Label messageLabel = new Label("");

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userName.getText();
                String usermobile = mobileNumber.getText();
                String useremail = emailName.getText();
                String userpass = userPassword.getText();
                String useraddress = userAddress.getText();
                try {
                    loggedInCustomer = login.customerLogin(useremail, userpass);
                    if (loggedInCustomer == null) {
                        // add headerBar, show prod list, footer
                        // update new user in database
                        signup.customerSignup(username, usermobile, useremail, userpass, useraddress);
                        loggedInCustomer = login.customerLogin(useremail, userpass);
                        messageLabel.setText("Sign Up Successful!!!");
                        welcomeLabel.setText("Welcome " + username);
                        bodyPane.getChildren().clear();
                        bodyPane.getChildren().add(productList.getAllProducts());
                        root.getChildren().clear();
                        root.getChildren().addAll(headerBar(signOutButton), welcomeLabel, bodyPane, footerBar());
                    }
                    else {

                        showDialogue("You are already registered, please Sign In!!!!");
                        loginPage();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        GridPane singupPane = new GridPane();
        singupPane.setTranslateX(100);
        singupPane.setTranslateY(100);
        singupPane.setVgap(10);
        singupPane.setHgap(10);
        singupPane.setScaleY(1.5);
        singupPane.setScaleX(1.5);

        singupPane.add(userLabel, 0, 0);
        singupPane.add(userName, 1, 0);
        singupPane.add(mobileLabel, 0, 1);
        singupPane.add(mobileNumber, 1, 1);
        singupPane.add(emailLabel, 0, 2);
        singupPane.add(emailName, 1, 2);
        singupPane.add(passLabel, 0, 3);
        singupPane.add(userPassword, 1, 3);
        singupPane.add(addressLabel, 0, 4);
        singupPane.add(userAddress, 1, 4);

        singupPane.add(signupButton, 0, 5);
        singupPane.add(messageLabel, 1, 2);

        return singupPane;
    }

    private GridPane loginPage() {
        Label userLabel = new Label("E-mail : ");
        Label passLabel = new Label("Password : ");
        TextField userName = new TextField();
        userName.setPromptText("Enter Your E-mail");
        PasswordField userPassword = new PasswordField();
        userPassword.setPromptText("Enter Your Password");
        Button loginButton = new Button("Login");
        Label messageLabel = new Label("");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = userPassword.getText();
                try {
                    loggedInCustomer = login.customerLogin(user, pass);
                    if (loggedInCustomer != null) {
                        messageLabel.setText("Login Successful!!");
                        welcomeLabel.setText("Welcome " + loggedInCustomer.getName());
                        bodyPane.getChildren().clear();
                        bodyPane.getChildren().addAll(productList.getAllProducts());
                        root.getChildren().clear();
                        root.getChildren().addAll(headerBar(signOutButton), bodyPane, footerBar());
                    }
                    else {
                        messageLabel.setText("Login Failed!!");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        GridPane loginPane = new GridPane();
        loginPane.setTranslateX(100);
        loginPane.setTranslateY(100);
        loginPane.setVgap(10);
        loginPane.setHgap(10);
        loginPane.setScaleY(1.5);
        loginPane.setScaleX(1.5);
        loginPane.add(userLabel, 0, 0);
        loginPane.add(userName, 1, 0);
        loginPane.add(passLabel, 0, 1);
        loginPane.add(userPassword, 1, 1);
        loginPane.add(loginButton, 0, 2);
        loginPane.add(messageLabel, 1, 2);

        return loginPane;
    }

    public void showDialogue(String message) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Order Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
    boolean includeremove=false;
    private GridPane footerBar() {

        GridPane footer = new GridPane();
        footer.setTranslateY(headerLine + height);
        footer.add(buyNowButton, 0, 0);
        footer.add(ordersButton, 2, 0);
        System.out.println(includeremove);
        if(includeremove){
            footer.add(removedButton,0,0);
            footer.setHgap(2);
            footer.add(back,3,0);
            buyNowButton.setVisible(false);
            includeremove=false;
        }

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                boolean orderStatus = false;
                try {
                    if (product != null && loggedInCustomer != null) {
                        orderStatus = Order.placeOrder(loggedInCustomer, product);
                        Order.myorder(loggedInCustomer, product);
                    }
                    if (orderStatus) {

                        showDialogue("Order Successful!!!!");

                    }
                    else {

                        showDialogue("Order can't be placed, please Sign In!!!!");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                buyNowButton.setVisible(true);
                bodyPane.getChildren().clear();
                bodyPane.getChildren().addAll(productList.getAllProducts());
                root.getChildren().clear();
                root.getChildren().addAll(headerBar(signOutButton), bodyPane, footerBar());
            }
        });
        ordersButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                includeremove=true;
                productList.getorderedlist(loggedInCustomer);
                bodyPane.getChildren().clear();
                bodyPane.getChildren().addAll( productList.getorderedlist(loggedInCustomer));
                root.getChildren().clear();

                root.getChildren().addAll(headerBar(signOutButton), bodyPane, footerBar());

            }
        });
        removedButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Product product = productList.getSelectedProduct();
                    boolean orderStatus = false;
                    orderStatus = Order.removeorder(loggedInCustomer, product);
                    ProductList.removerow();
                    if (orderStatus) {
                        showDialogue("Order Deleted!!!!");
                    }
                    else {
                        showDialogue("Order can't be deleted");
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        return footer;
    }
    private Pane createContent() {
        root = new Pane();
        root.setPrefSize(width,height + 2 * headerLine);

        bodyPane = new Pane();
        bodyPane.setPrefSize(width, height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateX(10);
        bodyPane.getChildren().add(signupPage());
        root.getChildren().addAll(headerBar(signInButton), bodyPane);

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Faizan's Ecommerce");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public static void main(String[] args) {
        launch();
    }
}





