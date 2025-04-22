package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginPageController {
    @FXML
    private ImageView EticketImage;

    @FXML
    private Button GoToDashBoard;

    @FXML
    private TextField PassWorDTextBox;

    @FXML
    private Label labelForadministrativeanimation;
    @FXML
    private TextField UserNameTextBox;

    @FXML
    void GoToDashBoard(MouseEvent event) throws IOException {
        if (UserNameTextBox.getText() == null || UserNameTextBox.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Admin Id missing");

            alert.setContentText("Enter admin id");
            alert.showAndWait();
            return;

        }
        String adminId = UserNameTextBox.getText();
        if (PassWorDTextBox.getText() == null || PassWorDTextBox.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pass word missing");

            alert.setContentText("Enter Password");
            alert.showAndWait();
            return;
        }
        String password = PassWorDTextBox.getText();

        if (adminId.equals("Admin_myticket") && password.equals("15240")) {

            Parent root = FXMLLoader.load(getClass().getResource("/views/dashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mitch match");

            alert.setContentText("Enter Correct Admin id and password");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void initialize() {
        UserNameTextBox.setText("Admin_myticket");
        PassWorDTextBox.setText("15240");
        functionProvider.sceneChange(EticketImage, AdminLoginPageController.class, "/views/first_interface.fxml");
        functionProvider.animateWelcomeText(labelForadministrativeanimation, "Administrative section.");
    }


}