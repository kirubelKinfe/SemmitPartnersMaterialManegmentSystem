package com.example.summitpartnersmaterialmanegmentsystem;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane approvedView;

    @FXML
    private AnchorPane manageItemsView;

    @FXML
    private AnchorPane home;

    @FXML
    private AnchorPane storeView;

    @FXML
    private Button issuedItems;

    @FXML
    private Button viewUsers;

    @FXML
    private AnchorPane issuedView;

    @FXML
    private AnchorPane usersView;

    @FXML
    private Button logout;

    @FXML
    private Button pendingRequests;

    @FXML
    private Button requestItem;

    @FXML
    private AnchorPane requestedView;

    @FXML
    private AnchorPane confirmedView;

    @FXML
    private Button viewStore;

    @FXML
    private AnchorPane requestView;


    @FXML
    private TextField searchField;

    @FXML
    private TableView<Items> viewTable;

    @FXML
    private TableColumn<Items, Integer> colId;

    @FXML
    private TableColumn<Items, String> colSupplier;

    @FXML
    private TableColumn<Items, Integer> colSupplierInvoiceNo;

    @FXML
    private TableColumn<Items, Float> colTotalValue;

    @FXML
    private TableColumn<Items, String> colUnitOfMeasure;

    @FXML
    private TableColumn<Items, Float> colUnitPrice;


    @FXML
    private TableColumn<Items, Integer> colQuantity;

    @FXML
    private TableColumn<Items, String> colItemCode;

    @FXML
    private TableColumn<Items, String> colDescription;



    @FXML
    private ComboBox<String> selectItem;

    @FXML
    private TextField tfCategory;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfQuantity;

    @FXML
    private TextField quantity;

    @FXML
    private TableView<RequestItems> requestTable;


    @FXML
    private TableColumn<RequestItems, String> colItem;

    @FXML
    private TableColumn<RequestItems, Integer> colItemId;

    @FXML
    private TableColumn<RequestItems, Integer> colItemQuantity;

    @FXML
    private TableColumn<RequestItems, String> colRequestDate;

    @FXML
    private VBox requestList;

    @FXML
    private HBox requestListItem;

    @FXML
    private TableColumn<RequestedItems, String> colRequestDepartment;

    @FXML
    private TableColumn<RequestedItems, String> colRequestFirstName;

    @FXML
    private TableColumn<RequestedItems, Integer> colRequestID;

    @FXML
    private TableColumn<RequestedItems, String> colRequestRequestDate;

    @FXML
    private TableColumn<RequestedItems, String> colRequestRequested;

    @FXML
    private TableColumn<RequestedItems, String> colRequestStatus;

    @FXML
    private TableColumn<RequestedItems, String> colConfirmedDepartment;

    @FXML
    private TableColumn<RequestedItems, String> colConfirmedFirstName;

    @FXML
    private TableColumn<RequestedItems, Integer> colConfirmedID;

    @FXML
    private TableColumn<RequestedItems, String> colConfirmedRequestDate;

    @FXML
    private TableColumn<RequestedItems, String> colConfirmedRequested;

    @FXML
    private TableColumn<RequestedItems, String> colConfirmedStatus;

    @FXML
    private TableColumn<RequestedItems, String> colApprovedDepartment;

    @FXML
    private TableColumn<RequestedItems, String> colApprovedFirstName;

    @FXML
    private TableColumn<RequestedItems, Integer> colApprovedID;

    @FXML
    private TableColumn<RequestedItems, String> colApprovedRequestDate;

    @FXML
    private TableColumn<RequestedItems, String> colApprovedRequested;

    @FXML
    private TableColumn<RequestedItems, String> colApprovedStatus;

    @FXML
    private TableColumn<RequestedItems, String> colIssuedDepartment;

    @FXML
    private TableColumn<RequestedItems, String> colIssuedFirstName;

    @FXML
    private TableColumn<RequestedItems, Integer> colIssuedID;

    @FXML
    private TableColumn<RequestedItems, String> colIssuedRequestDate;

    @FXML
    private TableColumn<RequestedItems, String> colIssuedRequested;

    @FXML
    private TableColumn<RequestedItems, String> colIssuedStatus;

    @FXML
    private TableView<RequestedItems> requestListTable;

    @FXML
    private TableView<RequestedItems> approvedTable;

    @FXML
    private TableView<RequestedItems> confirmedTable;

    @FXML
    private TableView<RequestedItems> issuedTable;




    @FXML
    private TableColumn<Users, String> colDepartment;

    @FXML
    private TableColumn<Users, String> colEmp_id;

    @FXML
    private TableColumn<Users, String> colFullName;

    @FXML
    private TableColumn<Users, String> colGender;

    @FXML
    private TableColumn<Users, String> colJobTitle;

    @FXML
    private TableColumn<Users, String> colPosition;


    @FXML
    private TableColumn<Users, String> colPrivilege;

    @FXML
    private TableColumn<Users, Integer> colSerNo;

    @FXML
    private TableColumn<Users, String> colUsername;

    @FXML
    private TableView<Users> usersTable;
    @FXML
    private GridPane storemanage;


    public static String LoggedIn = "";
    public static String loggedInUser = "";
    private String item = "";
    private Stage stage;
    private Scene scene;
    private Parent root;

    private int id = 0;
    ObservableList<Items> dataList;

    @FXML
    private void handleNewUser(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserRegistration.fxml"));
        root = loader.load();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("User Registration");
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }
    @FXML
    private void handleDeleteUser() {
        String query = "DELETE FROM usersTable WHERE id = " + id + "";
        int selectedIndex = usersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete a User?");
            alert.setHeaderText("Delete a User?");
            alert.setContentText("Are you sure you want to delete this user?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {

            } else {
                executeQuery(query);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select a user in the table.");
            alert.showAndWait();
        }
        showUsers();
    }
    @FXML
    private void handleNewItem(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GoodsReceiving.fxml"));
        root = loader.load();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Good Receiving");
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.setResizable(true);
        stage.show();
    }
    @FXML
    public void handleUserMouseAction(MouseEvent mouseEvent) {
        Users user = usersTable.getSelectionModel().getSelectedItem();
        if(user != null) {
            id =  user.getId();
        }
    }
    public ObservableList<Users> getUsersList() {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM usersTable";
        ResultSet rs;
        Statement st;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Users users;
            while (rs.next()) {
                users = new Users(rs.getInt("id"),rs.getString("username"),
                        rs.getString("password"),rs.getString("fullName"),
                        rs.getString("emp_id"), rs.getString("gender"),
                        rs.getString("jobTitle"), rs.getString("position"),
                        rs.getString("department"), rs.getString("privilege"));
                usersList.add(users);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usersList;
    }
    public void showUsers() {
        ObservableList<Users> list = getUsersList();

        colSerNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmp_id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colPrivilege.setCellValueFactory(new PropertyValueFactory<>("privilege"));

        usersTable.setItems(list);
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUsers();
        showItems();
        showRequestItems();
        showRequestedItems();
        showConfirmedItems();
        showApprovedItems();
        showIssuedItems();

        viewTable.setRowFactory(tv -> {
            TableRow<Items> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())) {
                    Items rowData = row.getItem();
                    GoodsUpdateController.id = rowData.getId();
                    GoodsUpdateController.itemCode = rowData.getItemCode();
                    GoodsUpdateController.description = rowData.getDescription();
                    GoodsUpdateController.unitOfMeasure = rowData.getUnitOfMeasure();
                    GoodsUpdateController.quantity = rowData.getQuantity();
                    GoodsUpdateController.unitPrice = rowData.getUnitPrice();
                    GoodsUpdateController.supplier = rowData.getSupplier();
                    GoodsUpdateController.supplierInvoiceNo = rowData.getSupplierInvoiceNo();

                    System.out.println("Double click on: " + rowData.getDescription());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GoodsUpdate.fxml"));
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Goods Update");
                    stage.setMaximized(false);
                    stage.centerOnScreen();
                    stage.setResizable(true);
                    stage.show();
                }
            });
            return row;
        });

        usersTable.setRowFactory(tv -> {
            TableRow<Users> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())) {
                    Users rowData = row.getItem();
                    UserUpdateController.id = rowData.getId();
                    UserUpdateController.emp_id = rowData.getEmp_id();
                    UserUpdateController.userName = rowData.getUsername();
                    UserUpdateController.password = rowData.getPassword();
                    UserUpdateController.fullName = rowData.getFullName();
                    UserUpdateController.gender = rowData.getGender();
                    UserUpdateController.department = rowData.getDepartment();
                    UserUpdateController.privilege = rowData.getPrivilege();

                    System.out.println("Double click on: " + rowData.getUsername());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserUpdate.fxml"));
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("User Update");
                    stage.setMaximized(false);
                    stage.centerOnScreen();
                    stage.setResizable(true);
                    stage.show();
                }
            });
            return row;
        });


        viewStore.getStyleClass().add("bg");
        viewUsers.setVisible(false);
        if(Objects.equals(LoggedIn, "user")){
            pendingRequests.setVisible(false);
            issuedItems.setVisible(false);

        } else if(Objects.equals(LoggedIn, "storeKeeper")){
            storemanage.setVisible(true);
        } else if(Objects.equals(LoggedIn, "depHead")){
            issuedItems.setVisible(false);
            requestedView.setVisible(false);
            confirmedView.setVisible(false);
        } else if(Objects.equals(LoggedIn, "Manager")){
            issuedItems.setVisible(false);
            requestedView.setVisible(false);
            confirmedView.setVisible(false);
        }
        else if(Objects.equals(LoggedIn, "admin")){
            requestItem.setVisible(false);
            pendingRequests.setVisible(false);
            storeView.setVisible(false);
            usersView.setVisible(true);
            viewUsers.setVisible(true);
            viewUsers.getStyleClass().add("bg");
            viewStore.getStyleClass().remove("bg");
        }


    }
    @FXML
    public void handleMouseAction(MouseEvent mouseEvent) {
        Items item = viewTable.getSelectionModel().getSelectedItem();
        if(item != null) {
            id =  item.getId();
        }

    }

    public ObservableList<Items> getItemsList() {
        ObservableList<Items> itemsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM itemsTable";
        ResultSet rs;
        Statement st;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Items items;
            while (rs.next()) {
                items = new Items(rs.getInt("id"),rs.getString("itemCode"),rs.getString("description"),rs.getString("unitOfMeasure"),rs.getInt("quantity"),rs.getFloat("unitPrice"),rs.getFloat("totalValue"),rs.getString("supplier"),rs.getInt("supplierInvoiceNo"));
                itemsList.add(items);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return itemsList;
    }
    public void showItems() {
        ObservableList<Items> list = getItemsList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitOfMeasure.setCellValueFactory(new PropertyValueFactory<>("unitOfMeasure"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalValue.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colSupplierInvoiceNo.setCellValueFactory(new PropertyValueFactory<>("supplierInvoiceNo"));
        viewTable.setItems(list);

        selectItem.getItems().clear();
        Connection conn = getConnection();
        String query = "SELECT * FROM `itemsTable`";
        ResultSet rs;
        Statement st;
        String message = "";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                selectItem.getItems().addAll(rs.getString("itemCode"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/summitmarketdb","root","");
            return conn;
        }catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void search(KeyEvent event) {
        dataList = getItemsList();
        viewTable.setItems(dataList);

        FilteredList<Items> filteredData = new FilteredList<>(getItemsList(), p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                if (searchField.getText() == null || searchField.getText().isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = searchField.getText().toLowerCase();

                if ((Integer.toString(item.getId())).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                if (item.getItemCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                if (item.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            SortedList<Items> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(viewTable.comparatorProperty());
            viewTable.setItems(sortedData);
        });
    }


    @FXML
    private void handleEditItems() throws IOException {
        String query = "UPDATE itemsTable SET product_name = '" + tfName.getText() + "', product_description = '" + tfDescription.getText() + "', product_quantity = " + tfQuantity.getText() + ", product_category= '" + tfCategory.getText() +  "' WHERE id = " + tfID.getText() + "";
        int selectedIndex = viewTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            if(isUpdateValid()) {
                executeQuery(query);
                tfID.setText("");
                tfName.setText("");
                tfDescription.setText("");
                tfQuantity.setText("");
                tfCategory.setText("");
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select a item in the table.");
            alert.showAndWait();
        }
        showItems();
    }

    @FXML
    private void handleDeleteItems() {
        String query = "DELETE FROM itemsTable WHERE id = " + id + "";
        int selectedIndex = viewTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete An Item?");
            alert.setHeaderText("Delete An Item?");
            alert.setContentText("Are you sure you want to delete this item?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {

            } else {
                executeQuery(query);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select a item in the table.");
            alert.showAndWait();
        }
        showItems();
    }


    private boolean isUpdateValid() throws IOException {
        String errorMessage = "";

        if (tfName.getText() == null || tfName.getText().length() == 0) {
            errorMessage += "Please Fill The Name Field!\n";
        }
        else if (tfDescription.getText() == null || tfDescription.getText().length() == 0) {
            errorMessage += "Please Fill The Description Field!\n";
        }
        else if (tfQuantity.getText() == null || tfQuantity.getText().length() == 0) {
            errorMessage += "Please Fill The Quantity Field!\n";
        }
        else if (tfCategory.getText() == null || tfCategory.getText().length() == 0) {
            errorMessage += "Please Fill The Category Field!\n";
        }
        for (int i = 0; i < tfQuantity.getLength(); i++) {
            if (tfQuantity.getText().charAt(i) < 48 || tfQuantity.getText().charAt(i) > 57) {
                errorMessage += "Quantity should be a number!\n";
            }
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
    public void handleRequestMouseAction(MouseEvent mouseEvent) {
        RequestItems requestItems = requestTable.getSelectionModel().getSelectedItem();
        id = requestItems.getId();
    }
    @FXML
    public void handleRequestedMouseAction(MouseEvent mouseEvent) {
        RequestedItems requestedItems = requestListTable.getSelectionModel().getSelectedItem();
        id = requestedItems.getId();
    }
    @FXML
    public void handleConfirmedMouseAction(MouseEvent mouseEvent) {
        RequestedItems confirmedItems = confirmedTable.getSelectionModel().getSelectedItem();
        id = confirmedItems.getId();
    }
    @FXML
    public void handleApprovedMouseAction(MouseEvent mouseEvent) {
        RequestedItems approvedItems = approvedTable.getSelectionModel().getSelectedItem();
        id = approvedItems.getId();
    }
    @FXML
    public void handleIssuedMouseAction(MouseEvent mouseEvent) {
        RequestedItems issuedItems = issuedTable.getSelectionModel().getSelectedItem();
        id = issuedItems.getId();
    }



    @FXML
    private void handleAdd(ActionEvent event) {
        int itemId = 0;
        int itemQuantity = 0;
        item = selectItem.getSelectionModel().getSelectedItem();
        if(item != null) {
            Connection conn = getConnection();
            String query = "SELECT * FROM itemsTable";
            ResultSet rs;
            Statement st;

            try {
                st = conn.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) {
                    if(Objects.equals(item, rs.getString("product_name"))) {
                        itemQuantity = rs.getInt("product_quantity");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (Objects.equals(quantity.getText(), "") || quantity.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Quantity Not Entered");
                alert.setHeaderText("Quantity Not Entered");
                alert.setContentText("Please enter a quantity.");
                alert.showAndWait();
            } else if(itemQuantity < Integer.parseInt(quantity.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Requested amount Not Available");
                alert.setHeaderText("Requested amount Not Available");
                alert.setContentText("Please request for available quantity.");
                alert.showAndWait();
            } else {
                String request_query = "INSERT INTO requestItemsTable (item, quantity,user) VALUES('" + item + "'," + quantity.getText() + ",'" + loggedInUser + "')";
                executeQuery(request_query);
                quantity.setText("");
                showRequestItems();
            }


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item in the selection.");
            alert.showAndWait();
        }

    }
    @FXML
    private void handleRequest(ActionEvent event) {
        Date date = new Date();
        int now = date.getDate();
        long time = date.getTime();
        System.out.println(now);
        System.out.println(time);
        String requested = "";
        String username = "";
        String fullName = "";
        String department = "";
        String privilege = "";
        String sta = "";
        Connection conn1 = getConnection();
        String query1 = "SELECT * FROM requestItemsTable WHERE user='"+loggedInUser +"' and status='Not  Requested'";
        String query2 = "UPDATE requestItemsTable SET status ='Requested', request_date='" + now +"', request_time='" + time + "' WHERE user='"+loggedInUser +"' and status='Not  Requested'";

        ResultSet rs1;
        Statement st1;
        Statement st2;
        try {
            st1 = conn1.createStatement();
            rs1 = st1.executeQuery(query1);
            while (rs1.next()) {
                requested += rs1.getString("item") + " " + rs1.getString("quantity") + "\n";
            }
            rs1.close();
            st1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Connection conn2 = getConnection();
        String query3 = "SELECT * FROM usersTable WHERE username='"+loggedInUser +"'";

        ResultSet rs2;
        Statement st3;
        Statement st4;

        try {
            st3 = conn2.createStatement();
            rs2 = st3.executeQuery(query3);
            while (rs2.next()) {
                username = rs2.getString("username");
                fullName = rs2.getString("fullname");
                department = rs2.getString("department");
                privilege = rs2.getString("privilege");
            }
            if(Objects.equals(department, "MM") && Objects.equals(privilege, "depHead")){
                sta = "Not Issued";
            } else if(Objects.equals(department, "MM") || Objects.equals(privilege, "depHead")){
                sta = "Not Approved";
            }  else {
                sta = "Not Confirmed";
            }
            String query4 = "INSERT INTO requestedItemsTable (username,full_name, department, requested,request_time,status) VALUES('"+ username + "','" + fullName +"','" + department + "','" + requested + "','" + time + "','" + sta + "')";
            st2 = conn1.createStatement();
            st4 = conn2.createStatement();
            if(requested.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Request An Item?");
                alert.setHeaderText("Request An Item?");
                alert.setContentText("Are you sure you want to request those items?");

                Optional<ButtonType> result = alert.showAndWait();
                if(!result.isPresent() || result.get() != ButtonType.OK) {

                } else {
                    st2.executeUpdate(query2);
                    st4.executeUpdate(query4);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Item Not Selected");
                alert.setHeaderText("Please Select An Item");
                alert.setContentText("Item Not Selected");

                alert.showAndWait();
            }
            rs2.close();
            st3.close();
            st4.close();
            conn2.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        showRequestItems();
        showRequestedItems();
        showConfirmedItems();
    }
    @FXML
    private void handleRemove(ActionEvent event) {
        String query = "DELETE FROM requestItemsTable WHERE id = " + id + "";
        int selectedIndex = requestTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove An Item?");
            alert.setHeaderText("Remove An Item?");
            alert.setContentText("Are you sure you want to remove this item?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {

            } else {
                executeQuery(query);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select a item in the table.");
            alert.showAndWait();
        }
        showRequestItems();
    }
    @FXML
    private void handleConfirm(ActionEvent event) {
        Connection conn = getConnection();
        Statement statement;
        String query = "UPDATE requestedItemsTable SET status ='Not Approved' WHERE id="+ id + "";
        System.out.println(id);
        int selectedIndex = requestListTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm a Request?");
            alert.setHeaderText("Confirm a Request?");
            alert.setContentText("Are you sure you want to confirm this request?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {

            } else {
                try {
                    statement = conn.createStatement();
                    statement.executeUpdate(query);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Request Selected");
            alert.setContentText("Please select a request in the table.");
            alert.showAndWait();
        }
        showRequestedItems();
    }
    @FXML
    private void handleConfirmReject(ActionEvent event){

    }
    @FXML
    private void handleApprove(ActionEvent event) {
        Connection conn = getConnection();
        Statement statement;
        String query = "UPDATE requestedItemsTable SET status ='Not Issued' WHERE id="+ id + "";
        System.out.println(id);
        int selectedIndex = confirmedTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Approve a Request?");
            alert.setHeaderText("Approve a Request?");
            alert.setContentText("Are you sure you want to approve this request?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {

            } else {
                try {
                    statement = conn.createStatement();
                    statement.executeUpdate(query);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Request Selected");
            alert.setContentText("Please select a request in the table.");
            alert.showAndWait();
        }
        showConfirmedItems();
    }
    @FXML
    private void handleApproveReject(ActionEvent event){

    }
    @FXML
    private void handleIssue(ActionEvent event) {
        String requestTime = "";
        String requestItem = "";
        int requestQuantity = 0;
        Connection conn = getConnection();
        Statement statement;
        String query1 = "SELECT * FROM requestedItemsTable WHERE id="+ id + "";
        ResultSet resultSet1;
        Statement statement1;
        ResultSet resultSet2;
        Statement statement2;
        ResultSet resultSet3;
        Statement statement3;
        Statement statement4;
        try {
            statement1 = conn.createStatement();
            resultSet1 = statement1.executeQuery(query1);
            while (resultSet1.next()) {
                requestTime = resultSet1.getString("request_time");
            }
            String query2 = "SELECT * FROM requestItemsTable WHERE request_time="+ requestTime + "";
            statement2 = conn.createStatement();
            resultSet2 = statement2.executeQuery(query2);
            while (resultSet2.next()){
                requestItem = resultSet2.getString("item");
                requestQuantity = resultSet2.getInt("quantity");
                String query3 = "SELECT * FROM itemsTable WHERE product_name='" + requestItem + "'";
                statement3 = conn.createStatement();
                resultSet3 = statement3.executeQuery(query3);
                while (resultSet3.next()) {
                    requestQuantity = resultSet3.getInt("product_quantity") - requestQuantity;
                    String query4 = "UPDATE itemsTable SET product_quantity="+requestQuantity +" WHERE product_name='" + requestItem + "'";
                    statement4 = conn.createStatement();
                    statement4.executeUpdate(query4);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String query = "UPDATE requestedItemsTable SET status ='Issued' WHERE id="+ id + "";
        System.out.println(id);
        int selectedIndex = approvedTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Issue a Request?");
            alert.setHeaderText("Issue a Request?");
            alert.setContentText("Are you sure you want to issue this request?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {

            } else {
                try {
                    statement = conn.createStatement();
                    statement.executeUpdate(query);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Request Selected");
            alert.setContentText("Please select a request in the table.");
            alert.showAndWait();
        }
        showApprovedItems();
        showIssuedItems();
        showItems();
    }
    @FXML
    private void handleIssueReject(ActionEvent event){

    }
    @FXML
    private void handleReturn(ActionEvent event) {
        String requestTime = "";
        String requestItem = "";
        int requestQuantity = 0;
        Connection conn = getConnection();
        Statement statement;
        String query1 = "SELECT * FROM requestedItemsTable WHERE id="+ id + "";
        ResultSet resultSet1;
        Statement statement1;
        ResultSet resultSet2;
        Statement statement2;
        ResultSet resultSet3;
        Statement statement3;
        Statement statement4;
        try {
            statement1 = conn.createStatement();
            resultSet1 = statement1.executeQuery(query1);
            while (resultSet1.next()) {
                requestTime = resultSet1.getString("request_time");
            }
            String query2 = "SELECT * FROM requestItemsTable WHERE request_time="+ requestTime + "";
            statement2 = conn.createStatement();
            resultSet2 = statement2.executeQuery(query2);
            while (resultSet2.next()){
                requestItem = resultSet2.getString("item");
                requestQuantity = resultSet2.getInt("quantity");
                String query3 = "SELECT * FROM itemsTable WHERE product_name='" + requestItem + "'";
                statement3 = conn.createStatement();
                resultSet3 = statement3.executeQuery(query3);
                while (resultSet3.next()) {
                    requestQuantity = resultSet3.getInt("product_quantity") + requestQuantity;
                    String query4 = "UPDATE itemsTable SET product_quantity="+requestQuantity +" WHERE product_name='" + requestItem + "'";
                    statement4 = conn.createStatement();
                    statement4.executeUpdate(query4);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String query = "UPDATE requestedItemsTable SET status ='Returned' WHERE id="+ id + "";
        System.out.println(id);
        int selectedIndex = issuedTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Return a Request?");
            alert.setHeaderText("Return a Request?");
            alert.setContentText("Are you sure you want to return this request?");

            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {

            } else {
                try {
                    statement = conn.createStatement();
                    statement.executeUpdate(query);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Request Selected");
            alert.setContentText("Please select a request in the table.");
            alert.showAndWait();
        }
        showIssuedItems();
        showItems();
    }


    public ObservableList<RequestItems> getRequestItemsList() {
        ObservableList<RequestItems> requestItemsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM requestItemsTable WHERE user='"+loggedInUser +"' and status='Not  Requested'";
        ResultSet rs;
        Statement st;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            RequestItems requestItems;
            while (rs.next()) {
                requestItems = new RequestItems(rs.getInt("id"),rs.getString("item"),rs.getString("quantity"));
                requestItemsList.add(requestItems);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requestItemsList;
    }
    public void showRequestItems() {
        ObservableList<RequestItems> list = getRequestItemsList();

        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colItemQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        requestTable.setItems(list);

    }
    public ObservableList<RequestedItems> getRequestedItemsList() {
        ObservableList<RequestedItems> requestedItemsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String dep = "";
        String query1 = "SELECT * FROM usersTable WHERE username='"+loggedInUser +"'";
        ResultSet rs1;
        Statement st1;
        try {
            st1 = conn.createStatement();
            rs1 = st1.executeQuery(query1);
            while (rs1.next()) {
                dep = rs1.getString("department");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        String query = "SELECT * FROM requestedItemsTable WHERE department='" + dep +"' and status='Not Confirmed'";
        ResultSet rs;
        Statement st;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            RequestedItems requestedItems;
            while (rs.next()) {
                requestedItems = new RequestedItems(rs.getInt("id"),rs.getString("full_name"),rs.getString("department"),rs.getString("requested"),rs.getString("request_date"),rs.getString("status"));
                requestedItemsList.add(requestedItems);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requestedItemsList;
    }
    public void showRequestedItems() {
        ObservableList<RequestedItems> list = getRequestedItemsList();

        colRequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRequestFirstName.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        colRequestDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colRequestRequested.setCellValueFactory(new PropertyValueFactory<>("requested"));
        colRequestRequestDate.setCellValueFactory(new PropertyValueFactory<>("request_date"));
        colRequestStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        requestListTable.setItems(list);

    }
    public ObservableList<RequestedItems> getConfirmedItemsList() {
        ObservableList<RequestedItems> requestedItemsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM requestedItemsTable WHERE status='Not Approved'";
        ResultSet rs;
        Statement st;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            RequestedItems requestedItems;
            while (rs.next()) {
                requestedItems = new RequestedItems(rs.getInt("id"),rs.getString("full_name"),rs.getString("department"),rs.getString("requested"),rs.getString("request_date"),rs.getString("status"));
                requestedItemsList.add(requestedItems);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requestedItemsList;
    }
    public void showConfirmedItems() {
        ObservableList<RequestedItems> list = getConfirmedItemsList();

        colConfirmedID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colConfirmedFirstName.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        colConfirmedDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colConfirmedRequested.setCellValueFactory(new PropertyValueFactory<>("requested"));
        colConfirmedRequestDate.setCellValueFactory(new PropertyValueFactory<>("request_date"));
        colConfirmedStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        confirmedTable.setItems(list);

    }
    public ObservableList<RequestedItems> getApprovedItemsList() {
        ObservableList<RequestedItems> requestedItemsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM requestedItemsTable WHERE status='Not Issued'";
        ResultSet rs;
        Statement st;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            RequestedItems requestedItems;
            while (rs.next()) {
                requestedItems = new RequestedItems(rs.getInt("id"),rs.getString("full_name"),rs.getString("department"),rs.getString("requested"),rs.getString("request_date"),rs.getString("status"));
                requestedItemsList.add(requestedItems);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requestedItemsList;
    }
    public void showApprovedItems() {
        ObservableList<RequestedItems> list = getApprovedItemsList();

        colApprovedID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colApprovedFirstName.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        colApprovedDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colApprovedRequested.setCellValueFactory(new PropertyValueFactory<>("requested"));
        colApprovedRequestDate.setCellValueFactory(new PropertyValueFactory<>("request_date"));
        colApprovedStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        approvedTable.setItems(list);

    }
    public ObservableList<RequestedItems> getIssuedItemsList() {
        ObservableList<RequestedItems> requestedItemsList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM requestedItemsTable WHERE status='Issued'";
        ResultSet rs;
        Statement st;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            RequestedItems requestedItems;
            while (rs.next()) {
                requestedItems = new RequestedItems(rs.getInt("id"),rs.getString("full_name"),rs.getString("department"),rs.getString("requested"),rs.getString("request_date"),rs.getString("status"));
                requestedItemsList.add(requestedItems);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return requestedItemsList;
    }
    public void showIssuedItems() {
        ObservableList<RequestedItems> list = getIssuedItemsList();

        colIssuedID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIssuedFirstName.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        colIssuedDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colIssuedRequested.setCellValueFactory(new PropertyValueFactory<>("requested"));
        colIssuedRequestDate.setCellValueFactory(new PropertyValueFactory<>("request_date"));
        colIssuedStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        issuedTable.setItems(list);

    }


    @FXML
    void handleViewUsers(ActionEvent event) {
        approvedView.setVisible(false);
        storeView.setVisible(false);
        issuedView.setVisible(false);
        requestView.setVisible(false);
        requestedView.setVisible(false);
        confirmedView.setVisible(false);
        usersView.setVisible(true);
        viewStore.getStyleClass().remove("bg");
        pendingRequests.getStyleClass().remove("bg");
        issuedItems.getStyleClass().remove("bg");
        viewUsers.getStyleClass().add("bg");
    }
    @FXML
    void handleViewStore(ActionEvent event) {
        approvedView.setVisible(false);
        issuedView.setVisible(false);
        requestView.setVisible(false);
        requestedView.setVisible(false);
        confirmedView.setVisible(false);
        usersView.setVisible(false);
        storeView.setVisible(true);
        pendingRequests.getStyleClass().remove("bg");
        issuedItems.getStyleClass().remove("bg");
        viewUsers.getStyleClass().remove("bg");
        viewStore.getStyleClass().add("bg");
    }
    @FXML
    void handleRequestItem(ActionEvent event) {
        approvedView.setVisible(false);
        storeView.setVisible(false);
        issuedView.setVisible(false);
        requestedView.setVisible(false);
        confirmedView.setVisible(false);
        usersView.setVisible(false);
        requestView.setVisible(true);
        viewStore.getStyleClass().remove("bg");
        pendingRequests.getStyleClass().remove("bg");
        issuedItems.getStyleClass().remove("bg");
        viewUsers.getStyleClass().remove("bg");
    }
    @FXML
    void handlePendingRequests(ActionEvent event) {
        storeView.setVisible(false);
        requestView.setVisible(false);
        usersView.setVisible(false);
        if(Objects.equals(LoggedIn, "depHead")){
            approvedView.setVisible(false);
            confirmedView.setVisible(false);
            issuedView.setVisible(false);
            requestedView.setVisible(true);
        }
        if(Objects.equals(LoggedIn, "Manager")){
            approvedView.setVisible(false);
            requestedView.setVisible(false);
            issuedView.setVisible(false);
            confirmedView.setVisible(true);
        }
        if(Objects.equals(LoggedIn, "storeKeeper")){
            confirmedView.setVisible(false);
            requestedView.setVisible(false);
            issuedView.setVisible(false);
            approvedView.setVisible(true);
        }
        viewStore.getStyleClass().remove("bg");
        issuedItems.getStyleClass().remove("bg");
        viewUsers.getStyleClass().remove("bg");
        pendingRequests.getStyleClass().add("bg");
    }
    @FXML
    void handleIssuedItems(ActionEvent event) {
        approvedView.setVisible(false);
        storeView.setVisible(false);
        requestView.setVisible(false);
        usersView.setVisible(false);
        issuedView.setVisible(true);
        viewStore.getStyleClass().remove("bg");
        pendingRequests.getStyleClass().remove("bg");
        viewUsers.getStyleClass().remove("bg");
        issuedItems.getStyleClass().add("bg");
    }
    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}