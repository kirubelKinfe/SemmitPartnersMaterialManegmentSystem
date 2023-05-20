package com.example.summitpartnersmaterialmanegmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserRegistrationController implements Initializable {

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

    private Stage stage;
    private Scene scene;
    private Parent root;
    HomeController homeController;
    String department ="";
    String gender = "";
    String privilege = "";


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbDepartment.getItems().clear();
        cbDepartment.getItems().addAll("IT", "Law","Finance","Construction","Human Resource");
        cbDepartment.getSelectionModel().select("IT");

        cbGender.getItems().clear();
        cbGender.getItems().addAll("Male", "Female");
        cbGender.getSelectionModel().select("Male");

        cbPrivilege.getItems().clear();
        cbPrivilege.getItems().addAll("Admin", "User","Department Head","Store Keeper","Manager");
        cbPrivilege.getSelectionModel().select("User");
    }

    @FXML
    public void handleDepartments() {

        department = cbDepartment.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void handleGender() {
        gender = cbGender.getSelectionModel().getSelectedItem();
    }
    @FXML
    public void handlePrivilege() {
        privilege = cbPrivilege.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void handleSave(ActionEvent event) throws IOException {
        homeController = new HomeController();
        String query = "INSERT INTO usersTable (emp_id, username, password, fullName,gender,department,privilege) " +
                "VALUES('"  + tfEmp_id.getText() + "','" + tfUsername.getText() + "','" + tfPassword.getText() +
                "','" + tfFullName.getText() + "','" + gender + "','" + department + "','" + privilege + "')";
        if(isInputValid()) {
                homeController.executeQuery(query);
                tfEmp_id.setText("");
                tfUsername.setText("");
                tfFullName.setText("");
                tfPassword.setText("");
                cbDepartment.getSelectionModel().select("IT");
                cbGender.getSelectionModel().select("Male");
                cbPrivilege.getSelectionModel().select("User");
        }
    }

    @FXML
    public void handleSaveAndClose(ActionEvent event) throws IOException {
        homeController = new HomeController();
        String query = "INSERT INTO usersTable (emp_id, username, password, fullName,gender,department,privilege) " +
                "VALUES('"  + tfEmp_id.getText() + "','" + tfUsername.getText() + "','" + tfPassword.getText() +
                "','" + tfFullName.getText() + "','" + gender + "','" + department + "','" + privilege + "')";
        if(isInputValid()) {
            homeController.executeQuery(query);
            tfEmp_id.setText("");
            tfUsername.setText("");
            tfFullName.setText("");
            tfPassword.setText("");
            cbDepartment.getSelectionModel().select("IT");
            cbGender.getSelectionModel().select("Male");
            cbPrivilege.getSelectionModel().select("User");

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

        Connection conn = homeController.getConnection();
        String query = "SELECT * FROM `usersTable`";
        ResultSet rs;
        Statement st;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                if(Objects.equals(tfEmp_id.getText().toLowerCase(), rs.getString("emp_id").toLowerCase())){
                    errorMessage += "User already exists!\n";

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
    public void handleClose(ActionEvent event) throws IOException {
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
