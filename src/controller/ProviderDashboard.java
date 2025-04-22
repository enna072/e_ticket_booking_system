package controller;

import Container.CurrentProviderContainer;
import Container.temporarydataContainer;
import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityBus;
import EntityClasses.EntityProvider;
import EntityClasses.EntitySeat;
import EntityClasses.EntityUser;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.management.Query;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProviderDashboard implements Initializable {
    List<EntityBus> allBus = new DatabaseHandler().allBusses();
    boolean comboinit = false;
    @FXML
    private Label nameHolder;
    @FXML
    private Button ButtonAddBus;
    @FXML
    private Label LabelHeader;
    @FXML
    private Button ButtonShowAvailableBus;

    @FXML
    private Button Buttonlogout;

    @FXML
    private TableColumn<EntityBus, Integer> Col_Bus_id;

    @FXML
    private TableColumn<EntityBus, String> Col_Bus_type;

    @FXML
    private TableColumn<EntityBus, String> Col_destination;

    @FXML
    private TableColumn<EntityBus, Double> Col_fair;

    @FXML
    private TableColumn<EntityBus, String> Col_fromLocation;

    @FXML
    private TableColumn<EntityBus, Date> Col_joirney_date;

    @FXML
    private TableColumn<EntityBus, String> Col_journey_time;

    @FXML
    private AnchorPane ViewAddBus;

    @FXML
    private AnchorPane View_availableBus;

    @FXML
    private TableView<EntityBus> availableBusShowtable;
    @FXML
    private Button buttonHisstory;


//    @Override
//    private void initialize() {
//
//    }

    private void printTable(List<EntityBus> listofBus) {


        ObservableList<EntityBus> allBusses = FXCollections.observableArrayList(listofBus);

        Col_Bus_id.setCellValueFactory(c -> c.getValue().getIntegerpropertyBusId().asObject());
        Col_Bus_type.setCellValueFactory(c -> c.getValue().getPropertyType());
        Col_joirney_date.setCellValueFactory(c -> c.getValue().getPropertyJourneyDate());
        Col_fromLocation.setCellValueFactory(c -> c.getValue().getPropertyStartingLocation());
        Col_destination.setCellValueFactory(c -> c.getValue().getpropertyEndLocation());
        Col_journey_time.setCellValueFactory(c -> c.getValue().getStartingPropertyTime());
        Col_fair.setCellValueFactory(c -> c.getValue().getPropertyFair().asObject());

        availableBusShowtable.setItems(allBusses);
        availableBusShowtable.setRowFactory(tv -> {
            TableRow<EntityBus> row = new TableRow<>();
            // Set the onMousePressed event for the row
            row.setOnMousePressed(event -> {
                if (!row.isEmpty() && event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    // Retrieve the EntityBus object associated with this row
                    EntityBus bus = row.getItem();

                    // Perform actions with the retrieved EntityBus object
                    temporarydataContainer.setSelectedEntityBus(bus);
                    temporarydataContainer.setPreviousPage("/views/providerDashboard.fxml");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/BusOverView.fxml"));
                    try {
                        Parent root = loader.load();
                        Stage stage = (Stage) availableBusShowtable.getParent().getScene().getWindow();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EntityProvider provider = CurrentProviderContainer.getCurrentProvider();
        String name = provider.getProvider_name();
        nameHolder.setText(name);
        ButtonAddBus.setOnAction(event -> {
            ViewAddBus.setManaged(true);
            ViewAddBus.setVisible(true);

            View_availableBus.setManaged(false);
            View_availableBus.setVisible(false);
            setComboboxes();
        });
        buttonHisstory.setOnAction(e -> {
            ViewAddBus.setManaged(false);
            ViewAddBus.setVisible(false);
            LabelHeader.setText("History");
            View_availableBus.setManaged(true);
            View_availableBus.setVisible(true);
            List<EntityBus> Buslist = new ArrayList<>();
            String providerId = CurrentProviderContainer.getCurrentProvider().getProviderId();
            for (EntityBus bus : allBus) {
                if (providerId.equals(bus.getProviderId()) && bus.getJourneyDate().compareTo(Date.valueOf(LocalDate.now())) < 0) {
                    Buslist.add(bus);
                }
            }
            printTable(Buslist);
            comboinit = false;
            //typeComboboxForAddB
        });
        ButtonShowAvailableBus.setOnAction(e -> {
            ViewAddBus.setManaged(false);
            ViewAddBus.setVisible(false);
            LabelHeader.setText("Available bus");
            View_availableBus.setManaged(true);
            View_availableBus.setVisible(true);
            List<EntityBus> Buslist = new ArrayList<>();
            String providerId = CurrentProviderContainer.getCurrentProvider().getProviderId();
            for (EntityBus bus : allBus) {
                if (providerId.equals(bus.getProviderId()) && bus.getJourneyDate().compareTo(Date.valueOf(LocalDate.now())) >= 0) {
                    Buslist.add(bus);
                }
            }
            printTable(Buslist);
            comboinit = false;
            //typeComboboxForAddBus.setItems(null);
        });
        printTable(allBus);


    }

    // code sectin for add bus
    @FXML
    private Button Add_buttonForAddBus;

    @FXML
    private TextField BusIdTextFieldForAddBus;

    @FXML
    private TextField fairlabel;
    @FXML
    private DatePicker DateForAddbus;

    @FXML
    private TextField DepartureTimeForAddBus;

    @FXML
    private TextField StartLocForAddBus;

    @FXML
    private TextField StartingTimeForAddBus;


    @FXML
    private TextField destinationForAddBus;


    @FXML
    private ComboBox<String> typeComboboxForAddBus;

    @FXML
    void addBus_final(ActionEvent event) {
        try {
            // Validate Bus ID
            if (BusIdTextFieldForAddBus.getText() == null || BusIdTextFieldForAddBus.getText().isEmpty()) {
                showAlert("Bus ID is required.");
                return;
            }
            int busId = Integer.parseInt(BusIdTextFieldForAddBus.getText());

            // Validate Journey Date
            if (DateForAddbus.getValue() == null) {
                showAlert("Journey Date is required.");
                return;
            }
            Date journeyDate = java.sql.Date.valueOf(DateForAddbus.getValue());

            // Validate Departure Time
            if (DepartureTimeForAddBus.getText() == null || DepartureTimeForAddBus.getText().isEmpty()) {
                showAlert("Departure Time is required.");
                return;
            }
            String departureTime = DepartureTimeForAddBus.getText();
            if (fairlabel.getText() == null || fairlabel.getText().isEmpty()) {
                showAlert("Enter fair");
                return;
            }
            Double fair = Double.parseDouble(fairlabel.getText());
            // Validate Starting Location
            if (StartLocForAddBus.getText() == null || StartLocForAddBus.getText().isEmpty()) {
                showAlert("Starting Location is required.");
                return;
            }
            String startingLocation = StartLocForAddBus.getText().toUpperCase();

            // Validate Destination
            if (destinationForAddBus.getText() == null || destinationForAddBus.getText().isEmpty()) {
                showAlert("Destination is required.");
                return;
            }
            String destination = destinationForAddBus.getText().toUpperCase();

            // Validate Starting Time
            if (StartingTimeForAddBus.getText() == null || StartingTimeForAddBus.getText().isEmpty()) {
                showAlert("Starting Time is required.");
                return;
            }
            String startingTime = StartingTimeForAddBus.getText();

            // Validate Bus Type
            if (typeComboboxForAddBus.getValue() == null || typeComboboxForAddBus.getValue().isEmpty()) {
                showAlert("Bus Type is required.");
                return;
            }
            String busType = typeComboboxForAddBus.getValue();
            int totalSeat;
            if (busType.equals("NON-AC")) totalSeat = 32;
            else totalSeat = 24;
            // If all fields are valid, proceed with further tasks
            EntityBus bus = new EntityBus(busId, CurrentProviderContainer.getCurrentProvider().getProviderId(), journeyDate, startingTime, departureTime, startingLocation, destination, busType, totalSeat, totalSeat, 0, fair);
            DatabaseHandler handler = new DatabaseHandler();
            handler.InsertIntoBus(bus);
            showSuccessAlert("Successfull", "A new bus added successfully!");


        } catch (NumberFormatException e) {
            showAlert("Invalid Bus ID. Please enter a valid integer.");
        } catch (Exception e) {
            showAlert("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setComboboxes() {
        if (comboinit == false) {
            typeComboboxForAddBus.getItems().addAll("AC", "NON-AC");
            comboinit = true;
        }
    }

    public void showSuccessAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void goToproviderLoginpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/bus_provider_login_page.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}