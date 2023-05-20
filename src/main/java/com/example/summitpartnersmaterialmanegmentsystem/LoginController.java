package com.example.summitpartnersmaterialmanegmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class LoginController {
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;

    private Stage stage;
    private Scene scene;
    private Parent root;

    HomeController homeController;

    @FXML
    private void handleLogin(ActionEvent event) {
        homeController = new HomeController();
        Connection conn = homeController.getConnection();
        String query = "SELECT * FROM `usersTable`";
        ResultSet rs;
        Statement st;
        String message = "";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                if(Objects.equals(tfUsername.getText().toLowerCase(), rs.getString("username").toLowerCase())) {
                    if(Objects.equals(tfPassword.getText().toLowerCase(), rs.getString("password").toLowerCase())){
                        HomeController.loggedInUser = rs.getString("username").toLowerCase();
                        HomeController.LoggedIn = rs.getString("privilege");

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                        root = loader.load();

                        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.setMaximized(true);
                        stage.setResizable(true);
                        stage.show();
                        message = "success";
                        break;
                    }
                }
            }
            String errorMessage = "Invalid User or Password";
            if (message.length() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(errorMessage);
                alert.setHeaderText(errorMessage);
                alert.setContentText("Please try to login with a valid account.");
                alert.showAndWait();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}