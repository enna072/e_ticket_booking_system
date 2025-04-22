package controller;

import Container.CurrentUserContainer;
import EntityClasses.EntityUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Helpline {

    @FXML
    private TextField Emailtextfield;

    @FXML
    private ImageView goTOHome;

    @FXML
    private Button submitButton;

    @FXML
    private TextField userNametextfield;
    @FXML
    private TextArea textarea;
    @FXML
    void goTOHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/home_page.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
    @FXML
    private void initialize(){
        EntityUser user= CurrentUserContainer.getCurrentUser();
        Emailtextfield.setText(user.getEmail());
        userNametextfield.setText(user.getUser_name());

        Emailtextfield.setEditable(false);
        userNametextfield.setEditable(false);
    submitButton.setOnAction(e->{
        if(textarea.getText().isEmpty()){
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Add text");
            successAlert.setHeaderText(null); // Optional: Set a header (e.g., "Welcome!")
            successAlert.setContentText("add yout query or objection");

            // Show the alert and wait for user interaction
            successAlert.showAndWait();
        }
        else{
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Sent");
            successAlert.setHeaderText(null); // Optional: Set a header (e.g., "Welcome!")
            successAlert.setContentText("Your mail sent successfully!");

            // Show the alert and wait for user interaction
            successAlert.showAndWait();
        }
    });

    }

}