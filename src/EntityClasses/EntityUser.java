package EntityClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class EntityUser {
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String password;
    private String User_name;
    private String address;
    private Date date_of_birth;
    private String gender;

    public EntityUser(String first_name, String last_name, String email, String phone, String password, String user_name, String address, Date date_of_birth, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        User_name = user_name;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
    }

    public EntityUser(String gender, Date date_of_birth, String address, String phone, String email, String last_name, String first_name) {
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public StringProperty getPropertyFullname() {
        return new SimpleStringProperty(getFirst_name() + " " + getLast_name());
    }

    public StringProperty getpropertyUserId() {
        return new SimpleStringProperty(User_name);
    }

    public StringProperty getpropertyPhoneNumber() {
        return new SimpleStringProperty(phone);
    }

    public StringProperty getpropertyEmail() {
        return new SimpleStringProperty(email);
    }

    public StringProperty getproertyAddress() {
        return new SimpleStringProperty(address);
    }
}
