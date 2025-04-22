package controller;

import Container.CurrentProviderContainer;
import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityProvider;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class BusProviderLoginPage {
    @FXML
    private ImageView backArrowtoFirstPage;

    @FXML
    private ImageView backarrowToLogin;

    @FXML
    private Button confirmButtonForRegister;

    @FXML
    private TextField lisenceNoTextFieldForregister;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginview;

    @FXML
    private TextField passwordTextFieldforLogin;

    @FXML
    private TextField passwordtextFieldForregister;

    @FXML
    private TextField providerNameforRegister;

    @FXML
    private Button registerButtontoGoToregisterView;

    @FXML
    private AnchorPane signupView;

    @FXML
    private Text textHolder;

    @FXML
    private TextField userIdTextFieldforLogin;
    String name;
    String pass;
    String licenseno;


    @FXML
    private void initialize() {
        userIdTextFieldforLogin.setText("Hanif_6187");
        passwordTextFieldforLogin.setText("12345678");
        registerButtontoGoToregisterView.setOnAction(e -> {
            loginview.setManaged(false);
            loginview.setVisible(false);
            signupView.setManaged(true);
            signupView.setVisible(true);
        });
        backarrowToLogin.setOnMousePressed(e -> {
            loginview.setManaged(true);
            loginview.setVisible(true);
            signupView.setManaged(false);
            signupView.setVisible(false);
        });
        animateWelcomeText(textHolder, "Welcome to provider section!!!");
        backArrowtoFirstPage.setOnMousePressed(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/first_interface.fxml"));
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
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

    //logic for registerpage
    @FXML
    public void confirmRegister(ActionEvent event) {
        DatabaseHandler db = new DatabaseHandler();
        if (providerNameforRegister.getText().isEmpty() || passwordtextFieldForregister.getText().isEmpty() || lisenceNoTextFieldForregister.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("input missing");
            alert.setHeaderText("some input is missing");
            alert.setContentText("fill every information");
            alert.showAndWait();
        } else {
            Random rn = new Random();
            int n = rn.nextInt(10000);
            name = String
                    .valueOf(providerNameforRegister.getText());
            pass = String.valueOf(passwordtextFieldForregister.getText());
            licenseno = String.valueOf(lisenceNoTextFieldForregister.getText());

            String userId = name + "_" + String.valueOf(n);
            LocalDate localDate = LocalDate.now();
            Date issuedate = Date.valueOf(localDate);
            EntityProvider provider = new EntityProvider(userId, name, pass, licenseno, issuedate);

            boolean flag = db.insertintoprovider(provider);
            if (flag) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/confirm_sign_up.fxml"));

                // Load the FXML file
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Retrieve the controller
                confirm_sign_up_controller controller = loader.getController();

                controller.setText(userId, "/views/bus_provider_login_page.fxml");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();


            }
        }
    }

    //logic for login
    @FXML
    void login(ActionEvent event) throws IOException {
        String userNmae = userIdTextFieldforLogin.getText();
        String password = passwordTextFieldforLogin.getText();
        EntityProvider provider;
        if (userNmae.isEmpty() || password.isEmpty() || (provider = new DatabaseHandler().providerValidation(userNmae, password)) == null) {
            if (userNmae.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("input missing");
                alert.setHeaderText("some input is missing");
                alert.setContentText("fill every information");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("mitch match");
                alert.setHeaderText("invalid username and password");
                alert.setContentText("enter valid username and password");
                alert.showAndWait();
            }
        } else {
            CurrentProviderContainer.setCurrentProvider(provider);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText("Loging in as " + CurrentProviderContainer.getCurrentProvider().getProviderId().split("_")[0] + "!");
            alert.setContentText("You have successfully logged in.");
            alert.showAndWait(); // Show the dialog and wait for the user to close it

            Parent root = FXMLLoader.load(getClass().getResource("/views/providerDashboard.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        }
    }

}
