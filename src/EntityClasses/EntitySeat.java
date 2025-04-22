package EntityClasses;

import javafx.beans.property.*;

import java.sql.Date;

public class EntitySeat {
    private int Bus_id;
    private String provider_id;
    private Date journey_date;
    private String seat_number;
    private String booking_status;
    private Integer TicketId;

    public EntitySeat(int bus_id, String provider_id, Date journey_date, String seat_number, String booking_status, Integer TIcketId) {
        Bus_id = bus_id;
        this.provider_id = provider_id;
        this.journey_date = journey_date;
        this.seat_number = seat_number;
        this.booking_status = booking_status;
        this.TicketId = TIcketId;
    }

    public EntitySeat(int bus_id, String provider_id, Date journey_date, String seat_number, String booking_status) {
        this(bus_id, provider_id, journey_date, seat_number, booking_status, null);
    }

    public int getBus_id() {
        return Bus_id;
    }

    public void setBus_id(int bus_id) {
        Bus_id = bus_id;
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

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }
    public IntegerProperty getBus_idProperty() {
        return new SimpleIntegerProperty(Bus_id);
    }

    public StringProperty getProvider_idProperty() {
        return new SimpleStringProperty(provider_id);
    }

    public ObjectProperty<Date> getJourney_dateProperty() {
        return new SimpleObjectProperty<>(journey_date);
    }

    public StringProperty getSeat_numberProperty() {
        return new SimpleStringProperty(seat_number);
    }

    public StringProperty getBooking_statusProperty() {
        return new SimpleStringProperty(booking_status);
    }

    public IntegerProperty getTicketIdProperty() {
        return new SimpleIntegerProperty(TicketId != null ? TicketId : 0);
    }
}
