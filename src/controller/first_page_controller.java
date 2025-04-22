package controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class first_page_controller {
    String text = "My-ticket is an efficient bus ticket management system designed to streamline ticket booking and management for users, providers, and administrators. Users can easily browse routes, check schedules, and book tickets, while providers can manage bus details and schedules seamlessly. Administrators oversee the entire system, ensuring smooth operations and managing user data. With a user-friendly interface and robust functionality, My-ticket simplifies the process of bus ticketing for all stakeholders.";
    @FXML
    private Label welcomelabel; // Make sure this is linked to your FXML label

    @FXML
    private Text textholder;

    @FXML
    private VBox adminVbox;

    @FXML
    private VBox serviceProviderVbox;

    @FXML
    private VBox userVbox;

    @FXML
    private void initialize() {
        functionProvider.sceneChange(serviceProviderVbox, first_page_controller.class,"/views/bus_provider_login_page.fxml");
        animateWelcomeText(textholder, text);
        functionProvider.animateWelcomeText(welcomelabel, "Welcome to My-ticket");
        customized(adminVbox, "Manage administrative task");
        customized(serviceProviderVbox, "add busses");
        customized(userVbox, "use as an user");

        functionProvider.sceneChange(adminVbox, first_page_controller.class, "/views/AdminLoginPage.fxml");

        functionProvider.sceneChange(userVbox, first_page_controller.class, "/views/UserLoginPage.fxml");
    }


    private void customized(VBox vBox, String text) {

        // Tooltip with dynamic text
        Tooltip tooltip = new Tooltip(text);


        // Scale transitions for hover effect
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), vBox);
        scaleUp.setToX(1.05); // Increase width by 20%
        scaleUp.setToY(1.05); // Increase height by 20%
        scaleUp.setInterpolator(Interpolator.EASE_BOTH);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), vBox);
        scaleDown.setToX(1.0); // Reset to original width
        scaleDown.setToY(1.0); // Reset to original height
        scaleDown.setInterpolator(Interpolator.EASE_BOTH);

        // Event listeners
        vBox.setOnMouseEntered(e -> {

            vBox.setCursor(Cursor.HAND); // Change cursor
            scaleUp.play(); // Play scale up animation
            tooltip.show(vBox, vBox.getLayoutX() + 350, vBox.getLayoutY() + 10);
        });

        vBox.setOnMouseExited(e -> {
            scaleDown.play();
            tooltip.hide();// Play scale down animation
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
                    Duration.millis(i * 10), // Delay for each letter
                    e -> text.setText(partialText)// Update label text
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        // Play the animation
        timeline.play();
    }

}



