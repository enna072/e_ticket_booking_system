package controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class functionProvider {
    public static void sceneChange(Node node, Class<?> classtype, String fxmlFilePath) {
        node.setOnMousePressed(e -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(classtype.getResource(fxmlFilePath));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Scene scene = new Scene(root);
            Stage stage = (Stage) (node.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void increaseSizeTransition(Node node) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), node);
        scaleUp.setToX(1.2); // Increase width by 20%
        scaleUp.setToY(1.2); // Increase height by 20%
        scaleUp.setInterpolator(Interpolator.EASE_BOTH);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), node);
        scaleDown.setToX(1.0); // Reset to original width
        scaleDown.setToY(1.0); // Reset to original height
        scaleDown.setInterpolator(Interpolator.EASE_BOTH);

        node.setOnMouseEntered(e -> {

            node.setCursor(Cursor.HAND); // Change cursor
            scaleUp.play(); // Play scale up animation

        });

        node.setOnMouseExited(e -> {
            scaleDown.play();

        });
    }
    public static void animateWelcomeText(Label label, String text) {
        String message = text;

        // Timeline for animating text
        Timeline timeline = new Timeline();

        // Add KeyFrames to animate each letter
        for (int i = 0; i <= message.length(); i++) {
            String partialText = message.substring(0, i);
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(i * 100), // Delay for each letter
                    e -> label.setText(partialText) // Update label text
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        // Play the animation
        timeline.play();
    }
}
