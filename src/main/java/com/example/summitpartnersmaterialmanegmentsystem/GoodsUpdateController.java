package com.example.summitpartnersmaterialmanegmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class GoodsUpdateController implements Initializable {


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

    public static int id = 0;
    public static String itemCode = "";
    public static String description = "";
    public static String unitOfMeasure = "";
    public static int quantity = 0;
    public static float unitPrice = 0;
    public static String supplier = "";
    public static int supplierInvoiceNo = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfItemCode.setText(itemCode);
        tfDescription.setText(description);
        tfUnitOfMeasure.setText(unitOfMeasure);
        tfQuantity.setText("" + quantity);
        tfUnitPrice.setText("" + unitPrice);
        tfSupplier.setText(supplier);
        tfSupplierInvoiceNo.setText("" + supplierInvoiceNo);
    }


    @FXML
    public void handleSave(ActionEvent event) throws IOException {

        if(isInputValid()) {
                float totalValue = Integer.parseInt(tfQuantity.getText()) * Float.parseFloat(tfUnitPrice.getText());
                String query = "UPDATE itemsTable SET " +
                        "itemCode='" + tfItemCode.getText() + "', description='"+ tfDescription.getText() +
                        "', unitOfMeasure='" + tfUnitOfMeasure.getText() + "', quantity=" + tfQuantity.getText() +
                        ", unitPrice=" + tfUnitPrice.getText() + ", totalValue=" + totalValue +
                        ", supplier='"+ tfSupplier.getText() + "', supplierInvoiceNo=" + tfSupplierInvoiceNo.getText()
                        + " WHERE id="+ id + "" ;

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
