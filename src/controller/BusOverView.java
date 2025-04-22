package controller;

import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityBus;
import EntityClasses.EntitySeat;
import EntityClasses.EntityTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Container.temporarydataContainer;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class BusOverView {
    EntityBus bus = temporarydataContainer.getSelectedBus();
    @FXML
    private TableColumn<EntityTicket, String> col_bookedBy;

    @FXML
    private TableColumn<EntityTicket, String> col_bookedSeats;

    @FXML
    private TableColumn<EntityTicket, String> col_email;

    @FXML
    private TableColumn<EntityTicket, Date> col_issueDate;

    @FXML
    private TableColumn<EntityTicket, String> col_phoneNumber;

    @FXML
    private TableColumn<EntityTicket, Integer> col_ticektId;


    @FXML
    private TableColumn<EntitySeat, String> Col_booking_status;

    @FXML
    private TableColumn<EntitySeat, String> Col_seat_number;

    @FXML
    private TableColumn<EntitySeat, Integer> Col_ticket_id;

    @FXML
    private ImageView ImgBackArrow;

    @FXML
    private Label LabelAvailableSeat;

    @FXML
    private Label LabelBookedSeat;

    @FXML
    private Label LabelBusId;

    @FXML
    private Label LabelDestination;
    @FXML
    private AnchorPane ticketViewAnchorepane;
    @FXML
    private Label LabelFare;
    @FXML
    private AnchorPane seatViewAnchorepane;
    @FXML
    private Label LabelName;

    @FXML
    private Label LabelStartingLocation;

    @FXML
    private Label LabelStartingTime;

    @FXML
    private Label LabelTotalSeat;

    @FXML
    private Label labelJourneyDate;
    @FXML
    private Label LabelType;
    @FXML
    private TableView<EntityTicket> tableTicketShow;
    @FXML
    private Label totalRevenew;


    @FXML
    private TableView<EntitySeat> tableseatShow;

    @FXML
    void GoToPrevious(MouseEvent event) throws IOException {
        String previouspage = temporarydataContainer.getPreviousPage();
        Parent root = FXMLLoader.load(getClass().getResource(previouspage));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private Button butttonshowTickets;

    @FXML
    private Button butttonshowseats;

    @FXML
    private void initialize() {
        butttonshowseats.setOnAction(event -> {
            preinitializeSeatShow();
        });
        butttonshowTickets.setOnAction(e -> {
            preinitializeTicketView();
        });
        LabelAvailableSeat.setText(String.valueOf(bus.getAvailableSeat()));
        LabelBookedSeat.setText(String.valueOf(bus.getBookedSeat()));
        LabelBusId.setText(String.valueOf(bus.getBusId()));
        LabelDestination.setText(bus.getEndLocation());
        LabelFare.setText(String.valueOf(bus.getFair()));
        LabelStartingLocation.setText(bus.getStartingLocation());
        LabelName.setText(bus.getProviderId().split("_")[0]);
        LabelStartingTime.setText(bus.getStartingTime());
        LabelType.setText(bus.getType());
        LabelTotalSeat.setText(String.valueOf(bus.getTotal_seat()));
        labelJourneyDate.setText(String.valueOf(bus.getJourneyDate()));
        totalRevenew.setText(String.valueOf(bus.getBookedSeat() * bus.getFair()));
        preinitializeSeatShow();
    }

    private void preinitializeTicketView() {
        ticketViewAnchorepane.setManaged(true);
        ticketViewAnchorepane.setVisible(true);
        seatViewAnchorepane.setManaged(false);
        seatViewAnchorepane.setVisible(false);

        ObservableList<EntityTicket> alltickets = FXCollections.observableArrayList(new DatabaseHandler().getTikcetbyBus(bus));

        col_ticektId.setCellValueFactory(c -> c.getValue().getPropertyTicket_id().asObject());
        col_bookedSeats.setCellValueFactory(c -> c.getValue().getPropertySeatnumbers());
        col_bookedBy.setCellValueFactory(c -> c.getValue().getTicketHolderName());
        col_email.setCellValueFactory(c -> c.getValue().getholderEmail());
        col_phoneNumber.setCellValueFactory(c -> c.getValue().getholderPhone());
        col_issueDate.setCellValueFactory(c -> c.getValue().getPropertyBooking_date());

        tableTicketShow.setItems(alltickets);
    }


    private void preinitializeSeatShow() {

        ticketViewAnchorepane.setManaged(false);
        ticketViewAnchorepane.setVisible(false);
        seatViewAnchorepane.setManaged(true);
        seatViewAnchorepane.setVisible(true);

        ObservableList<EntitySeat> allseats = FXCollections.observableList(new DatabaseHandler().getAllSeats(bus));

        Col_booking_status.setCellValueFactory(c -> c.getValue().getBooking_statusProperty());
        Col_seat_number.setCellValueFactory(c -> c.getValue().getSeat_numberProperty());
        Col_ticket_id.setCellValueFactory(c -> c.getValue().getTicketIdProperty().asObject());

        tableseatShow.setItems(allseats);

    }
}
