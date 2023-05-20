package com.example.summitpartnersmaterialmanegmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserUpdateController implements Initializable {

    @FXML
    private ComboBox<String> cbDepartment;

    @FXML
    private ComboBox<String> cbGender;

    @FXML
    private ComboBox<String> cbJobTitle;

    @FXML
    private ComboBox<String> cbPosition;

    @FXML
    private ComboBox<String> cbPrivilege;

    @FXML
    private TextField tfEmp_id;

    @FXML
    private TextField tfFullName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfUsername;

    public static int id;
    public static String emp_id;
    public static String fullName;
    public static String password;
    public static String userName;
    public static String gender;
    public static String department;
    public static String privilege;

    private Stage stage;
    private Scene scene;
    private Parent root;
    HomeController homeController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfEmp_id.setText(emp_id);
        tfUsername.setText(userName);
        tfPassword.setText(password);
        tfFullName.setText(fullName);
        cbDepartment.getItems().clear();
        cbDepartment.getItems().addAll("IT", "Law","Finance","Construction","Human Resource");
        cbDepartment.getSelectionModel().select(department);

        cbGender.getItems().clear();
        cbGender.getItems().addAll("Male", "Female");
        cbGender.getSelectionModel().select(gender);

        cbPrivilege.getItems().clear();
        cbPrivilege.getItems().addAll("Admin", "User","Department Head","Store Keeper","Manager");
        cbPrivilege.getSelectionModel().select(privilege);
    }

    @FXML
    public void handleSave(ActionEvent event) throws IOException {
        homeController = new HomeController();
        String query = "UPDATE usersTable SET emp_id='" + tfEmp_id.getText() + "', username='" + tfUsername.getText() +
                "', password='" + tfPassword.getText() + "', fullName='" + tfFullName.getText() + "' WHERE id=" + id + "";
        if(isInputValid()) {
                homeController.executeQuery(query);
                tfEmp_id.setText("");
                tfUsername.setText("");
                tfFullName.setText("");
                tfPassword.setText("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            root = loader.load();

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setMaximized(true);
            stage.setResizable(true);
            stage.show();
        }

    }

    private boolean isInputValid() throws IOException {
        String errorMessage = "";

        if (tfEmp_id.getText() == null || tfEmp_id.getText().length() == 0) {
            errorMessage += "Please Fill The Employee ID Field!\n";
        }
        else if (tfUsername.getText() == null || tfUsername.getText().length() == 0) {
            errorMessage += "Please Fill The Username Field!\n";
        }
        else if (tfFullName.getText() == null || tfFullName.getText().length() == 0) {
            errorMessage += "Please Fill The FullName Field!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please Insert Valid Input");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        root = loader.load();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(true);
        stage.setResizable(true);
        stage.show();
    }
}
