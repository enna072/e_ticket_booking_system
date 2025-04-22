package controller;

import Container.CurrentUserContainer;
import Container.temporarydataContainer;
import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityBus;
import EntityClasses.EntityTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDashBoardController {
    List<EntityTicket> onGoingTrip;
    List<EntityTicket> historyTrip;
    @FXML
    private AnchorPane AnchorepaneForOngoingtripView;
    @FXML
    private TableColumn<EntityTicket, String> Col_Bus_type_holder_for_onGoingView;
    @FXML
    private TableColumn<EntityTicket, Date> Col_Journeydate_holder_for_onGoingView1;

    @FXML
    private Button Button_for_view_on_goingView;
    @FXML
    private Button ButtonForVIewTripHistory;
    @FXML
    private TableColumn<EntityTicket, String> Col_From_for_on_going_view;

    @FXML
    private TableColumn<EntityTicket, String> Col_bus_name_for_on_going_view;

    @FXML
    private TableColumn<EntityTicket, String> Col_time_for_on_going_view;

    @FXML
    private TableColumn<EntityTicket, String> Col_to_for_on_going_view;
    @FXML
    private TableView<EntityTicket> TableForViewingOngoingTrip;
    @FXML
    private ImageView GoToHomepage;

    @FXML
    void historyTrip(ActionEvent event) {
        preInitialize();
        setDataOnTableOngoingTrip(historyTrip);
    }

    @FXML
    void on_going_journey(ActionEvent event) {
        preInitialize();
        setDataOnTableOngoingTrip(onGoingTrip);

    }


    @FXML
    private TableView<?> purchaseTable;

    @FXML
    void GoToHomepage(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/home_page.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    private void preInitialize() {
        List<EntityTicket> alltickets = new DatabaseHandler().getAllTickets(CurrentUserContainer.getCurrentUser().getUser_name());
        onGoingTrip = new ArrayList<>();
        historyTrip = new ArrayList<>();
        for (EntityTicket ticket : alltickets) {
            if (ticket.getJourney_date().compareTo(java.sql.Date.valueOf(LocalDate.now())) >= 0) {
                onGoingTrip.add(ticket);
            } else historyTrip.add(ticket);
        }
    }

    private void setDataOnTableOngoingTrip(List<EntityTicket> TicketLIst) {
        ObservableList<EntityTicket> observableTIcketList = FXCollections.observableList(TicketLIst);
        Col_From_for_on_going_view.setCellValueFactory(c -> c.getValue().getPropertyStartingLocation());
        Col_to_for_on_going_view.setCellValueFactory(c -> c.getValue().getPropertyEndingLocation());
        Col_time_for_on_going_view.setCellValueFactory(c -> c.getValue().getPropertyStime());
        Col_bus_name_for_on_going_view.setCellValueFactory(c -> c.getValue().getBusname());
        Col_Bus_type_holder_for_onGoingView.setCellValueFactory(c -> c.getValue().getPropertyType());
        Col_Journeydate_holder_for_onGoingView1.setCellValueFactory(c -> c.getValue().getPropertyJourney_date());
        TableForViewingOngoingTrip.setItems(observableTIcketList);
    }

    @FXML
    private void initialize() {
        preInitialize();
        setDataOnTableOngoingTrip(onGoingTrip);

        TableForViewingOngoingTrip.setRowFactory(tv -> {
            TableRow<EntityTicket> row = new TableRow<>();
            // Set the onMousePressed event for the row
            row.setOnMousePressed(event -> {
                if (!row.isEmpty() && event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    // Retrieve the EntityBus object associated with this row
                    EntityTicket ticket = row.getItem();
                    ticket.setSeatNumbers(new DatabaseHandler().getSeatNumber(ticket.getTicket_id()));
                    // Perform actions with the retrieved EntityBus object
                    temporarydataContainer.setCurrentTicket(ticket);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ticket_view.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) TableForViewingOngoingTrip.getParent().getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Example: Show details of the selected bus

                }
            });

            return row;
        });
    }
}