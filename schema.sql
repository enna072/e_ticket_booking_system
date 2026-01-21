create database Bus_database_Schema;
use bus_database_Schema;



CREATE TABLE user_table (
    user_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(255),
    phone VARCHAR(15),
    password VARCHAR(255),
    address TEXT,
    date_of_birth DATE,
    gender VARCHAR(10),
    PRIMARY KEY (user_name)
);


CREATE TABLE bus (
    Bus_id INT NOT NULL,
    provider_id VARCHAR(50) NOT NULL,
    journey_date DATE NOT NULL,
    Starting_time VARCHAR(10),
    departure_time VARCHAR(10),
    starting_location VARCHAR(50),
    end_location VARCHAR(50),
    type VARCHAR(20),
    Total_seat INT,
    available_seat INT,
    booked_seat INT,
    fair DOUBLE,
    PRIMARY KEY (Bus_id, provider_id, journey_date)
);

CREATE TABLE bus_provider (
    provider_id VARCHAR(10) NOT NULL,
    provider_name VARCHAR(50),
    password VARCHAR(8),
    license_no VARCHAR(100),
    issue_date DATE,
    PRIMARY KEY (provider_id)
);


CREATE TABLE seat (
    Bus_id INT NOT NULL,
    provider_id VARCHAR(50) NOT NULL,
    journey_date DATE NOT NULL,
    seat_number VARCHAR(3) NOT NULL,
    booking_status VARCHAR(20),
    ticket_id INT,
    PRIMARY KEY (Bus_id, provider_id, journey_date, seat_number)
);
select * from bus_provider;

CREATE TABLE ticket (
    ticket_id INT AUTO_INCREMENT,
    user_name VARCHAR(50),
    Bus_id INT,
    provider_id VARCHAR(50),
    journey_date DATE,
    booking_date DATE,
    fare DOUBLE,
    PRIMARY KEY (ticket_id)
);




