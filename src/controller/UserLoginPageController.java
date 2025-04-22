package controller;

import Container.CurrentUserContainer;
import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityUser;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class UserLoginPageController {
    String text = "Do sign up to join with My-ticket and have the best deal!!!";
    @FXML
    private ImageView passkeyvisibilityoff;

    @FXML
    private Text textholder;
    @FXML
    private PasswordField passwordTextfieldNovisible;
    @FXML
    private Button LoginButton;

    @FXML
    private TextField PassWorDTextBox;

    @FXML
    private TextField UserNameTextBox;

    @FXML
    private Button ViewAsAGuestButton;

    @FXML
    private Button signup;
    @FXML
    private ImageView EticketImage;

    @FXML
    private ImageView visiblepassword;

    @FXML
    void goTOViewAsAGuest(ActionEvent event) {

    }


    @FXML
    void goToHomePage(ActionEvent event) throws IOException {
        String user_name = UserNameTextBox.getText();
        String password = PassWorDTextBox.getText();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        EntityUser user = databaseHandler.userValidation(user_name, password);

        if (user_name.isEmpty() || password.isEmpty() || user == null) {
            String title, header, content;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            if (user_name.isEmpty() || password.isEmpty()) {
                title = "Missing input";
                if (user_name.isEmpty()) {
                    header = "Username is missing";
                    content = "Enter username";
                } else {
                    header = "Password is missing";
                    content = "Enter password";
                }
            } else {
                title = "Wrong input";
                header = "Password didn't match with the username";
                content = "Enter correct password";
            }
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(content);
            alert.showAndWait();

        } else {

            CurrentUserContainer.setCurrentUser(user);
            Parent root = FXMLLoader.load(getClass().getResource("/views/home_page.fxml"));
            VBox Slider = (VBox) root.lookup("#Slider");
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.001));
            slide.setNode(Slider);

            slide.setToX(-177.6);
            slide.play();

            Slider.setTranslateX(0);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        }
    }


    @FXML
    void gotoSignupPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/signuppage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void goToAdminPanel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/AdminLoginPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();


    }

    ;

    @FXML
    private void initialize() {

        UserNameTextBox.setText("Enna_82711");
        passwordTextfieldNovisible.setText("12345678");


        animateWelcomeText(textholder, text);

        EticketImage.setCursor(Cursor.HAND);
        passwordTextfieldNovisible.managedProperty().bind(visiblepassword.visibleProperty());
        passwordTextfieldNovisible.visibleProperty().bind(visiblepassword.visibleProperty());

        PassWorDTextBox.managedProperty().bind(passkeyvisibilityoff.visibleProperty());
        PassWorDTextBox.visibleProperty().bind(passkeyvisibilityoff.visibleProperty());

        PassWorDTextBox.textProperty().bindBidirectional(passwordTextfieldNovisible.textProperty());

        passwordTextfieldNovisible.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if (newvalue.length() > 8) passwordTextfieldNovisible.setText(oldvalue);
        });

        passkeyvisibilityoff.setOnMouseClicked(event -> {
            visiblepassword.setVisible(true);
            passkeyvisibilityoff.setVisible(false);
        });
        visiblepassword.setOnMouseClicked(mouseEvent -> {
            visiblepassword.setVisible(false);
            passkeyvisibilityoff.setVisible(true);
        });
        functionProvider.sceneChange(EticketImage, UserLoginPageController.class, "/views/first_interface.fxml");
    }

    public void animateWelcomeText(Text text, String msgtext) {
        String message = msgtext;

        // Timeline for animating text
        Timeline timeline = new Timeline();

        // Add KeyFrames to animate each letter
        for (int i = 0; i <= message.length(); i++) {
            String partialText = message.substring(0, i);
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(i * 100), // Delay for each letter
                    e -> text.setText(partialText)// Update label text
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        // Play the animation
        timeline.play();
    }

}
