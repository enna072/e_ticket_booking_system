package controller;

import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityProvider;
import EntityClasses.EntityUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class Dashboard {
    DatabaseHandler handler = new DatabaseHandler();
    List<EntityProvider> providers = handler.allprovider();
    List<EntityUser> Users = handler.retriveAllUser();
    @FXML
    private TableColumn<EntityProvider, Date> Registratin_Date;

    @FXML
    private AnchorPane anchorepaneUsershow;

    @FXML
    private AnchorPane anchorepaneforProvider;

//    @FXML
//    private ImageView backToHome;

    @FXML
    private AnchorPane boxactiveuser;

    @FXML
    private AnchorPane boxserviceprovider;

    @FXML
    private TableColumn<EntityUser, String> col_full_name;

    @FXML
    private TableColumn<EntityProvider, String> col_license_no;

    @FXML
    private TableColumn<EntityProvider, String> col_providerId;

    @FXML
    private TableColumn<EntityProvider, String> col_providerName;

    @FXML
    private TableColumn<EntityUser, String> col_u_userId;

    @FXML
    private TableColumn<EntityUser, String> col_user_address;

    @FXML
    private TableColumn<EntityUser, String> col_user_email;

    @FXML
    private TableColumn<EntityUser, String> col_user_phoneNumber;

    @FXML
    private Label labelUsernummbers;

    @FXML
    private Label labelnumberofServiceprovider;

    @FXML
    private TableView<EntityProvider> tableForProvider;

    @FXML
    private TableView<EntityUser> tableuserShow;


    @FXML
    void backToHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/AdminLoginPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }


    private void enableView(int n) {
        anchorepaneforProvider.setManaged(n == 1);
        anchorepaneforProvider.setVisible(n == 1);

        anchorepaneUsershow.setManaged(n == 2);
        anchorepaneUsershow.setVisible(n == 2);
    }


    private void printproviders() {
        enableView(1);

        ObservableList<EntityProvider> allproviders = FXCollections.observableArrayList(providers);
        col_providerId.setCellValueFactory(c -> c.getValue().getpropertyprovidrid());
        col_providerName.setCellValueFactory(c -> c.getValue().getPropertyProvidername());
        Registratin_Date.setCellValueFactory(c -> c.getValue().getpropertydate());
        col_license_no.setCellValueFactory(c -> c.getValue().getpropertylicenseno());

        tableForProvider.setItems(allproviders);

    }


    private void printusers() {

        enableView(2);

        ObservableList<EntityUser> users = FXCollections.observableArrayList(Users);

        col_full_name.setCellValueFactory(c -> c.getValue().getPropertyFullname());
        col_u_userId.setCellValueFactory(c -> c.getValue().getpropertyUserId());
        col_user_email.setCellValueFactory(c -> c.getValue().getpropertyEmail());
        col_user_phoneNumber.setCellValueFactory(c -> c.getValue().getpropertyPhoneNumber());
        col_user_address.setCellValueFactory(c -> c.getValue().getproertyAddress());

        tableuserShow.setItems(users);

    }

    @FXML
    private void initialize() {
        labelnumberofServiceprovider.setText(String.valueOf(providers.size()));
        labelUsernummbers.setText(String.valueOf(Users.size()));

        boxactiveuser.setOnMousePressed(e -> {
            printusers();
        });
        boxserviceprovider.setOnMousePressed(e -> {
            printproviders();
        });
        printusers();
    }

}

