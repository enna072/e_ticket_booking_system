package controller;

import Container.CurrentUserContainer;
import Container.temporarydataContainer;
import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityBus;
import EntityClasses.EntitySeat;
import EntityClasses.EntityTicket;
import EntityClasses.EntityUser;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class BusSeatshow {

    String availabelstyle = "-fx-background-color: #FFFFFF;";
    String selectedStyle = "-fx-background-color: #636363;";
    String bookedStyle = "-fx-background-color:#ff991c;";
    HashSet<String> idset = new HashSet<>();
    HashSet<String> seatNumberSet = new HashSet<>();
    EntityBus selectedBus = temporarydataContainer.getSelectedBus();
    EntityUser user = CurrentUserContainer.getCurrentUser();
    int totalFare = 0;
    @FXML
    private AnchorPane root;
    @FXML
    private Label A1;

    @FXML
    private Label A2;

    @FXML
    private Label A3;

    @FXML
    private Label A4;

    @FXML
    private AnchorPane AcView;

    @FXML
    private Label B1;

    @FXML
    private Label B2;

    @FXML
    private Label B3;

    @FXML
    private Label B4;

    @FXML
    private Button ButtonConfirmBook;
    @FXML
    private ComboBox<String> ComboboxPaymentMethod;
    @FXML
    private Label C1;

    @FXML
    private Label C2;

    @FXML
    private Label C3;

    @FXML
    private Label C4;

    @FXML
    private Label D1;

    @FXML
    private Label D2;

    @FXML
    private Label D3;

    @FXML
    private Label D4;

    @FXML
    private Label E1;

    @FXML
    private Label E2;

    @FXML
    private Label E3;

    @FXML
    private Label E4;

    @FXML
    private Label F1;

    @FXML
    private Label F2;

    @FXML
    private Label F3;

    @FXML
    private Label F4;

    @FXML
    private Label G1;

    @FXML
    private Label G2;

    @FXML
    private Label G3;

    @FXML
    private Label G4;

    @FXML
    private Label H1;

    @FXML
    private Label H2;

    @FXML
    private Label H3;

    @FXML
    private Label H4;

    @FXML
    private ImageView ImageBackArrow;

    @FXML
    private Label LabelBusnameHolder;

    @FXML
    private Label LabelDepartureTimeHolder;

    @FXML
    private Label LabelDestinationHolder;

    @FXML
    private Label LabelFareHolder;

    @FXML
    private Label LabelJourneyDateHolder;

    @FXML
    private Label LabelSelectedSeat;

    @FXML
    private Label LabelStartingLocationHolder;

    @FXML
    private Label LabelStartingTimeHolder;

    @FXML
    private Label LabelTotalFare;

    @FXML
    private AnchorPane NonAcView;

    @FXML
    private Label a1;

    @FXML
    private Label a2;

    @FXML
    private Label a3;

    @FXML
    private Label b1;

    @FXML
    private Label b2;

    @FXML
    private Label b3;

    @FXML
    private Label c1;

    @FXML
    private Label c2;

    @FXML
    private Label c3;

    @FXML
    private Label d1;

    @FXML
    private Label d2;

    @FXML
    private Label d3;

    @FXML
    private Label e1;

    @FXML
    private Label e2;

    @FXML
    private Label e3;

    @FXML
    private Label f1;

    @FXML
    private Label f2;

    @FXML
    private Label f3;

    @FXML
    private Label g1;

    @FXML
    private Label g2;

    @FXML
    private Label g3;

    @FXML
    private Label h1;

    @FXML
    private Label h2;

    @FXML
    private Label h3;

    @FXML
    void backToavailableBuspage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/available_bus.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void confirmBook(ActionEvent event) {
        if (idset.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Select seat");
            alert.showAndWait();
        } else {
            int ticketId = new DatabaseHandler().InsertTicket(selectedBus, CurrentUserContainer.getCurrentUser().getUser_name(), idset);
            if (ticketId > 0) {
                //EntityTicket newTicket=new EntityTicket(ticketId,user.getUser_name(),selectedBus.getBusId(),selectedBus.getProviderId(),selectedBus.getJourneyDate(), Date.valueOf(LocalDate.now()),totalFare);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("booked successfully");
                alert.showAndWait();

                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/UserDashBoard.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }

    @FXML
    private void initialize() {
        initialSetup();
    }

    public void initialSetup() {

        ComboboxPaymentMethod.getItems().addAll("Bkash", "Nagod", "Rocket");
        LabelBusnameHolder.setText(selectedBus.getProviderId().split("_")[0]);
        LabelStartingLocationHolder.setText(selectedBus.getStartingLocation());
        LabelDestinationHolder.setText(selectedBus.getEndLocation());
        LabelStartingTimeHolder.setText(selectedBus.getStartingTime());
        LabelDepartureTimeHolder.setText(selectedBus.getDepartureTime());
        LabelFareHolder.setText(String.valueOf(selectedBus.getFair()) + "TK");
        LabelJourneyDateHolder.setText(String.valueOf(selectedBus.getJourneyDate()));
        LabelSelectedSeat.setText("No seat selected");
        LabelTotalFare.setText("0 TK");
        if (selectedBus.getType().equals("AC")) {
            NonAcView.setManaged(false);
            NonAcView.setVisible(false);
            AcView.setManaged(true);
            AcView.setVisible(true);

        } else {
            AcView.setManaged(false);
            AcView.setVisible(false);
            NonAcView.setManaged(true);
            NonAcView.setVisible(true);
        }

        List<EntitySeat> allseats = new DatabaseHandler().getAllSeats(selectedBus);
        for (EntitySeat seat : allseats) {
            if (seat.getBooking_status().equals("Booked")) {
                Label label = (Label) root.lookup("#" + seat.getSeat_number());
                label.setStyle(bookedStyle);
                label.setOnMousePressed(e -> e.consume());
                label.setOnMouseClicked(e -> e.consume());
            } else {
                Label label = (Label) root.lookup("#" + seat.getSeat_number());
                label.setOnMousePressed(e -> {
                    lebelPressed(e);
                });
            }
        }
    }

    private void lebelPressed(Event event) {
        Label label = (Label) event.getSource();
        String id = (String) label.getId();
        String seatNumber = (String) label.getText();
        if (label.getStyle().equals(availabelstyle)) {
            label.setStyle(selectedStyle);
            idset.add(id);
            seatNumberSet.add(seatNumber);
            LabelSelectedSeat.setText(seatNumberSet.toString());
            totalFare += selectedBus.getFair();
            LabelTotalFare.setText(String.valueOf(totalFare));

        } else {
            label.setStyle(availabelstyle);
            idset.remove(id);
            seatNumberSet.remove(seatNumber);
            LabelSelectedSeat.setText(seatNumberSet.toString());
            if (seatNumberSet.isEmpty()) LabelSelectedSeat.setText("No seat selected");
            if (totalFare > 0) {
                totalFare -= selectedBus.getFair();
                LabelTotalFare.setText(String.valueOf(totalFare));
            }

        }
    }
}
