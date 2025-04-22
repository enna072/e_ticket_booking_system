package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class confirm_sign_up_controller {

    @FXML
    private Label user_name_holder;
    @FXML
    private Button goToLoginPage;
    public String Fxmlfile;

    @FXML
    void goTOLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Fxmlfile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void setText(String userName, String fxmlfile) {
        this.Fxmlfile = fxmlfile;
        user_name_holder.setText("user_name: " + userName);
    }

}
