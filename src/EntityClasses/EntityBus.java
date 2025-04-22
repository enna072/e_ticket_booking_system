package EntityClasses;

import javafx.beans.property.*;

import java.sql.Date;

public class EntityBus {
    private int busId;
    private String providerId;
    private Date journeyDate;
    private String startingTime;
    private String departureTime;
    private String startingLocation;
    private String endLocation;
    private String type;
    private double fair;
    private int total_seat;
    private int availableSeat;
    private int bookedSeat;

    // Constructor
    public EntityBus(int busId, String providerId, Date journeyDate, String startingTime, String departureTime,
                     String startingLocation, String endLocation, String type, int total_seat, int availableSeat, int bookedSeat, double fair) {
        this.busId = busId;
        this.providerId = providerId;
        this.journeyDate = journeyDate;
        this.startingTime = startingTime;
        this.departureTime = departureTime;
        this.startingLocation = startingLocation;
        this.endLocation = endLocation;
        this.type = type;
        this.total_seat = total_seat;
        this.availableSeat = availableSeat;
        this.bookedSeat = bookedSeat;
        this.fair = fair;
    }

    // Getters and Setters
    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public int getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(int bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public int getTotal_seat() {
        return total_seat;
    }

    public void setTotal_seat(int total_seat) {
        this.total_seat = total_seat;
    }

    public double getFair() {
        return fair;
    }

    public void setFair(double fair) {
        this.fair = fair;
    }

    // Method to display bus information (optional)
    @Override
    public String toString() {
        return "Bus ID: " + busId + "\n" +
                "Provider ID: " + providerId + "\n" +
                "Journey Date: " + journeyDate + "\n" +
                "Starting Time: " + startingTime + "\n" +
                "Departure Time: " + departureTime + "\n" +
                "Starting Location: " + startingLocation + "\n" +
                "End Location: " + endLocation + "\n" +
                "Type: " + type + "\n" +
                "total seat:" + total_seat + "\n" +
                "Available Seats: " + availableSeat + "\n" +
                "Booked Seats: " + bookedSeat + "\n" +
                "fair" + fair;
    }


    // Getters and Setters
    public IntegerProperty getIntegerpropertyBusId() {
        return new SimpleIntegerProperty(busId);
    }


    public StringProperty getStringPropettyProviderId() {
        return new SimpleStringProperty(providerId);
    }


    public ObjectProperty<Date> getPropertyJourneyDate() {
        return new SimpleObjectProperty<>(journeyDate);
    }


    public StringProperty getStartingPropertyTime() {
        return new SimpleStringProperty(startingTime);
    }


    public StringProperty getStringPropertyDepartureTime() {
        return new SimpleStringProperty(departureTime);
    }


    public StringProperty getPropertyStartingLocation() {
        return new SimpleStringProperty(startingLocation);
    }


    public StringProperty getpropertyEndLocation() {
        return new SimpleStringProperty(endLocation);
    }


    public StringProperty getPropertyType() {
        return new SimpleStringProperty(type);
    }


    public IntegerProperty integerPropertyAvailableSeat() {
        return new SimpleIntegerProperty(availableSeat);
    }


    public IntegerProperty integerPropertyBookedSeat() {
        return new SimpleIntegerProperty(bookedSeat);
    }


    public IntegerProperty integerPropertyTotal_seat() {
        return new SimpleIntegerProperty(total_seat);
    }

    public DoubleProperty getPropertyFair() {
        return new SimpleDoubleProperty(fair);
    }

    public StringProperty getProvider() {
        return new SimpleStringProperty(providerId.split("_")[0]);
    }

    public StringProperty getRout() {
        return new SimpleStringProperty(startingLocation + " to " + endLocation);
    }
}
