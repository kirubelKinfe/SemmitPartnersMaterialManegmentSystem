package com.example.summitpartnersmaterialmanegmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

public class GoodsReceivingController {


    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfItemCode;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField tfSupplier;

    @FXML
    private TextField tfSupplierInvoiceNo;

    @FXML
    private TextField tfUnitOfMeasure;

    @FXML
    private TextField tfUnitPrice;
    private Stage stage;
    private Scene scene;
    private Parent root;
    HomeController homeController = new HomeController();

    @FXML
    public void handleSave(ActionEvent event) throws IOException {

        if(isInputValid()) {
                float totalValue = Integer.parseInt(tfQuantity.getText()) * Float.parseFloat(tfUnitPrice.getText());
                String query = "INSERT INTO itemsTable (itemCode, description,unitOfMeasure, quantity,unitPrice,totalValue,supplier,supplierInvoiceNo) " +
                        "VALUES('"  + tfItemCode.getText() + "','" + tfDescription.getText() + "','" + tfUnitOfMeasure.getText() + "'," +
                        tfQuantity.getText() + "," + tfUnitPrice.getText() + "," + totalValue + ",'" + tfSupplier.getText() + "'," + tfSupplierInvoiceNo.getText() + ")";
                homeController.executeQuery(query);
                tfItemCode.setText("");
                tfDescription.setText("");
                tfQuantity.setText("");
                tfUnitPrice.setText("");
                tfSupplier.setText("");
                tfSupplierInvoiceNo.setText("");
                tfUnitOfMeasure.setText("");
        }
    }

    @FXML
    public void handleSaveAndClose(ActionEvent event) throws IOException {

        if(isInputValid()) {
            float totalValue = Integer.parseInt(tfQuantity.getText()) * Float.parseFloat(tfUnitPrice.getText());
            String query = "INSERT INTO itemsTable (itemCode, description,unitOfMeasure, quantity,unitPrice,totalValue,supplier,supplierInvoiceNo) " +
                    "VALUES('"  + tfItemCode.getText() + "','" + tfDescription.getText() + "','" + tfUnitOfMeasure.getText() + "'," +
                    tfQuantity.getText() + "," + tfUnitPrice.getText() + "," + totalValue + ",'" + tfSupplier.getText() + "'," + tfSupplierInvoiceNo.getText() + ")";
            homeController.executeQuery(query);
            tfItemCode.setText("");
            tfDescription.setText("");
            tfQuantity.setText("");
            tfUnitPrice.setText("");
            tfSupplier.setText("");
            tfSupplierInvoiceNo.setText("");
            tfUnitOfMeasure.setText("");
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

        if (tfDescription.getText() == null || tfDescription.getText().length() == 0) {
            errorMessage += "Please Fill The Description Field!\n";
        }
        else if (tfQuantity.getText() == null || tfQuantity.getText().length() == 0) {
            errorMessage += "Please Fill The Quantity Field!\n";
        }

        Connection conn = homeController.getConnection();
        String query = "SELECT * FROM `itemsTable`";
        ResultSet rs;
        Statement st;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                if(Objects.equals(tfDescription.getText().toLowerCase(), rs.getString("description").toLowerCase())){
                    errorMessage += "Item already exists!\n";

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
