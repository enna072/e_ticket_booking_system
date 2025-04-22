package controller;

import Container.temporarydataContainer;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Date;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

public class FindBusController {

    @FXML
    private Label findBuslabel;
    @FXML
    private ImageView homeIcon;
    @FXML
    private Button Availablebus;
    @FXML
    private DatePicker datePickerJourneydate;
    @FXML
    private TextField fromlebel;
    @FXML
    private Label messagelabel_second;

    @FXML
    private TextField tolebel;

    @FXML
    void goToHome(MouseEvent event) throws IOException {
        temporarydataContainer.searchBusinfo.remove();
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

    @FXML
    void goToAvailableBus(ActionEvent event) {
        String startLocation, destination;
        if (fromlebel.getText() == null || fromlebel.getText().isEmpty()) {
            showAlert("Enter starting location");
            return;
        }
        startLocation = fromlebel.getText().toUpperCase();
        if (tolebel.getText() == null || tolebel.getText().isEmpty()) {
            showAlert("Enter destination");
            return;
        }
        destination = tolebel.getText().toUpperCase();
        if (datePickerJourneydate.getValue() == null || datePickerJourneydate.getValue().compareTo(LocalDate.now()) < 0) {
            showAlert("select A valid journey date");
            return;
        }
        Date journeyDate = java.sql.Date.valueOf(datePickerJourneydate.getValue());
        temporarydataContainer.searchBusinfo.setData(startLocation, destination, journeyDate);
        try {
            // Load the FXML file (correct path with .fxml extension)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/available_bus.fxml"));
            Parent root = loader.load();
            AvailableBus availableBus = loader.getController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initialize() {
        fromlebel.setText("Dhaka");
        tolebel.setText("Rangpur");
        datePickerJourneydate.setValue(LocalDate.now());
//        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), Availablebus);
//        scaleUp.setToX(1.2); // Increase width by 20%
//        scaleUp.setToY(1.2); // Increase height by 20%
//        scaleUp.setInterpolator(Interpolator.EASE_BOTH);
//
//        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), Availablebus);
//        scaleDown.setToX(1.0); // Reset to original width
//        scaleDown.setToY(1.0); // Reset to original height
//        scaleDown.setInterpolator(Interpolator.EASE_BOTH);
//
//        Availablebus.setOnMouseEntered(e -> {
//
//            Availablebus.setCursor(Cursor.HAND); // Change cursor
//            scaleUp.play(); // Play scale up animation
//
//        });
//
//        Availablebus.setOnMouseExited(e -> {
//            scaleDown.play();
//
//        });
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                functionProvider.animateWelcomeText(messagelabel_second, "Search , pay and GOO!");
            }
        }, 2000);
        functionProvider.animateWelcomeText(findBuslabel, "Find Your Bus");
        functionProvider.increaseSizeTransition(Availablebus);

        if (!temporarydataContainer.searchBusinfo.isnull()) {

            fromlebel.setText(temporarydataContainer.searchBusinfo.getFromLocation());
            tolebel.setText(temporarydataContainer.searchBusinfo.getToDestination());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}