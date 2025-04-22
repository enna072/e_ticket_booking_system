package controller;

import Container.CurrentUserContainer;
import EntityClasses.EntityTicket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Container.temporarydataContainer;
import javafx.stage.Stage;

import java.io.IOException;

public class TicketView {
    @FXML
    private Label LabelBookedSeeat;

    @FXML
    private Label TypeLabel;
    @FXML
    private Label LabelCoachHolder;

    @FXML
    private Label LabelHoldsDestination;

    @FXML
    private Label LabelHoldsStartingLocation;

    @FXML
    private Label LabelProviderHoldeer;

    @FXML
    private Label LabelProviderHolder;

    @FXML
    private Label LabelStartingTime;

    @FXML
    private Label LabeljourneyDateHolder;

    @FXML
    private Label TotalFareHolder;

    @FXML
    private ImageView gotoDashBoard;

    @FXML
    private Label labelHOldsThenemoftheticketHolder;

    @FXML
    void goTouserDahsboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/UserDashBoard.fxml"));
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        st.setScene(new Scene(root));
        st.show();
    }

    @FXML
    private void initialize() {
        EntityTicket ticket = temporarydataContainer.getCurrentTicket();
        LabelBookedSeeat.setText(ticket.getSeatNumbers());
        LabelCoachHolder.setText(String.valueOf(ticket.getBus_id()));
        LabelHoldsDestination.setText(ticket.getEndingLocation());
        LabelHoldsStartingLocation.setText(ticket.getStartingLocation());
        LabelProviderHoldeer.setText(ticket.getProvider_id().split("_")[0]);
        LabelStartingTime.setText(ticket.getStime());
        LabeljourneyDateHolder.setText(String.valueOf(ticket.getJourney_date()));
        TotalFareHolder.setText(String.valueOf(ticket.getFare()));
        TypeLabel.setText(ticket.getType());
        labelHOldsThenemoftheticketHolder.setText(CurrentUserContainer.getCurrentUser().getFirst_name() + " " + CurrentUserContainer.getCurrentUser().getLast_name());

    }


}
