package DatabaseHandling;

import Container.CurrentProviderContainer;
import EntityClasses.*;
import controller.User;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

public class DatabaseHandler {
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/e_ticket_for_bus";
    public static final String USER = "root";
    public static final String PASSWORD = "15240";
    private Connection connection;
    public DatabaseHandler() {

        try {
// COMMENT ADDED
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // code section for the user management*************
    public boolean insert(EntityUser user) {
        String first_name = user.getFirst_name().toUpperCase();
        String last_name = user.getLast_name().toUpperCase();
        String email = user.getEmail();
        String phone = user.getPhone();
        String password = user.getPassword();
        String user_name = user.getUser_name();
        String address = user.getAddress();
        Date date_of_birth = user.getDate_of_birth();
        String gender = user.getGender();

        // Corrected SQL query
        String Query = "INSERT INTO user_table(user_name, first_name," +
                " last_name, email, phone," +
                " password, address, gender, date_of_birth) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(Query)) {
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, gender);
            preparedStatement.setDate(9, new java.sql.Date(date_of_birth.getTime()));  // Proper conversion from java.util.Date to java.sql.Date

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    // to retrive all user and return it in the form fo list
    public List<EntityUser> retriveAllUser() {
        String query = "SELECT * FROM user_table";
        List<EntityUser> entityUserList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                if(connection==null) System.out.println("connection is null................");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String firstName = resultSet.getString("first_name").toUpperCase();
                    String lastName = resultSet.getString("last_name").toUpperCase();
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String password = resultSet.getString("password");
                    String address = resultSet.getString("address");
                    Date dateOfBirth = resultSet.getDate("date_of_birth");
                    String gender = resultSet.getString("gender");
                    String user_name = resultSet.getString("user_name");
                    EntityUser user = new EntityUser(firstName, lastName, email, phone, password, user_name, address, dateOfBirth, gender);
                    entityUserList.add(user);


                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entityUserList;
    }

    public EntityUser retriveUser(String username) {
        List<EntityUser> allusers = retriveAllUser();
        for (EntityUser user : allusers) {
            if (user.getUser_name().equals(username)) return user;
        }
        return null;
    }

    // validate the user
    public EntityUser userValidation(String userName, String password) {
        List<EntityUser> allusers = this.retriveAllUser();
        for (EntityUser user : allusers) {
            if (user.getUser_name().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // to check that the username is already available or not
    private boolean isAvailableUser(String userName) {
        List<EntityUser> allusers = this.retriveAllUser();
        for (EntityUser user : allusers) {
            if (user.getUser_name().equals(userName)) return true;
        }
        return false;
    }

    // to create a unique username
    public String CreateUser_name(String last_name) {
        Random rn = new Random();
        String userName;
        do {
            int randomNumber = rn.nextInt(0, 100000);
            userName = last_name + "_" + String.valueOf(randomNumber);
        } while (isAvailableUser(userName));// using the isAvailable function to ficnd there already exist this username or not
        return userName;
    }

    public void updateUser(EntityUser user) {
        String query = "UPDATE user_table SET email=?, phone=?, address=?, date_of_birth=? WHERE user_name=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPhone());
            preparedStatement.setString(3, user.getAddress());

            // Correct way to set the date: no need to convert to String, use setDate directly
            preparedStatement.setDate(4, new java.sql.Date(user.getDate_of_birth().getTime()));

            // Set the user_name parameter for the WHERE clause
            preparedStatement.setString(5, user.getUser_name());

            // Execute the update query
            int x = preparedStatement.executeUpdate();
            if (x > 0) {
                // Show success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Update Successful");
                successAlert.setHeaderText(null); // Optional: Set a header (e.g., "Welcome!")
                successAlert.setContentText("You have successfully updated your information.");

                // Show the alert and wait for user interaction
                successAlert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Log the error if needed, rather than throwing a runtime exception
        }
    }
// code section for the provider section**************
//****************************************************
//****************************************************
//****************************************************
//****************************************************
//****************************************************
//****************************************************
//****************************************************

    public boolean insertintoprovider(EntityProvider provider) {
        String providerName = provider.getProvider_name().toUpperCase();
        String providerId = provider.getProviderId();
        String password = provider.getPassword();
        String license = provider.getLicense_no();
        Date registeredDate = provider.getRegistered_date();
        String query = "Insert into bus_provider(provider_id,provider_name,password,license_no,issue_date) values(?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, providerId);
            preparedStatement.setString(2, providerName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, license);
            preparedStatement.setDate(5, new java.sql.Date(registeredDate.getTime()));
            System.out.println();
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                connection.close();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<EntityProvider> allprovider() {
        List<EntityProvider> allproviderslist = new ArrayList<>();
        String query = "select * from bus_provider";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("provider_name").toUpperCase();
                String id = resultSet.getString("provider_id");
                String pass = resultSet.getString("password");
                String licenseno = resultSet.getString("license_no");
                Date regeisterde = resultSet.getDate("issue_date");

                EntityProvider provider = new EntityProvider(id, name, pass, licenseno, regeisterde);
                allproviderslist.add(provider);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allproviderslist;
    }


    public EntityProvider providerValidation(String provider_id, String password) {
        List<EntityProvider> allproviders = allprovider();
        for (EntityProvider provider : allproviders) {
            if (provider.getProviderId().equals(provider_id) && provider.getPassword().equals(password)) {
                return provider;
            }
        }
        return null;
    }

    //code section for bus manipulation
    //**********************************
    //**********************************
    //**********************************
    //**********************************d
    public List<EntityBus> allBusses() {
        List<EntityBus> allbus = new ArrayList<>();
        String Query = "select * from bus";

        try (PreparedStatement preparedStatement = connection.prepareStatement(Query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int busId = resultSet.getInt("Bus_id");
                String providerId = resultSet.getString("provider_id");
                Date journeyDate = resultSet.getDate("journey_date");
                String startingTime = resultSet.getString("Starting_time");
                String departureTime = resultSet.getString("departure_time");
                String startingLocation = resultSet.getString("starting_location");
                String endLocation = resultSet.getString("end_location");
                String type = resultSet.getString("type");
                int availableSeat = resultSet.getInt("available_seat");
                int bookedSeat = resultSet.getInt("booked_seat");
                int total_seat = resultSet.getInt("total_seat");
                double fair = resultSet.getDouble("fair");
                // Create a new Bus object and add it to the list
                EntityBus bus = new EntityBus(busId, providerId, (java.sql.Date) journeyDate, startingTime, departureTime,
                        startingLocation, endLocation, type, total_seat, availableSeat, bookedSeat, fair);
                allbus.add(bus);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allbus;
    }

    private boolean isAbailable(int Bus_id, String providerId, java.sql.Date date) {
        String query = "select * from bus where bus_id=? and provider_id=? and journey_date=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Bus_id);
            preparedStatement.setString(2, providerId);
            preparedStatement.setDate(3, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean InsertIntoBus(EntityBus B) {
        boolean check = isAbailable(B.getBusId(), B.getProviderId(), B.getJourneyDate());
        if (!check) {
            String query = "Insert into bus(Bus_id,provider_id,journey_date,Starting_time," +
                    "departure_time,starting_location,end_location," +
                    "type,Total_seat,available_seat,booked_seat,fair) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, B.getBusId());
                preparedStatement.setString(2, B.getProviderId());
                preparedStatement.setDate(3, B.getJourneyDate());
                preparedStatement.setString(4, B.getStartingTime());
                preparedStatement.setString(5, B.getDepartureTime());
                preparedStatement.setString(6, B.getStartingLocation().toUpperCase());
                preparedStatement.setString(7, B.getEndLocation().toUpperCase());
                preparedStatement.setString(8, B.getType());
                preparedStatement.setInt(9, B.getTotal_seat());
                preparedStatement.setInt(10, B.getTotal_seat());
                preparedStatement.setInt(11, 0);
                preparedStatement.setDouble(12, B.getFair());
                int row = preparedStatement.executeUpdate();
                if (row > 0) {
                    InsertIntoSeat(B.getTotal_seat(), B, B.getJourneyDate());
                    return true;
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            // Set the title, header, and content text
            alert.setTitle("Duplicate insetion");
            alert.setHeaderText("Threre is already a bus for same rout and date");
            alert.setContentText("Change information");

            // Show the alert box
            alert.showAndWait();
        }
        return false;
    }


    // code for seat manipulation
//***************************
//***************************
//***************************
//***************************
    private void InsertIntoSeat(int NumberOfSeats, EntityBus bus, java.sql.Date journey_date) {
        String query = "Insert into seat(Bus_id, provider_id, journey_date, seat_number, booking_status) values(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bus.getBusId());
            preparedStatement.setString(2, CurrentProviderContainer.getCurrentProvider().getProviderId());
            preparedStatement.setDate(3, journey_date);
            preparedStatement.setString(5, "Available");

            int seatsPerRow = 4;
            char seatChar = 'A';
            if (bus.getType().equals("AC")) {
                seatsPerRow = 3;
                seatChar = 'a';
            }// Seats per row (A1, A2, A3, A4, ...)
            int rowNumber = 1; // Row starts from 1
            // Row letter starts from A
            int seatInRow = 1; // Seat number in each row starts from 1

            for (int i = 1; i <= NumberOfSeats; i++) {
                // Generate seat number like A1, A2, B1, B2, etc.
                String seatNumber = seatChar + Integer.toString(seatInRow);

                preparedStatement.setString(4, seatNumber); // Set the seat number
                preparedStatement.addBatch(); // Add this insert to batch

                // Update seat number in the row
                seatInRow++;

                if (seatInRow > seatsPerRow) {
                    seatInRow = 1; // Reset seat number to 1 for next row
                    seatChar++; // Move to the next row (A -> B -> C -> ...)
                }
            }

            // Execute the batch
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EntitySeat> getAllSeats(EntityBus bus) {
        String query = "Select * from seat where Bus_id=? and provider_id=? and journey_date=?";
        List<EntitySeat> allSeats = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bus.getBusId());
            preparedStatement.setString(2, bus.getProviderId());
            preparedStatement.setDate(3, bus.getJourneyDate());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int Bus_id = resultSet.getInt(1);
                String provider_id = resultSet.getString(2);
                java.sql.Date journey_date = resultSet.getDate(3);
                String seat_number = resultSet.getString(4);
                String booking_status = resultSet.getString(5);
                Integer ticektid = resultSet.getInt(6);
                EntitySeat newSeat = new EntitySeat(Bus_id, provider_id, journey_date, seat_number, booking_status, ticektid);
                allSeats.add(newSeat);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allSeats;
    }

    public int InsertTicket(EntityBus bus, String user_name, HashSet<String> seatNummbers) {
        int bus_id = bus.getBusId();
        int ticketid = 0;
        String providerId = bus.getProviderId();
        java.sql.Date journeydate = bus.getJourneyDate();
        Double totalfare = seatNummbers.size() * bus.getFair();
        java.sql.Date bookingDate = java.sql.Date.valueOf(LocalDate.now());
        String query = "Insert into ticket(user_name,Bus_id,provider_id,journey_date,booking_date,fare) values(?,?,?,?,?,?)";
        int updatedSeat = bus.getTotal_seat() - seatNummbers.size();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user_name);
            preparedStatement.setInt(2, bus_id);
            preparedStatement.setString(3, providerId);
            preparedStatement.setDate(4, journeydate);
            preparedStatement.setDate(5, bookingDate);
            preparedStatement.setDouble(6, totalfare);

            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    ticketid = generatedKeys.getInt(1);
                    UpdateSeats(ticketid, seatNummbers, bus);


                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticketid;
    }

    public void UpdateBus(int selectedSeat, EntityBus bus) {
        String query = "UPDATE bus SET available_seat = ? , booked_seat=? WHERE bus_id = ? and provider_id=? and journey_date=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bus.getAvailableSeat() - selectedSeat);
            preparedStatement.setInt(2, bus.getBookedSeat() + selectedSeat);
            preparedStatement.setInt(3, bus.getBusId());
            preparedStatement.setString(4, bus.getProviderId());
            preparedStatement.setDate(5, bus.getJourneyDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating bus: " + e.getMessage(), e);
        }
    }


    private boolean UpdateSeats(int ticketid, HashSet<String> seatNumbers, EntityBus bus) {
        String query = "UPDATE seat SET booking_status = 'Booked', ticket_id = ? WHERE Bus_id = ? AND provider_id = ? AND journey_date = ? AND seat_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (String seatNumber : seatNumbers) {
                preparedStatement.setInt(1, ticketid);
                preparedStatement.setInt(2, bus.getBusId());
                preparedStatement.setString(3, bus.getProviderId());
                preparedStatement.setDate(4, bus.getJourneyDate());
                preparedStatement.setString(5, seatNumber);
                preparedStatement.addBatch();
            }
            int[] rows = preparedStatement.executeBatch();
            if (rows.length > 0) {

                UpdateBus(seatNumbers.size(), bus);
                return true;
            } // Ensure all seats are updated
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public List<EntityTicket> getAllTickets(String userName) {
        String query = "select * from (select ticket.ticket_id,ticket.user_name,ticket.Bus_id,ticket.provider_id,ticket.journey_date,ticket.booking_date,ticket.fare,bus.starting_location,bus.end_location,starting_time, departure_time,bus.type from ticket natural join bus where ticket.Bus_id=bus.Bus_id and ticket.provider_id=bus.provider_id and ticket.journey_date=bus.journey_date) as temp  where temp.user_name=?";
        List<EntityTicket> alltickets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ticket_id = resultSet.getInt(1);
                String user_name = resultSet.getString(2);
                int bus_id = resultSet.getInt(3);
                String provider_id = resultSet.getString(4);
                Date journey_date = resultSet.getDate(5);
                Date booking_date = resultSet.getDate(6);
                double fare = resultSet.getDouble(7);
                String startingLocation = resultSet.getString(8);
                String endLocation = resultSet.getString(9);
                String stime = resultSet.getString(10);
                String etime = resultSet.getString(11);
                String type = resultSet.getString(12);


                EntityTicket entityTicket = new EntityTicket(ticket_id, user_name, bus_id, provider_id, journey_date, booking_date, fare, startingLocation, endLocation, stime, etime, type);
                alltickets.add(entityTicket);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alltickets;
    }

    public List<EntityTicket> getTikcetbyBus(EntityBus bus) {
        String query = "SELECT * FROM ticket where Bus_id=? and provider_id=? and journey_date=?";
        List<EntityTicket> alltickets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bus.getBusId());
            preparedStatement.setString(2, bus.getProviderId());
            preparedStatement.setDate(3, bus.getJourneyDate());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ticket_id = resultSet.getInt(1);
                String user_name = resultSet.getString(2);
                int bus_id = resultSet.getInt(3);

                String provider_id = resultSet.getString(4);
                Date journey_date = resultSet.getDate(5);
                Date booking_date = resultSet.getDate(6);
                double fare = resultSet.getDouble(7);
                String startingLocation = bus.getStartingLocation();
                String endingLocation = bus.getEndLocation();
                String stime = bus.getStartingTime();
                String etime = bus.getDepartureTime();
                String type = bus.getType();
                String seatNumbers = getSeatNumber(ticket_id);
                EntityUser user = retriveUser(user_name);
                EntityTicket ticket = new EntityTicket(ticket_id, user_name, bus_id, provider_id, journey_date, booking_date, fare, startingLocation, endingLocation, stime, etime, type, seatNumbers, user);
                alltickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alltickets;
    }


    public String getSeatNumber(int ticketId) {
        String query = "select seat_number from seat where ticket_id=?";
        ArrayList<String> allseats = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, ticketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String seatnumber = resultSet.getString(1);
                allseats.add(seatnumber);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allseats.toString();
    }
}
