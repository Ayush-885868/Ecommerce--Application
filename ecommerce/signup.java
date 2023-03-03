package com.example.ecommerce;

import java.security.NoSuchAlgorithmException;

import static com.example.ecommerce.login.getEncryptedPassword;

public class signup {
    String name;
    String pass;
    String email;
    String mobile;
    String address;

    public static void customerSignup(String userName, String userMobile, String userEmail, String userPass, String userAddress) throws NoSuchAlgorithmException {
        String encryptedPassword = getEncryptedPassword(userPass);
        String query = "insert into customers(name, email, mobile, password, address)values('"+userName+"','"+userEmail+"','"+userMobile+"','"+encryptedPassword+"', '" + userAddress+"')";
        DatabaseConnection dbConn = new DatabaseConnection();
        dbConn.insertUpdate(query);
    }
}

