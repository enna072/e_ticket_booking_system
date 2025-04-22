package EntityClasses;

import javafx.beans.property.*;

import java.sql.Date;

public class EntityTicket {

    private int ticket_id;
    private String user_name;
    private int bus_id;
    private String provider_id;
    private Date journey_date;
    private Date booking_date;
    private double fare;
    private String startingLocation;
    private String endingLocation;
    private String stime;
    private String etime;
    private String type;
    private String seatNumbers;

    private EntityUser user;

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    // Constructor
    public EntityTicket(int ticket_id, String user_name, int bus_id, String provider_id, Date journey_date, Date booking_date, double fare, String startingLocation, String endingLocation, String stime, String etime, String type) {
        this.ticket_id = ticket_id;
        this.user_name = user_name;
        this.bus_id = bus_id;
        this.provider_id = provider_id;
        this.journey_date = journey_date;
        this.booking_date = booking_date;
        this.fare = fare;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.stime = stime;
        this.etime = etime;
        this.type = type;
    }

    public EntityTicket(int ticket_id, String user_name, int bus_id, String provider_id, Date journey_date, Date booking_date, double fare, String startingLocation, String endingLocation, String stime, String etime, String type, String seatNumbers) {
        this.ticket_id = ticket_id;
        this.user_name = user_name;
        this.bus_id = bus_id;
        this.provider_id = provider_id;
        this.journey_date = journey_date;
        this.booking_date = booking_date;
        this.fare = fare;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.stime = stime;
        this.etime = etime;
        this.type = type;
        this.seatNumbers = seatNumbers;
    }

    public EntityTicket(int ticket_id, String user_name, int bus_id, String provider_id, Date journey_date, Date booking_date, double fare, String startingLocation, String endingLocation, String stime, String etime, String type, String seatNumbers, EntityUser user) {
        this.ticket_id = ticket_id;
        this.user_name = user_name;
        this.bus_id = bus_id;
        this.provider_id = provider_id;
        this.journey_date = journey_date;
        this.booking_date = booking_date;
        this.fare = fare;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.stime = stime;
        this.etime = etime;
        this.type = type;
        this.seatNumbers = seatNumbers;
        this.user = user;
    }

    // Getters and Setters
    public int getTicket_id() {
        return ticket_id;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public String getEndingLocation() {
        return endingLocation;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public Date getJourney_date() {
        return journey_date;
    }

    public void setJourney_date(Date journey_date) {
        this.journey_date = journey_date;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getStime() {
        return stime;
    }

    public String getEtime() {
        return etime;
    }

    // toString method
    @Override
    public String toString() {
        return "EntityTicket{" +
                "ticket_id=" + ticket_id +
                ", user_name='" + user_name + '\'' +
                ", bus_id=" + bus_id +
                ", provider_id='" + provider_id + '\'' +
                ", journey_date=" + journey_date +
                ", booking_date=" + booking_date +
                ", fare=" + fare +
                ",starting location" + startingLocation +
                ",endign location" + endingLocation +
                ",starting time" + stime +
                ",ending time" + etime +
                '}';
    }

    // Getters and Setters
    public IntegerProperty getPropertyTicket_id() {
        return new SimpleIntegerProperty(ticket_id);
    }


    public StringProperty getPropertyUser_name() {
        return new SimpleStringProperty(user_name);
    }


    public IntegerProperty getPropertyBus_id() {
        return new SimpleIntegerProperty(bus_id);
    }


    public StringProperty getPropertyProvider_id() {
        return new SimpleStringProperty(provider_id);
    }


    public ObjectProperty<Date> getPropertyJourney_date() {
        return new SimpleObjectProperty<>(journey_date);
    }


    public ObjectProperty<java.sql.Date> getPropertyBooking_date() {
        return new SimpleObjectProperty<>(booking_date);
    }


    public DoubleProperty getPropertyFare() {
        return new SimpleDoubleProperty(fare);
    }

    public StringProperty getPropertyStartingLocation() {
        return new SimpleStringProperty(startingLocation);
    }

    public StringProperty getPropertyEndingLocation() {
        return new SimpleStringProperty(endingLocation);
    }

    public StringProperty getPropertyStime() {
        return new SimpleStringProperty(stime);
    }

    public StringProperty getPropertyEtime() {
        return new SimpleStringProperty(etime);
    }

    public StringProperty getBusname() {
        return new SimpleStringProperty(provider_id.split("_")[0]);
    }

    public String getType() {
        return type;
    }

    public StringProperty getPropertyType() {
        return new SimpleStringProperty(type);
    }

    public StringProperty getPropertySeatnumbers() {
        return new SimpleStringProperty(seatNumbers);
    }

    public StringProperty getTicketHolderName() {
        return new SimpleStringProperty(user.getFirst_name() + " " + user.getLast_name());
    }

    public StringProperty getholderEmail() {
        return new SimpleStringProperty(user.getEmail());
    }

    public StringProperty getholderPhone() {
        return new SimpleStringProperty(user.getPhone());
    }

}

