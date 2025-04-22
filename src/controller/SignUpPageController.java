package controller;

import DatabaseHandling.DatabaseHandler;
import EntityClasses.EntityUser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;

public class SignUpPageController extends Application {
    public Stage stage;
    @FXML
    private ImageView backtoHome;
    @FXML
    private TextField password_visible_text_field;
    @FXML
    private Button joinButton;
    @FXML
    private ImageView passwordVisibilityoff;

    @FXML
    private ImageView passwordVisible;
    @FXML
    private PasswordField password_text_field;

    @FXML
    private TextField address_text_field;

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField email_text_field;

    @FXML
    private CheckBox female_check_box;

    @FXML
    private TextField first_name_text_field;
    @FXML
    private TextField last_name_text_field;

    @FXML
    private CheckBox male_check_box;

    @FXML
    private CheckBox others_check_box;

    @FXML
    private TextField phone_number_text_field;
    String first_name;
    String last_name;
    String email;
    String phone;
    String password;
    String user_name = "12345";
    String address;

    Date date_of_birth;
    String gender;


    @FXML
    void gotoHomePage(MouseEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/views/UserloginPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        double width = stage.getWidth();
        double height = stage.getHeight();
        stage.setScene(new Scene(parent));
        stage.show();

    }


//    public SignUpPageController() {
//
//        first_name_text_field.textProperty().addListener((observable,oldValue, newValue) -> {
//            if (!newValue.matches("[a-zA-Z ]*")) { // Allow only alphabets and spaces
//                first_name_text_field.setText(oldValue != null ? oldValue : ""); // Revert to old value or empty string
//                showAlert("Invalid Input", "First name can only contain alphabetic characters and spaces.");
//            }
//        });
//
//
//    }


    @FXML
    void goToCongratulationPage(ActionEvent event) throws IOException {
        DatabaseHandler handler = new DatabaseHandler();
        String str = InputaddedProperly();
        if (str == null) {
            first_name = first_name_text_field.getText();
            last_name = last_name_text_field.getText();
            email = email_text_field.getText();
            phone = phone_number_text_field.getText();
            password = password_text_field.getText();
            user_name = handler.CreateUser_name(last_name);
            address = address_text_field.getText();
            LocalDate localDate = datePicker.getValue();
            if (localDate != null) date_of_birth = java.sql.Date.valueOf(localDate);
            else {
                LocalDate currentDate = LocalDate.now();

// Convert LocalDate to java.sql.Date
                date_of_birth = java.sql.Date.valueOf(currentDate);
            }


            EntityUser entityUser = new EntityUser(first_name, last_name, email, phone, password, user_name, address, date_of_birth, gender);


            boolean flag = handler.insert(entityUser);

            if (flag) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/confirm_sign_up.fxml"));

                // Load the FXML file
                Parent root = loader.load();

                // Retrieve the controller
                confirm_sign_up_controller controller = loader.getController();

                controller.setText(user_name, "/views/UserloginPage.fxml");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            showAlert("Input missing", "insert " + str);
        }
    }


    @FXML
    void male_check_box_click(ActionEvent event) {
        female_check_box.setSelected(false);
        others_check_box.setSelected(false);
        if (male_check_box.isSelected()) {
            gender = "male";
        } else gender = "";
    }

    @FXML
    void female_check_box_click(ActionEvent event) {
        male_check_box.setSelected(false);
        others_check_box.setSelected(false);
        if (female_check_box.isSelected()) {
            gender = "female";
        } else gender = "";
    }

    @FXML
    void other_check_box_click(ActionEvent event) {
        male_check_box.setSelected(false);
        female_check_box.setSelected(false);
        if (others_check_box.isSelected()) {
            gender = "other";
        } else gender = "";
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {


        // Ensure the TextField and PasswordField are synced
        password_visible_text_field.managedProperty().bind(passwordVisibilityoff.visibleProperty());
        password_visible_text_field.visibleProperty().bind(passwordVisibilityoff.visibleProperty());
        password_text_field.managedProperty().bind(passwordVisible.visibleProperty());
        password_text_field.visibleProperty().bind(passwordVisible.visibleProperty());

        // Sync the text between PasswordField and TextField
        password_visible_text_field.textProperty().bindBidirectional(password_text_field.textProperty());

        passwordVisible.setOnMouseClicked(mouseEvent -> {
            passwordVisibilityoff.setVisible(true);
            passwordVisible.setVisible(false);
        });
        passwordVisibilityoff.setOnMouseClicked(mouseEvent -> {
            passwordVisible.setVisible(true);
            passwordVisibilityoff.setVisible(false);
        });
        // Add listener for validation
        first_name_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*")) {
                first_name_text_field.setText(oldValue != null ? oldValue : "");
                showAlert("Invalid Input", "First name can only contain alphabetic characters and spaces.");
            }
        });
        // First name and last name: Only alphabetic input

        last_name_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z ]*")) {
                last_name_text_field.setText(oldValue);
                showAlert("Invalid Input", "Last name can only contain alphabetic characters and spaces.");
            }
        });

        // Email: Validate email format
        email_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-.]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {

            }
        });

        // Phone number: Only numeric input, with length restriction (10 digits)
        phone_number_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[0-9]*") || newValue.length() > 11) {
                phone_number_text_field.setText(oldValue);
                showAlert("Invalid input", "enter valid characters");
            }
        });

        // Password: Minimum length of 8 characters
        password_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            password_text_field.setPromptText("enter 8 digit password");

            if (newValue.length() > 8) password_text_field.setText(oldValue);
        });

        // Address: Ensure it's not empty (optional check)
        address_text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                // Optionally show a warning message for empty address
            }
        });
//        datePicker.setPromptText("mm/dd/yy");
        datePicker.setValue(LocalDate.now());
//        // Date of birth: Ensure it's not a future date
//        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
//
//            if (newValue != null) {
//                // Get the date as a string (in format yyyy-MM-dd)
//                String dateString = newValue.toString();
//
//                // Regex pattern to match date format (yyyy-MM-dd)
//                String regex = "^\\d{2}/\\d{2}/\\d{4}$"; // MM/DD/YYYY format
//
//
//                // Validate using the regex
//                if (!Pattern.matches(regex, dateString)) {
//                    // Show an error alert if it doesn't match the pattern
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Invalid Date");
//                    alert.setHeaderText("The selected date is in an invalid format.");
//                    alert.setContentText("Date format should be yyyy-MM-dd.");
//                    alert.showAndWait();
//
//                    // Optionally reset to the old value (or current date)
//                    datePicker.setValue(LocalDate.now());
//                }
//            }
//        });

    }

    public String InputaddedProperly() {
        if (first_name_text_field.getText().isEmpty()) return "first name";
        if (last_name_text_field.getText().isEmpty()) return "last name";
        if (phone_number_text_field.getText().isEmpty()) return "phone number";
        if (email_text_field.getText().isEmpty()) return "email";
        if (datePicker.getValue() == null) return "date of birth";
        return null;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
