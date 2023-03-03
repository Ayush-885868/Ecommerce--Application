package com.example.ecommerce;/*ecommerce is the name of base project directory*/
/*import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
  import java.sql.SQLException;
//Class.DatabaseConnection("com.mysql.jdbc.Driver");


public class DatabaseConnection {
    String dbURL = "jdbc:mysql://localhost:3306/ecomm"; /* last name is the name of database*/
  //*  String userName = "root";
  //*  String password = "A8957693018a";
   //* private Statement getStatement()  {
        /* for creating connection with the database*/
     //*   try{
            /*Driver Manager class this class helps to create a connection */
         //*   Connection conn = DriverManager.getConnection(dbURL,userName,password);// driverManager is
            // a class it manages connection returns the object of connection class by using that class
            // we can create statement and execute our query
          //*  return conn.createStatement();/*from connection we are creating statements that statement
           //*  is used to select query as well as to run query*/

       //* }catch(Exception e){
          //*  e.printStackTrace();/**/
       //* }
       //* return null;
   //* }
   //* public ResultSet getQueryTable (String query)/*executing statement using query*/{
   //* Statement statement = getStatement();
       //* try {
          //*  return statement.executeQuery(query);//executing query and giving the result
       //* } catch (SQLException e) {
           // throw new RuntimeException(e);
           //* e.printStackTrace();
        //*}
        //*return null;

    //*}
   //* public boolean insertUpdate(String query)/*executing statement using query*/{
      //*  Statement statement = getStatement();
       //* try {
           //*  statement.executeUpdate (query);//executing query and giving the result
           //* return true;
       /* } catch (SQLException e) {
            // throw new RuntimeException(e);
            e.printStackTrace();
        }
        //boolean b = false;
        return false;

    }*/


       /*-- public static void main(String[] args) {
        String query = "select * from products";
        DatabaseConnection dbConn = new DatabaseConnection();
        ResultSet rs = dbConn.getQueryTable(query);//getQueryTable returning datatable or dataset to
        // rs(result set)
        if(rs != null){
            System.out.println("Connected to database");
        }

    }--*/
//*}

import javafx.util.StringConverter;

import java.sql.*;

public class DatabaseConnection {

    String dbURL = "jdbc:mysql://localhost:3306/ecomm";
    String userName = "root";
    String password = "A8957693018a";

    private Statement getStatement(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, userName, password);
            return conn.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getQueryTable(String query)
    {
        Statement statement = getStatement();
        try{
            return statement.executeQuery(query);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertUpdate(String query){
        Statement statement = getStatement();
        try {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static void main(String[]args){
        String query = "Select * From Products";
        DatabaseConnection dbConn = new DatabaseConnection();
        ResultSet rs = dbConn.getQueryTable(query);
        if(rs != null)
        {
            System.out.println("Connected to Database");
        }
    }
}