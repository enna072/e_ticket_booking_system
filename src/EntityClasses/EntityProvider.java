package EntityClasses;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.sql.SQLTimeoutException;

public class EntityProvider {
    private String providerId;
    private String provider_name;
    private String password;
    private String license_no;
    private Date registered_date;

    public EntityProvider(String providerId, String provider_name, String password, String license_no, Date registered_date) {
        this.providerId = providerId;
        this.provider_name = provider_name;
        this.password = password;
        this.license_no = license_no;
        this.registered_date = registered_date;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public Date getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(Date registered_date) {
        this.registered_date = registered_date;
    }

    @Override
    public String toString() {
        return provider_name + " " + providerId;
    }

    public StringProperty getPropertyProvidername() {
        return new SimpleStringProperty(provider_name);
    }

    public StringProperty getpropertyprovidrid() {
        return new SimpleStringProperty(providerId);
    }

    public StringProperty getpropertylicenseno() {
        return new SimpleStringProperty(license_no);
    }

    public ObjectProperty<Date> getpropertydate() {
        return new SimpleObjectProperty<>(registered_date);
    }
}
