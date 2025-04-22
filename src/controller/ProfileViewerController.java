package controller;

import Container.CurrentUserContainer;
import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

public class ProfileViewerController {
    @FXML
    private Button applyckanges;
    @FXML
    private Button editProfileButton;
    @FXML
    private TextField AddressTextbox;
    @FXML
    private Label genderLabel;
    @FXML
    private ImageView backArrowToHomePage;
    @FXML
    private Label usernamelabel;
    @FXML
    private DatePicker dateOfBirthTextbox;

    @FXML
    private TextField mailtextbox;

    @FXML
    private Label nameTextbox;

    @FXML
    private TextField phonenumberTextbox;


    @FXML
    void goToHomePage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/home_page.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void initialize() {
        initilizeagain();
        applyckanges.setOnAction(e -> {
            EntityUser user = CurrentUserContainer.getCurrentUser();
            user.setAddress(AddressTextbox.getText().toString());
            user.setEmail(mailtextbox.getText().toString());
            LocalDate localDate = dateOfBirthTextbox.getValue();
            Date date_of_birth;
            if (localDate != null) date_of_birth = java.sql.Date.valueOf(localDate);
            else {
                LocalDate currentDate = LocalDate.now();

// Convert LocalDate to java.sql.Date
                date_of_birth = java.sql.Date.valueOf(currentDate);
            }
            user.setDate_of_birth(date_of_birth);
            CurrentUserContainer.setCurrentUser(user);
            new DatabaseHandler().updateUser(user);
            applyckanges.setVisible(false);
            editProfileButton.setVisible(true);
            enableFields(false);
        });
        editProfileButton.setOnAction(e -> {
            enableFields(true);
            applyckanges.setVisible(true);
            editProfileButton.setVisible(false);
        });
    }

    public void initilizeagain() {
        EntityUser user = CurrentUserContainer.getCurrentUser();
        nameTextbox.setText(user.getFirst_name() + " " + user.getLast_name());
        phonenumberTextbox.setText(user.getPhone());
        mailtextbox.setText(user.getEmail());


        LocalDate localDate = LocalDate.parse(user.getDate_of_birth().toString());
        dateOfBirthTextbox.setValue(localDate);
        AddressTextbox.setText(user.getAddress());
        usernamelabel.setText("User: " + user.getUser_name());
        genderLabel.setText("Gender: " + user.getGender());

        enableFields(false);
    }

    public void enableFields(boolean temp) {
        mailtextbox.setEditable(temp);
        phonenumberTextbox.setEditable(temp);
        AddressTextbox.setEditable(temp);
        dateOfBirthTextbox.setDisable(!temp);
    }
}
