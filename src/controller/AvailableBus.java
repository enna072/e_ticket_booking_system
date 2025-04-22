package controller;

import Container.temporarydataContainer;
import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityBus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvailableBus {
    String from, to;
    Date journey_date;

    @FXML
    private Label FromLabel;
    @FXML
    private Label Tolabel;

    @FXML
    private Label datelabel;

    @FXML
    private TableColumn<EntityBus, Integer> Col_available_seat;

    @FXML
    private TableColumn<EntityBus, String> Col_destination;

    @FXML
    private TableColumn<EntityBus, Double> Col_fare;

    @FXML
    private TableColumn<EntityBus, String> Col_provider_name;

    @FXML
    private TableColumn<EntityBus, String> Col_start_location;

    @FXML
    private TableColumn<EntityBus, String> Col_starting_time;

    @FXML
    private TableColumn<EntityBus, String> Col_type;

    @FXML
    private TableView<EntityBus> TableForAvailAblBus;

    @FXML
    private Button modifySearch;


    @FXML
    void goToSearchBusPage(ActionEvent event) {
        try {
            // Load the FXML file (correct path with .fxml extension)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Find_bus_Page.fxml"));
            Parent root = loader.load();
            FindBusController findBusController = loader.getController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        setThedata();
        TableForAvailAblBus.setRowFactory(tv -> {
            TableRow<EntityBus> row = new TableRow<>();
            // Set the onMousePressed event for the row
            row.setOnMousePressed(event -> {
                if (!row.isEmpty() && event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    // Retrieve the EntityBus object associated with this row
                    EntityBus bus = row.getItem();

                    // Perform actions with the retrieved EntityBus object
                    temporarydataContainer.setSelectedEntityBus(bus);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BusSeat.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) TableForAvailAblBus.getParent().getScene().getWindow();
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

    public void setThedata() {

        this.from = temporarydataContainer.searchBusinfo.getFromLocation();
        this.to = temporarydataContainer.searchBusinfo.getToDestination();
        this.journey_date = temporarydataContainer.searchBusinfo.getJourneydate();
        System.out.println(from + " " + to + " " + journey_date);
        FromLabel.setText(from);
        Tolabel.setText(to);
        datelabel.setText(String.valueOf(journey_date));
        //  System.out.println(this.from + " " + this.to + " " + journey_date);// Call initialize after setting the data
        printAvailableBus();

    }

    public void printAvailableBus() {
        List<EntityBus> allbus = new DatabaseHandler().allBusses();

        List<EntityBus> selectedList = new ArrayList<>();
        for (EntityBus bus : allbus) {
            if (from.equals(bus.getStartingLocation()) && to.equals(bus.getEndLocation()) && journey_date.equals(bus.getJourneyDate())) {
                selectedList.add(bus);
            }
        }
        ObservableList<EntityBus> observableList = FXCollections.observableArrayList(selectedList);
        Col_provider_name.setCellValueFactory(c -> c.getValue().getStringPropettyProviderId());
        Col_start_location.setCellValueFactory(c -> c.getValue().getPropertyStartingLocation());
        Col_destination.setCellValueFactory(c -> c.getValue().getpropertyEndLocation());
        Col_type.setCellValueFactory(c -> c.getValue().getPropertyType());
        Col_available_seat.setCellValueFactory(c -> c.getValue().integerPropertyAvailableSeat().asObject());
        Col_starting_time.setCellValueFactory(c -> c.getValue().getStartingPropertyTime());
        Col_fare.setCellValueFactory(c -> c.getValue().getPropertyFair().asObject());
        TableForAvailAblBus.setItems(observableList);
    }

}
